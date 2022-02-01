package br.com.alex.imdbstudycase.core

import androidx.annotation.RestrictTo
import androidx.fragment.app.Fragment
import br.com.alex.imdbstudycase.core.presentation.BaseActivity

@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentTestActivity : BaseActivity() {

    override fun launchFragment() {}

    override fun configureToolbar() {}

    fun setupFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}