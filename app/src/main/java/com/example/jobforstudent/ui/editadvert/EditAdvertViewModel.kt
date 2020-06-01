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
    val editNameWork: MutableLiveData<String>
        get() = _editNameWork

    private val _editCompanyName = MutableLiveData<String>()
    val editCompanyName: MutableLiveData<String>
        get() = _editCompanyName

    private val _editLocation = MutableLiveData<String>()
    val editLocation: MutableLiveData<String>
        get() = _editLocation

    private val _editSalary = MutableLiveData<String>()
    val editSalary: MutableLiveData<String>
        get() = _editSalary

    private val _editDescription = MutableLiveData<String>()
    val editDescription: MutableLiveData<String>
        get() = _editDescription

    private val _editPhone = MutableLiveData<String>()
    val editPhone: MutableLiveData<String>
        get() = _editPhone

    private var _sessionEmployer = MutableLiveData<Long>()
    val sessionEmployer: LiveData<Long>
        get() = _sessionEmployer

    private val _addEvent = MutableLiveData<Boolean>()
    val addEvent: LiveData<Boolean>
        get() = _addEvent

    private var _advert = MutableLiveData<Advert>()
    val advert: LiveData<Advert>
        get() = _advert

    init {
        initializeSessionEmployer()
    }

    fun onClickAdd() {
        _addEvent.value = true
    }

    fun editTextFilling(id: Long) {
        uiScope.launch {
            _advert.value = getCreateAdvertFromDatabase(id)
            if (advert.value != null) {
                _editNameWork.value = advert.value!!.workName
                _editCompanyName.value = advert.value!!.companyName
                _editLocation.value = advert.value!!.location
                _editSalary.value = advert.value!!.salary.toString()
                _editDescription.value = advert.value!!.description
                _editPhone.value = advert.value!!.phone
            }
            Log.i("EditAdvert1", advert.value?.advertId.toString())
        }
    }

    private suspend fun getCreateAdvertFromDatabase(id: Long): Advert? {
        return withContext(Dispatchers.IO) {
            val advert = database.get(id)
            advert
        }
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
            Log.i("EditAdvert2", advert.value?.advertId.toString())
            val newAdvert = Advert()
            newAdvert.workName = editNameWork.value ?: ""
            newAdvert.companyName = editCompanyName.value ?: ""
            newAdvert.location = editLocation.value ?: ""
            newAdvert.salary = (editSalary.value ?: "0").toInt()
            newAdvert.description = editDescription.value ?: ""
            newAdvert.phone = editPhone.value ?: ""
            sessionEmployer.value?.let { newAdvert.ownerId = it }
            insert(newAdvert)
        }
    }

    fun onUpdateAdvert() {
        uiScope.launch {
            Log.i("EditAdvert3", advert.value?.advertId.toString())
            val advert = advert.value!!
            advert.workName = editNameWork.value ?: ""
            advert.companyName = editCompanyName.value ?: ""
            advert.location = editLocation.value ?: ""
            advert.salary = (editSalary.value ?: "0").toInt()
            advert.description = editDescription.value ?: ""
            advert.phone = editPhone.value ?: ""
            insert(advert)
        }
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
