package com.example.dynamicdiet

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var appModule = module{
    viewModel{MainViewModel()}
}