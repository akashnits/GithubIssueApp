package com.akash.githubissuesapp.di

import android.app.Application
import com.akash.githubissuesapp.GithubApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by akash on 25,July,2020
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    MainActivityModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GithubApp)
}