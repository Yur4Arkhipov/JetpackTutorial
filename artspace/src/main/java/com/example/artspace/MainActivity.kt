package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.JetpackTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)              
                            .wrapContentSize(align = Alignment.Center)
                            .padding(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtworkWall(
    painter: Painter,
    imageDescription: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = imageDescription,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

@Composable
fun ArtworkDescription(
    artworkTitle: String,
    artworkArtist: String,
    artworkYear: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier
    ) {
        Text(
            text = artworkTitle,
            fontSize = 26.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = modifier
        ) {
            Text(
                text = artworkArtist,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Default
            )
            Text(text = " ($artworkYear)")
        }
    }
}

@Composable
fun DisplayController(
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Button(
            onClick = onClickPrevious,
            modifier = Modifier.size(height = 40.dp, width = 120.dp)
        ) {
            Text("Previous")
        }
        Button(
            onClick = onClickNext,
            modifier = Modifier.size(height = 40.dp, width = 120.dp)
        ) {
            Text("Next")
        }
    }
}

@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableIntStateOf(0) }
    val artsPaintersList = listOf(
        R.drawable.therookshavereturned,
        R.drawable.girlwithpeaches
    )
    val artsImageDescriptionList = listOf(
        R.string.theRooksHaveReturnedContentDescription,
        R.string.girlWithPeachesDescription
    )
    val artsTitleList = listOf(
        R.string.theRooksHaveReturnedTitle,
        R.string.girlWithPeachesTitle
    )
    val artsArtistsList = listOf(
        R.string.theRooksHaveReturnedArtist,
        R.string.girlWithPeachesArtist
    )
    val artsYearList = listOf(
        R.string.theRooksHaveReturnedYear,
        R.string.girlWithPeachesYear
    )
    Column(
        modifier = modifier
    ) {
        ArtworkWall(
            painter = painterResource(artsPaintersList[counter]),
            imageDescription = stringResource(artsImageDescriptionList[counter]),
            modifier = Modifier
        )
        Spacer(Modifier.height(40.dp))
        ArtworkDescription(
            artworkTitle = stringResource(artsTitleList[counter]),
            artworkArtist = stringResource(artsArtistsList[counter]),
            artworkYear = stringResource(artsYearList[counter]),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(80.dp))
        DisplayController(
            onClickPrevious = { counter-- },
            onClickNext = { counter++ },
            modifier = Modifier.fillMaxWidth()
        )
        Text("Counter: $counter")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    JetpackTutorialTheme {
        ArtSpaceApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
                .padding(20.dp)
        )
    }
}