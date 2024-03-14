package com.example.projectcompose.horizontalScrollView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectcompose.R

@Composable
fun HorizontalScrollView(images: List<Int>) {
    LazyRow {
        itemsIndexed(images) { index, imageResId ->
            // Composable item to display in the horizontal scroll view
            HorizontalScrollItem(imageResId = imageResId)
        }
    }
}

@Composable
fun HorizontalScrollItem(imageResId: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp) // Add some padding between items
            .fillMaxSize() // Fill maximum available width and height
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null, // Content description can be provided for accessibility
            modifier = Modifier.fillMaxWidth().height(160.dp)
        )
    }
}

@Preview
@Composable
fun PreviewHorizontalScrollView() {
    val images = listOf(
        R.drawable.ab1_inversions,
        R.drawable.ab2_quick_yoga,
        R.drawable.ab4_tabata,
        R.drawable.ab1_inversions,
        R.drawable.ab2_quick_yoga,
        R.drawable.ab4_tabata,
        R.drawable.ab1_inversions,
        R.drawable.ab2_quick_yoga,
        R.drawable.ab4_tabata
    )
    HorizontalScrollView(images = images)
}