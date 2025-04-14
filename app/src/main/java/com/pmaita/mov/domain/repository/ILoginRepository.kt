package com.pmaita.mov.domain.repository

interface ILoginRepository {

    fun login(username:String, password:String):Boolean

}