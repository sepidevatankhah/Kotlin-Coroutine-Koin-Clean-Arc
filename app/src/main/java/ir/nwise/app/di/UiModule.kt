package ir.nwise.app.di

import ir.nwise.app.ui.album.TopAlbumsViewModel
import ir.nwise.app.ui.home.HomeFragment
import ir.nwise.app.ui.home.HomeViewModel
import ir.nwise.app.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {
    factory { HomeFragment() }

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { TopAlbumsViewModel(get(), get(), get()) }
}
