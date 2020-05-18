package com.example.jobforstudent.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.RegistrationFragmentBinding

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() =
                RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel
    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)

        binding.registrationViewModel = viewModel
        binding.setLifecycleOwner(this)

        //todo verification registration
        binding.registrationFragmentRegistrationButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_registrationFragment_to_loginFragment)
        )

        return binding.root
    }
}
