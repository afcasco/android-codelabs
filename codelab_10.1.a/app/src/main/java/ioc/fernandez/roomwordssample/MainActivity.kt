package ioc.fernandez.roomwordssample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ioc.fernandez.roomwordssample.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mWordList: List<Word>
    private lateinit var mWordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        mWordList = ArrayList()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val adapter = WordListAdapter(this, mWordList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mWordViewModel = ViewModelProvider(this)[WordViewModel::class.java]
        mWordViewModel.getAllWords().observe(this) {
            adapter.setWords(it)


        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        mWordViewModel.deleteAll()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val word = data?.getStringExtra(NewWordActivity.EXTRA_REPLY).toString()
            word.let {
                lifecycleScope.launch {
                    mWordViewModel.insert(Word(word))
                }
            }
        }
    }
}