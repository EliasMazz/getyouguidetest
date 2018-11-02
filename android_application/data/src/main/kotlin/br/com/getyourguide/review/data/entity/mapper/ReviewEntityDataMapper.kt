package br.com.getyourguide.review.data.entity.mapper

import br.com.getyourguide.review.data.entity.ReviewEntity
import br.com.getyourguide.review.domain.Review

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewEntityDataMapper
@Inject constructor() {

    private fun transform(reviewEntity: ReviewEntity): Review {
        return Review(
                id = reviewEntity.id,
                rating = reviewEntity.rating,
                title = reviewEntity.title,
                message = reviewEntity.message,
                author = reviewEntity.author,
                date = reviewEntity.date,
                foreignLanguage = reviewEntity.foreignLanguage,
                languageCode = reviewEntity.languageCode,
                travelerType = reviewEntity.travelerType,
                reviewName = reviewEntity.reviewName,
                reviewCountry = reviewEntity.reviewCountry)

    }

    fun transform(reviewEntityList: List<ReviewEntity>): List<Review> {
        return reviewEntityList.map(this::transform)
    }
}

