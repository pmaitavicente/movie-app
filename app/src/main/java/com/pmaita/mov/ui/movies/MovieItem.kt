package com.pmaita.mov.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pmaita.mov.R
import com.pmaita.mov.core.utils.Configuration
import com.pmaita.mov.domain.model.Movie

@Composable
fun MovieItem(
    movie: Movie.Data,
    onItemClick: () -> Unit
) {
    val imageUrl = movie.posterPath?.let { Configuration.IMAGE_ENDPOINT + it }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageUrl ?: R.drawable.ic_not_found,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(0.66f)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
        )
    }
}