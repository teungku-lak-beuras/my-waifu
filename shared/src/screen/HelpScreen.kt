package zeef.mywaifu.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.stringResource
import zeef.mywaifu.component.MyWaifuFloatingAppBar
import zeef.mywaifu.component.MyWaifuPreview
import zeef.mywaifu.component.MyWaifuSideAppBar
import zeef.mywaifu.component.MyWaifuTopAppBar
import zeef.mywaifu.definition.LocalWindowSize
import zeef.mywaifu.definition.WindowSize
import zeef.mywaifu.definition.sizeMedium
import zeef.mywaifu.layout.MyWaifuCompactScaffold
import zeef.mywaifu.layout.MyWaifuExpandedScaffold
import zeef.mywaifu.layout.MyWaifuMediumScaffold
import zeef.mywaifu.resources.Res
import zeef.mywaifu.resources.help

@Composable
private fun Content(
    paddingValues: PaddingValues
) {
    Text(
        modifier = Modifier.padding(paddingValues),
        text = "Help screen is under construction :)"
    )
}

@Composable
fun HelpScreen(
    popCallback: () -> Unit
) {
    when (LocalWindowSize.current) {
        WindowSize.Compact -> {
            MyWaifuCompactScaffold(
                topAppBar = {
                    MyWaifuTopAppBar(
                        title = stringResource(Res.string.help),
                        popCallback = popCallback
                    )
                }
            ) { paddingValues ->
                Content(
                    paddingValues = paddingValues
                )
            }
        }
        WindowSize.Medium -> {
            MyWaifuMediumScaffold(
                sideAppBar = {
                    MyWaifuSideAppBar(
                        popCallback = popCallback
                    )
                }
            ) {
                Content(
                    paddingValues = PaddingValues(sizeMedium)
                )
            }
        }
        WindowSize.Expanded -> {
            MyWaifuExpandedScaffold(
                floatingAppBar = {
                    MyWaifuFloatingAppBar(
                        title = stringResource(Res.string.help),
                        popCallback = popCallback
                    )
                }
            ) { paddingValues ->
                Content(
                    paddingValues = paddingValues
                )
            }
        }
    }
}

@Composable
private fun HelpScreenPreview() {
    HelpScreen(
        popCallback = {}
    )
}

@Preview
@Composable
private fun HelpScreenAndroidPreview() {
    MyWaifuPreview {
        HelpScreenPreview()
    }
}

@Preview(device = Devices.TABLET)
@Composable
private fun HelpScreenTabletPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Medium
    ) {
        HelpScreenPreview()
    }
}

@Preview(device = Devices.DESKTOP)
@Composable
private fun HelpScreenDesktopPreview() {
    MyWaifuPreview(
        windowSize = WindowSize.Expanded
    ) {
        HelpScreenPreview()
    }
}
