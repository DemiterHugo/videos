package com.example.imagevideos.ui.main

import android.Manifest
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.imagevideos.R
import com.example.imagevideos.model.Error
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.ui.common.PermissionRequester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,
    private val locationPermissionRequest: PermissionRequester
) {

     fun onImageClicked(image: Image){
        val navAction = MainFragmentDirections.actionMainToVideo(image.id)
        navController.navigate(navAction)
    }

    fun requestLocationPermission(afterRequest: (Boolean)-> Unit){
        scope.launch {
            val result = locationPermissionRequest.request()
            afterRequest(result)
        }
    }

    fun errorToString(error: Error) = when(error){
        Error.Connectivity -> context.getString(R.string.connnectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}

fun Fragment.buildMainState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
): MainState {
    return MainState(context, scope, navController, locationPermissionRequester)
}