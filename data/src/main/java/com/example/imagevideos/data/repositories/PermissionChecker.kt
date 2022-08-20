package com.example.imagevideos.data.repositories


interface PermissionChecker {

    enum class Permission { COARSE_LOCATION }

    fun check(permission: Permission): Boolean
}