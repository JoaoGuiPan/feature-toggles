package com.jpan.togglemanager.common

interface ListRepository<T> {
    fun listAll(): List<T>
}