package com.pmaita.mov.ui.movies

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pmaita.mov.core.utils.NetworkManager
import com.pmaita.mov.data.repository.MovieRepository
import com.pmaita.mov.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
class MoviesViewModel(
    private val context: Context,
    private val repository: MovieRepository
) : ViewModel() {

    private val _moviesList = MutableLiveData<List<Movie.Data>>(emptyList())
    val moviesList: LiveData<List<Movie.Data>> = _moviesList

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _favoriteMovies = MutableLiveData<List<Movie.Data>>(emptyList())
    val favoriteMovies: LiveData<List<Movie.Data>> = _favoriteMovies

    private var currentPage = 1
    private var isLastPage = false
    private var isFetching = false

    fun loadNextPage(apiKey: String) {
        if (_isLoading.value == true || isFetching || isLastPage) return

        isFetching = true
        _isLoading.value = true

        viewModelScope.launch {
            delay(1000) // Simula tiempo de red

            val newList = mutableListOf<Movie.Data>()

            if (NetworkManager.isOnline(context)) {
                val result = repository.getMoviesRemote(currentPage, apiKey)
                if (result.message.isEmpty()) {
                    val array = result.data as List<*>
                    newList.addAll(array.filterIsInstance<Movie.Data>())
                }
            } else {
                withContext(Dispatchers.IO) {
                    newList.addAll(repository.getMoviesLocal(20, (currentPage - 1) * 20))
                }
            }

            if (newList.isEmpty()) {
                isLastPage = true
            }

            _moviesList.value = _moviesList.value.orEmpty() + newList
            _isLoading.value = false
            isFetching = false
            currentPage++
        }
    }

    fun reset() {
        _moviesList.value = emptyList()
        _isLoading.value = false
        currentPage = 1
        isLastPage = false
        isFetching = false
    }

    fun getFavorites() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getFavoritesLocal()
            }
            _favoriteMovies.value = result
        }
    }

    fun getMovieById(id: Int): LiveData<Movie.Data> {
        return repository.getMovieById(id)
    }

    fun toggleFavorite(movie: Movie.Data) {
        viewModelScope.launch {
            val updated = movie.copy(isFavorite = !(movie.isFavorite ?: false))
            repository.updateMovie(updated)
        }
    }

}