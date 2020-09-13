package com.jpan.togglemanager.service

import com.jpan.togglemanager.*
import com.jpan.togglemanager.dto.FeatureRequestDto
import com.jpan.togglemanager.dto.FeatureToggleDto
import com.jpan.togglemanager.dto.RequestListCustomerFeaturesDto
import com.jpan.togglemanager.repository.FeatureToggleJpaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RequestFeaturesServiceTest {

    @Mock
    var listFeaturesRepository: FeatureToggleJpaRepository? = null

    @Test
    fun `When Requesting Features For Customer One Then Return Valid Flags`() {

        Mockito
            .`when`(listFeaturesRepository!!.listAll())
            .thenReturn(listOf(
                    Mocks.customerOneFeatureNotExpired,
                    Mocks.customerOneFeatureInvertedNotExpired,
                    Mocks.customerTwoFeatureNotExpired,
                    Mocks.customerTwoFeatureExpired,
                    Mocks.customerThreeFeatureNotExpired,
                    Mocks.customerThreeFeatureInvertedNotExpired,
                    Mocks.customerFourFeatureNotExpired,
                    Mocks.customerFourFeatureExpired
            ))

        val featuresService = RequestFeaturesService(listFeaturesRepository!!)

        val features = listOf(
                FeatureToggleDto(FEATURE_A),
                FeatureToggleDto(FEATURE_B),
                FeatureToggleDto(FEATURE_C),
                FeatureToggleDto(FEATURE_D)
        )

        val requestDto = RequestListCustomerFeaturesDto(
                FeatureRequestDto(CUSTOMER_ONE, features)
        )

        val listFeatures = featuresService.requestFeatures(requestDto).features

        assertEquals(true, listFeatures[0].active)
        assertEquals(false, listFeatures[0].inverted)
        assertEquals(false, listFeatures[0].isExpired())

        assertEquals(false, listFeatures[1].active)
        assertEquals(true, listFeatures[1].inverted)
        assertEquals(false, listFeatures[1].isExpired())

        assertEquals(false, listFeatures[2].active)
        assertEquals(false, listFeatures[2].inverted)
        assertEquals(false, listFeatures[2].isExpired())

        assertEquals(true, listFeatures[3].active)
        assertEquals(false, listFeatures[3].inverted)
        assertEquals(true, listFeatures[3].isExpired())
    }
}