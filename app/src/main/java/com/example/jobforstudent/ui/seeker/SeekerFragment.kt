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
import com.example.jobforstudent.databinding.SeekerFragmentBinding

class SeekerFragment : Fragment() {

    companion object {
        fun newInstance() = SeekerFragment()
    }

    private lateinit var viewModel: SeekerViewModel
    private lateinit var binding: SeekerFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.seeker_fragment, container, false)
        viewModel = ViewModelProvider(this).get(SeekerViewModel::class.java)
        binding.seekerFragmentViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireActivity(), R.id.seekerFragment)
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == R.id.searchFragment || nd.id == R.id.favoriteFragment
                    || nd.id == R.id.notificationsFragment || nd.id == R.id.profileSeekerFragment) {
                binding.navViewSeeker.visibility = View.VISIBLE
            } else {
                binding.navViewSeeker.visibility = View.GONE
            }
        }
        binding.navViewSeeker.setupWithNavController(navController)
    }
}
