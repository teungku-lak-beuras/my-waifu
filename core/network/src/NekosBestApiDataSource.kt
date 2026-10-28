package zeef.mywaifu.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import zeef.mywaifu.network.response.NekosBestApiResponse

class NekosBestApiDataSource(
    private val client: HttpClient
) {
    suspend fun getWaifu(amount: Int): NekosBestApiResponse {
        return client.get("waifu") {
            url {
                parameters.append("amount", amount.toString())
            }
        }.body()
    }
}
