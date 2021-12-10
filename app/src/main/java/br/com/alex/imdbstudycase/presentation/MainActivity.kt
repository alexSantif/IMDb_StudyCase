package br.com.alex.imdbstudycase.presentation

import android.os.Bundle
import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.navigateTo()
        finish()
    }

    override fun launchFragment() {}

    override fun configureToolbar() {}
}