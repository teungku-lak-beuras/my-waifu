package zeef.mywaifu.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import zeef.mywaifu.component.MyWaifuPreview
import zeef.mywaifu.resources.Res
import zeef.mywaifu.resources.error

@Composable
fun ErrorScreen(message: String = "Why error? :(") {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = stringResource(Res.string.error)
            )
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() = MyWaifuPreview {
    ErrorScreen()
}
