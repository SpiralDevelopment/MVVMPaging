package com.example.seemenstask.di.components

import android.app.Application
import com.example.seemenstask.di.modules.ActivityBuilderModule
import com.example.seemenstask.di.modules.ApiModule
import com.example.seemenstask.di.modules.AppModule
import com.example.seemenstask.di.modules.ViewModelFactoryModule
import com.spiraldev.mvvmpaging.MVVMPagingApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        ApiModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<MVVMPagingApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
