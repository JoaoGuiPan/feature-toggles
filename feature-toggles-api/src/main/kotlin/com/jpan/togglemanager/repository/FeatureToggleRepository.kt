package com.jpan.togglemanager.repository

import com.jpan.togglemanager.common.CreateRepository
import com.jpan.togglemanager.common.DeleteRepository
import com.jpan.togglemanager.common.ListRepository
import com.jpan.togglemanager.common.UpdateRepository
import com.jpan.togglemanager.model.FeatureToggle
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

interface FeatureToggleRepository: CrudRepository<FeatureToggle, String>

@Repository
data class FeatureToggleJpaRepository(
        private val repository: FeatureToggleRepository
): CreateRepository<FeatureToggle>, UpdateRepository<FeatureToggle>, DeleteRepository<FeatureToggle>,
        ListRepository<FeatureToggle> {

    override fun create(entity: FeatureToggle): FeatureToggle {
        entity.id = UUID.randomUUID().toString()
        return repository.save(entity)
    }

    override fun update(entity: FeatureToggle): FeatureToggle {
        return repository.save(entity)
    }

    override fun listAll(): List<FeatureToggle> {
        return repository.findAll().toList()
    }

    // assumption: archiving is basically a forced expiration
    override fun delete(entity: FeatureToggle) {
        entity.expiresOn = LocalDate.now().atStartOfDay().toLocalDate()
        repository.save(entity)
    }

}