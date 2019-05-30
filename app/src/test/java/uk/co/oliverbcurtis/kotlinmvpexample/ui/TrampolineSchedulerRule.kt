package uk.co.oliverbcurtis.kotlinmvpexample.ui

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TrampolineSchedulerRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return MyStatement(base)
    }

    inner class MyStatement(private val base: Statement) : Statement() {

        @Throws(Throwable::class)
        override fun evaluate() {
            try {
                RxJavaPlugins.setComputationSchedulerHandler { scheduler -> Schedulers.trampoline() }
                RxJavaPlugins.setIoSchedulerHandler { scheduler -> Schedulers.trampoline() }
                RxJavaPlugins.setNewThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
                RxJavaPlugins.setSingleSchedulerHandler { scheduler -> Schedulers.trampoline() }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
                base.evaluate()
            } finally {
                RxJavaPlugins.reset()
                RxAndroidPlugins.reset()
            }
        }
    }
}