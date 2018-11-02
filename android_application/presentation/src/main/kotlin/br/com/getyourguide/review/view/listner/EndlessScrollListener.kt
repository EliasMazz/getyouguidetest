package br.com.getyourguide.review.view.listner

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import br.com.getyourguide.review.ListScrollView

class EndlessScrollListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private lateinit var scrollView: ListScrollView
    private var layoutManager: RecyclerView.LayoutManager = layoutManager

    private val visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true
    private val startingPageIndex = 0

    fun view(scrollView: ListScrollView) {
        this.scrollView = scrollView
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && (totalItemCount >= previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            loading = onLoadMore()
        }
    }

    private fun onLoadMore(): Boolean {
        this.scrollView.loadMore()
        return true
    }

}