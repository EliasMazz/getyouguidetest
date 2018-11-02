package br.com.getyourguide.review

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.multidex.MultiDexApplication
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import br.com.getyourguide.review.internal.di.component.ApplicationComponent
import br.com.getyourguide.review.internal.di.component.DaggerApplicationComponent
import br.com.getyourguide.review.internal.di.modules.ApplicationModule
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import javax.inject.Inject

class AndroidApplication : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector, Application.ActivityLifecycleCallbacks {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @set:VisibleForTesting
    private
    lateinit var component: ApplicationComponent

    private val fragmentLifecycleCallbacks: FragmentManager.FragmentLifecycleCallbacks
        get() =
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, b: Bundle?) {
                    AndroidSupportInjection.inject(f)
                }
            }

    init {
        registerActivityLifecycleCallbacks(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setupDagger()
        setupLoggers()
        initializeLeakDetection()
        initCrashlytics()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
    override fun onActivityCreated(activity: Activity?, p1: Bundle?) = handleActivity(activity)
    override fun onActivityPaused(p0: Activity?) {}
    override fun onActivityResumed(p0: Activity?) {}
    override fun onActivityStarted(p0: Activity?) {}
    override fun onActivityDestroyed(p0: Activity?) {}
    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}
    override fun onActivityStopped(p0: Activity?) {}

    private fun createComponent(): ApplicationComponent {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .appModule(ApplicationModule(this))
                .build()

    }

    private fun setupDagger() {
        component = createComponent()
        component.inject(this)
    }

    private fun setupLoggers() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initCrashlytics() {
        val core = CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build()

        Fabric.with(this, Crashlytics.Builder().core(core).build())
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }

    private fun handleActivity(activity: Activity?) {
        if (activity is AppCompatActivity) AndroidInjection.inject(activity)
        if (activity is FragmentActivity) {
            activity
                    .supportFragmentManager
                    .registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
        }

    }
}