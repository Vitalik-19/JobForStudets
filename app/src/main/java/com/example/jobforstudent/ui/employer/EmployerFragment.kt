package com.example.jobforstudent.ui.employer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.jobforstudent.R
import com.example.jobforstudent.database.AppDatabase
import com.example.jobforstudent.databinding.EmployerFragmentBinding

class EmployerFragment : Fragment() {

    companion object {
        fun newInstance() = EmployerFragment()
    }

    private lateinit var viewModel: EmployerViewModel
    private lateinit var binding: EmployerFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.employer_fragment, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = EmployerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EmployerViewModel::class.java)
        binding.employerViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.bottom_nav_fragment_employer)
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.advertEmployerFragment || nd.id == R.id.profileEmployerFragment) {
                binding.navViewEmployer.visibility = View.VISIBLE
            } else {
                binding.navViewEmployer.visibility = View.GONE
            }
        }
        binding.navViewEmployer.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = EmployerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EmployerViewModel::class.java)

        viewModel.deleteSession()
    }
}
