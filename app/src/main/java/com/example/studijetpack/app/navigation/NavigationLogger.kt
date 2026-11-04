package com.example.studijetpack.app.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

private const val NAV_TAG = "SCREEN_NAVIGATION"

@SuppressLint("RestrictedApi")
fun NavController.logScreenNavigation() {
    addOnDestinationChangedListener { _, destination, _ ->
        val screenName = destination.route
            ?: destination.displayName

        Log.d(
            NAV_TAG,
            "Navigated to screen: $screenName"
        )
    }
}