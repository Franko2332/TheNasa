package ru.gb.thenasa.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.gb.thenasa.model.appstates.PictureFromTheMarsState
import ru.gb.thenasa.model.repo.NasaRepositoryImpl
import java.lang.Exception

class PictureFromTheMarsViewModel : ViewModel() {
    private val repo = NasaRepositoryImpl()
    private var _uiState: MutableStateFlow<PictureFromTheMarsState?> = MutableStateFlow(null)
    val uiState get() = _uiState

    fun getPicturesFromTheMars() {
        _uiState.value = PictureFromTheMarsState.Loading
        viewModelScope.launch {
            try {
              val result = repo.getPicturesFromTheMars()
                result.let {
                Log.e("mars_result", result.toString())
                    _uiState.value = PictureFromTheMarsState.Success(it)
                }
            } catch (e: Exception) {
                _uiState.value = PictureFromTheMarsState.Error(e)
                e.printStackTrace()
            }
        }

    }
}