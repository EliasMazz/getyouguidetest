package br.com.getyourguide.review.data.net.service

import br.com.getyourguide.review.data.entity.PagedListReviewEntity
import br.com.getyourguide.review.data.entity.ReviewEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewService {

    @GET("berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json")
    fun listReview(@Query("count") count: Int?,
                   @Query("page") page: Int?,
                   @Query("rating") rating: Int?,
                   @Query("sortBy") sortBy: String?,
                   @Query("direction") direction: String?
                   ): Observable<PagedListReviewEntity<List<ReviewEntity>>>

}

