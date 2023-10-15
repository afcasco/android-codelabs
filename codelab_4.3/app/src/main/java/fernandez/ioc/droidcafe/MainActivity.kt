package fernandez.ioc.droidcafe

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.core.view.MenuCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fernandez.ioc.droidcafe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "ioc.fernandez.android.droidcafe.MESSAGE"
    }

    private lateinit var mOrderMessage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val fab = findViewById<FloatingActionButton>(R.id.fab)


        setSupportActionBar(toolbar)
        toolbar.showOverflowMenu()

        fab.setOnClickListener {view ->
            if(this::mOrderMessage.isInitialized){
                val intent = Intent(this,OrderActivity::class.java)
                intent.putExtra(EXTRA_MESSAGE,mOrderMessage)
                startActivity(intent)
            } else {
                Snackbar.make(view, getString(R.string.empty_order_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }

        }



        findViewById<ImageView>(R.id.donut).setOnClickListener {
            showDonutOrder()
        }

        findViewById<ImageView>(R.id.ice_cream).setOnClickListener {
            showIceCreamOrder()
        }

        findViewById<ImageView>(R.id.froyo).setOnClickListener {
            showFroyoOrder()
        }

    }

    private fun showDonutOrder() {
        mOrderMessage = getString(R.string.donut_order_message)
        displayToast(mOrderMessage)
    }

    private fun showIceCreamOrder(){
        mOrderMessage = getString(R.string.ice_cream_order_message)
        displayToast(mOrderMessage)
    }


    private fun showFroyoOrder(){
        mOrderMessage = getString(R.string.froyo_order_message)
        displayToast(mOrderMessage)
    }

    private fun displayToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_order -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


}