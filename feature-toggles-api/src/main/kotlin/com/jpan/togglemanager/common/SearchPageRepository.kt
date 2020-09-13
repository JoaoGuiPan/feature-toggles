package com.jpan.togglemanager.common

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SearchPageRepository<F, T> {
    fun filterBy(filter: F, pageable: Pageable): Page<T>
}