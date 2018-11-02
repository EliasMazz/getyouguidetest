package br.com.getyourguide.review.domain.repository

import br.com.getyourguide.review.domain.PagedReviewList
import br.com.getyourguide.review.domain.Review
import io.reactivex.Observable


interface ReviewRepository {

    fun listReview(count: Int?, page: Int?,rating: Int?, sortBy: String?, direction: String?): Observable<PagedReviewList<Review>>
}

