package com.jpan.togglemanager.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class FeatureToggle(
        @Id
        var id: String? = null,

        @NotNull
        @NotBlank
        @Column(unique = true)
        var technicalName: String? = null,

        var displayName: String? = null,

        var expiresOn: LocalDate? = null,

        var description: String? = null,

        var inverted: Boolean = false,

        @NotBlank
        @CollectionTable
        @ElementCollection(fetch = FetchType.EAGER)
        var customerIds: MutableSet<String>? = null
)
