package uk.co.oliverbcurtis.kotlinmvpexample.dagger

/*
Below is where we specify our app modules, so that Dagger knows where to look for the @Provides annotation

We also need to specify our activities here so Dagger knows where to inject
(if you open BaseActivity, you will see the keyword @Inject at the top. This is where you'll have your objects that
you don't need to initialise.
 */

import javax.inject.Singleton

import dagger.Component
import uk.co.oliverbcurtis.kotlinmvpexample.ui.BaseActivity


//Component binds our dependencies
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(application: DaggerApplication)

    fun inject(baseActivity: BaseActivity)
}
