package br.com.getyourguide.review.internal.di.component

import android.app.Application
import br.com.getyourguide.review.AndroidApplication
import br.com.getyourguide.review.internal.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidSupportInjectionModule::class),
            (ApplicationModule::class),
            (NetworkModule::class),
            (RepositoryModule::class),
            (ActivityModule::class),
            (FragmentModule::class),
            (ServiceModule::class)
        ])

interface ApplicationComponent {

    fun inject(androidApplication: AndroidApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun appModule(applicationModule: ApplicationModule): Builder
        fun build(): ApplicationComponent
    }
}
