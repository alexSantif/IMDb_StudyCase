package br.com.alex.imdbstudycase.core.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alex.imdbstudycase.core.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        launchFragment()
        configureToolbar()
    }

    abstract fun launchFragment()

    abstract fun configureToolbar()
}