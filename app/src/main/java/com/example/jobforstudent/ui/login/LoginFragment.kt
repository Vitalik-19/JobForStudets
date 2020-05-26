package com.example.jobforstudent.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = LoginViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.apply {
            employerNavigationEvent.observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it) findNavController().navigate(R.id.action_loginFragment_to_employerFragment)
                }
            })
            seekerNavigationEvent.observe(viewLifecycleOwner, Observer {
                it?.let {
                    if (it) findNavController().navigate(R.id.action_loginFragment_to_seekerFragment)
                }
            })
            toast.observe(viewLifecycleOwner, Observer {
                it?.let {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            })
        }
        //todo in view model
        binding.loginFragmentRegistrationButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registrationFragment))

        return binding.root
    }
}
