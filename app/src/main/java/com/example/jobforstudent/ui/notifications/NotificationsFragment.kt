package com.example.jobforstudent.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jobforstudent.R

class NotificationsFragment : Fragment() {

    private lateinit var viewModel: NotificationsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.notifications_fragment, container, false)

        viewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}
