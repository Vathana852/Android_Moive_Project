package com.example.ifood.The_Moive_db

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val _results = mutableStateListOf<MovieResult>()
    var errorMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val results: List<MovieResult>
        get() = _results

    fun getResultList(page: Int = 1) {
        viewModelScope.launch {
            isLoading = true
            val apiService = MovieService.getInstance()
            try {
                _results.clear()
                _results.addAll(apiService.getMovies(page = page).results)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }
}



