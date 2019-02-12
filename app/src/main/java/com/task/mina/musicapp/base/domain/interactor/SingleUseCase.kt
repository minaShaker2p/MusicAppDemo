package com.task.mina.musicapp.base.domain.interactor

import io.reactivex.Single

abstract class SingleUseCase<in Params, Type> where Type : Any {

    abstract fun build(params: Params): Single<Type>
}