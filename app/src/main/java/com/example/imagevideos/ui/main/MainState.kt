package com.example.imagevideos.ui.main

import android.Manifest
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.ui.common.PermissionRequester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainState(
    private val scope: CoroutineScope,
    private val navController: NavController,
    private val locationPermissionRequest: PermissionRequester
) {

     fun onImageClicked(image: ApiImage){
        val navAction = MainFragmentDirections.actionMainToVideo(image)
        navController.navigate(navAction)
    }

    fun requestLocationPermission(afterRequest: (Boolean)-> Unit){
        scope.launch {
            val result = locationPermissionRequest.request()
            afterRequest(result)
        }
    }
}

fun Fragment.buildMainState(
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
): MainState {
    return MainState(scope, navController, locationPermissionRequester)
}