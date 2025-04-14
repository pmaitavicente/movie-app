package com.pmaita.mov.ui.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: LoginError? = null,
    val passwordError: LoginError? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: LoginError? = null
)