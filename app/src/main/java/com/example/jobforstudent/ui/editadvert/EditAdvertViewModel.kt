package com.example.jobforstudent.ui.editadvert

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.AdvertDatabaseDao
import com.example.jobforstudent.database.SessionEmployer
import kotlinx.coroutines.*

class EditAdvertViewModel(val database: AdvertDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _editNameWork = MutableLiveData<String>()
    val editNameWork: LiveData<String>
        get() = _editNameWork

    private val _editCompanyName = MutableLiveData<String>()
    val editCompanyName: LiveData<String>
        get() = _editCompanyName

    private val _editLocation = MutableLiveData<String>()
    val editLocation: LiveData<String>
        get() = _editLocation

    private val _editSalary = MutableLiveData<Int>()
    val editSalary: LiveData<Int>
        get() = _editSalary

    private var _sessionEmployer = MutableLiveData<Long>()
    val sessionEmployer: LiveData<Long>
        get() = _sessionEmployer

    init {
        initializeSessionEmployer()
    }

    private fun initializeSessionEmployer() {
        uiScope.launch {
            _sessionEmployer.value = getSessionEmployerFromDatabase()?.employerId
        }
    }

    private suspend fun getCreateAdvertFromDatabase(): Advert? {
        return withContext(Dispatchers.IO) {
            val advert = database.getCreateAdvert()
            advert
        }
    }

    fun onCreateAdvert() {
        uiScope.launch {
            Log.i("employer", "sessionEmployer = " + sessionEmployer.value.toString())
            val newAdvert = Advert()
            newAdvert.workName = editNameWork.value ?: ""
            newAdvert.companyName = editCompanyName.value ?: ""
            newAdvert.location = editLocation.value ?: ""
            newAdvert.salary = editSalary.value ?: 0
            sessionEmployer.value?.let { newAdvert.ownerId = it }
            insert(newAdvert)
        }
    }

    fun dataFilling(work: String, company: String, location: String, salary: Int) {
        _editNameWork.value = work
        _editCompanyName.value = company
        _editLocation.value = location
        _editSalary.value = salary
    }

    private suspend fun insert(advert: Advert) {
        withContext(Dispatchers.IO) {
            database.insert(advert)
        }
    }

    private suspend fun update(advert: Advert) {
        withContext(Dispatchers.IO) {
            database.update(advert)
        }
    }

    private suspend fun getSessionEmployerFromDatabase(): SessionEmployer? {
        return withContext(Dispatchers.IO) {
            val session = database.getSessionEmployer()
            session
        }
    }

}
