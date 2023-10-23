package com.example.dal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dal.data.DalRepository
import com.example.dal.data.entities.Proverb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class DalViewModel @Inject constructor(repository: DalRepository) : ViewModel() {

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
}
