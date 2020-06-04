package com.example.jobforstudent.ui.workInfoEmployer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.AdvertDatabaseDao
import kotlinx.coroutines.*

class WorkInfoEmployerViewModel(val database: AdvertDatabaseDao, application: Application) :
        AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _advert = MutableLiveData<Advert>()
    val advert: LiveData<Advert>
        get() = _advert

    fun initializeAdvert(id: Long) {
        uiScope.launch {
            _advert.value = getCreateAdvertFromDatabase(id)
        }
    }

    fun onDeleteAdvert() {
        uiScope.launch {
            deleteAdvert(advert.value!!.advertId)
        }
    }

    private suspend fun getCreateAdvertFromDatabase(id: Long): Advert? {
        return withContext(Dispatchers.IO) {
            val advert = database.get(id)

            advert
        }
    }

    private suspend fun deleteAdvert(key: Long) {
        withContext(Dispatchers.IO) {
            database.clear(key)
        }
    }
}
