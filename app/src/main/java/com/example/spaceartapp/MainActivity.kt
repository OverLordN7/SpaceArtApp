package com.example.spaceartapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spaceartapp.ui.theme.SpaceArtAppTheme

private const val TAG= "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceArtAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SpaceArtApp()
                }
            }
        }
    }
}


@Preview
@Composable
fun SpaceArtApp() {
    var pictureNumber by remember {
        mutableStateOf(0)
    }

    var imageRes: Int
    var stringTitleRes: Int
    var stringAuthorRes: Int

    when(pictureNumber){
        1 ->{
            imageRes = R.drawable.picture2
            stringTitleRes = R.string.picture_name_2
            stringAuthorRes = R.string.picture_author_2
        }
        2 ->{
            imageRes = R.drawable.picture3
            stringTitleRes = R.string.picture_name_3
            stringAuthorRes = R.string.picture_author_3
        }
        3 ->{
            imageRes = R.drawable.picture4
            stringTitleRes = R.string.picture_name_4
            stringAuthorRes = R.string.picture_author_4
        }
        else ->{
            imageRes = R.drawable.picture1
            stringTitleRes = R.string.picture_name_1
            stringAuthorRes = R.string.picture_author_1
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageCard(imageRes)

        Spacer(modifier = Modifier.height(30.dp))

        TextCard(stringTitleRes,stringAuthorRes,2022)

        Spacer(modifier = Modifier.height(15.dp))

        Row() {
            Button(onClick = {
                if (pictureNumber == 3) pictureNumber=0 else pictureNumber++ }, modifier = Modifier.width(100.dp)) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = { if (pictureNumber == 0) pictureNumber=3 else pictureNumber-- }, modifier = Modifier.width(100.dp)) {
                Text(text = "Next")

            }
        }
        
    }
}

@Composable
fun ImageCard(imageRes: Int, modifier: Modifier = Modifier){
    Card(elevation = 10.dp) {
        Box(
            modifier = modifier
                .width(350.dp)
                .border(width = 3.dp, Color.Black, shape = RectangleShape)
                .padding(all = 30.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null
            )
        }
    }
}

@Composable
fun TextCard(
    stringResTitle:Int,
    stringResAuthor:Int,
    year: Int,
    modifier: Modifier = Modifier
){
    val paddingModifier  = Modifier.padding(10.dp)
    Card(elevation = 10.dp, modifier = paddingModifier.width(300.dp)) {

        Column(verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(all = 20.dp)) {

            Text(text = stringResource(id = stringResTitle), fontSize = 18.sp)
            Row() {
                Text(text = stringResource(id = stringResAuthor), fontWeight = FontWeight.Bold)
                Text(text = " ($year)")
            }
        }
    }
}



fun nextPicture(currentPicture: Int):Int{
    var temp = currentPicture
    if (temp == 3){
        return 0
    } else {
        return temp++
    }
}





