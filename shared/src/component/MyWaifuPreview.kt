package zeef.mywaifu.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import zeef.mywaifu.definition.LocalWindowSize
import zeef.mywaifu.definition.WindowSize
import zeef.mywaifu.theme.MyWaifuTheme

@Composable
fun MyWaifuPreview(
    windowSize: WindowSize = WindowSize.Compact,
    content: @Composable () -> Unit
) {
    MyWaifuTheme {
        CompositionLocalProvider(LocalWindowSize provides windowSize) {
            content.invoke()
        }
    }
}
