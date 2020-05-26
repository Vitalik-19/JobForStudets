package com.example.jobforstudent.ui.workInfoSeeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.WorkInfoSeekerFragmentBinding


class WorkInfoSeekerFragment : Fragment() {

    companion object {
        fun newInstance() = WorkInfoSeekerFragment()
    }

    private lateinit var binding: WorkInfoSeekerFragmentBinding
    private lateinit var viewModel: WorkInfoSeekerViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.work_info_seeker_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).advertDatabaseDao
        val viewModelFactory = WorkInfoSeekerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WorkInfoSeekerViewModel::class.java)

        binding.workInfoSeekerViewModel = viewModel
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<View>(R.id.action_favorite).setOnClickListener {
            viewModel.initializeActionFavoriteEnable()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
