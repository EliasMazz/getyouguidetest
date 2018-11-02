package br.com.getyourguide.review.presenter

import br.com.getyourguide.review.domain.PagedReviewList
import br.com.getyourguide.review.domain.Review

import br.com.getyourguide.review.domain.interactor.DefaultObservableObserver
import br.com.getyourguide.review.domain.interactor.ListReviewInteractor
import br.com.getyourguide.review.mapper.SectionedListItemDataMapper
import br.com.getyourguide.review.model.ReviewHeaderModel
import br.com.getyourguide.review.view.ReviewListView

import javax.inject.Inject

class ReviewListPresenter
@Inject constructor(
        private val sectionedListItemDataMapper: SectionedListItemDataMapper,
        private val getDonationListInteractor: ListReviewInteractor) : LoadDataBasePresenter<ReviewListView>() {

    private var pagedOffset = 0
    private var rating = 0
    private var pagedMax = 20
    private var pagedTotalCount: Int = 800
    private var donationMutableCollection: MutableCollection<Review>? = null
    private var isLoading = false

    private var listHeaderReviewTotalValue: ReviewHeaderModel? = ReviewHeaderModel()
    private var filterDirectionSwap = "DESC"
    private var filterSortBy = "date_of_review"


    fun filterRating(rating: Int){
        this.rating = rating
        resetPagedParams()
        loadReviewList()
    }


    fun loadReviewList() {
        if (needsLoadMoreData()) {
            showViewLoading()
            this.getDonationListInteractor.execute(ReviewListObserver(), ListReviewInteractor.Params.forList(pagedMax, (pagedOffset / pagedMax), rating, filterSortBy, filterDirectionSwap))
        }
    }

    fun filterDirectionSwap(){
        if(filterDirectionSwap.equals("DESC")){
            filterDirectionSwap = ("ASC")
        }else{
            filterDirectionSwap = ("DESC")
        }
        resetPagedParams()
        loadReviewList()
    }


    fun filterSortBy(){
        if(filterSortBy.equals("date_of_review")){
            filterSortBy = ("rating")
        }else{
            filterSortBy = ("date_of_review")
        }
        resetPagedParams()
        loadReviewList()
    }

    private fun resetPagedParams() {
        pagedOffset = 0
        donationMutableCollection = emptyList<Review>().toMutableList()
    }

    private fun needsLoadMoreData(): Boolean {
        return (donationMutableCollection == null || donationMutableCollection?.size == 0 || donationMutableCollection!!.size < pagedTotalCount) && !isLoading
    }

    override fun resume() {
        super.resume()
        if (this.view != null) {
            resetPagedParams()
            loadReviewList()
        }
    }

    override fun destroy() {
        super.destroy()
        this.getDonationListInteractor.dispose()
    }

    override fun showViewLoading() {
        super.showViewLoading()
        isLoading = true
    }

    override fun hideViewLoading() {
        super.hideViewLoading()
        isLoading = false
    }

    private fun updateDonationCollection(list: List<Review>) {
        if (donationMutableCollection == null) {
            donationMutableCollection = list.toMutableList()
            return
        }
        donationMutableCollection!!.addAll(list.toMutableList())
        donationMutableCollection = donationMutableCollection!!.distinct().toMutableList()
    }

    private fun updatePagedList(pagedList: PagedReviewList<Review>) {
        updateDonationCollection(pagedList.list)
        pagedOffset = donationMutableCollection!!.size
        pagedTotalCount = pagedList.total
        listHeaderReviewTotalValue?.totalReviewCount = pagedList.total.toString()
        hideViewLoading()
        renderSectionedCollection()
    }



    private fun renderSectionedCollection() {
        val list = donationMutableCollection!!.toList()
        if (list.isEmpty()) return this.view!!.showEmptyView()
        this.view!!.renderTransactionsSectioned(this.sectionedListItemDataMapper.transformTransactionsListWithHeader(list, listHeaderReviewTotalValue!!))
    }

    inner class ReviewListObserver : DefaultObservableObserver<PagedReviewList<Review>>() {
        override fun onComplete() {}

        override fun onNext(reviewList: PagedReviewList<Review>) {
            updatePagedList(reviewList)
        }

        override fun onError(e: Throwable) {
            parseError(e)
        }
    }

}