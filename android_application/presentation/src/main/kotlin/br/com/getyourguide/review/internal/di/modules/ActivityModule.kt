package br.com.getyourguide.review.internal.di.modules

import br.com.getyourguide.review.view.activity.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector (modules = [(FragmentModule::class)])
    internal abstract fun bindAndroidInjectorMainActivity(): MainActivity


}