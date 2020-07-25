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

package com.akash.githubissuesapp.api

import androidx.lifecycle.LiveData
import com.akash.githubissuesapp.vo.RepoIssue
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("repos/{owner}/{name}/issues?state=open")
    fun getOpenIssues(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<RepoIssue>>

    @GET("repos/{owner}/{name}//issues?state=closed")
    fun getClosedIssues(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<List<RepoIssue>>>

    @GET("repos/{owner}/{name}//issues?state=all")
    fun getAllIssues(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<List<RepoIssue>>>
}
