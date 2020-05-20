package com.example.jobforstudent.ui.registration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Employer
import com.example.jobforstudent.database.Seeker
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class RegistrationViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val employer = database.getAllEmployer()

    private val _positionBool = MutableLiveData<Boolean>()
    private val positionBool: LiveData<Boolean>
        get() = _positionBool

    private val _loginText = MutableLiveData<String>()
    val loginText: MutableLiveData<String>
        get() = _loginText

    private val _passwordText = MutableLiveData<String>()
    val passwordText: MutableLiveData<String>
        get() = _passwordText

    private val _navigationEvent = MutableLiveData<Boolean>()
    val navigationEvent: LiveData<Boolean>
        get() = _navigationEvent

    init {
        _positionBool.value = true
    }

    fun onPositionRadioButton(boolean: Boolean) {
        _positionBool.value = boolean
    }

    fun onAddUser() {
        if (positionBool.value!!) {
            onCreateSeeker()
        } else {
            onCreateEmployer()
        }
        _navigationEvent.value = true
    }

    private fun onCreateEmployer() {
        uiScope.launch {
            val newEmployer = Employer()
            newEmployer.loginEmployer = loginText.value ?: "item null"
            newEmployer.password = passwordText.value ?: "item null"
            insertEmployer(newEmployer)
        }
    }

    private fun onCreateSeeker() {
        uiScope.launch {
            val newSeeker = Seeker()
            newSeeker.loginSeeker = loginText.value ?: "item null"
            newSeeker.password = passwordText.value ?: "item null"
            insertSeeker(newSeeker)
        }
    }

    private suspend fun insertEmployer(employer: Employer) {
        withContext(Dispatchers.IO) {
            database.insertEmployer(employer)
        }
    }

    private suspend fun insertSeeker(seeker: Seeker) {
        withContext(Dispatchers.IO) {
            database.insertSeeker(seeker)
        }
    }
}
