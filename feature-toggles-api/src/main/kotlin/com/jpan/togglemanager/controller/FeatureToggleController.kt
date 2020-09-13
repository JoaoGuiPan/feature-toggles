package com.jpan.togglemanager.controller

import com.jpan.togglemanager.common.CONSTANTS.IS_PRODUCT_MANAGER
import com.jpan.togglemanager.common.CreateRepository
import com.jpan.togglemanager.common.DeleteRepository
import com.jpan.togglemanager.common.ListRepository
import com.jpan.togglemanager.common.UpdateRepository
import com.jpan.togglemanager.dto.FeatureToggleListDto
import com.jpan.togglemanager.dto.toDto
import com.jpan.togglemanager.model.FeatureToggle
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(value = "Feature toggles", description = "REST endpoints responsible for CRUD operations of feature toggles")
@RestController
@RequestMapping("features")
data class FeatureToggleController(
        private val createFeature: CreateRepository<FeatureToggle>,
        private val updateFeature: UpdateRepository<FeatureToggle>,
        private val archiveFeature: DeleteRepository<FeatureToggle>,
        private val listFeatures: ListRepository<FeatureToggle>
) {

    val logger: Logger = LoggerFactory.getLogger(FeatureToggleController::class.java)

    @ApiOperation(value = "Create feature. User role: PM")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PostMapping
    @PreAuthorize(IS_PRODUCT_MANAGER)
    fun create(@RequestBody @Valid payload: FeatureToggle): FeatureToggle {
        logger.debug("Creating new Feature Toggle - $payload")
        val created = createFeature.create(payload)
        logger.info("Feature Toggle created: ${created.technicalName}")
        return created
    }

    @ApiOperation(value = "Fetch all features. User role: PM")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping
    @PreAuthorize(IS_PRODUCT_MANAGER)
    fun list(): List<FeatureToggleListDto> {
        logger.info("Fetching all Feature Toggles")
        return listFeatures.listAll().toDto()
    }

    @ApiOperation(value = "Update feature. User role: PM")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @PutMapping("{feature}")
    @PreAuthorize(IS_PRODUCT_MANAGER)
    fun update(@PathVariable feature: FeatureToggle, @RequestBody @Valid payload: FeatureToggle): FeatureToggle {
        logger.debug("Updating Feature Toggle " + feature.technicalName)
        val updated = updateFeature.update(
                // avoids technicalName rewrite by the client
                feature.copy(
                        displayName = payload.displayName,
                        expiresOn = payload.expiresOn,
                        description = payload.description,
                        inverted = payload.inverted,
                        customerIds = payload.customerIds
                )
        )
        logger.info("Feature Toggle updated - $updated")
        return updated
    }

    @ApiOperation(value = "Fetch feature by id. User role: PM")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping("{feature}")
    @PreAuthorize(IS_PRODUCT_MANAGER)
    fun getById(@PathVariable feature: FeatureToggle): FeatureToggle {
        logger.info("Fetching Feature Toggle ${feature.technicalName}")
        return feature
    }

    @ApiOperation(value = "Archive feature. User role: PM")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @DeleteMapping("{feature}")
    @PreAuthorize(IS_PRODUCT_MANAGER)
    fun archive(@PathVariable feature: FeatureToggle) {
        logger.debug("Archiving Feature Toggle ${feature.technicalName}")
        // assumption: archiving is a logical delete
        archiveFeature.delete(feature)
        logger.info("Feature Toggle archived, ${feature.technicalName}")
    }
}