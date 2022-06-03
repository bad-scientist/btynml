package com.example.beautymnl.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beautymnl.data.repository.DeveloperRepository
import com.example.beautymnl.ui.viewmodel.DeveloperViewModel

class ViewModelFactory(var developerRepository: DeveloperRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeveloperViewModel::class.java)) {
            return DeveloperViewModel(developerRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}