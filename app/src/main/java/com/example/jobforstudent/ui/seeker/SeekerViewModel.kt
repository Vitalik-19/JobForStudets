package com.example.jobforstudent.ui.seeker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class SeekerViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun deleteSession() {
        uiScope.launch {
            deleteSessionSeeker()
        }
    }

    private suspend fun deleteSessionSeeker() {
        withContext(Dispatchers.IO) {
            database.deleteSessionSeeker()
        }
    }
}
