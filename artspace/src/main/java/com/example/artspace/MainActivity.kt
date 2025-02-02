package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .shadow(5.dp)
            .padding(40.dp),
        contentScale = ContentScale.Crop
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
        modifier = modifier
            .background(Color.Gray.copy(0.2f), shape = RoundedCornerShape(16.dp))
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
            Text(text = " ($artworkYear) ")
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

data class Art(
    val imageResId: Int,
    val descriptionResId: Int,
    val titleResId: Int,
    val artistResId: Int,
    val yearResId: Int
)

object ArtRepository {
    val artsList = listOf(
        Art(
            R.drawable.therookshavereturned,
            R.string.theRooksHaveReturnedDescription,
            R.string.theRooksHaveReturnedTitle,
            R.string.theRooksHaveReturnedArtist,
            R.string.theRooksHaveReturnedYear
        ),
        Art(
            R.drawable.thegirlwithpeaches,
            R.string.girlWithPeachesDescription,
            R.string.girlWithPeachesTitle,
            R.string.girlWithPeachesArtist,
            R.string.girlWithPeachesYear
        ),
        Art(
            R.drawable.theninthwave,
            R.string.theNinthWaveDescription,
            R.string.theNinthWaveTitle,
            R.string.theNinthWaveArtist,
            R.string.theNinthWaveYear
        ),
        Art(
            R.drawable.blacksquare,
            R.string.blackSquareDescription,
            R.string.blackSquareTitle,
            R.string.blackSquareArtist,
            R.string.blackSquareYear
        ),
        Art(
            R.drawable.morninginapineforest,
            R.string.morningInAPineForestDescription,
            R.string.morningInAPineForestTitle,
            R.string.morningInAPineForestArtist,
            R.string.morningInAPineForestYear
        ),
    )
}


@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableIntStateOf(0) }
    val arts = ArtRepository.artsList
    val totalArts = arts.size
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        ArtworkWall(
            painter = painterResource(arts[counter].imageResId),
            imageDescription = stringResource(arts[counter].descriptionResId),
            modifier = Modifier
        )
        Spacer(Modifier.height(40.dp))
        ArtworkDescription(
            artworkTitle = stringResource(arts[counter].titleResId),
            artworkArtist = stringResource(arts[counter].artistResId),
            artworkYear = stringResource(arts[counter].yearResId),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(80.dp))
        DisplayController(
            onClickPrevious = {
                counter = if (counter > 0) counter - 1 else totalArts - 1
                              },
            onClickNext = {
                counter = (counter + 1) % totalArts
                          },
            modifier = Modifier.fillMaxWidth()
        )
//        Text("Counter: $counter")
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