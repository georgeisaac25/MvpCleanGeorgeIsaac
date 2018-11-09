package com.example.georgeissac.mvp.domain.interfaces

/**
 * Data mapper used to map database entities to model classes and vice versa.
 */
interface CountryModelMapper<E, M> {
    fun fromEntity(fromList: E): M
    fun toEntity(fromList: M): E
}