package heaven.from.mywaifump.route

import androidx.compose.runtime.Composable
import zeef.mywaifu.screen.SettingsScreen

@Composable
fun SettingsRoute(
    popCallback: () -> Unit
) {
    SettingsScreen(
        popCallback = popCallback
    )
}
