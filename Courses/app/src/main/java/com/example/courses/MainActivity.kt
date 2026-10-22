package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import com.example.courses.data.DataSource
import com.example.courses.model.Topic

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme(colorScheme = lightColorScheme(surface = Color(0xFFFFFBFE))) {
        Surface(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
          TopicGrid()
        }
      }
    }
  }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
  Card(modifier = modifier) {
    Row {
      Image(
        painter = painterResource(topic.imageResourceId),
        contentDescription = stringResource(topic.nameResourceId),
        modifier = Modifier.size(68.dp),
        contentScale = ContentScale.Crop
      )
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = stringResource(topic.nameResourceId),
          style = MaterialTheme.typography.bodyMedium
        )
        Row(
          modifier = Modifier.padding(top = 8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            painter = painterResource(R.drawable.ic_courses),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
          )
          Text(
            text = topic.courseCount.toString(),
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.labelMedium
          )
        }
      }
    }
  }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    modifier = modifier,
    contentPadding = PaddingValues(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(DataSource.topics) { topic ->
      TopicCard(topic, Modifier.fillMaxWidth())
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun TopicCardPreview() {
  MaterialTheme {
    TopicCard(DataSource.topics.first())
  }
}
