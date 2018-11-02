package br.com.getyourguide.review.view.fragment

import android.os.Bundle
import android.view.*
import br.com.getyourguide.review.ListScrollView
import br.com.getyourguide.review.R
import br.com.getyourguide.review.model.SectionedListItem
import br.com.getyourguide.review.presenter.ReviewListPresenter
import br.com.getyourguide.review.view.ReviewListView
import br.com.getyourguide.review.view.adapter.ListLayoutManager
import br.com.getyourguide.review.view.adapter.ReviewListAdapter
import br.com.getyourguide.review.view.listner.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_review.*
import kotlinx.android.synthetic.main.layout_empty_list.*
import javax.inject.Inject


class ReviewFragment : BaseToolbarFragment(), ReviewListView, ListScrollView, ReviewFilterDialogFragment.Callback  {

    @Inject
    lateinit var reviewListAdapter: ReviewListAdapter

    @Inject
    lateinit var reviewListPresenter: ReviewListPresenter

    private lateinit var endlessScrollListener: EndlessScrollListener


    override val fragmentLayout: Int
        get() = R.layout.fragment_review

    override val actionBarTitleResource: Int
        get() = R.string.review_toolbar_title

    private lateinit var mailReportMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reviewListPresenter.view(this)
        setupRecyclerView()

        if (savedInstanceState == null)
            loadMore()
    }


    override fun onReviewFilter(rating: Int) {
        this.reviewListPresenter.filterRating(rating)
    }

    override fun onResume() {
        super.onResume()
        this.reviewListPresenter.resume()
        loadMore()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        this.recycler_view_review.adapter = null
    }

    override fun showLoading() {
        if (!this.swiperefreshlayout_all.isRefreshing) {
            super.showLoading()
            hideEmptyView()
        }
    }

    override fun loadMore() {
        this.reviewListPresenter.loadReviewList()
    }

    private fun showDialog() {
        val reviewFilterDialogFragment = ReviewFilterDialogFragment()
        reviewFilterDialogFragment.setTargetFragment(this@ReviewFragment,300)
        reviewFilterDialogFragment.show(fragmentManager, "ReviewDialogFragment")
    }


    override fun hideLoading() {
        super.hideLoading()
        this.swiperefreshlayout_all.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.fragment_review_bottom_app_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fragment_review_filter -> filterSortBy()
            R.id.menu_fragment_review_swap -> filterDirectionSwap()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun filterDirectionSwap() {
        reviewListPresenter.filterDirectionSwap()
    }

    private fun filterSortBy() {
        reviewListPresenter.filterSortBy()
    }


    private fun setupRecyclerView() {
        this.swiperefreshlayout_all.setOnRefreshListener {
            this.reviewListPresenter.loadReviewList()
        }
        val layoutManager = ListLayoutManager(context()!!)
        this.endlessScrollListener = EndlessScrollListener(layoutManager)
        this.endlessScrollListener.view(this)
        this.recycler_view_review.layoutManager = layoutManager
        this.recycler_view_review.adapter = reviewListAdapter
        this.text_empty_list.text = resources.getText(R.string.review_empty_view_title)
        this.recycler_view_review.addOnScrollListener(endlessScrollListener)
    }

    override fun onReviewFilterTip(ratingStar: Int) {
        reviewListPresenter.filterRating(ratingStar)
    }

    override fun renderTransactionsSectioned(transactions: Collection<SectionedListItem>) {
        this.reviewListAdapter.setItemsCollection(transactions)
    }

    private fun hideEmptyView() {
        this.linearlayout_empty_list.visibility = View.GONE
        showMainContainer()
    }

    override fun showEmptyView() {
        this.linearlayout_empty_list.visibility = View.VISIBLE
        hideMainContainer()
    }
    override fun onPause() {
        super.onPause()
        this.reviewListPresenter.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.reviewListPresenter.destroy()
    }
}



