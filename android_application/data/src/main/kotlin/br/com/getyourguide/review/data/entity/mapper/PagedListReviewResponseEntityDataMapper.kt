package br.com.getyourguide.review.data.entity.mapper

import br.com.getyourguide.review.data.entity.*
import br.com.getyourguide.review.domain.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagedListReviewResponseEntityDataMapper
@Inject constructor(private val reviewEntityDataMapper: ReviewEntityDataMapper) {

    fun transformToSectionReviewList(pagedListResponseEntity: PagedListReviewEntity<List<ReviewEntity>>)
            : PagedReviewList<Review>? {
        return PagedReviewList(transformResult(pagedListResponseEntity.result), pagedListResponseEntity.total)
    }

    private fun transformResult(result: List<ReviewEntity>): List<Review> {
        return this.reviewEntityDataMapper.transform(result)
    }


}