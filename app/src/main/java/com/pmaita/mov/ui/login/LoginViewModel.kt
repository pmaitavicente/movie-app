package com.pmaita.mov.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmaita.mov.data.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _uiState = MutableLiveData(LoginUiState())
    val uiState: LiveData<LoginUiState> = _uiState

    fun onUsernameChanged(username: String) {
        _uiState.value = _uiState.value?.copy(username = username)
        validate()
    }

    fun onPasswordChanged(password: String) {
        _uiState.value = _uiState.value?.copy(password = password)
        validate()
    }

    private fun validate() {
        val state = _uiState.value ?: return
        val usernameError = if (state.username.isBlank()) LoginError.EMPTY_USERNAME else null
        val passwordError = if (state.password.isBlank()) LoginError.EMPTY_PASSWORD else null

        _uiState.value = state.copy(
            usernameError = usernameError,
            passwordError = passwordError,
            error = null,
            isSuccess = false
        )
    }

    fun login() {
        val state = _uiState.value ?: return
        validate()
        if (state.usernameError != null || state.passwordError != null) return

        _uiState.value = state.copy(isLoading = true, error = null)

        val result = repository.login(state.username, state.password)

        _uiState.value = if (result) {
            state.copy(isLoading = false, isSuccess = true)
        } else {
            state.copy(isLoading = false, error = LoginError.INVALID_CREDENTIALS)
        }
    }
}