package com.akash.githubissuesapp.repository

import androidx.lifecycle.LiveData
import com.akash.githubissuesapp.AppExecutors
import com.akash.githubissuesapp.api.ApiResponse
import com.akash.githubissuesapp.api.GithubService
import com.akash.githubissuesapp.db.GithubDb
import com.akash.githubissuesapp.db.RepoDao
import com.akash.githubissuesapp.util.RateLimiter
import com.akash.githubissuesapp.vo.RepoIssue
import com.akash.githubissuesapp.vo.Resource
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by akash on 25,July,2020
 */
@Singleton
class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val db: GithubDb,
    private val repoDao: RepoDao,
    private val githubService: GithubService
) {
    private val repoIssueListRateLimit = RateLimiter<String>(30, TimeUnit.MINUTES)

    fun loadAllRepoIssueList(org: String, repoName: String) : LiveData<Resource<List<RepoIssue>>>{
        val key = org.plus("_")
            .plus(repoName).plus("_").plus("u")
        return object : NetworkBoundResource<List<RepoIssue>, List<RepoIssue>>(appExecutors){
            override fun saveCallResult(item: List<RepoIssue>) {
                item.forEach {
                    it.org= org
                    it.repo= repoName
                }
                repoDao.insert(item)
            }

            override fun shouldFetch(data: List<RepoIssue>?): Boolean {
                return data == null || data.isEmpty() ||
                        repoIssueListRateLimit.shouldFetch(key)
            }

            override fun loadFromDb(): LiveData<List<RepoIssue>> {
                return repoDao.loadRepoAllIssues(org, repoName)
            }

            override fun createCall(): LiveData<ApiResponse<List<RepoIssue>>> {
                return  githubService.getAllIssues(org, repoName)
            }

            override fun onFetchFailed() {
                repoIssueListRateLimit.reset(key)
            }
        }.asLiveData()
    }

    fun loadOpenRepoIssueList(org: String, repoName: String) : LiveData<Resource<List<RepoIssue>>>{
        val key = org.plus("_")
            .plus(repoName).plus("_").plus("y")
        return object : NetworkBoundResource<List<RepoIssue>, List<RepoIssue>>(appExecutors){
            override fun saveCallResult(item: List<RepoIssue>) {
                item.forEach {
                    it.org= org
                    it.repo= repoName
                }
                repoDao.insert(item)
            }

            override fun shouldFetch(data: List<RepoIssue>?): Boolean {
                return data == null || data.isEmpty() ||
                        repoIssueListRateLimit.shouldFetch(key)
            }

            override fun loadFromDb(): LiveData<List<RepoIssue>> {
                return repoDao.loadRepoOpenIssues(org, repoName, "open")
            }

            override fun createCall(): LiveData<ApiResponse<List<RepoIssue>>> {
                return  githubService.getOpenIssues(org, repoName)
            }

            override fun onFetchFailed() {
                repoIssueListRateLimit.reset(key)
            }
        }.asLiveData()
    }

    fun loadClosedRepoIssueList(org: String, repoName: String) : LiveData<Resource<List<RepoIssue>>>{
        val key = org.plus("_")
            .plus(repoName).plus("_").plus("n")
        return object : NetworkBoundResource<List<RepoIssue>, List<RepoIssue>>(appExecutors){
            override fun saveCallResult(item: List<RepoIssue>) {
                item.forEach {
                    it.org= org
                    it.repo= repoName
                }
                repoDao.insert(item)
            }

            override fun shouldFetch(data: List<RepoIssue>?): Boolean {
                return data == null || data.isEmpty() ||
                        repoIssueListRateLimit.shouldFetch(org.plus("_")
                            .plus(repoName).plus("_").plus("n"))
            }

            override fun loadFromDb(): LiveData<List<RepoIssue>> {
                return repoDao.loadRepoClosedIssues(org, repoName, "closed")
            }

            override fun createCall(): LiveData<ApiResponse<List<RepoIssue>>> {
                return  githubService.getClosedIssues(org, repoName)
            }

            override fun onFetchFailed() {
                repoIssueListRateLimit.reset(key)
            }
        }.asLiveData()
    }

}