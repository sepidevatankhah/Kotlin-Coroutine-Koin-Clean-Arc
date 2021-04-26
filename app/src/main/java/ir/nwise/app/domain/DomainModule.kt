package ir.nwise.app.domain.usecase

import ir.nwise.app.domain.repository.AppRepository
import ir.nwise.app.domain.repository.AppRepositoryImp
import ir.nwise.app.domain.repository.FoodRepositoryImp
import org.koin.dsl.module

val domainModule = module {
    factory { GetFoodResultUseCase(get()) }
    factory { FoodRepositoryImp(get()) }

    single<AppRepository> { AppRepositoryImp(get()) }

}