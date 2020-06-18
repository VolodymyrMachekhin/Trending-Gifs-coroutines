package com.vmac.giphy.common.utils.ui

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

abstract class BaseViewModel : ViewModel(), Observable {
    @Transient
    private var mCallbacks: PropertyChangeRegistry? = null

    fun <T> BaseViewModel.observable(initialValue: T, br: Int)
            : ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {

        override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean {
            return true
        }

        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
            if (oldValue != newValue) {
                this@observable.notifyPropertyChanged(br)
            }
        }
    }

    private fun notifyPropertyChanged(fieldId: Int) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks!!.notifyCallbacks(this, fieldId, null)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                mCallbacks = PropertyChangeRegistry()
            }
        }
        mCallbacks?.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (mCallbacks == null) {
                return
            }
        }
        mCallbacks?.remove(callback)
    }
}