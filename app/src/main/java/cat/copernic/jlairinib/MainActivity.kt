package cat.copernic.jlairinib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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