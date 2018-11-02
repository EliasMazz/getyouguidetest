package br.com.getyourguide.review.view.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * Base class for every Activity in this application.
 * @see android.support.v7.app.AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity(){

    /**
     * Adds a [Fragment] to this activity's layout.
     *
     * @param fragment The fragment to be added.
     * @param containerViewId The container view to where add the fragment.
     */
    fun AppCompatActivity.addFragment(fragment: Fragment, containerViewId: Int) {
        supportFragmentManager.inTransaction { add(containerViewId, fragment) }
    }

    /**
     * Replace a [Fragment] in this activity's layout.
     *
     * @param fragment The fragment to be added.
     * @param containerViewId The container view to where add the fragment.
     */
    fun AppCompatActivity.replaceFragment(fragment: Fragment, containerViewId: Int) {
        supportFragmentManager.inTransaction { replace(containerViewId, fragment) }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}