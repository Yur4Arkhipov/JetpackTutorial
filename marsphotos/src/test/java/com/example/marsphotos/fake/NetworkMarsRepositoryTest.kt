package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import org.junit.Test

class NetworkMarsRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotoList(){
        val repository = NetworkMarsPhotosRepository(
            marsApiService = FakeMarsApiService()
        )
    }
}