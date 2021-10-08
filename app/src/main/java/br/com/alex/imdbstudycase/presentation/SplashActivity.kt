package br.com.alex.imdbstudycase.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.alex.imdbstudycase.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportFragmentManager.beginTransaction()
            .replace(R.id.splash_container, SplashFragment.newInstance())
            .setReorderingAllowed(true)
            .addToBackStack("splash")
            .commit()
    }
}