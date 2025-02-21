package com.example.dessertclicker.data

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.example.dessertclicker.data.Datasource.dessertList


data class DessertUIState(
    val currentDessertIndex: Int = 0,
    val dessertSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentImageId: Int = dessertList[currentDessertIndex].imageId
)