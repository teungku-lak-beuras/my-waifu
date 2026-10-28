package zeef.mywaifu.route

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import zeef.mywaifu.model.MyWaifuModelV2
import zeef.mywaifu.model.MyWaifuState
import zeef.mywaifu.provider.MyWaifuRepositoryProvider
import zeef.mywaifu.screen.ErrorScreen
import zeef.mywaifu.screen.HomeScreen
import zeef.mywaifu.screen.LoadingScreen
import zeef.mywaifu.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    helpCallback: () -> Unit,
    settingsCallback: () -> Unit,
    aboutCallback: () -> Unit,
    detailCallback: (MyWaifuModelV2) -> Unit
) {
    AnimatedContent(
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        targetState = MyWaifuRepositoryProvider.repository.collectAsStateWithLifecycle().value
    ) { targetState ->
        when (targetState) {
            is MyWaifuState.Loading -> LoadingScreen()
            is MyWaifuState.Success -> {
                val viewModel = viewModel {
                    HomeViewModel(targetState.data)
                }

                HomeScreen(
                    waifu = viewModel.nekosiaCatWaifu.collectAsState().value,
                    isLoadingMore = viewModel.isLoadingMore,
                    isInitialyLoaded = viewModel.isInitialyLoaded,
                    helpCallback = helpCallback,
                    settingsCallback = settingsCallback,
                    aboutCallback = aboutCallback,
                    loadMoreCallback = { viewModel.getMoreNekosiaCatWaifu() },
                    detailCallback = detailCallback
                )
            }
            is MyWaifuState.Error -> ErrorScreen(message = targetState.message)
        }
    }
}
