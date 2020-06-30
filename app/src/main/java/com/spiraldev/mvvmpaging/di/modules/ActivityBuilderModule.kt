package com.example.seemenstask.di.modules

import com.example.seemenstask.di.annotations.scopes.ActivityScope
import com.spiraldev.mvvmpaging.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): MainActivity
}
