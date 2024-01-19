package com.applakazam.androidmvvmtemplate.navigation

import android.net.Uri
import android.util.Base64
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.ContactItem
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.modules.SerializersModule
import com.applakazam.androidmvvmtemplate.api.NavigationArgument

/**
 *  Created by paulbisioc on 17.01.2024
 */
object NavExtensions {
    private val module = SerializersModule {
        polymorphic(NavigationArgument::class) {
            subclass(ContactItem::class)
        }
    }
    private val format = Json { serializersModule = module }

    // Navigation
    fun NavController.deeplinkTo(path: String, args: String? = null) {
        val uri = if (args == null) {
            path.toUri()
        } else {
            Uri.parse(path).buildUpon()
                .appendQueryParameter("args", args)
                .build()
        }
        val request = NavDeepLinkRequest.Builder.fromUri(uri).build()
        this.navigate(request)
    }

    // TODO @Paul: improve navigation by adding expected type of Args at serialization/deserialization
    fun NavigationArgument.prepareArgsForDeeplink(): String {
        // serialize to JSON String
        val jsonString = format.encodeToString(this)
        // encode to Base64
        return jsonString.toBase64()
    }

    fun String.getArgsFromDeeplink(): NavigationArgument {
        // decode from Base64
        val decodedJson = this.decodeBase64()
        // deserialize to NavigationArgument
        return format.decodeFromString<NavigationArgument>(decodedJson)
    }

    private fun String.toBase64(): String {
        return Base64.encodeToString(this.toByteArray(), Base64.DEFAULT)
    }

    private fun String.decodeBase64(): String {
        val decodedBytes = Base64.decode(this, Base64.DEFAULT)
        return String(decodedBytes, Charsets.UTF_8)
    }

    private fun test() {}
}