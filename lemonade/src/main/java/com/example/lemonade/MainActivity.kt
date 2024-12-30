package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.JetpackTutorialTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var step by remember { mutableIntStateOf(1) }
    val squeezeCount = Random.nextInt(2, 5)
    var count = 0
    when (step) {
        1 -> ScreenStep(
                image = painterResource(R.drawable.lemon_tree),
                text = stringResource(R.string.lemon_tree_step),
                contentDescription = stringResource(R.string.lemon_tree_step_description),
                step = squeezeCount,
                onClick = { step++ },
                modifier = modifier
            )
        2 -> ScreenStep(
                image = painterResource(R.drawable.lemon_squeeze),
                text = stringResource(R.string.lemon_squeeze_step),
                contentDescription = stringResource(R.string.lemon_squeeze_step_description),
                step = step,
                onClick = {
                    if (count < squeezeCount) {
                        count++
                    } else {
                        count = 0
                        step++
                    }
                },
                modifier = modifier
            )
        3 -> ScreenStep(
                image = painterResource(R.drawable.lemon_drink),
                text = stringResource(R.string.lemonade_drink_step),
                contentDescription = stringResource(R.string.lemonade_drink_step_description),
                step = step,
                onClick = { step++ },
                modifier = modifier
            )
        4 -> ScreenStep(
                image = painterResource(R.drawable.lemon_restart),
                text = stringResource(R.string.empty_glass_step),
                contentDescription = stringResource(R.string.empty_glass_step_description),
                step = step,
                onClick = { step = 1 },
                modifier = modifier
            )
    }

}

@Composable
fun ScreenStep(
    image: Painter,
    text: String,
    contentDescription: String,
    step: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50.dp))
                .background(Color(0xFFD0ED7B))
                .clickable(onClick = onClick)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = text,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(20.dp))
        Text("Step $step")
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    JetpackTutorialTheme {
        LemonadeApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}