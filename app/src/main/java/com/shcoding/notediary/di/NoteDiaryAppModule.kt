package com.shcoding.notediary.di

import com.shcoding.notediary.presentation.screens.authentication.di.authenticationModule
import com.shcoding.notediary.presentation.screens.home.di.homeModule
import org.koin.dsl.module

val noteDiaryAppModule = module {

    includes(authenticationModule, homeModule)
}