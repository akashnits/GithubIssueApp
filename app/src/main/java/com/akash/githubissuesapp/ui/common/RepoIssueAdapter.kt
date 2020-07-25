package com.akash.githubissuesapp.ui.common

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.akash.githubissuesapp.R
import com.akash.githubissuesapp.ui.common.DataBoundListAdapter

import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import com.akash.githubissuesapp.AppExecutors
import com.akash.githubissuesapp.databinding.ItemRepoIssueBinding
import com.akash.githubissuesapp.vo.RepoIssue

/**
 * Created by akash on 26,July,2020
 */
class RepoIssueAdapter(appExecutors: AppExecutors, private val dataBindingComponent: DataBindingComponent) :
    DataBoundListAdapter<RepoIssue, ItemRepoIssueBinding>(appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<RepoIssue>(){
            override fun areItemsTheSame(oldItem: RepoIssue, newItem: RepoIssue): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepoIssue, newItem: RepoIssue): Boolean {
                return oldItem.number == newItem.number && oldItem.user?.login == newItem.user?.login
                        && oldItem.title == newItem.title &&
                        oldItem.pullRequest?.patchUrl == newItem.pullRequest?.patchUrl
            }
        }) {

    override fun createBinding(parent: ViewGroup): ItemRepoIssueBinding {
        val binding= DataBindingUtil.inflate<ItemRepoIssueBinding>(LayoutInflater.from(parent.context),
        R.layout.item_repo_issue,
        parent,
        false,
        dataBindingComponent)

        return binding
    }

    override fun bind(binding: ItemRepoIssueBinding, item: RepoIssue) {
        binding.repoIssue= item
    }
}