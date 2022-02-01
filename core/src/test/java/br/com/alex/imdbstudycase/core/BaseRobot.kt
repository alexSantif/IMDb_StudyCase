package br.com.alex.imdbstudycase.core

import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario

fun <T: Fragment> launchFragment(
    fragment: T,
    block: OnActivityScope<T>
) {
    val scenario = ActivityScenario.launch(SingleFragmentTestActivity::class.java)
    scenario.onActivity {
        it.setupFragment(fragment)
        block(fragment)
        it.finish()
    }
    scenario.close()
}

typealias OnActivityScope<T> = T.() -> Unit