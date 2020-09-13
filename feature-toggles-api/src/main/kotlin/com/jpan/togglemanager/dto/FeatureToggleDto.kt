package com.jpan.togglemanager.dto

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSetter
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

// used by the list page
data class FeatureToggleListDto(
        var id: String? = null,
        var technicalName: String? = null,
        var displayName: String? = null
)

data class ListCustomerFeaturesDto(
        var features: List<FeatureToggleDto> = emptyList()
)

data class RequestListCustomerFeaturesDto(
        var featureRequest: FeatureRequestDto
)

data class FeatureRequestDto (
        @field:NotNull
        var customerId: String? = null,

        @field:NotEmpty
        var features: List<FeatureToggleDto> = emptyList()
)

data class FeatureToggleDto (
        @JsonIgnore
        @field:NotNull
        @field:NotBlank
        var technicalName: String? = null,

        var active: Boolean = false,

        var inverted: Boolean = false,

        @JsonIgnore
        var expiresOn: LocalDate? = null,

        @JsonIgnore
        var customerIds: Set<String> = emptySet()
) {

    @JsonGetter
    fun getName(): String {
        return technicalName!!
    }

    @JsonSetter
    fun setName(name: String) {
        technicalName = name
    }

    @JsonGetter
    fun isExpired(): Boolean {
        if (expiresOn != null) {
            return LocalDate.now() >= expiresOn!!
        }
        return false
    }
}