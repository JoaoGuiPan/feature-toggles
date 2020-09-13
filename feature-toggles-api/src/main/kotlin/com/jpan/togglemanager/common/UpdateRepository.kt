package com.jpan.togglemanager.common

interface UpdateRepository<T> {
    fun update(entity: T): T
}