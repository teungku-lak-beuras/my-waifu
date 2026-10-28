package zeef.mywaifu.route

import androidx.compose.runtime.Composable
import zeef.mywaifu.screen.AboutScreen

@Composable
fun AboutRoute(
    popCallback: () -> Unit
) {
    AboutScreen(
        popCallback = popCallback
    )
}
