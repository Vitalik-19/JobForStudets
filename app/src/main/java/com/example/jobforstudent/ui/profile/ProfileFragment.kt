package com.example.jobforstudent.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.jobforstudent.R
import com.example.jobforstudent.databinding.ProfileFragmentBinding


class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: ProfileFragmentBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
//        binding.profileFragmentLoginProfile.setOnClickListener(
//                //TODO to make an account out
//                Navigation.createNavigateOnClickListener(R.id.action_navigation_profile_to_loginFragment))
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
