package br.com.getyourguide.review.internal.di.modules

import android.content.Context
import android.content.SharedPreferences
import br.com.getyourguide.review.AndroidApplication
import br.com.getyourguide.review.UIThread
import br.com.getyourguide.review.data.executor.JobExecutor
import br.com.getyourguide.review.domain.executor.PostExecutionThread
import br.com.getyourguide.review.domain.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ApplicationModule(private val androidApplication: AndroidApplication) {

    @Provides
    @Singleton
    fun application(): AndroidApplication {
        return androidApplication
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return androidApplication.applicationContext
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return androidApplication.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

}
