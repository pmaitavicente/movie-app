package com.pmaita.mov.domain.model.map

abstract class Mapper<M, P> {

    abstract fun map(model: M): P
    abstract fun inverseMap(model: P): M

}