package com.task.mina.musicapp.base.presentation.model

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import com.task.mina.musicapp.base.domain.exception.MusicAppException

class ObservableResource<T> : SingleLiveEvent<T>() {

    val error: SingleLiveEvent<MusicAppException> = ErrorLiveData()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun observe(
        owner: LifecycleOwner, successObserver: Observer<T>,
        loadingObserver: Observer<Boolean>? = null,
        commonErrorObserver: Observer<MusicAppException>,
        httpErrorConsumer: Observer<MusicAppException>? = null,
        networkErrorConsumer: Observer<MusicAppException>? = null,
        unExpectedErrorConsumer: Observer<MusicAppException>? = null,
        serverDownErrorConsumer: Observer<MusicAppException>? = null,
        timeOutErrorConsumer: Observer<MusicAppException>? = null,
        unAuthorizedErrorConsumer: Observer<MusicAppException>? = null
    ) {
        super.observe(owner, successObserver)
        loadingObserver?.let { loading.observe(owner, it) }
        (error as ErrorLiveData).observe(
            owner, commonErrorObserver, httpErrorConsumer, networkErrorConsumer, unExpectedErrorConsumer,
            serverDownErrorConsumer, timeOutErrorConsumer, unAuthorizedErrorConsumer
        )
    }


    class ErrorLiveData : SingleLiveEvent<MusicAppException>() {
        private var ownerRef: LifecycleOwner? = null
        private var httpErrorConsumer: Observer<MusicAppException>? = null
        private var networkErrorConsumer: Observer<MusicAppException>? = null
        private var unExpectedErrorConsumer: Observer<MusicAppException>? = null
        private var commonErrorConsumer: Observer<MusicAppException>? = null

        private var serverDownErrorConsumer: Observer<MusicAppException>? = null
        private var timeOutErrorConsumer: Observer<MusicAppException>? = null
        private var unAuthorizedErrorConsumer: Observer<MusicAppException>? = null

        override fun setValue(value: MusicAppException?) {
            ownerRef?.also {
                removeObservers(it)
                value?.let { vale -> addProperObserver(vale) }
                super.setValue(value)
            }

        }

        override fun postValue(value: MusicAppException) {
            ownerRef?.also {
                removeObservers(it)
                addProperObserver(value)
                super.postValue(value)
            }

        }

        private fun addProperObserver(value: MusicAppException) {
            when (value.kind) {
                MusicAppException.Kind.NETWORK -> networkErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)
                MusicAppException.Kind.HTTP -> httpErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)
                MusicAppException.Kind.UNEXPECTED -> unExpectedErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                MusicAppException.Kind.SERVER_DOWN -> serverDownErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                MusicAppException.Kind.TIME_OUT -> timeOutErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                MusicAppException.Kind.UNAUTHORIZED -> unAuthorizedErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                else -> {
                }
            }
        }


        fun observe(
            owner: LifecycleOwner, commonErrorConsumer: Observer<MusicAppException>,
            httpErrorConsumer: Observer<MusicAppException>? = null,
            networkErrorConsumer: Observer<MusicAppException>? = null,
            unExpectedErrorConsumer: Observer<MusicAppException>? = null,

            serverDownErrorConsumer: Observer<MusicAppException>? = null,
            timeOutErrorConsumer: Observer<MusicAppException>? = null,
            unAuthorizedErrorConsumer: Observer<MusicAppException>? = null
        ) {
            super.observe(owner, commonErrorConsumer)
            this.ownerRef = owner
            this.commonErrorConsumer = commonErrorConsumer
            this.httpErrorConsumer = httpErrorConsumer
            this.networkErrorConsumer = networkErrorConsumer
            this.unExpectedErrorConsumer = unExpectedErrorConsumer
            this.serverDownErrorConsumer = serverDownErrorConsumer
            this.timeOutErrorConsumer = timeOutErrorConsumer
            this.unAuthorizedErrorConsumer = unAuthorizedErrorConsumer
        }
    }
}