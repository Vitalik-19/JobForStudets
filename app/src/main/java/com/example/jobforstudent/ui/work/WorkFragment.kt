package com.example.jobforstudent.ui.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AdvertDatabase
import com.example.jobforstudent.databinding.WorkFragmentBinding


class WorkFragment : Fragment() {

    companion object {
        fun newInstance() = WorkFragment()
    }

    private lateinit var binding: WorkFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.work_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AdvertDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = WorkViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WorkViewModel::class.java)

        binding.workViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.initializeAdvert(arguments!!.getLong("advertId"))

        viewModel.advert.observe(viewLifecycleOwner, Observer {
            it.let {
                //Todo output data
                binding.name.text = it.workName
            }
        })

        return binding.root
    }
}
