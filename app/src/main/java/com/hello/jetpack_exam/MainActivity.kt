/*
 * Copyright© 2024. KSNET INC, All rights reserved.
 * @Company : KSNET INC.
 * @Name : PETER HOON LEE
 * @TelNo : 02-3420-5844
 * @Email : foxworld@ksnet.co.kr
 * @Created Date : 2024-6-22
 */

package com.hello.jetpack_exam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //val textValue = remember {
            val (text, setValue) = remember {
                mutableStateOf("")
            }
            var isFavorite by rememberSaveable {
                mutableStateOf(false)
            }

            val scaffoldState = rememberScaffoldState()
            Scaffold(
                modifier = Modifier.fillMaxSize(),

            ) { innerPadding ->
                ImageCard(
                    modifier = Modifier
                        .fillMaxWidth(1.0f)
                        .padding(8.dp)
                        .padding(innerPadding),
                    isFavorite = isFavorite,

                ) { favorite ->
                    isFavorite = !isFavorite
                }

                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        TextField(
                            //value = textValue.value,
                            //onValueChange = { textValue.value = it }
                            value = text,
                            onValueChange = setValue
                        )
                        Button(onClick = { }) {
                            Text("클릭")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onTabFavorite: (Boolean) -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier.height(200.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.flower),
                contentDescription = "flower",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd,
            ) {
                IconButton(onClick = {
                    onTabFavorite.invoke(isFavorite)
                }) {
                    Icon(
                        imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

