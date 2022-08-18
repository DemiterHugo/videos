package com.example.imagevideos.data.repositories

/* import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionChecker(private val application: Application, private val permission: String) {

    fun check(): Boolean = ContextCompat.checkSelfPermission(
        application,
        permission
    ) == PackageManager.PERMISSION_GRANTED
} */

interface PermissionChecker {

    enum class Permission { COARSE_LOCATION }

    fun check(permission: Permission): Boolean
}