package br.com.getyourguide.review.internal.di.modules

import br.com.getyourguide.review.BuildConfig
import br.com.getyourguide.review.data.net.RxCallAdapterFactory
import br.com.getyourguide.review.data.net.interceptor.Interceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
    }

    @Provides
    @Named("api_client")
    @Singleton
    fun provideApiOkHttpClient(okHttpClient: OkHttpClient.Builder,
                               interceptor: Interceptor): OkHttpClient {
        return okHttpClient
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Named("current_api")
    @Singleton
    fun provideApiRetrofit(@Named("api_client") okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return provideRetrofit(okHttpClient, gson)
                .build()
    }


    private fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl("https://www.getyourguide.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxCallAdapterFactory.create())
                .client(okHttpClient)

    }
}