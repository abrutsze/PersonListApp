package com.example.person.di

import com.example.person.fragment.data.viewmodel.DataViewModel
import com.example.person.fragment.post.viewmodel.UserPostViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DataViewModel(get()) }
    viewModel { UserPostViewModel(get()) }
}
