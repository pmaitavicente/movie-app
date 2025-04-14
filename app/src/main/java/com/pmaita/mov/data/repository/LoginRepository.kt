package com.pmaita.mov.data.repository

import com.pmaita.mov.core.utils.Configuration
import com.pmaita.mov.domain.repository.ILoginRepository

class LoginRepository : ILoginRepository {

    override fun login(username: String, password: String):Boolean {
        return (username == Configuration.USERNAME &&
                password == Configuration.PASSWORD)
    }

}