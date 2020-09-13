package com.jpan.togglemanager.controller

import com.jpan.togglemanager.common.CONSTANTS.IS_PRODUCT_MANAGER
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@Api(value = "Authentication", description = "Endpoint for checking authentication credentials")
@RestController
@RequestMapping("auth")
class AuthenticationController {

    @ApiOperation(value = "Check user credentials. User role: PM")
    @ApiImplicitParams(
            ApiImplicitParam(name = "Authorization", value = "Basic *encoded user info*", required = false, paramType = "header", dataType = "string")
    )
    @GetMapping
    @PreAuthorize(IS_PRODUCT_MANAGER)
    fun validateLogin(): HttpStatus {
        return HttpStatus.OK
    }
}