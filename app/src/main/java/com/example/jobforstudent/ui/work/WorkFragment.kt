package com.example.jobforstudent.ui.work

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobforstudent.R


class WorkFragment : Fragment() {

    companion object {
        fun newInstance() = WorkFragment()
    }

    private lateinit var viewModel: WorkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.work_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WorkViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
