package com.example.jobforstudent.ui.advertemployer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.*
import kotlinx.coroutines.*

class AdvertEmployerViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _sessionEmployer = MutableLiveData<Long>()
    val sessionEmployer: LiveData<Long>
        get() = _sessionEmployer

    //TODO rename
    private var _employer = MutableLiveData<Employer>()
    val employer1: LiveData<Employer>
        get() = _employer

    private val _createAdverts = MutableLiveData<List<Advert>>()
    val createAdvert: LiveData<List<Advert>>
        get() = _createAdverts

    val employer = database.getAllEmployer()
    private var newCreateEmployer = MutableLiveData<Employer>()


    init {
        onCreateEmployerWithAdverts()
        initializeCreateAdverts()
    }

    private fun initializeCreateAdverts() {
        uiScope.launch {
            _sessionEmployer.value = getSessionEmployerFromDatabase()?.employerId
            Log.i("employer", "size list advert" + sessionEmployer.value?.let { getEmployerWithAdverts(it)?.advertList?.size })

            when {
                _sessionEmployer.value != null -> {
                    Log.i("employer", "size list advert" + createAdvert.value?.size.toString())
                    _createAdverts.value = sessionEmployer.value?.let { getEmployerWithAdverts(it)?.advertList }
                    Log.i("employer", "size list advert" + createAdvert.value?.size.toString())
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

    private fun onCreateEmployerWithAdverts() {
        uiScope.launch {
            _sessionEmployer.value = getSessionEmployerFromDatabase()?.employerId
            _employer.value = _sessionEmployer.value?.let { getEmployerById(it) }
        }
    }

    private suspend fun insertEmployerWithAdverts(employer: Employer, adverts: List<Advert>) {
        withContext(Dispatchers.IO) {
            database.insertEmployerWithAdverts(employer, adverts)
        }
    }

    private suspend fun getEmployerById(key: Long): Employer? {
        return withContext(Dispatchers.IO) {
            val employer = database.getEmployerById(key)
            employer
        }
    }
}
