package com.akash.githubissuesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.akash.githubissuesapp.AppExecutors
import com.akash.githubissuesapp.R
import com.akash.githubissuesapp.binding.FragmentDataBindingComponent
import com.akash.githubissuesapp.databinding.FragmentGithubIssuesBinding
import com.akash.githubissuesapp.di.Injectable
import com.akash.githubissuesapp.ui.common.RepoIssueAdapter
import com.akash.githubissuesapp.ui.common.RetryCallback
import com.akash.githubissuesapp.vo.All
import com.android.example.github.util.autoCleared
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [GithubIssuesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GithubIssuesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val repoIssueViewModel: RepoIssueViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    // mutable for testing
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var binding by autoCleared<FragmentGithubIssuesBinding>()

    //private val params by navArgs<RepoFragmentArgs>()
    private var adapter by autoCleared<RepoIssueAdapter>()

    private fun initRepoIssueList(viewModel: RepoIssueViewModel) {
        viewModel.repoIssueList.observe(viewLifecycleOwner, Observer { listResource ->
            // we don't need any null checks here for the adapter since LiveData guarantees that
            // it won't call us if fragment is stopped or not started.
            if (listResource?.data != null) {
                adapter.submitList(listResource.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val dataBinding = DataBindingUtil.inflate<FragmentGithubIssuesBinding>(
            inflater,
            R.layout.fragment_github_issues,
            container,
            false
        )
        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                repoIssueViewModel.retry()
            }
        }
        binding = dataBinding
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repoIssueViewModel.setId("prestodb", "presto", All)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.repoIssue= repoIssueViewModel.repoIssueList

        val adapter = RepoIssueAdapter(appExecutors, dataBindingComponent)
        this.adapter = adapter
        binding.repoIssueList.adapter = adapter
        initRepoIssueList(repoIssueViewModel)
    }

    companion object {

    }
}