package com.jpan.togglemanager.common

interface CreateRepository<T> {
    fun create(entity: T): T
}