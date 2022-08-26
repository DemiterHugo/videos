package com.example.imagevideos.data

import com.example.imagevideos.data.datasource.LocationDataSource
import com.example.imagevideos.data.repositories.PermissionChecker
import com.example.imagevideos.data.repositories.PermissionChecker.Permission.COARSE_LOCATION
import com.example.imagevideos.data.repositories.RegionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class RegionRepositoryTest{

    @Test
    fun `Return default region when coarse permission not granted`(): Unit = runBlocking{
        //given
        val sut = RegionRepository(
            locationDataSource = mock(),
            permissionChecker = mock(){
                on{ check(COARSE_LOCATION)} doReturn false
            }
        )
        //when
        val region = sut.findLastRegion()
        //then
        Assert.assertEquals("en",region)
    }

    @Test
    fun `Return region from location data source when permission granted`(): Unit = runBlocking {
        //given
        val sut = buildRegionRepository(
            locationDataSource = mock{ onBlocking{findLastLocation()} doReturn "es" },
            permissionChecker = mock{ on{ check(COARSE_LOCATION)} doReturn true }
        )
        //when
        val region = sut.findLastRegion()
        //then
        assertEquals("es",region)
    }
}

private fun buildRegionRepository(
    locationDataSource: LocationDataSource = mock(),
    permissionChecker: PermissionChecker = mock()
) = RegionRepository(locationDataSource, permissionChecker)