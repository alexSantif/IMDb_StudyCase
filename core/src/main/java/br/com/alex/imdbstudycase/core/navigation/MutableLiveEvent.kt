package br.com.alex.imdbstudycase.core.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

typealias LiveEvent<T> = LiveData<Event<T>>

fun <T> MutableLiveEvent<T>.publish(value: T? = null) {
    this.value = Event(value)
}
