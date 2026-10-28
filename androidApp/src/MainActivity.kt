package zeef.mywaifu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import zeef.mywaifu.configuration.DEBUG_MODE
import zeef.mywaifu.provider.CoilProvider.provideCoilLogger
import zeef.mywaifu.provider.MyWaifuRepositoryProvider

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MyWaifuRepositoryProvider.provideRepository(applicationContext)

            // Set Coil 3 image loader.
            setSingletonImageLoaderFactory { context ->
                ImageLoader
                    .Builder(context)
                    .components {
                        add(
                            KtorNetworkFetcherFactory(
                                httpClient = HttpClient(CIO)
                            )
                        )
                    }
                    .logger(if (DEBUG_MODE) provideCoilLogger() else null)
                    .build()
            }

            MyWaifu()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
}
