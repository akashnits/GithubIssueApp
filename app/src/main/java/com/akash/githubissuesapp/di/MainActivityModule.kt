package com.akash.githubissuesapp.di

import com.akash.githubissuesapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by akash on 25,July,2020
 */
@Module
@Suppress("unused")
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesMainActivity(): MainActivity
}