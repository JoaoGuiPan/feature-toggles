package com.jpan.togglemanager.common

interface DeleteRepository<T> {
    fun delete(entity: T)
}