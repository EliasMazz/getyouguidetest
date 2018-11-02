package br.com.getyourguide.review.internal.di.modules

import br.com.getyourguide.review.data.net.service.ReviewService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideReviewService(@Named("current_api") retrofit: Retrofit): ReviewService {
        return retrofit.create(ReviewService::class.java)
    }

}