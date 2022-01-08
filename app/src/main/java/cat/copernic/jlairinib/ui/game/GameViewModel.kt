package cat.copernic.jlairinib.ui.game

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import cat.copernic.jlairinib.R
import cat.copernic.jlairinib.ui.main.MainFragmentDirections
import kotlin.math.abs

class GameViewModel : ViewModel() {

 //   var clicB = arrayListOf<Int>()
 //   var clicBView = arrayListOf<Button>()
//    var tauler = IntArray(9) { 0 }
//    var torn = 1
//    var estat = 0
//    var combG = IntArray(3){-1}
//    var contador = 0



    private var _clicB = MutableLiveData<List<Int>>()
    val clicB: LiveData<List<Int>> get() = _clicB
    fun setClicB (clic : List<Int>){
        _clicB.value = clic
    }

    private var _clicBView = MutableLiveData<List<Button>>()
    val clicBView: LiveData<List<Button>> get() = _clicBView
    fun setClicBView (clicV : List<Button>){
        _clicBView.value = clicV
    }

    private var _tauler = MutableLiveData<IntArray>()
    val tauler: LiveData<IntArray> get() = _tauler
    fun setTauler (taulerI: IntArray){
        _tauler.value = taulerI
    }

    private var _torn = MutableLiveData<Int>(1)
    val torn: LiveData<Int> get() = _torn

    private var _estat = MutableLiveData<Int>(0)
    val estat: LiveData<Int> get() = _estat

    private var _combG = MutableLiveData<IntArray>()
    val combG: LiveData<IntArray> get() = _combG
    fun setCombG(combGI: IntArray){
        _combG.value = combGI
    }

    private var _contador = MutableLiveData<Int>(0)
    val contador: LiveData<Int> get() = _contador

    private var _level = MutableLiveData<String>("")
    val level: LiveData<String> get() = _level
    fun setLevel(lvl: String){
        _level.value = lvl
    }

    private  var _score = MutableLiveData<String>("")
    val score: LiveData<String> get() = _score

    private val _scoreStatus = MutableLiveData<Boolean>(false)
    val scoreStatus: LiveData<Boolean> get() = _scoreStatus


//    fun reloadBackgound(button: View){
////        for(posTauler in _tauler.value!!.indices){
////            if(_tauler.value!![posTauler] == 1){
////                val b: Button = _clicBView.value!![posTauler]
////                b.setBackgroundResource(R.drawable.ic_baseline_close_24)
////            }else if(_tauler.value!![posTauler] == -1){
////                val b: Button = _clicBView.value!![posTauler]
////                b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24)
////            }
////        }
//        if(_tauler.value!![button.id] == 1){
//            val b: Button = _clicBView.value!![button.id]
//            b.setBackgroundResource(R.drawable.ic_baseline_close_24)
//        }else if (_tauler.value!![button.id] == -1){
//            val b: Button = _clicBView.value!![button.id]
//            b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24)
//        }
//
//    }


    fun posarFitxa(posicio: View) {

        if(_estat.value == 0){
            var numB = _clicB.value!!.indexOf(posicio.id)
            if(_tauler.value!![numB] == 0){
                _torn.value = 1
                posicio.setBackgroundResource(R.drawable.ic_baseline_close_24)
                _tauler.value!![numB] = 1
                _contador.value = _contador.value!! + 1

                _estat.value = comprovarGuanyador()
                finalPartida()

                var cont = 0
                if (_estat.value == 0){
                    for (valor in _tauler.value!!){
                        if(valor == 0) cont =+1
                    }
                    if(cont != 0){
                        var posicioM = comprovarTauler()
                        _torn.value = -1
                        posarFitxaM(posicioM)
                        _estat.value = comprovarGuanyador()
                        finalPartida()
                    }
                }
            }
        }
    }

    fun posarFitxaM(pos: Int){

        var posicioM = pos

        if (_level.value == "high"){
            if(_contador.value == 1){
                if(_tauler.value!![1] == 1 || _tauler.value!![3] == 1 || _tauler.value!![4] == 1 || _tauler.value!![5] == 1 || _tauler.value!![7] == 1){
                    val num = listOf(0, 2, 6, 8)
                    posicioM = num.random()
                }else if(_tauler.value!![4] == 0){
                    posicioM = 4
                }
            }
        }

        while(_tauler.value!![posicioM] !=0){
            posicioM = (0..8).random()
        }

        val b: Button = _clicBView.value!![posicioM]
        b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24)
        _tauler.value!![posicioM] = -1
        _contador.value = _contador.value!! + 1
    }

    fun finalPartida(){

        if (_estat.value == 1 || _estat.value == -1){
            if(_torn.value == 1){

                _score.value = "HAS GUANYAT"

                for (valor in _combG.value!!){
                    val b: Button = _clicBView.value!![valor]
                    b.setBackgroundResource(R.drawable.ic_baseline_close_24_green)
                }

                _scoreStatus.value = true

            }else if(_torn.value == -1){

                _score.value = "HAS PERDUT"

                for (valor in _combG.value!!){
                    val b: Button = _clicBView.value!![valor]
                    b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24_green)
                }

                _scoreStatus.value = true
            }
        }else if(_estat.value == 2){
            _score.value = "HAS EMPATAT"
            _scoreStatus.value = true
        }


    }

    fun comprovarGuanyador(): Int{

        var nouEstat = 0

        if(abs(_tauler.value!![0] + _tauler.value!![1] + _tauler.value!![2]) == 3 ){
            _combG.value = intArrayOf(0,1,2)
            nouEstat = 1*_torn.value!!
        }else if (abs(_tauler.value!![3] + _tauler.value!![4] + _tauler.value!![5]) == 3){
            _combG.value = intArrayOf(3,4,5)
            nouEstat = 1*_torn.value!!
        }else if(abs(_tauler.value!![6] + _tauler.value!![7] + _tauler.value!![8]) == 3){
            _combG.value = intArrayOf(6,7,8)
            nouEstat = 1*_torn.value!!
        }else if(abs(_tauler.value!![0] + _tauler.value!![3] + _tauler.value!![6]) == 3){
            _combG.value = intArrayOf(0,3,6)
            nouEstat = 1*_torn.value!!
        }else if(abs(_tauler.value!![1] + _tauler.value!![4] + _tauler.value!![7]) == 3) {
            _combG.value = intArrayOf(1,4,7)
            nouEstat = 1 * _torn.value!!
        }else if(abs(_tauler.value!![2] + _tauler.value!![5] + _tauler.value!![8]) == 3) {
            _combG.value = intArrayOf(2,5,8)
            nouEstat = 1 * _torn.value!!
        }else if(abs(_tauler.value!![0] + _tauler.value!![4] + _tauler.value!![8]) == 3){
            _combG.value = intArrayOf(0,4,8)
            nouEstat = 1*_torn.value!!
        }else if(abs(_tauler.value!![6] + _tauler.value!![4] + _tauler.value!![2]) == 3){
            _combG.value = intArrayOf(6,4,2)
            nouEstat = 1*_torn.value!!
        }else if (_contador.value == 9){
            nouEstat = 2
        }

        return nouEstat
    }

    fun comprovarTauler(): Int{

        var posicioM = (0..8).random()
        var posC = arrayListOf<Int>()
        var posCM = arrayListOf<Int>()

        if (_level.value != "low") {
            for (pos in _tauler.value!!.indices) {

                if (_tauler.value!![pos] == 1) {
                    if (!posC.contains(pos))
                        posC.add(pos)
                } else {
                    if (_tauler.value!![pos] == -1) {
                        if (!posCM.contains(pos))
                            posCM.add(pos)
                    }
                }
            }

            posC.sortedBy { it }


            if (posC.contains(0) && posC.contains(1) && _tauler.value!![2] == 0) {
                posicioM = 2
            } else {
                if (posC.contains(0) && posC.contains(2) && _tauler.value!![1] == 0) {
                    posicioM = 1
                } else {
                    if (posC.contains(1) && posC.contains(2) && _tauler.value!![0] == 0) {
                        posicioM = 0
                    }
                }
            }
            if (posC.contains(3) && posC.contains(4) && _tauler.value!![5] == 0) {
                posicioM = 5
            } else {
                if (posC.contains(3) && posC.contains(5) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posC.contains(4) && posC.contains(5) && _tauler.value!![3] == 0) {
                        posicioM = 3
                    }
                }
            }
            if (posC.contains(6) && posC.contains(7) && _tauler.value!![8] == 0) {
                posicioM = 8
            } else {
                if (posC.contains(6) && posC.contains(8) && _tauler.value!![7] == 0) {
                    posicioM = 7
                } else {
                    if (posC.contains(7) && posC.contains(8) && _tauler.value!![6] == 0) {
                        posicioM = 6
                    }
                }
            }
            if (posC.contains(0) && posC.contains(3) && _tauler.value!![6] == 0) {
                posicioM = 6
            } else {
                if (posC.contains(0) && posC.contains(6) && _tauler.value!![3] == 0) {
                    posicioM = 3
                } else {
                    if (posC.contains(3) && posC.contains(6) && _tauler.value!![0] == 0) {
                        posicioM = 0
                    }
                }
            }
            if (posC.contains(1) && posC.contains(4) && _tauler.value!![7] == 0) {
                posicioM = 7
            } else {
                if (posC.contains(1) && posC.contains(7) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posC.contains(4) && posC.contains(7) && _tauler.value!![1] == 0) {
                        posicioM = 1
                    }
                }
            }
            if (posC.contains(2) && posC.contains(5) && _tauler.value!![8] == 0) {
                posicioM = 8
            } else {
                if (posC.contains(2) && posC.contains(8) && _tauler.value!![5] == 0) {
                    posicioM = 5
                } else {
                    if (posC.contains(5) && posC.contains(8) && _tauler.value!![2] == 0) {
                        posicioM = 2
                    }
                }
            }
            if (posC.contains(0) && posC.contains(4) && _tauler.value!![8] == 0) {
                posicioM = 8
            } else {
                if (posC.contains(0) && posC.contains(8) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posC.contains(4) && posC.contains(8) && _tauler.value!![0] == 0) {
                        posicioM = 0
                    }
                }
            }
            if (posC.contains(2) && posC.contains(4) && _tauler.value!![6] == 0) {
                posicioM = 6
            } else {
                if (posC.contains(2) && posC.contains(6) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posC.contains(4) && posC.contains(6) && _tauler.value!![2] == 0) {
                        posicioM = 2
                    }
                }
            }


            posCM.sortedBy { it }


            if (posCM.contains(0) && posCM.contains(1) && _tauler.value!![2] == 0) {
                posicioM = 2
            } else {
                if (posCM.contains(0) && posCM.contains(2) && _tauler.value!![1] == 0) {
                    posicioM = 1
                } else {
                    if (posCM.contains(1) && posCM.contains(2) && _tauler.value!![0] == 0) {
                        posicioM = 0
                    }
                }
            }
            if (posCM.contains(3) && posCM.contains(4) && _tauler.value!![5] == 0) {
                posicioM = 5
            } else {
                if (posCM.contains(3) && posCM.contains(5) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posCM.contains(4) && posCM.contains(5) && _tauler.value!![3] == 0) {
                        posicioM = 3
                    }
                }
            }
            if (posCM.contains(6) && posCM.contains(7) && _tauler.value!![8] == 0) {
                posicioM = 8
            } else {
                if (posCM.contains(6) && posCM.contains(8) && _tauler.value!![7] == 0) {
                    posicioM = 7
                } else {
                    if (posCM.contains(7) && posCM.contains(8) && _tauler.value!![6] == 0) {
                        posicioM = 6
                    }
                }
            }
            if (posCM.contains(0) && posCM.contains(3) && _tauler.value!![6] == 0) {
                posicioM = 6
            } else {
                if (posCM.contains(0) && posCM.contains(6) && _tauler.value!![3] == 0) {
                    posicioM = 3
                } else {
                    if (posCM.contains(3) && posCM.contains(6) && _tauler.value!![0] == 0) {
                        posicioM = 0
                    }
                }
            }
            if (posCM.contains(1) && posCM.contains(4) && _tauler.value!![7] == 0) {
                posicioM = 7
            } else {
                if (posCM.contains(1) && posCM.contains(7) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posCM.contains(4) && posCM.contains(7) && _tauler.value!![1] == 0) {
                        posicioM = 1
                    }
                }
            }
            if (posCM.contains(2) && posCM.contains(5) && _tauler.value!![8] == 0) {
                posicioM = 8
            } else {
                if (posCM.contains(2) && posCM.contains(8) && _tauler.value!![5] == 0) {
                    posicioM = 5
                } else {
                    if (posCM.contains(5) && posCM.contains(8) && _tauler.value!![2] == 0) {
                        posicioM = 2
                    }
                }
            }
            if (posCM.contains(0) && posCM.contains(4) && _tauler.value!![8] == 0) {
                posicioM = 8
            } else {
                if (posCM.contains(0) && posCM.contains(8) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posCM.contains(4) && posCM.contains(8) && _tauler.value!![0] == 0) {
                        posicioM = 0
                    }
                }
            }
            if (posCM.contains(2) && posCM.contains(4) && _tauler.value!![6] == 0) {
                posicioM = 6
            } else {
                if (posCM.contains(2) && posCM.contains(6) && _tauler.value!![4] == 0) {
                    posicioM = 4
                } else {
                    if (posCM.contains(4) && posCM.contains(6) && _tauler.value!![2] == 0) {
                        posicioM = 2
                    }
                }
            }
        }

        return posicioM

    }
}