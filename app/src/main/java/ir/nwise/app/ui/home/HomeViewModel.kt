package ir.nwise.app.ui.home

import ir.nwise.app.domain.model.PhotoResponse
import ir.nwise.app.domain.usecase.GetFoodResultUseCase
import ir.nwise.app.domain.usecase.base.UseCaseResult
import ir.nwise.app.ui.base.BaseViewModel


class HomeViewModel(private val getFoodResultUseCase: GetFoodResultUseCase) :
    BaseViewModel<HomeViewState>() {

    init {
        getFoods()
    }

    private fun getFoods() {
        getFoodResultUseCase.execute {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.Loaded(this.data.photoList))
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getFoodResultUseCase.unsubscribe()
    }
}

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Loaded(val photos: List<PhotoResponse>) : HomeViewState()
    data class Error(val throwable: Throwable) : HomeViewState()
}