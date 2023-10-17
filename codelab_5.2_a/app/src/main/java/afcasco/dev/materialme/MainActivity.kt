package afcasco.dev.materialme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSportsData: ArrayList<Sport>
    private lateinit var mAdapter: SportsAdapter
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mSportsData = ArrayList()
        mAdapter = SportsAdapter(this, mSportsData)
        fab = findViewById(R.id.floating_button_main)

        mRecyclerView.adapter = mAdapter
        initializeData()

        fab.setOnClickListener {
            initializeData()
        }

        // Create and attach ItemTouchHelper
        ItemTouchHelper(object : SimpleCallback(
            ItemTouchHelper.RIGHT or
                    ItemTouchHelper.LEFT or
                    ItemTouchHelper.UP or
                    ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(mSportsData,from,to)
                mAdapter.notifyItemMoved(from,to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mSportsData.removeAt(viewHolder.adapterPosition)
                mAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }

        }).attachToRecyclerView(mRecyclerView)


    }



    private fun initializeData() {
        val sportsList = resources.getStringArray(R.array.sports_titles)
        val sportsInfo = resources.getStringArray(R.array.sports_info)
        mSportsData.clear()

        val sportsImageResources = resources.obtainTypedArray(R.array.sports_images)

        for (i in sportsList.indices) {
            mSportsData.add(
                Sport(sportsList[i], sportsInfo[i], sportsImageResources.getResourceId(i, 0))
            )
            println(sportsList[i])
        }
        sportsImageResources.recycle()
        mAdapter.notifyDataSetChanged()
    }
}