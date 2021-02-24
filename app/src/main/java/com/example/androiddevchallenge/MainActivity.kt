/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    companion object {
        val petlist = listOf<Pet>(
            Pet("Good dog", R.drawable.dog_1, "Medium"),
            Pet("Fast dog", R.drawable.dog_2, "Small"),
            Pet("Goodest dog", R.drawable.dog_3, "Small"),
            Pet("Lazy dog", R.drawable.dog_4, "Big"),
            Pet("Fluffy Bdog", R.drawable.dog_5, "Big"),
            Pet("Jolly dog", R.drawable.dog_6, "Big"),
            Pet("Small dog", R.drawable.dog_7, "Medium"),
            Pet("Big dog", R.drawable.dog_8, "Small"),
            Pet("Strange dog", R.drawable.dog_9, "Medium")
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(petlist) {
                    openDetailView(it)
                }
            }
        }
    }

    private fun openDetailView(pet: Pet) {
        val intent = DetailActivity.getIntent(this, pet)
        startActivity(intent)
    }
}

// Start building your app here!./gradlew app:spotlessApply
@Composable
fun MyApp(pets: List<Pet>, onPetClicked: (Pet) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            items(pets) { pet ->
                PetListItem(
                    pet = pet,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onPetClicked(pet)
                        }
                )
            }
        }
    }
}

@Composable
fun PetListItem(
    pet: Pet,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painterResource(id = pet.drawable), pet.name,
            modifier = Modifier
                .width(120.dp)
                .height(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = pet.name,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(
            MainActivity.petlist,
            {}
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(
            MainActivity.petlist,
            {}
        )
    }
}
