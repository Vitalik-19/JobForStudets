package com.example.jobforstudent.ui.advertemployer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.EmployerWithAdverts
import com.example.jobforstudent.database.SessionEmployer
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class AdvertEmployerViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _sessionEmployer = MutableLiveData<Long>()
    private val sessionEmployer: LiveData<Long>
        get() = _sessionEmployer

    private val _createAdverts = MutableLiveData<List<Advert>>()
    val createAdvert: LiveData<List<Advert>>
        get() = _createAdverts

    init {
        initializeCreateAdverts()
    }

    private fun initializeCreateAdverts() {
        uiScope.launch {
            _sessionEmployer.value = getSessionEmployerFromDatabase()?.employerId

            when {
                _sessionEmployer.value != null -> {
                    _createAdverts.value = sessionEmployer.value?.let { getEmployerWithAdverts(it)?.advertList }
                }
            }
        }
    }

    private suspend fun getEmployerWithAdverts(key: Long): EmployerWithAdverts? {
        return withContext(Dispatchers.IO) {
            val employerWithAdverts = database.getEmployerWithAdverts(key)
            employerWithAdverts
        }
    }

    private suspend fun getSessionEmployerFromDatabase(): SessionEmployer? {
        return withContext(Dispatchers.IO) {
            val session = database.getSessionEmployer()
            session
        }
    }
}
