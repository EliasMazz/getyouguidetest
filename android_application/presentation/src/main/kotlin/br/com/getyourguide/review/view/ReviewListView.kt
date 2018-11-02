package br.com.getyourguide.review.view

import br.com.getyourguide.review.model.SectionedListItem

interface ReviewListView : LoadDataView{

    fun renderTransactionsSectioned(transactions: Collection<SectionedListItem>)

    fun showEmptyView()

    fun onReviewFilter(rating: Int)
}