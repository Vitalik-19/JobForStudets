package com.example.jobforstudent.ui.advertemployer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class AdvertEmployerViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val adverts = database.getEmployerWithAdverts()
    val employer = database.getAllEmployer()
}
