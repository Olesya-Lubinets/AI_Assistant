package com.example.ai_assistant.di

import android.util.Log
import com.example.ai_assistant.API.authAPI.AuthAPIService
import com.example.ai_assistant.API.authAPI.AuthInterceptor
import com.example.ai_assistant.API.authAPI.TokenManager
import com.example.ai_assistant.API.chatAPI.ChatAPIService
import com.example.ai_assistant.API.chatAPI.ChatInterceptor
import com.example.ai_assistant.di.Qualifiers.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    @AuthOkHttpClient
    fun providesAuthOkHttpClient(): OkHttpClient {
        val basicAuth = Credentials.basic(
            UserCredentials.clientID,
            UserCredentials.clientSecret
        )
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AuthInterceptor(basicAuth))
            .build()
    }


    @Provides
    @Singleton
    @AuthRetrofit
    fun providesAuthRetrofit(@AuthOkHttpClient authOkHttpClient: OkHttpClient): Retrofit {
        val BASE_URL = "https://ngw.devices.sberbank.ru:9443/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(authOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesAuthAPIService(@AuthRetrofit authRetrofit: Retrofit): AuthAPIService {
        return authRetrofit.create(AuthAPIService::class.java)
    }


    @Provides
    @Singleton
    @ChatOkHttpClient
    fun providesChatOkHttpClient(chatInterceptor: ChatInterceptor): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(chatInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @ChatRetrofit
    fun providesChatRetrofit( @ChatOkHttpClient chatOkHttpClient: OkHttpClient): Retrofit {
        val BASE_URL = "https://api.giga.chat/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(chatOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesChatAPIService(@ChatRetrofit chatRetrofit: Retrofit): ChatAPIService {
        return chatRetrofit.create(ChatAPIService::class.java)
    }
}
