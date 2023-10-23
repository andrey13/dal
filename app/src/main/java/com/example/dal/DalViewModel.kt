package com.example.dal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dal.data.DalRepository
import com.example.dal.data.entities.Proverb
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class DalViewModel(repository: DalRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)

    //val isLoading: StateFlow<Boolean> = _isLoading

    val allProverbs : LiveData<List<Proverb>> = repository.getAllProverbs()
        .onStart {
            Log.i("--==>", "onStart")
            _isLoading.value = false
        }
        .onCompletion {
            Log.i("--==>", "onCompletion")
            _isLoading.value = false
        }
        .asLiveData(viewModelScope.coroutineContext)


//    fun getAll(): List<Proverb> {
//        viewModelScope.launch {
//            allProverbs.value = repository.getAll()
//            _isLoading.value = false
//        }
//
//    }


//    fun loadData() : LiveData<List<Proverb>> {
//        Log.i("--==>", "loadData")
//        val resFlow = repository.getAllProverbs()
//            .onStart {
//                Log.i("--==>", "onStart")
//                _isLoading.value = true
//            }
//            .onCompletion {
//                Log.i("--==>", "onCompletion")
//                _isLoading.value = false
//            }
//        val res: LiveData<List<Proverb>> = resFlow
//            .asLiveData(viewModelScope.coroutineContext)
//        return res
//    }
}

//---------------------------------------------------------------------------------------
class DalViewModelFactory(private val repository: DalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}