package com.example.jobforstudent.ui.workInfoEmployer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.database.AdvertDatabaseDao

class WorkInfoEmployerViewModelFactory(
    private val dataSource: AdvertDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkInfoEmployerViewModel::class.java)) {
            return WorkInfoEmployerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}