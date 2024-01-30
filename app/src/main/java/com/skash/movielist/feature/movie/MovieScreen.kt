package com.skash.movielist.feature.movie

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asFlow
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.skash.movielist.R
import com.skash.movielist.core.model.Movie

@Composable
fun MovieScreen(viewModel: MovieViewModel = hiltViewModel()) {
    val popularMovies = viewModel.popularMoviesLiveData.asFlow().collectAsLazyPagingItems()

    MovieSection(title = R.string.title_whats_popular, movies = popularMovies)
}

@Composable
fun MovieSection(@StringRes title: Int, movies: LazyPagingItems<Movie>) {
    Column {
        Text(text = stringResource(id = title), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(movies.itemCount) { index ->
                movies[index]?.let {
                    MovieListItem(movie = movies[index]!!, onClick = {})
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListItem(movie: Movie, onClick: (Movie) -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .height(260.dp)
            .width(200.dp)
            .padding(start = 5.dp, end = 5.dp)
    ) {
        GlideImage(
            model = movie.imageURL,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text(
            text = movie.title,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}