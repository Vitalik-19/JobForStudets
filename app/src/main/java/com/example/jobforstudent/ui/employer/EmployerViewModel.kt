package com.example.jobforstudent.ui.employer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class EmployerViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteSession() {
        uiScope.launch {
            deleteSessionEmployer()
        }
    }

    private suspend fun deleteSessionEmployer() {
        withContext(Dispatchers.IO) {
            database.deleteSessionEmployer()
        }
    }
}