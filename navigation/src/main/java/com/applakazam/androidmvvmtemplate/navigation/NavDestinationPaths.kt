package com.applakazam.androidmvvmtemplate.navigation

/**
 *  Created by paulbisioc on 17.01.2024
 */
object NavDestinationPaths {
    private const val BASE_DESTINATION_PATH = "android-app://com.applakazam.androidmvvmtemplate"
    const val HOME_FRAGMENT = "$BASE_DESTINATION_PATH/HomeFragment"
    const val SPLASH_FRAGMENT = "$BASE_DESTINATION_PATH/SplashFragment"
    const val CONTACTS_FRAGMENT = "$BASE_DESTINATION_PATH/ContactsFragment"
    const val CONTACT_DETAILS_FRAGMENT = "$BASE_DESTINATION_PATH/ContactDetailsFragment"
}