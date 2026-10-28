package heaven.from.network.provider

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import zeef.mywaifu.configuration.DEBUG_MODE
import zeef.mywaifu.configuration.NEKOSIA_CAT_API
import zeef.mywaifu.network.definition.CONNECT_TIMEOUT
import zeef.mywaifu.network.definition.REQUEST_TIMEOUT
import zeef.mywaifu.network.definition.SOCKET_TIMEOUT

actual object NekosiaCatApiProvider {
    actual fun provideEngine(): HttpClientEngineFactory<HttpClientEngineConfig> {
        return CIO
    }

    actual fun provideHttpClient(
        engine: HttpClientEngineFactory<HttpClientEngineConfig>
    ): HttpClient {
        return HttpClient(engineFactory = engine) {
            defaultRequest {
                url(NEKOSIA_CAT_API)
            }

            if (DEBUG_MODE) {
                install(Logging) {
                    logger = object : Logger {
                        private val tag = "Ktor"

                        override fun log(message: String) {
                            Napier.d(tag = tag, message = message)
                        }
                    }
                }
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        coerceInputValues = true // Coerce to use default values in case of null.
                        ignoreUnknownKeys = true // Just in case of sudden API changes.
                    }
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = CONNECT_TIMEOUT
                requestTimeoutMillis = REQUEST_TIMEOUT
                socketTimeoutMillis = SOCKET_TIMEOUT
            }
        }
    }
}
