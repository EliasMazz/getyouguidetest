package br.com.getyourguide.review.internal.di.modules

import br.com.getyourguide.review.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun bindAndroidInjectorReviewFragment(): ReviewFragment

    @ContributesAndroidInjector
    internal abstract fun bindAndroidInjectorReviewFilterDialogFragment(): ReviewFilterDialogFragment

    @ContributesAndroidInjector
    internal abstract fun bindAndroidInjectorGlide(): com.bumptech.glide.manager.SupportRequestManagerFragment

}