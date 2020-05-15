package com.example.jobforstudent.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.AdvertDatabaseDao
import kotlinx.coroutines.*

class SearchViewModel(val database: AdvertDatabaseDao, application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var createAdvert = MutableLiveData<Advert?>()
    val adverts = database.getAllAdvert()

    private val _navigateToAdvert = MutableLiveData<Advert>()
    val navigateToAdvert: LiveData<Advert>
        get() = _navigateToAdvert

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
            var advert = database.getCreateAdvert()

//            if (advert?.workName != "Work Name") {
//                advert = null
//            }
            advert
        }
    }

    fun onStartCreateAdvert() {
        uiScope.launch {
            val newNight = Advert()
            newNight.salary = System.currentTimeMillis().toInt()
            insert(newNight)
            createAdvert.value = getCreateAdvertFromDatabase()
        }
    }

    private suspend fun insert(advert: Advert) {
        withContext(Dispatchers.IO) {
            database.insert(advert)
        }
    }

    fun onStopCreateAdvert() {
        uiScope.launch {
            val oldAdvert = createAdvert.value ?: return@launch
            oldAdvert.workName = "Android"
            oldAdvert.companyName = "Vitalik_19"
            oldAdvert.location = "Vinnytsia"
            update(oldAdvert)
            _navigateToAdvert.value = oldAdvert
        }
    }

    private suspend fun update(advert: Advert) {
        withContext(Dispatchers.IO) {
            database.update(advert)
        }
    }
}
