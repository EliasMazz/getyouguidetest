package br.com.getyourguide.review.internal.di.modules

import br.com.getyourguide.review.data.repository.ReviewDataRepository
import br.com.getyourguide.review.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideReviewRepository(reviewDataRepository: ReviewDataRepository): ReviewRepository

}