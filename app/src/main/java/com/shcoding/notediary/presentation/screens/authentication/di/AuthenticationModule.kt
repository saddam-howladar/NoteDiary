package com.shcoding.notediary.presentation.screens.authentication.di

import com.shcoding.notediary.presentation.screens.authentication.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authenticationModule = module {
    viewModelOf(::AuthenticationViewModel)
}