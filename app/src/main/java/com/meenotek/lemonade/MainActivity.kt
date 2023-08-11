package com.meenotek.lemonade
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meenotek.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

val topBarBackground = Color(0xFFF9E44C)
val textsColor = Color(0xFF40403F)
val paddingColor = Color(0xFFC3ECD2)
val appFullScreenBackground = Color(0xFFFFFBFF)
val topBarTextColor = Color(0xFF010000)

@Composable
fun AppScreen() {
    var currentStep by remember { mutableStateOf(1) }
    var clickCounter by remember { mutableStateOf(0) }
    val randomThreshold = (2..4).random()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appFullScreenBackground),
    ) {
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        LemonadeTopBar(word = stringResource(R.string.lemonade_top_bar))
    }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        when (currentStep) {

            1 -> LemonadeFirstAppScreenImageAndText(
                image = painterResource(id = R.drawable.lemon_tree),
                imageText = stringResource(R.string.tap_to_select_lemon),
                onStepChange = { currentStep = (currentStep % 4) + 1 }
            )

            2 -> {
                if (clickCounter >= randomThreshold) { // Check if clicked between 2 and 4 times
                    currentStep = 3 // Move to the next screen
                    clickCounter = 0 // Reset the click counter
                } else {
                    LemonadeSecondAppScreenImageAndText(
                        image = painterResource(id = R.drawable.lemon_squeeze),
                        imageText = stringResource(R.string.keep_tapping_lemon),
                        onStepChange = { clickCounter++ }
                    )
                }
            }

            3 -> LemonadeThirdAppScreenImageAndText(
                image = painterResource(id = R.drawable.lemon_drink),
                imageText = stringResource(R.string.tap_lemonade_to_drink),
                onStepChange = { currentStep = (currentStep % 4) + 1 }
            )

            4 -> LemonadeFourthAppScreenImageAndText(
                image = painterResource(id = R.drawable.lemon_restart),
                imageText = stringResource(R.string.tap_empty_glass_to_start_again),
                onStepChange = { currentStep = (currentStep % 4) + 1 }
            )
        }
    }
}
@Composable
fun LemonadeTopBar(
    word: String,
) {
    Column(
        modifier = Modifier
            .background(topBarBackground)
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = word,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = topBarTextColor
        )
    }
}

@Composable
fun LemonadeFirstAppScreenImageAndText(
    image: Painter,
    imageText: String,
    onStepChange: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            modifier = Modifier
                .background(paddingColor, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onStepChange() },
            contentDescription = stringResource(R.string.tree_picture)
        )
        Text(
            text = imageText,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            color = textsColor
        )
    }
}

@Composable
fun LemonadeSecondAppScreenImageAndText(
    image: Painter,
    imageText: String,
    onStepChange: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            modifier = Modifier
                .background(paddingColor, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onStepChange() },
            contentDescription = stringResource(R.string.orange_picture)
        )
        Text(
            text = imageText,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            color = textsColor
        )
    }
}

@Composable
fun LemonadeThirdAppScreenImageAndText(
    image: Painter,
    imageText: String,
    onStepChange: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            modifier = Modifier
                .background(paddingColor, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onStepChange() },
            contentDescription = stringResource(R.string.drink_lemonade)
        )
        Text(
            text = imageText,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            color = textsColor
        )
    }
}

@Composable
fun LemonadeFourthAppScreenImageAndText(
    image: Painter,
    imageText: String,
    onStepChange: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            modifier = Modifier
                .background(paddingColor, shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable { onStepChange() },
            contentDescription = stringResource(R.string.empty_glass_picture)
        )
        Text(
            text = imageText,
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            color = textsColor
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        AppScreen()
    }
}
