package com.example.testapp.di

import android.content.Context
import android.content.res.Resources
import com.example.testapp.R
import com.example.testapp.apiservice.ApiService
import com.example.testapp.utils.Constants
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    fun provideResource(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideOkhttpClient(resources: Resources): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(createAuthInterceptor(resources))
            .build()
    }

    private fun createAuthInterceptor(resources: Resources): Interceptor {

        return Interceptor { chain: Interceptor.Chain ->
            val updatedUrl = chain.request().url().newBuilder()
                .addQueryParameter(Constants.PARAM_API_KEY, resources.getString(R.string.api_key))
                .build()

            println("updatedUrl: ${updatedUrl.toString()}")

            chain.proceed(
                chain.request().newBuilder().url(updatedUrl).build()
            )
        }
    }

    @Provides
    @Singleton
    fun providePicasso(): Picasso = Picasso.get()

}