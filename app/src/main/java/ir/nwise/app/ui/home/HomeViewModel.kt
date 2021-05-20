package ir.nwise.app.ui.home

import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.UserDto
import ir.nwise.app.domain.model.UserResponse
import ir.nwise.app.domain.usecase.DeleteAlbumUseCase
import ir.nwise.app.domain.usecase.GetAlbumsFromCacheUseCase
import ir.nwise.app.domain.usecase.GetMobileSessionUseCase
import ir.nwise.app.domain.usecase.base.UseCaseResult
import ir.nwise.app.ui.base.BaseViewModel
import ir.nwise.app.ui.utils.emptyString
import kotlin.reflect.full.declaredMemberProperties

class HomeViewModel(
    private val getMobileSessionUseCase: GetMobileSessionUseCase,
    private val getAlbumsFromCacheUseCase: GetAlbumsFromCacheUseCase,
    private val deleteAlbumUseCase: DeleteAlbumUseCase
) :
    BaseViewModel<HomeViewState>() {

    //TODO: to use for users
    fun readProperties(instance: Any): String {
        var api_sig: String = emptyString()
        val clazz = instance.javaClass.kotlin
        clazz.declaredMemberProperties.sortedBy { it.name }.forEach {
            api_sig += it.name + it.get(instance)
        }
        return api_sig
    }

    fun getCachedAlbums(isCalledFromOnViewCreated: Boolean = false) {
        if (isCalledFromOnViewCreated) return
        getAlbumsFromCacheUseCase.execute {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.Loaded(this.data))
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }

    fun deleteAlbum(album: Album) {
        deleteAlbumUseCase.execute(album) {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.DeletedAlbum)
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }

    //TODO: Remove in case that user won't be implemented
    private fun getMobileSession() {
        val user = UserDto()
        val api_sig = readProperties(user)
        getMobileSessionUseCase.execute(UserDto()) {
            when (this) {
                is UseCaseResult.Success -> liveData.postValue(HomeViewState.PreLoaded(this.data))
                is UseCaseResult.Error -> liveData.postValue(HomeViewState.Error(this.exception))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        getMobileSessionUseCase.unsubscribe()
        getAlbumsFromCacheUseCase.unsubscribe()
        deleteAlbumUseCase.unsubscribe()
    }
}

sealed class HomeViewState {
    object Loading : HomeViewState()
    object DeletedAlbum : HomeViewState()
    data class PreLoaded(val session: UserResponse) : HomeViewState()
    data class Loaded(val photos: List<Album>) : HomeViewState()
    data class Error(val throwable: Throwable) : HomeViewState()
}