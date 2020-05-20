package com.example.jobforstudent.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = RegistrationViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)

        binding.registrationViewModel = viewModel
        binding.lifecycleOwner = this

        //todo verification registration
        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        })

        return binding.root
    }
}
