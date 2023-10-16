package afcasco.dev.tabexperiment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.lang.IllegalArgumentException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = PagerAdapter(supportFragmentManager, lifecycle, 3)
        viewPager.adapter = adapter

        // Using TabLayoutMediator instead of tabLayout.addTab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                1 -> getString(R.string.tab_label1)
                2 -> getString(R.string.tab_label2)
                3 -> getString(R.string.tab_label3)
                else -> {"Error"}
            }

        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewPager.currentItem = tab!!.position
                Log.d("ActivityMain", "----------TEST----------------")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }


}