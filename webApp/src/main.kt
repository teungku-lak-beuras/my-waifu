package zeef.mywaifu

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.ComposeViewport
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.preloadFont
import zeef.mywaifu.configuration.DEBUG_MODE
import zeef.mywaifu.provider.CoilProvider.provideCoilLogger
import zeef.mywaifu.provider.MyWaifuRepositoryProvider
import zeef.mywaifu.resources.Res
import zeef.mywaifu.resources.noto_coloremoji_regular
import zeef.mywaifu.resources.notosans_jp_regular
import zeef.mywaifu.resources.notosans_kr_regular
import zeef.mywaifu.resources.notosans_sc_regular
import zeef.mywaifu.resources.notosans_tc_regular

@OptIn(ExperimentalComposeUiApi::class, ExperimentalResourceApi::class, ExperimentalCoilApi::class)
fun main() {
    // Initialise Napier.
    if (DEBUG_MODE) {
        Napier.base(DebugAntilog())
    }

    ComposeViewport {
        MyWaifuRepositoryProvider.provideRepository()

        val emojiFallbackFont = preloadFont(Res.font.noto_coloremoji_regular).value
        val fontFamilyResolver = LocalFontFamilyResolver.current
        val jpFallbackFont = preloadFont(Res.font.notosans_jp_regular).value
        val scFallbackFont = preloadFont(Res.font.notosans_sc_regular).value
        val tcFallbackFont = preloadFont(Res.font.notosans_tc_regular).value
        val krFallbackFont = preloadFont(Res.font.notosans_kr_regular).value

        // Compose hasn't yet support fallback font for web WASM.
        LaunchedEffect(fontFamilyResolver, emojiFallbackFont) {
            if (emojiFallbackFont != null) {
                fontFamilyResolver.preload(
                    FontFamily(listOf(emojiFallbackFont))
                )
            }
        }
        LaunchedEffect(fontFamilyResolver, jpFallbackFont) {
            if (jpFallbackFont != null) {
                fontFamilyResolver.preload(
                    FontFamily(listOf(jpFallbackFont))
                )
            }
        }
        LaunchedEffect(fontFamilyResolver, scFallbackFont) {
            if (scFallbackFont != null) {
                fontFamilyResolver.preload(
                    FontFamily(listOf(scFallbackFont))
                )
            }
        }
        LaunchedEffect(fontFamilyResolver, tcFallbackFont) {
            if (tcFallbackFont != null) {
                fontFamilyResolver.preload(
                    FontFamily(listOf(tcFallbackFont))
                )
            }
        }
        LaunchedEffect(fontFamilyResolver, krFallbackFont) {
            if (krFallbackFont != null) {
                fontFamilyResolver.preload(
                    FontFamily(listOf(krFallbackFont))
                )
            }
        }
        // Set Coil 3 image loader.
        setSingletonImageLoaderFactory { context ->
            ImageLoader
                .Builder(context)
                .components {
                    add(
                        KtorNetworkFetcherFactory(
                            httpClient = HttpClient(Js)
                        )
                    )
                }
                .logger(if (DEBUG_MODE) provideCoilLogger() else null)
                .build()
        }

        MyWaifu()
    }
}
