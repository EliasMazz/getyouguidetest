package br.com.getyourguide.review.domain.interactor


import br.com.getyourguide.review.domain.PagedReviewList
import br.com.getyourguide.review.domain.Review
import br.com.getyourguide.review.domain.executor.PostExecutionThread
import br.com.getyourguide.review.domain.executor.ThreadExecutor
import br.com.getyourguide.review.domain.interactor.usecases.ObservableUseCase
import br.com.getyourguide.review.domain.repository.ReviewRepository
import io.reactivex.Observable
import javax.inject.Inject

class ListReviewInteractor
@Inject constructor(private val reviewRepository: ReviewRepository,
                    threadExecutor: ThreadExecutor,
                    postExecutionThread: PostExecutionThread) :
        ObservableUseCase<PagedReviewList<Review>, ListReviewInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<PagedReviewList<Review>> {
        return reviewRepository.listReview(params.count, params.page, params.rating, params.sortBy, params.direction)
    }

    class Params
    private constructor(
            internal val count: Int?,
            internal val page: Int?,
            internal val rating: Int?,
            internal val sortBy: String?,
            internal val direction: String?

    ) {

        companion object {
            fun forList(count: Int?, page: Int?, rating: Int?, sortBy: String?, direction: String?): Params {
                return Params(count, page, rating, sortBy, direction)

            }
        }
    }
}

