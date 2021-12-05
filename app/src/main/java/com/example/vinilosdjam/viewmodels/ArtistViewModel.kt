package com.example.vinilosdjam.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilosdjam.database.VinilosRoomDatabase
import com.example.vinilosdjam.models.Artist
import com.example.vinilosdjam.network.NetworkServiceAdapter
import com.example.vinilosdjam.repositories.ArtistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArtistViewModel(application: Application) :  AndroidViewModel(application) {

    private val _artist = MutableLiveData<List<Artist>>()
//    private val artistsRepository = ArtistRepository(application)
    private val artistsRepository = ArtistRepository(application, VinilosRoomDatabase.getDatabase(application.applicationContext).artistDao())


    val artist: LiveData<List<Artist>>
        get() = _artist

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch (Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = artistsRepository.refreshData()
                    _artist.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}