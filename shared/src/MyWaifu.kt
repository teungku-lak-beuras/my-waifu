package zeef.mywaifu

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import zeef.mywaifu.definition.LocalWindowSize
import zeef.mywaifu.definition.WindowSize
import zeef.mywaifu.navigation.MyWaifuNavigation
import zeef.mywaifu.theme.MyWaifuTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun provideWindowSizeClass(): WindowSizeClass {
    val height = with (LocalDensity.current) { LocalWindowInfo.current.containerSize.height.toDp() }
    val width = with (LocalDensity.current) { LocalWindowInfo.current.containerSize.width.toDp() }

    return WindowSizeClass.calculateFromSize(DpSize(width, height))
}

@Composable
fun MyWaifu() {
    val windowSizeClass = provideWindowSizeClass()
    val windowSize = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Medium -> WindowSize.Medium
        WindowWidthSizeClass.Expanded -> WindowSize.Expanded
        else -> WindowSize.Compact
    }

    CompositionLocalProvider(LocalWindowSize provides windowSize) {
        MyWaifuTheme {
            MyWaifuNavigation()
        }
    }
}
