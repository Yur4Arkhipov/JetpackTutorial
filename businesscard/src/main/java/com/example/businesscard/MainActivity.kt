package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.JetpackTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackTutorialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color = Color(60, 179, 113))
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    val myAppIcons = Icons.Rounded
    Column(
        modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.7f)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.size(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.android_logo_stacked_rgb__5),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = stringResource(R.string.full_name),
                fontSize = 34.sp,
                fontFamily = FontFamily.SansSerif )
            Text(
                text = stringResource(R.string.job),
                color = Color(34, 139, 34)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize()
                .padding(50.dp)
        ) {
            Box {
                Column {
                    Row {
                        Icon(
                            imageVector = myAppIcons.Call,
                            contentDescription = null,
                            tint = Color(34, 139, 34)
                        )
                        Spacer(Modifier.width(20.dp))
                        Text(stringResource(R.string.phone_number))
                    }
                    Row {
                        Icon(
                            imageVector = myAppIcons.Share,
                            contentDescription = null,
                            tint = Color(34, 139, 34)
                        )
                        Spacer(Modifier.width(20.dp))
                        Text(stringResource(R.string.telegram))
                    }
                    Row {
                        Icon(
                            imageVector = myAppIcons.Email,
                            contentDescription = null,
                            tint = Color(34, 139, 34)
                        )
                        Spacer(Modifier.width(20.dp))
                        Text(stringResource(R.string.mail))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    JetpackTutorialTheme {
        BusinessCard(
            modifier = Modifier
                .background(color = Color(60, 179, 113))
                .fillMaxWidth()
        )
    }
}