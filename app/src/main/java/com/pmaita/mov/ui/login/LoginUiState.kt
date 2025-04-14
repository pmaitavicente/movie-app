package com.pmaita.mov.ui.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: Int? = null
)