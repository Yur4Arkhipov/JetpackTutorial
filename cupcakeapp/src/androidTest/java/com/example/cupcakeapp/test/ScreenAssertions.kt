package com.example.cupcakeapp.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals
import org.junit.Test

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}