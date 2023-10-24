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
import javax.inject.Inject

@HiltViewModel
class DalViewModel @Inject constructor(repository: DalRepository) : ViewModel() {

    var text by mutableStateOf("")
        private set

    val allProverbs : LiveData<List<Proverb>> = repository.getAllProverbs()
        .asLiveData(viewModelScope.coroutineContext)

    fun updateText(text: String) {
        this.text = text
    }

}
