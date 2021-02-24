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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_PET = "PET"

        public fun getIntent(caller: Activity, pet: Pet): Intent {
            val intent = Intent(caller, DetailActivity::class.java)
            intent.putExtra(KEY_PET, pet)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pet = intent.extras?.getParcelable<Pet>(KEY_PET)
        pet?.let {
            title = it.name
        }

        setContent {
            MyTheme {
                PetDetail(pet) {
                    Toast.makeText(this, "Booking is not implemented, yet", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

@Composable
fun PetDetail(pet: Pet?, bookClicked: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            pet?.let {
                Image(
                    painter = painterResource(id = pet.drawable),
                    contentDescription = pet.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .clip(shape = CutCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = pet.name,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.h3
                )

                Text(text = "This dog has a ${pet.size} size")
                Spacer(modifier = Modifier.height(36.dp))
                Button(onClick = bookClicked, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Book pet")
                }
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailPreview() {
    MyTheme {
        PetDetail(
            Pet("Good Boi", R.drawable.dog_1, "Small"),
            {}
        )
    }
}
