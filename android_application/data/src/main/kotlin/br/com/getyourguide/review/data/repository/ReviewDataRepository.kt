package br.com.getyourguide.review.data.repository

import br.com.getyourguide.review.data.entity.mapper.PagedListReviewResponseEntityDataMapper
import br.com.getyourguide.review.data.net.service.ReviewService
import br.com.getyourguide.review.domain.PagedReviewList
import br.com.getyourguide.review.domain.Review
import br.com.getyourguide.review.domain.repository.ReviewRepository
import io.reactivex.Observable
import javax.inject.Inject

class ReviewDataRepository
@Inject constructor(private val reviewService: ReviewService,
                    private val pagedResponseEntityDataMapper: PagedListReviewResponseEntityDataMapper) : ReviewRepository {
    override fun listReview(count: Int?, page: Int?, rating: Int?, sortBy: String?, direction: String?): Observable<PagedReviewList<Review>> {
        return this.reviewService.listReview(count, page, rating, sortBy, direction)
                .map(this.pagedResponseEntityDataMapper::transformToSectionReviewList)
    }


}


