package com.jpan.togglemanager.controller

import com.jpan.togglemanager.dto.ListCustomerFeaturesDto
import com.jpan.togglemanager.dto.RequestListCustomerFeaturesDto
import com.jpan.togglemanager.service.RequestFeaturesService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(value = "Feature toggles API", description = "REST API for feature requests")
@RestController
@RequestMapping("api/v1/features")
data class FeaturesApiController(
        private val requestFeatures: RequestFeaturesService
) {

    val logger: Logger = LoggerFactory.getLogger(FeaturesApiController::class.java)

    @ApiOperation(value = "Query features given a customer")
    @PostMapping
    fun requestFeatures(@RequestBody @Valid payload: RequestListCustomerFeaturesDto): ListCustomerFeaturesDto {
        logger.info("Query features for customer - ${payload.featureRequest.customerId}")
        val list = requestFeatures.requestFeatures(payload)
        logger.debug("Query response: - $list")
        return list
    }
}