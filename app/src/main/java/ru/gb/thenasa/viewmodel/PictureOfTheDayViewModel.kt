package ru.gb.thenasa.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.gb.thenasa.model.appstates.PictureOfTheDayState
import ru.gb.thenasa.model.repo.NasaRepositoryImpl
import ru.gb.thenasa.model.repo.Repository
import java.io.IOException

class PictureOfTheDayViewModel() : ViewModel() {
    private var repo: Repository = NasaRepositoryImpl()
    private val _uiState: MutableStateFlow<PictureOfTheDayState?> = MutableStateFlow(null)
    val uiState: Flow<PictureOfTheDayState?> get() = _uiState

    fun getPictureOfTheDay() {
        _uiState.value = PictureOfTheDayState.Loading
        viewModelScope.launch {
            try {
                val result = repo.getPictureOfTheDay()
                if (result.thumbnailUrl != null) {
                    _uiState.value =
                        PictureOfTheDayState.Success(result.thumbnailUrl, result.explanation)
                } else _uiState.value = PictureOfTheDayState.Success(result.url, result.explanation)
            } catch (e: IOException) {
                _uiState.value = PictureOfTheDayState.Error(e)
                e.printStackTrace()

            }
        }
    }

    fun getPictureOfTheDayWithDate(date: String) {
        _uiState.value = PictureOfTheDayState.Loading
        viewModelScope.launch {
            try {
                val result = repo.getPictureOfTheDayWithDate(date)
                if (result.thumbnailUrl != null) {
                    _uiState.value =
                        PictureOfTheDayState.Success(result.thumbnailUrl, result.explanation)
                } else _uiState.value = PictureOfTheDayState.Success(result.url, result.explanation)
            } catch (e: IOException) {
                _uiState.value = PictureOfTheDayState.Error(e)
                e.printStackTrace()

            }
        }
    }
}