package com.example.jobforstudent.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private var _editTextWork = MutableLiveData<String>()
    val editTextWork: LiveData<String>
        get() = _editTextWork
}
