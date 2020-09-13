package com.jpan.togglemanager.dto

import com.jpan.togglemanager.model.FeatureToggle

fun FeatureToggle.toDto(customerId: String): FeatureToggleDto {

    val featureToggleDto = FeatureToggleDto(
            technicalName = this.technicalName,
            expiresOn = this.expiresOn,
            customerIds = this.customerIds!!
    )

    if (!featureToggleDto.isExpired()) {

        featureToggleDto.active = !this.inverted
                && featureToggleDto.customerIds.contains(customerId)

        featureToggleDto.inverted = this.inverted
                && featureToggleDto.customerIds.contains(customerId)
    } else {

        featureToggleDto.active = !this.inverted

        // assumption: same logic as active
        featureToggleDto.inverted = this.inverted
    }

    return featureToggleDto
}

fun List<FeatureToggle>.toDto(): List<FeatureToggleListDto> = this.map {
    FeatureToggleListDto(it.id, it.technicalName, it.displayName)
}