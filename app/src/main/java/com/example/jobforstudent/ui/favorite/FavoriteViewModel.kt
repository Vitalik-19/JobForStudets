package com.example.jobforstudent.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.Advert
import com.example.jobforstudent.database.SeekerWithAdverts
import com.example.jobforstudent.database.SessionSeeker
import com.example.jobforstudent.database.UserDatabaseDao
import kotlinx.coroutines.*

class FavoriteViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _favoriteAdverts = MutableLiveData<List<Advert>>()
    val favoriteAdapters: LiveData<List<Advert>>
        get() = _favoriteAdverts

    private var _sessionSeeker = MutableLiveData<Long>()
    val sessionSeeker: LiveData<Long>
        get() = _sessionSeeker

    init {
        initializeCreateAdvert()
    }


    private suspend fun getSessionSeekerFromDatabase(): SessionSeeker? {
        return withContext(Dispatchers.IO) {
            val session = database.getSessionSeeker()
            session
        }
    }

    private fun initializeCreateAdvert() {
        uiScope.launch {
            _sessionSeeker.value = getSessionSeekerFromDatabase()?.seekerId
            _favoriteAdverts.value = sessionSeeker.value?.let { getCreateAdvertFromDatabase(it)?.advertList }
        }
    }

    private suspend fun getCreateAdvertFromDatabase(key: Long): SeekerWithAdverts? {
        return withContext(Dispatchers.IO) {
            val adverts = database.getSeekerWithAdverts(key)

            adverts
        }
    }
}