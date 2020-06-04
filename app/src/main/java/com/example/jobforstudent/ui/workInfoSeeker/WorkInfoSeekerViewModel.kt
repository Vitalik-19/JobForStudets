package com.example.jobforstudent.ui.workInfoSeeker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jobforstudent.database.*
import kotlinx.coroutines.*

class WorkInfoSeekerViewModel(val database: AdvertDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _actionFavoriteEnable = MutableLiveData<Boolean>()
    val actionFavoriteEnable: LiveData<Boolean>
        get() = _actionFavoriteEnable

    private var _advert = MutableLiveData<Advert>()
    val advert: LiveData<Advert>
        get() = _advert

    private var _sessionSeeker = MutableLiveData<Long>()
    val sessionSeeker: LiveData<Long>
        get() = _sessionSeeker


    init {
        _actionFavoriteEnable.value = false
        initializeSessionSeeker()
    }

    private fun onCreateAdvertsSeekers() {
        uiScope.launch {
            val advertsSeekers = AdvertsSeekers(
                    sessionSeeker.value!!,
                    advert.value!!.advertId
            )
            getSeekerWithAdvertsFromDatabase(sessionSeeker.value!!)?.advertList?.let {
                when (it.size) {
                    0 -> insertAdvertsSeekers(advertsSeekers)
                    else -> loop@ for (advert1 in it) {
                        when (advert1.advertId) {
                            advert.value!!.advertId -> {
                                deleteAdvertsSeekers(advertsSeekers)
                                break@loop
                            }
                            else -> {
                                insertAdvertsSeekers(advertsSeekers)
                                break@loop
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun getCreateAdvertFromDatabase(id: Long): Advert? {
        return withContext(Dispatchers.IO) {
            val advert = database.get(id)

            advert
        }
    }

    private suspend fun getSeekerFromDatabase(key: Long): Seeker? {
        return withContext(Dispatchers.IO) {
            val seeker = database.getSeekerById(key)
            seeker
        }
    }

    private suspend fun getAdvertWithSeekersFromDatabase(key: Long): AdvertWithSeekers? {
        return withContext(Dispatchers.IO) {
            val advert = database.getAdvertWithSeekers(key)

            advert
        }
    }

    private suspend fun getSeekerWithAdvertsFromDatabase(key: Long): SeekerWithAdverts? {
        return withContext(Dispatchers.IO) {
            val seeker = database.getSeekerWithAdverts(key)
            seeker
        }
    }

    fun initializeAdvert(id: Long) {
        uiScope.launch {
            _advert.value = getCreateAdvertFromDatabase(id)
        }
    }

    fun initializeActionFavoriteEnable() {
        onCreateAdvertsSeekers()
    }

    private fun initializeSessionSeeker() {
        uiScope.launch {
            _sessionSeeker.value = getSessionSeekerFromDatabase()?.seekerId
        }
    }

    private suspend fun getSessionSeekerFromDatabase(): SessionSeeker? {
        return withContext(Dispatchers.IO) {
            val session = database.getSessionSeeker()
            session
        }
    }

    private suspend fun insertAdvertsSeekers(advertsSeekers: AdvertsSeekers) {
        return withContext(Dispatchers.IO) {
            database.insertAdvertsSeekers(advertsSeekers)
        }
    }

    private suspend fun deleteAdvertsSeekers(advertsSeekers: AdvertsSeekers) {
        return withContext(Dispatchers.IO) {
            database.deleteAdvertsSeekers(advertsSeekers)
        }
    }
}
