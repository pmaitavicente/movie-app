package com.pmaita.mov

import com.pmaita.mov.data.repository.LoginRepository
import com.pmaita.mov.data.repository.MovieRepository
import com.pmaita.mov.data.source.remote.response.GenericResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    companion object {
        const val USERNAME = "Admin"
        const val PASSWORD = "Password*123"
        const val API_KEY = "f46b58478f489737ad5a4651a4b25079"
    }

    @Mock
    lateinit var loginRepository: LoginRepository

    @Mock
    lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

    }

    @Test
    fun loginRepository_wrongCredentianls_invalid() {
        Mockito.`when`(loginRepository.login(USERNAME, PASSWORD))
            .thenReturn(false)
        val output = runBlocking { loginRepository.login(USERNAME, PASSWORD) }

        assertEquals(output, false)
    }

    @Test
    fun loginRepository_correctCredentials_login() {
        Mockito.`when`(loginRepository.login(USERNAME, PASSWORD))
            .thenReturn(true)

        val output = runBlocking { loginRepository.login(USERNAME, PASSWORD) }

        assertEquals(output, true)
    }

    @Test
    fun movieRepository_getMovies() {
        runBlocking {
            val generic =  GenericResponse(null, "")
            whenever(movieRepository.getMoviesRemote(1, API_KEY)).thenReturn(generic)
            Mockito.verify(movieRepository).getMoviesRemote(1, API_KEY)
            Mockito.verifyNoMoreInteractions(movieRepository)
        }
    }

}