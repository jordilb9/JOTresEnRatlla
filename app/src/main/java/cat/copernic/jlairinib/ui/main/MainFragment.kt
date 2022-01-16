package cat.copernic.jlairinib.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import cat.copernic.jlairinib.R
import cat.copernic.jlairinib.databinding.MainFragmentBinding
import android.content.SharedPreferences
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceManager


class MainFragment : Fragment() {

    private lateinit var  binding: MainFragmentBinding

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val level = sharedPreferences.getString("level", "")

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        binding.buttonPlay.setOnClickListener {

            val action =  MainFragmentDirections.actionMainFragmentToGameFragment(level.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }

        binding.buttonSetting.setOnClickListener {
            val action =  MainFragmentDirections.actionMainFragmentToSettingsFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

//        binding.buttonLow.setOnClickListener {
//            level = "low"
//            val action =  MainFragmentDirections.actionMainFragmentToGameFragment(level)
//            NavHostFragment.findNavController(this).navigate(action)
//        }
//        binding.buttonMedium.setOnClickListener {
//            level = "medium"
//            val action =  MainFragmentDirections.actionMainFragmentToGameFragment(level)
//            NavHostFragment.findNavController(this).navigate(action)
//        }
//        binding.buttonHigh.setOnClickListener {
//            level = "high"
//            val action =  MainFragmentDirections.actionMainFragmentToGameFragment(level)
//            NavHostFragment.findNavController(this).navigate(action)
//        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}