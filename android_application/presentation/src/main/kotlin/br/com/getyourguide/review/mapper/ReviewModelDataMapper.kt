package br.com.getyourguide.review.mapper

import br.com.getyourguide.review.domain.Review
import br.com.getyourguide.review.model.ReviewModel
import javax.inject.Inject

class ReviewModelDataMapper
@Inject constructor() {

    fun transform(review: Review): ReviewModel {
        return ReviewModel(id = review.id,
                rating = review.rating,
                title = review.title,
                message = review.message,
                date = review.date,
                author = review.author,
                foreignLanguage = review.foreignLanguage,
                languageCode = review.languageCode,
                travelerType = review.travelerType,
                reviewName = review.reviewName,
                reviewCountry = review.reviewCountry
                )
    }
}

