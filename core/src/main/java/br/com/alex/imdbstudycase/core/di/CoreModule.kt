package br.com.alex.imdbstudycase.core.di

import android.app.Application
import androidx.room.Room
import br.com.alex.imdbstudycase.core.data.db.MovieDao
import br.com.alex.imdbstudycase.core.data.db.MovieDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit

object CoreModule {

    private const val TIMEOUT = 40L

    val instance = module {
        val connectTimeout : Long = TIMEOUT
        val readTimeout : Long  = TIMEOUT

        fun provideHttpClient(): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)

            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
            okHttpClientBuilder.build()
            return okHttpClientBuilder.build()
        }

        fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        single { provideHttpClient() }
        single {
            val baseUrl = "https://imdb-api.com/${Locale.getDefault().language}/API/"
            provideRetrofit(get(), baseUrl)
        }

        fun provideDataBase(application: Application): MovieDatabase {
            return Room.databaseBuilder(application, MovieDatabase::class.java, "movie_database")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideDao(dataBase: MovieDatabase): MovieDao {
            return dataBase.movieDao()
        }
        single { provideDataBase(androidApplication()) }
        single { provideDao(get()) }
    }
}