package com.task.mina.musicapp.base.domain.interactor

import io.reactivex.Observable

abstract class ObservableUseCase<in Params, Type> where Type : Any {

    abstract fun build(params: Params): Observable<Type>
}