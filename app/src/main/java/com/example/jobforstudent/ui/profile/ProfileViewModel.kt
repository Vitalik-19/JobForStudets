package com.example.jobforstudent.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class ProfileViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteSession() {
        uiScope.launch {
            deleteSessionUser()
        }
    }

    private suspend fun deleteSessionUser() {
        withContext(Dispatchers.IO) {
            database.deleteSessionSeeker()
            database.deleteSessionEmployer()
        }
    }

}
