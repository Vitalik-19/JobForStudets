package com.example.jobforstudent.ui.workInfoSeeker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.database.AdvertDatabaseDao

class WorkInfoSeekerViewModelFactory(
        private val dataSource: AdvertDatabaseDao,
        private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkInfoSeekerViewModel::class.java)) {
            return WorkInfoSeekerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}