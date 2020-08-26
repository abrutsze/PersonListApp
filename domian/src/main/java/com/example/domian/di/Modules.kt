package com.example.domian.di

import com.example.domian.interactors.GetDataInteractor
import com.example.domian.interactors.UserPostInteractor
import com.example.domian.usecase.GetDataUseCase
import com.example.domian.usecase.UserPostUseCase
import org.koin.dsl.module

val interactorModule = module {
    single<GetDataInteractor> { GetDataUseCase(get()) }
    single<UserPostInteractor> { UserPostUseCase(get()) }
}
