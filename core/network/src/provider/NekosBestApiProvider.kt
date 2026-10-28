package heaven.from.network.provider

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

expect object NekosBestApiProvider {
    fun provideEngine(): HttpClientEngineFactory<HttpClientEngineConfig>

    fun provideHttpClient(
        engine: HttpClientEngineFactory<HttpClientEngineConfig>
    ): HttpClient
}
