package com.example.jobforstudent.ui.workInfoEmployer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.WorkInfoEmployerFragmentBinding


class WorkInfoEmployerFragment : Fragment() {

    companion object {
        fun newInstance() = WorkInfoEmployerFragment()
    }

    private lateinit var binding: WorkInfoEmployerFragmentBinding
    private lateinit var viewModel: WorkInfoEmployerViewModel
    private var bundle = Bundle()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.work_info_employer_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = WorkInfoEmployerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WorkInfoEmployerViewModel::class.java)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.action_edit).setOnClickListener {
            bundle.putLong("advertId", arguments!!.getLong("advertId"))
            it.findNavController().navigate(R.id.action_workInfoEmployerFragment_to_editAdvertFragment, bundle)
        }
        view.findViewById<View>(R.id.action_delete).setOnClickListener {
            viewModel.onDeleteAdvert()
            it.findNavController().navigate(R.id.action_workInfoEmployerFragment_to_advertEmployerFragment)
        }
    }
}
