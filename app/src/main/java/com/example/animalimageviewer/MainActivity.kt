package com.example.animalimageviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.animalimageviewer.ui.theme.AnimalImageViewerTheme
import androidx.compose.ui.graphics.ColorFilter

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimalImageViewerTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Cute Animal Image Viewer App") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF143711),
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) { innerPadding ->
                    ImageViewerApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ImageViewerApp(modifier: Modifier = Modifier) {
    data class Animal(val imageRes: Int, val label: String)

    val cat = Animal(R.drawable.animal1, "Cute cat image")
    val dog = Animal(R.drawable.animal2, "Cute dog image")
    val chicken = Animal(R.drawable.animal3, "Cute chicken image")

    var selectedAnimal by remember { mutableStateOf(cat) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Crossfade(targetState = selectedAnimal) { animal ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = animal.imageRes),
                    contentDescription = animal.label,
                    modifier = Modifier
                        .size(350.dp)
                        .padding(20.dp)
                )
                Text(
                    text = animal.label,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { selectedAnimal = cat },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF98B8A0))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat),
                    contentDescription = "Cat",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(Modifier.width(8.dp))
                Text("Cat")
            }
            Button(
                onClick = { selectedAnimal = dog },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7E452F))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog),
                    contentDescription = "Dog",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(Modifier.width(8.dp))
                Text("Dog")
            }
            Button(
                onClick = { selectedAnimal = chicken },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB7521E))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chicken),
                    contentDescription = "Chicken",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Spacer(Modifier.width(8.dp))
                Text("Chicken")
            }
        }
    }
}
