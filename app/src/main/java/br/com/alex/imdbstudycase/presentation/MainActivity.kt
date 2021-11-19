package br.com.alex.imdbstudycase.presentation

import android.os.Bundle
import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.router.FeatureRouter
import br.com.alex.imdbstudycase.router.actions.OpenHomeAction
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val featureRouter: FeatureRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureRouter.start(this, OpenHomeAction)
    }

    override fun launchFragment() {}

    override fun configureToolbar() {}
}