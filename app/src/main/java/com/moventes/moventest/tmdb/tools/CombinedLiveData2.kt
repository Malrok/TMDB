package com.moventes.moventest.tmdb.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData


class CombinedLiveData2<A, B>(ld1: LiveData<A>, ld2: LiveData<B>) : MediatorLiveData<Pair<A, B>>() {
    private var a: A? = null
    private var b: B? = null

    init {
        super.addSource(ld1) { a ->
            if (a != null) {
                this.a = a
            }
            updateValue()
        }

        super.addSource(ld2) { b ->
            if (b != null) {
                this.b = b
            }
            updateValue()
        }
    }

    private fun updateValue() {
        if (a != null && b != null) {
            value = Pair(a!!, b!!)
        }
    }
}