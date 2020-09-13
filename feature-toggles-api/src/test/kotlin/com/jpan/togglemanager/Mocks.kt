package com.jpan.togglemanager

import com.jpan.togglemanager.model.FeatureToggle
import java.time.LocalDate

const val CUSTOMER_ONE = "1"
const val CUSTOMER_TWO = "2"
const val CUSTOMER_THREE = "3"
const val CUSTOMER_FOUR = "4"

const val FEATURE_A = "feature-a"
const val FEATURE_B = "feature-b"
const val FEATURE_C = "feature-c"
const val FEATURE_D = "feature-d"
const val FEATURE_E = "feature-e"
const val FEATURE_F = "feature-f"
const val FEATURE_G = "feature-g"
const val FEATURE_H = "feature-h"

object Mocks {

    val customerOneFeatureNotExpired = FeatureToggle(
            technicalName = FEATURE_A,
            expiresOn = LocalDate.of(2222, 9, 30),
            customerIds = mutableSetOf(CUSTOMER_ONE)
    )

    val customerOneFeatureInvertedNotExpired = FeatureToggle(
            technicalName = FEATURE_B,
            expiresOn = LocalDate.of(2222, 9, 30),
            inverted = true,
            customerIds = mutableSetOf(CUSTOMER_ONE)
    )

    val customerTwoFeatureNotExpired = FeatureToggle(
            technicalName = FEATURE_C,
            expiresOn = LocalDate.of(2222, 9, 30),
            customerIds = mutableSetOf(CUSTOMER_TWO)
    )

    val customerTwoFeatureExpired = FeatureToggle(
            technicalName = FEATURE_D,
            expiresOn = LocalDate.of(2019, 9, 30),
            customerIds = mutableSetOf(CUSTOMER_TWO)
    )

    val customerThreeFeatureNotExpired = FeatureToggle(
            technicalName = FEATURE_E,
            expiresOn = LocalDate.of(2222, 9, 30),
            customerIds = mutableSetOf(CUSTOMER_THREE)
    )

    val customerThreeFeatureInvertedNotExpired = FeatureToggle(
            technicalName = FEATURE_F,
            expiresOn = LocalDate.of(2222, 9, 30),
            inverted = true,
            customerIds = mutableSetOf(CUSTOMER_THREE)
    )

    val customerFourFeatureNotExpired = FeatureToggle(
            technicalName = FEATURE_G,
            expiresOn = LocalDate.of(2222, 9, 30),
            customerIds = mutableSetOf(CUSTOMER_FOUR)
    )

    val customerFourFeatureExpired = FeatureToggle(
            technicalName = FEATURE_H,
            expiresOn = LocalDate.of(2019, 9, 30),
            customerIds = mutableSetOf(CUSTOMER_FOUR)
    )
}