package com.example.dal

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
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

    var state1 by mutableStateOf("xxx")
        private set

    private val _isLoading = MutableStateFlow(true)

    val allProverbs : LiveData<List<Proverb>> = repository.getAllProverbs()
        .onStart {
            _isLoading.value = false
        }
        .onCompletion {
            _isLoading.value = false
        }
        .asLiveData(viewModelScope.coroutineContext)
}
