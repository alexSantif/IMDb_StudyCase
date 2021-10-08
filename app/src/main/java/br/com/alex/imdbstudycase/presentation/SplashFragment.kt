package br.com.alex.imdbstudycase.presentation

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.alex.imdbstudycase.R
import br.com.alex.imdbstudycase.router.FeatureRouter
import br.com.alex.imdbstudycase.router.actions.OpenHomeAction
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {

    private val featureRouter: FeatureRouter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCountDown()
    }

    private fun setCountDown() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                featureRouter.start(requireActivity(), OpenHomeAction)
            }
        }.start()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }
}