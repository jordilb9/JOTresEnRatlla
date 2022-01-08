package cat.copernic.jlairinib.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private  var _score = MutableLiveData<String>("")
    val score: LiveData<String> get() = _score
    fun setScore(sc: String){
        _score.value = sc
    }

    private val _reload = MutableLiveData<Boolean>(false)
    val reload: LiveData<Boolean> get() = _reload

    fun reloadGame(){
        _reload.value = true
    }
}