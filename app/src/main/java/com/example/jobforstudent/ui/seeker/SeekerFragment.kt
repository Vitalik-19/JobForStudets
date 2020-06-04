package com.example.jobforstudent.ui.seeker


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
import com.example.jobforstudent.databinding.SeekerFragmentBinding

class SeekerFragment : Fragment() {

    companion object {
        fun newInstance() = SeekerFragment()
    }

    private lateinit var binding: SeekerFragmentBinding
    private lateinit var viewModel: SeekerViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.seeker_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = SeekerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SeekerViewModel::class.java)

        binding.seekerFragmentViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.seekerFragment)
        binding.navViewSeeker.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            when (nd.id) {
                R.id.searchFragment -> binding.navViewSeeker.visibility = View.VISIBLE
                R.id.favoriteFragment -> binding.navViewSeeker.visibility = View.VISIBLE
                R.id.notificationsFragment -> binding.navViewSeeker.visibility = View.VISIBLE
                R.id.profileFragment -> binding.navViewSeeker.visibility = View.VISIBLE
                else -> binding.navViewSeeker.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val application = requireNotNull(this.activity).application
        val dataSource = AppDatabase.getInstance(application).userDatabaseDao
        val viewModelFactory = SeekerViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SeekerViewModel::class.java)

        viewModel.deleteSession()
    }
}
