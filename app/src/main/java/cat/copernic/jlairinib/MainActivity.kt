package cat.copernic.jlairinib

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager
import cat.copernic.jlairinib.ui.main.MainFragmentDirections
import cat.copernic.jlairinib.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.fragmentContainerView, SettingsFragment())
//                .commit()
//        }
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.setting_menu, menu)
//
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId){
//            R.id.action_settings -> {
//                val action =  MainFragmentDirections.actionMainFragmentToSettingsFragment()
//                val navHost = Navigation.findNavController(this, R.id.fragmentContainerView)
//                Log.d("test", "fragment:" + navHost)
//               navHost.navigate(action)
//            }
//        }
//
//        return false
//    }


    override fun onResume() {
        super.onResume()
        val supportActionBar = (this as AppCompatActivity).supportActionBar

        supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar = (this as AppCompatActivity).supportActionBar

        supportActionBar?.show()
    }
}