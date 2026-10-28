package zeef.mywaifu.route

import androidx.compose.runtime.Composable
import zeef.mywaifu.model.MyWaifuModelV2
import zeef.mywaifu.screen.DetailScreen

@Composable
fun DetailRoute(
    waifu: MyWaifuModelV2,
    popCallback: () -> Unit
) {
    DetailScreen(
        waifu = waifu,
        popCallback = popCallback
    )
}
