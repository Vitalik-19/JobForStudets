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
    var advertId = MutableLiveData<Long>()

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
            val advert = database.getCreateAdvert()
            advert
        }
    }
}
