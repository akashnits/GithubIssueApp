package com.akash.githubissuesapp.di

import com.akash.githubissuesapp.ui.GithubIssuesFragment
import com.akash.githubissuesapp.ui.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by akash on 25,July,2020
 */
@Module
@Suppress("unused")
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesGithubIssuesFragment(): GithubIssuesFragment

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment
}