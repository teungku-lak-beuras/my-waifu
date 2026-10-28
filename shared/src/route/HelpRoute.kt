package zeef.mywaifu.route

import androidx.compose.runtime.Composable
import zeef.mywaifu.screen.HelpScreen

@Composable
fun HelpRoute(
    popCallback: () -> Unit
) {
    // val viewModel: add later
    HelpScreen(
        popCallback = popCallback
    )
}
