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
                binding.workInfoEmployerNameWorkText.text = it.workName
                binding.workInfoEmployerSalaryText.text = it.salary.toString()
                binding.workInfoEmployerCompanyNameText.text = it.companyName
                binding.workInfoEmployerLocationText.text = it.location
                binding.workInfoEmployerDescriptionText.text = it.description
                binding.workInfoEmployerPhoneText.text = it.phone
            }
        })
        return binding.root
    }

    //TODO
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
