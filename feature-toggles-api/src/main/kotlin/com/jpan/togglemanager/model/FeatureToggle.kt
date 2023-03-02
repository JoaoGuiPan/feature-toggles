package com.jpan.togglemanager.model

import jdk.jfr.Timestamp
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Suppress("com.haulmont.jpb.DataClassEqualsAndHashCodeInspection") // kotlin data class already provides equals and hashcode
@Entity
data class FeatureToggle(
        @Id
        var id: String? = null,

        @NotNull
        @NotBlank
        @Column(unique = true)
        var technicalName: String? = null,

        var displayName: String? = null,

        @Timestamp
        @FutureOrPresent
        var expiresOn: LocalDateTime? = null,

        var description: String? = null,

        var inverted: Boolean = false,

        @NotBlank
        @CollectionTable
        @ElementCollection(fetch = FetchType.EAGER)
        var customerIds: MutableSet<String>? = null
)
