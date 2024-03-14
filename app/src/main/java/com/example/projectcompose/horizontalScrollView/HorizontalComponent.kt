package com.example.projectcompose.horizontalScrollView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projectcompose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HorizontalScrollView(items: List<String>) {
    var currentIndex by remember { mutableStateOf(Int.MAX_VALUE / 2) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(currentIndex) {
        coroutineScope.launch {
            delay(5000) // Delay for auto-scrolling (adjust as needed)
            currentIndex++
        }
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.dp) // Adjust spacing between items as needed
    ) {
        items(count = Int.MAX_VALUE) { index ->
            val itemIndex = index % items.size
            val adjustedIndex = (itemIndex + currentIndex) % items.size
            HorizontalScrollItem(imageResId = items[adjustedIndex])
        }
    }
}

@Composable
fun HorizontalScrollItem(imageResId: String) {
    Box(
        modifier = Modifier
            .padding(0.dp) // Add some padding between items
            .fillMaxWidth()
            .height(270.dp)// Fill maximum available width and height
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageResId)
                .build(),
//            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.ab2_quick_yoga),
//            contentScale = ContentScale.Crop,/"
//            modifier = Modifier.clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHorizontalScrollView() {
    Column {
        HorizontalScrollView(
            listOf(
                "https://images.unsplash.com/photo-1628373383885-4be0bc0172fa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1301&q=80",
                "https://www.rei.com/dam/2023_q4_gift_center_gift_card_secondary_story_web_lg.jpeg"
            )
        )
//        HorizontalScrollItem(R.drawable.ic_launcher_background)
    }
}