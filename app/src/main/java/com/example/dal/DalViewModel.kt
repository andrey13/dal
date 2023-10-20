package com.example.dal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.dal.data.DalRepository
import com.example.dal.data.entities.Proverb

class DalViewModel(repository: DalRepository) : ViewModel() {
    val allProverbs : LiveData<List<Proverb>> = repository.getAllProverbs().asLiveData()
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