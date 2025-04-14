package com.pmaita.mov.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmaita.mov.R
import com.pmaita.mov.data.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        val result = repository.login(username, password)

        if (result) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = ""))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (username.isEmpty()) {
            _loginForm.value = LoginFormState(usernameError = R.string.login_username_error)
            return
        }

        if (password.isEmpty()) {
            _loginForm.value = LoginFormState(passwordError = R.string.login_password_error)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

}