package com.example.jobforstudent.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this
        //todo verification login
        binding.loginFragmentLoginButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_seekerFragment))

        //todo button navigation to employer fragment
        binding.loginFragmentRegistrationButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registrationFragment))

        //todo delete the Back button
        binding.button.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_employerFragment))

        return binding.root
    }
}
