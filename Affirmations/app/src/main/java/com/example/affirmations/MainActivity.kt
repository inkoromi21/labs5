package com.example.affirmations

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme(colorScheme = lightColorScheme(surface = Color(0xFFFFFBFE))) {
        AffirmationsApp()
      }
    }
  }
}

@Composable
fun AffirmationsApp() {
  Surface(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
    AffirmationList(affirmationList = Datasource().loadAffirmations())
  }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
  Card(modifier = modifier) {
    Column {
      Image(
        painter = painterResource(affirmation.imageResourceId),
        contentDescription = stringResource(affirmation.stringResourceId),
        modifier = Modifier.fillMaxWidth().height(194.dp),
        contentScale = ContentScale.Crop
      )
      Text(
        text = stringResource(affirmation.stringResourceId),
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.headlineSmall
      )
    }
  }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
  LazyColumn(modifier = modifier) {
    items(affirmationList) { affirmation ->
      AffirmationCard(affirmation, Modifier.padding(8.dp))
    }
  }
}

@Preview(showBackground = true)
@Composable
private fun AffirmationCardPreview() {
  MaterialTheme {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
  }
}
