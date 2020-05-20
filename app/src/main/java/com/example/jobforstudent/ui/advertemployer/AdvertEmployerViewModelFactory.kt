package com.example.jobforstudent.ui.advertemployer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.database.UserDatabaseDao

class AdvertEmployerViewModelFactory(
        private val dataSource: UserDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdvertEmployerViewModel::class.java)) {
            return AdvertEmployerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
