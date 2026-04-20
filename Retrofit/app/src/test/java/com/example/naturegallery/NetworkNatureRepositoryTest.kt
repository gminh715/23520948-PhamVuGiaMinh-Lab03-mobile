/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.naturegallery

import com.example.naturegallery.data.NetworkNaturePhotosRepository
import com.example.naturegallery.fake.FakeDataSource
import com.example.naturegallery.fake.FakeNatureApiService
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkNatureRepositoryTest {

    @Test
    fun networkNaturePhotosRepository_getNaturePhotos_verifyPhotoList() =
        runTest {
            val repository = NetworkNaturePhotosRepository(
                natureApiService = FakeNatureApiService()
            )
            assertEquals(FakeDataSource.photosList, repository.getNaturePhotos())
        }
}
