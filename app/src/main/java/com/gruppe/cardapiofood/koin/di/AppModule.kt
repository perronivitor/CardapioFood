package com.gruppe.cardapiofood.koin.di

import com.gruppe.cardapiofood.ui.viewmodel.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {RecipeViewModel()}
}