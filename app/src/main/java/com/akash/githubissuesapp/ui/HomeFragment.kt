package com.akash.githubissuesapp.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akash.githubissuesapp.MainActivity
import com.akash.githubissuesapp.R
import com.akash.githubissuesapp.databinding.FragmentHomeBinding
import com.akash.githubissuesapp.di.Injectable
import com.android.example.github.util.autoCleared


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), Injectable {

    var binding by autoCleared<FragmentHomeBinding>()
    private var state= "All"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding= DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
        R.layout.fragment_home,
        container,
        false)

        binding= dataBinding
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // This will get the radiobutton that has changed in its check state
            val checkedRadioButton = group.findViewById(checkedId) as RadioButton
            // This puts the value (true/false) into the variable
            val isChecked = checkedRadioButton.isChecked
            // If the radiobutton that has changed in check state is now checked...
            if (isChecked) {
                state= checkedRadioButton.text.toString()
            }
        }

        binding.button.setOnClickListener {
            //commit repoIssueFragment with args
            if(binding.etOrg.text.isNullOrEmpty()){
                binding.etOrg.error= "Invalid input"
            }else if(binding.etRepo.text.isNullOrEmpty()){
                binding.etRepo.error= "Invalid input"
            }else{
                (activity as MainActivity).hideSoftKeyboard()
                val navDirections= HomeFragmentDirections.actionSubmit(binding.etOrg.text.toString(),
                    binding.etRepo.text.toString(), state)
                findNavController().navigate(navDirections)
            }
        }
    }
}