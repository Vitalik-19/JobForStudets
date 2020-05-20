package com.example.jobforstudent.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Employer
import com.example.jobforstudent.database.Seeker
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class LoginViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _openEmployer = MutableLiveData<Employer?>()
    val openEmployer: LiveData<Employer?>
        get() = _openEmployer

    private val _openSeeker = MutableLiveData<Seeker?>()
    val openSeeker: LiveData<Seeker?>
        get() = _openSeeker

    val _editLogin = MutableLiveData<String?>()
    val editLogin: LiveData<String?>
        get() = _editLogin


    private val _editPassword = MutableLiveData<String>()
    val editPassword: MutableLiveData<String>
        get() = _editPassword

    private val _employerNavigationEvent = MutableLiveData<Boolean>()
    val employerNavigationEvent: LiveData<Boolean>
        get() = _employerNavigationEvent

    private val _seekerNavigationEvent = MutableLiveData<Boolean>()
    val seekerNavigationEvent: LiveData<Boolean>
        get() = _seekerNavigationEvent

    private val _toast = MutableLiveData<String>()
    val toast: LiveData<String>
        get() = _toast

    init {
        _employerNavigationEvent.value = false
        _seekerNavigationEvent.value = false
    }

    private fun initializeCreateEmployer(login: String?) {
        uiScope.launch {
            _openEmployer.value = login?.let { getEmployerFromDatabase(it) }
            if (openEmployer.value?.loginEmployer != null) {
                _employerNavigationEvent.value = true
            }
        }
    }

    private fun initializeCreateSeeker(login: String?) {
        uiScope.launch {
            _openSeeker.value = login?.let { getSeekerFromDatabase(it) }
            if (openSeeker.value?.loginSeeker != null) {
                _seekerNavigationEvent.value = true
            }
        }
    }

    fun onLogin() {
        if (editLogin.value != null) {
            initializeCreateEmployer(editLogin.value)
            initializeCreateSeeker(editLogin.value)
            if (_seekerNavigationEvent.value == false || _employerNavigationEvent.value == false)
                _toast.value = "Данні введено не вірно"
        } else _toast.value = "Введіть дані!"
    }

    private suspend fun getEmployerFromDatabase(login: String): Employer? {
        return withContext(Dispatchers.IO) {
            val employer = database.getEmployer(login)

            employer
        }
    }

    private suspend fun getSeekerFromDatabase(login: String): Seeker? {
        return withContext(Dispatchers.IO) {
            val seeker = database.getSeeker(login)

            seeker
        }
    }
}
