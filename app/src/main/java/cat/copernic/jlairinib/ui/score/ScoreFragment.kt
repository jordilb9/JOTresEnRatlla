package cat.copernic.jlairinib.ui.score

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import cat.copernic.jlairinib.R
import cat.copernic.jlairinib.databinding.GameFragmentBinding
import cat.copernic.jlairinib.databinding.ScoreFragmentBinding
import cat.copernic.jlairinib.ui.game.GameFragmentArgs
import cat.copernic.jlairinib.ui.game.GameFragmentDirections
import cat.copernic.jlairinib.ui.game.GameViewModel
import kotlinx.android.synthetic.main.score_fragment.*

class ScoreFragment : Fragment() {

    val args: ScoreFragmentArgs by navArgs()
    private lateinit var  binding: ScoreFragmentBinding

    companion object {
        fun newInstance() = ScoreFragment()
    }

    private lateinit var viewModel: ScoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel

        viewModel.setScore(args.score)

        if(args.score == "HAS GUANYAT"){
            binding.textView2.setTextColor(Color.parseColor("#47f535"))
        }else if(args.score == "HAS PERDUT"){
            binding.textView2.setTextColor(Color.parseColor("#e81010"))
        }else{
            binding.textView2.setTextColor(Color.parseColor("#f3e91d"))
        }

        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.reload.observe(viewLifecycleOwner,
            { hasFinished -> if (hasFinished) navigationMain()})


        return binding.root
    }

    fun navigationMain(){
        val action =  ScoreFragmentDirections.actionScoreFragmentToMainFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}