package com.example.vijayiproject.di

import com.example.vijayiproject.presentation.viewModel.MainViewModel
import com.example.vijayiproject.domain.MovieRepository
import com.example.vijayiproject.data.remote.ApiService
import com.example.vijayiproject.utils.Constants.BASE_URL
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single {
        MovieRepository(get())
    }

    viewModel{
        MainViewModel(get())
    }
}