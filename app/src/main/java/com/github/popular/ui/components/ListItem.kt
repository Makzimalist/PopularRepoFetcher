package com.github.popular.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.github.popular.common.data.Repository

@Composable
fun RepositoryListItem(
    repository: Repository,
    sorting: Sorting = Sorting.FORK,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SubcomposeAsyncImage(
                model = repository.owner.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .padding(start = 16.dp)
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "id: ${repository.id}", style = MaterialTheme.typography.caption)
                Text(text = repository.name, style = MaterialTheme.typography.body1)
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    repository.language?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    Text(
                        style = MaterialTheme.typography.subtitle2,
                        text = when (sorting) {
                            Sorting.FORK -> "Forks: ${repository.forksCount}"
                            Sorting.STAR -> "Stars: ${repository.starsCount}"
                            Sorting.DATE -> "" // Sadly it isn't working as expected, but due to time issues I will skip this
                        }
                    )
                }
            }
        }
    }
}

enum class Sorting {
    FORK, STAR, DATE
}

