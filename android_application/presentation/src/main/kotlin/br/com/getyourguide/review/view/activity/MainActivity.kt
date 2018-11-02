package br.com.getyourguide.review.view.activity

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.View
import br.com.getyourguide.review.R
import br.com.getyourguide.review.view.component.OnSingleClickListener
import br.com.getyourguide.review.view.fragment.ReviewFilterDialogFragment
import br.com.getyourguide.review.view.fragment.ReviewFragment

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton



class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, ReviewFilterDialogFragment.Callback {

    private lateinit var mBottomDrawerBehavior: BottomSheetBehavior<View>
    private var fragmentTransactionAttach: Boolean = false
    private var reviewFragment = ReviewFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(reviewFragment, R.id.frame_layout_container_activity_main)
        fragmentTransactionAttach = true
        configureViews()
    }


    override fun onReviewFilterTip(ratingStar: Int) {
        reviewFragment.onReviewFilter(ratingStar)
    }

    private fun configureViews() {
        setSupportActionBar(bottom_map_bar_activity_main)
        ViewCompat.setElevation(this.fab_activity_main, 4f)
        ViewCompat.setElevation(this.bottom_map_bar_activity_main, 8f)
        ViewCompat.setElevation(this.bottom_drawer_activity_main, 16f)

        mBottomDrawerBehavior = BottomSheetBehavior.from(this.bottom_drawer_activity_main)
        mBottomDrawerBehavior.isHideable = true
        mBottomDrawerBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        mBottomDrawerBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (BottomSheetBehavior.STATE_COLLAPSED == newState || BottomSheetBehavior.STATE_HIDDEN == newState) {
                    showFab()
                } else if (BottomSheetBehavior.STATE_EXPANDED == newState) {
                    hideFab()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })

        this.navigation_view_acitivity_main.setNavigationItemSelectedListener(this)


        val fabListener: View.OnClickListener = View.OnClickListener {
            showReviewFilterDialogFragment()
        }

        //fab bug material design - sometimes clicking 2 times
        this.fab_activity_main.setOnClickListener(OnSingleClickListener.wrap(fabListener))

        this.bottom_drawer_activity_main.setOnClickListener {
            hideBottomDrawer()
            showFab()
        }
        this.bottom_map_bar_activity_main.setNavigationOnClickListener {
            mBottomDrawerBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
    }


    private fun showFab() {
        if (fragmentTransactionAttach)
            this.fab_activity_main!!.show()
    }

    private fun hideFab() {
        this.fab_activity_main!!.hide()
    }

    private fun showReviewFilterDialogFragment() {
        val reviewFilterDialogFragment = ReviewFilterDialogFragment()
        reviewFilterDialogFragment.show(supportFragmentManager, "ReviewFilterDialogFragment")
    }

    private fun hideBottomDrawer(): Boolean {

        if (mBottomDrawerBehavior.state !== BottomSheetBehavior.STATE_HIDDEN) {
            mBottomDrawerBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            return true
        }
        return false
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        hideBottomDrawer()

        when (item.itemId) {
            R.id.nav_review -> {
                replaceFragment(reviewFragment, R.id.frame_layout_container_activity_main)
            }
            R.id.nav_quit -> {
                val alert = alert(getString(R.string.main_alert_message), getString(R.string.main_alert_title)) {
                    yesButton { }
                    noButton { it.dismiss() }
                }.show()

                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.cyan))
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.cyan))
            }
        }

        return true
    }

}