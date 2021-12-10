package br.com.alex.imdbstudycase.core.navigation

import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner

inline val ComponentActivity.lifecycleOwner: LifecycleOwner
    get() = this
