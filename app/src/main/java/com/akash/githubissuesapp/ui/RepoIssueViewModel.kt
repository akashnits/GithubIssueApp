/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.akash.githubissuesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.akash.githubissuesapp.repository.RepoRepository
import com.akash.githubissuesapp.vo.*
import com.android.example.github.util.AbsentLiveData
import javax.inject.Inject

class RepoIssueViewModel @Inject constructor(private val repoRepository: RepoRepository) : ViewModel() {

    private val _repoIssueId: MutableLiveData<RepoIssueId> = MutableLiveData()
    val repoIssueId: LiveData<RepoIssueId>
        get() = _repoIssueId

    val repoIssueList: LiveData<Resource<List<RepoIssue>>> = _repoIssueId.switchMap { input ->
        input.ifExists { org, repoName, state ->
            when(state){
                is Open -> repoRepository.loadOpenRepoIssueList(org, repoName)
                is Closed -> repoRepository.loadClosedRepoIssueList(org, repoName)
                is All -> repoRepository.loadAllRepoIssueList(org, repoName)
            }

        }
    }

    fun retry() {
        val org = _repoIssueId.value?.org
        val repoName = _repoIssueId.value?.repoName
        val state = _repoIssueId.value?.state
        if (org != null && repoName != null && state != null) {
            _repoIssueId.value = RepoIssueId(org, repoName, state)
        }
    }

    fun setId(org: String, repoName: String, state: State) {
        val update = RepoIssueId(org, repoName, state)
        if (_repoIssueId.value == update) {
            return
        }
        _repoIssueId.value = update
    }

    data class RepoIssueId(val org: String, val repoName: String, val state: State) {
        fun <T> ifExists(f: (String, String, State) -> LiveData<T>): LiveData<T> {
            return if (org.isBlank() || repoName.isBlank()) {
                AbsentLiveData.create()
            } else {
                f(org, repoName, state)
            }
        }
    }
}
