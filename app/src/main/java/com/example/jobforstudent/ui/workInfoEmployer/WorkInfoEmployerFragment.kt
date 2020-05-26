package com.example.jobforstudent.ui.workInfoEmployer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.WorkInfoEmployerFragmentBinding
import com.example.jobforstudent.databinding.WorkInfoSeekerFragmentBinding
import com.example.jobforstudent.ui.workInfoSeeker.WorkInfoSeekerViewModel
import com.example.jobforstudent.ui.workInfoSeeker.WorkInfoSeekerViewModelFactory


class WorkInfoEmployerFragment : Fragment() {

    companion object {
        fun newInstance() = WorkInfoEmployerFragment()
    }

    private lateinit var binding: WorkInfoEmployerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.work_info_employer_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = WorkInfoEmployerViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WorkInfoEmployerViewModel::class.java)

        binding.workInfoEmployerViewModel = viewModel
        binding.lifecycleOwner = this
        //todo add safe args
        viewModel.initializeAdvert(arguments!!.getLong("advertId"))

        viewModel.advert.observe(viewLifecycleOwner, Observer {
            it.let {
                //Todo output data
                binding.name.text = it.workName
            }
        })
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_delete -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }
        R.id.action_edit -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}