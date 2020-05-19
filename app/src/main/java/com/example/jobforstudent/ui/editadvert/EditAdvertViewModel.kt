package com.example.jobforstudent.ui.editadvert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.AdvertDatabaseDao
import kotlinx.coroutines.*

class EditAdvertViewModel(val database: AdvertDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var createAdvert = MutableLiveData<Advert?>()

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

    init {
        initializeCreateAdvert()
    }

    private fun initializeCreateAdvert() {
        uiScope.launch {
            createAdvert.value = getCreateAdvertFromDatabase()
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
            val newNight = Advert()
            newNight.workName = editNameWork.value ?: ""
            newNight.companyName = editCompanyName.value ?: ""
            newNight.location = editLocation.value ?: ""
            newNight.salary = editSalary.value ?: 0
            insert(newNight)
            createAdvert.value = getCreateAdvertFromDatabase()
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

}
