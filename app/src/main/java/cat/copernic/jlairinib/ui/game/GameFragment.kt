package cat.copernic.jlairinib.ui.game

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import cat.copernic.jlairinib.R
import cat.copernic.jlairinib.databinding.GameFragmentBinding
import cat.copernic.jlairinib.databinding.MainFragmentBinding
import cat.copernic.jlairinib.ui.main.MainFragmentDirections
import kotlin.math.abs

class GameFragment : Fragment() {
    val args: GameFragmentArgs by navArgs()
    private lateinit var  binding: GameFragmentBinding
    private lateinit var viewModel: GameViewModel

    var clicB = arrayListOf<Int>()
    var clicBView = arrayListOf<Button>()
    var tauler = IntArray(9) { 0 }
//    var torn = 1
//    var estat = 0
    var combG = IntArray(3){-1}

//    var contador = 0


    companion object {
        fun newInstance() = GameFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        clicB.addAll(listOf(binding.button0.id, binding.button1.id, binding.button2.id,
            binding.button3.id, binding.button4.id, binding.button5.id,
            binding.button6.id, binding.button7.id, binding.button8.id))

        clicBView.addAll(listOf(binding.button0, binding.button1, binding.button2,
            binding.button3, binding.button4, binding.button5,
            binding.button6, binding.button7, binding.button8))


        if(viewModel.clicB.value == null){
            viewModel.setClicB(clicB)
            viewModel.setClicBView(clicBView)
            viewModel.setTauler(tauler)
            viewModel.setCombG(combG)
            viewModel.setLevel(args.level)
        }

        Log.d("Atributo", "Nivel del juego: " + args.level )

        binding.gameViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.scoreStatus.observe(viewLifecycleOwner,
            { hasFinished -> if (hasFinished) navigationScore()})

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        for(posTauler in viewModel.tauler.value!!.indices){
            if(viewModel.tauler.value!![posTauler] == 1){
                val b: Button = viewModel.clicBView.value!![posTauler]
                b.setBackgroundResource(R.drawable.ic_baseline_close_24)
            }else if(viewModel.tauler.value!![posTauler] == -1){
                val b: Button = viewModel.clicBView.value!![posTauler]
                b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24)
            }
        }
    }

    fun navigationScore(){
        val score = viewModel.score.value!!
        val action =  GameFragmentDirections.actionGameFragmentToScoreFragment(score)
        NavHostFragment.findNavController(this).navigate(action)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    }

}