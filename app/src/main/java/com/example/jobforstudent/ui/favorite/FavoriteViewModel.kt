package com.example.jobforstudent.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.SeekerWithAdverts
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class FavoriteViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var favoriteAdverts = MutableLiveData<Advert>()

    init {
        initializeCreateAdvert()
    }

    private fun initializeCreateAdvert() {
        uiScope.launch {
//            favoriteAdverts.value = getCreateAdvertFromDatabase()[0].advertList
        }
    }

    private suspend fun getCreateAdvertFromDatabase(): List<SeekerWithAdverts> {
        return withContext(Dispatchers.IO) {
            val advert = database.getSeekerWithAdverts()

            advert
        }
    }
}