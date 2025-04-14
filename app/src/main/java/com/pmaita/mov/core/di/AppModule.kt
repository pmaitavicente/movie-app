package com.pmaita.mov.core.di

import android.app.Application
import androidx.room.Room
import com.pmaita.mov.core.utils.Configuration
import com.pmaita.mov.data.repository.LoginRepository
import com.pmaita.mov.data.repository.mapper.MovieMapper
import com.pmaita.mov.data.repository.MovieRepository
import com.pmaita.mov.data.repository.mapper.MovieEntityMapper
import com.pmaita.mov.data.source.local.MovieDatabase
import com.pmaita.mov.data.source.local.dao.MovieDao
import com.pmaita.mov.data.source.remote.RemoteDataSource
import com.pmaita.mov.ui.login.LoginViewModel
import com.pmaita.mov.ui.movies.MoviesViewModel

import org.koin.android.BuildConfig.DEBUG

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource {
        return retrofit.create(RemoteDataSource::class.java)
    }

    single { provideRemoteDataSource(get()) }

}

val databaseModule = module {

    fun provideDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java,
            Configuration.DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    fun provideMovieDao(database: MovieDatabase):MovieDao {
        return database.movieDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideMovieDao(get()) }

}

val repositoryModule = module {
    fun provideMovieRepository(
        remote:RemoteDataSource,
        dao: MovieDao,
        mapper: MovieMapper,
        mapperEntity: MovieEntityMapper
    ) : MovieRepository {
        return MovieRepository(remote, dao, mapper, mapperEntity)
    }

    single { provideMovieRepository(get(), get(), get(), get()) }
    single { LoginRepository() }
}

val networkModule = module {

    val connectTimeout : Long = 40 // 20s
    val readTimeout : Long  = 40 // 20s

    fun provideRetrofit(client: OkHttpClient):Retrofit {
        return Retrofit.Builder().client(client)
            .baseUrl(Configuration.REMOTE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    single { provideHttpClient() }
    single { provideRetrofit(get()) }

}

val viewModelModule = module {
    viewModel { MoviesViewModel(get(), repository = get()) }
    viewModel { LoginViewModel(get()) }
}

val mapperModule = module {
    factory { MovieMapper() }
    factory { MovieEntityMapper() }
}

val allModules = apiModule +
        databaseModule +
        repositoryModule +
        networkModule +
        viewModelModule +
        mapperModule