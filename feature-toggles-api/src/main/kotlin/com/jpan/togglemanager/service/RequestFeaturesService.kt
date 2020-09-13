package com.jpan.togglemanager.service

import com.jpan.togglemanager.common.ListRepository
import com.jpan.togglemanager.dto.ListCustomerFeaturesDto
import com.jpan.togglemanager.dto.RequestListCustomerFeaturesDto
import com.jpan.togglemanager.dto.toDto
import com.jpan.togglemanager.model.FeatureToggle
import org.springframework.stereotype.Service

@Service
data class RequestFeaturesService(
        private val listFeatures: ListRepository<FeatureToggle>
) {

    fun requestFeatures(payload: RequestListCustomerFeaturesDto): ListCustomerFeaturesDto {

        val allFeatures = listFeatures.listAll()

        val customerId = payload.featureRequest.customerId

        val featureIds = payload.featureRequest.features.map { it.technicalName }

        val customerFeatures = allFeatures.filter { featureIds.contains(it.technicalName!!) }

        return ListCustomerFeaturesDto(customerFeatures.map { it.toDto(customerId!!) })
    }
}