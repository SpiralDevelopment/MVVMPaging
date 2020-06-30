package com.example.seemenstask.di.modules

import androidx.lifecycle.ViewModel
import com.example.seemenstask.di.annotations.ViewModelKey
import com.spiraldev.mvvmpaging.ui.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: MainActivityViewModel) : ViewModel
}