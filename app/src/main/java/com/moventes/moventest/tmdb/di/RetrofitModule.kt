package com.moventes.moventest.tmdb.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.moventes.moventest.tmdb.BuildConfig
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import dagger.Module
import dagger.Provides
import me.alfredobejarano.retrofitadapters.LiveDataAdapter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    @ApplicationScope
    fun getTmdbService(retrofit: Retrofit): TmdbNetworkService {
        return retrofit.create(TmdbNetworkService::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetrofit(httpClientBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(LiveDataAdapter.Factory())
            .client(httpClientBuilder.build())
            .build()
    }

    @Provides
    @ApplicationScope
    fun getHttpClientBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        httpClientBuilder.addInterceptor(interceptor)

        return httpClientBuilder
    }

}