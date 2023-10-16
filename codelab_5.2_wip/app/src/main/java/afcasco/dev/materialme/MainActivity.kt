package afcasco.dev.materialme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSportsData: ArrayList<Sport>
    private lateinit var mAdapter: SportsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mSportsData = ArrayList()
        mAdapter = SportsAdapter(this, mSportsData)

        mRecyclerView.adapter = mAdapter
        initializeData()
    }

    private fun initializeData() {
        val sportsList = resources.getStringArray(R.array.sports_titles)
        val sportsInfo = resources.getStringArray(R.array.sports_info)
        mSportsData.clear()

        for (i in sportsList.indices) {
            mSportsData.add(Sport(sportsList[i], sportsInfo[i]))
            println(sportsList[i])
        }

        mAdapter.notifyDataSetChanged()
    }
}