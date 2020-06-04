package com.example.jobforstudent.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.*
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

    private val _positionBool = MutableLiveData<Boolean>()
    private val positionBool: LiveData<Boolean>
        get() = _positionBool

    init {
        _positionBool.value = true
        _employerNavigationEvent.value = false
        _seekerNavigationEvent.value = false
        initializeCreateSession()
    }

    fun onPositionRadioButton(boolean: Boolean) {
        _positionBool.value = boolean
    }

    private fun initializeCreateEmployer(login: String?, password: String?) {
        uiScope.launch {
            password?.let {
                login?.let { it1 ->
                    getEmployerFromDatabase(it1, it).let { it2 -> _openEmployer.value = it2 }
                }
            }
            when (openEmployer.value?.employerId) {
                null -> onToastSignIn()
                -1L -> onToastSignIn()
                else -> {
                    val newSessionEmployer = SessionEmployer()
                    newSessionEmployer.employerId = openEmployer.value!!.employerId
                    insertSessionEmployer(newSessionEmployer)
                    _employerNavigationEvent.value = true
                }
            }
        }
    }

    private fun initializeCreateSeeker(login: String?, password: String?) {
        uiScope.launch {
            password?.let {
                login?.let { it1 ->
                    getSeekerFromDatabase(it1, it).let { it2 -> _openSeeker.value = it2 }
                }
            }
            when (openSeeker.value?.seekerId) {
                null -> onToastSignIn()
                -1L -> onToastSignIn()
                else -> {
                    val newSessionSeeker = SessionSeeker()
                    newSessionSeeker.seekerId = openSeeker.value!!.seekerId
                    insertSessionSeeker(newSessionSeeker)
                    _seekerNavigationEvent.value = true
                }
            }
        }
    }

    private fun initializeCreateSession() {
        uiScope.launch {
            when {
                getSessionSeekerFromDatabase()?.seekerId != null ->
                    _seekerNavigationEvent.value = true
                getSessionEmployerFromDatabase()?.employerId != null ->
                    _employerNavigationEvent.value = true
            }
        }
    }

    fun onSignIn() {
        when {
            positionBool.value!! -> {
                initializeCreateSeeker(editLogin.value, editPassword.value)
            }
            !positionBool.value!! -> {
                initializeCreateEmployer(editLogin.value, editPassword.value)
            }
        }
    }

    private fun onToastSignIn() {
        when {
            editLogin.value == null || editLogin.value == "" &&
                    editPassword.value == null || editPassword.value == "" -> _toast.value = "Введіть дані!"
            editLogin.value == null || editLogin.value == "" -> _toast.value = "Введіть логін!"
            editPassword.value == null || editPassword.value == "" -> _toast.value = "Введіть пароль!"
            openSeeker.value == null || openEmployer.value == null -> _toast.value = "Дані введено не вірно!"
            else -> _toast.value = "else"
        }
    }

    private suspend fun getEmployerFromDatabase(login: String, password: String): Employer? {
        return withContext(Dispatchers.IO) {
            val employer = database.getEmployer(login, password)

            employer
        }
    }

    private suspend fun getSeekerFromDatabase(login: String, password: String): Seeker? {
        return withContext(Dispatchers.IO) {
            val seeker = database.getSeeker(login, password)

            seeker
        }
    }

    private suspend fun getSessionSeekerFromDatabase(): SessionSeeker? {
        return withContext(Dispatchers.IO) {
            val session = database.getSessionSeeker()
            session
        }
    }

    private suspend fun getSessionEmployerFromDatabase(): SessionEmployer? {
        return withContext(Dispatchers.IO) {
            val session = database.getSessionEmployer()
            session
        }
    }

    private suspend fun insertSessionEmployer(sessionEmployer: SessionEmployer) {
        withContext(Dispatchers.IO) {
            database.insertSessionEmployer(sessionEmployer)
        }
    }

    private suspend fun insertSessionSeeker(sessionSeeker: SessionSeeker) {
        withContext(Dispatchers.IO) {
            database.insertSessionSeeker(sessionSeeker)
        }
    }
}
