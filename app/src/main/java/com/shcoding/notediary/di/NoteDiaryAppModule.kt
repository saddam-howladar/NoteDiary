package com.shcoding.notediary.di

import com.shcoding.notediary.presentation.screens.auth.di.authModule
import com.shcoding.notediary.presentation.screens.home.di.homeModule
import org.koin.dsl.module

val noteDiaryAppModule = module {

    includes(authModule, homeModule)
}