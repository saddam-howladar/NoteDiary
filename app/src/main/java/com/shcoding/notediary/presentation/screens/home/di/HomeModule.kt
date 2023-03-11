package com.shcoding.notediary.presentation.screens.home.di

import com.shcoding.notediary.presentation.screens.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}