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

package com.akash.githubissuesapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akash.githubissuesapp.vo.RepoIssue

/**
 * Interface for database access on Repo related operations.
 */
@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repoIssue: List<RepoIssue>)

    @Query("SELECT * FROM RepoIssue WHERE org = :org AND repo = :repoName")
    fun loadRepoAllIssues(org: String, repoName: String) : LiveData<List<RepoIssue>>

    @Query("SELECT * FROM RepoIssue WHERE org = :org AND repo = :repoName AND state = :state")
    fun loadRepoOpenIssues(org: String, repoName: String, state: String) : LiveData<List<RepoIssue>>

    @Query("SELECT * FROM RepoIssue WHERE org = :org AND repo = :repoName AND state =  :state")
    fun loadRepoClosedIssues(org: String, repoName: String, state: String) : LiveData<List<RepoIssue>>
}
