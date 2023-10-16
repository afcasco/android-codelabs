package afcasco.dev.reciclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import afcasco.dev.reciclerview.databinding.ActivityMainBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.LinkedList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mWordList: LinkedList<String> = LinkedList()
    private lateinit var mAdapter: WordListAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        mAdapter = WordListAdapter(mWordList)

        binding.fab.setOnClickListener {
            val wordListSize = mWordList.size
            mWordList.addLast("+ Word $wordListSize")
            mRecyclerView.adapter?.notifyDataSetChanged()
            mRecyclerView.smoothScrollToPosition(wordListSize)
        }

        for (i in 0..20) {
            mWordList.addLast("Word $i")
        }


        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Coding challenge: Reset the wordlist when reset button clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_reset -> {
                mWordList.clear()
                for (i in 0..20) {
                    mWordList.addLast("Word $i")
                }

                mAdapter.notifyDataSetChanged()
            }
        }

        return super.onOptionsItemSelected(item)

    }
}