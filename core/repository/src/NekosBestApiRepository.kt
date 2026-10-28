package zeef.mywaifu.repository

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import zeef.mywaifu.model.MyWaifuModelV1
import zeef.mywaifu.model.MyWaifuState
import zeef.mywaifu.network.NekosBestApiDataSource

class NekosBestApiRepository(
    private val nekosBestApiDataSource: NekosBestApiDataSource
) {
    fun getWaifu(amount: Int): Flow<MyWaifuState<List<MyWaifuModelV1>>> = flow {
        emit(MyWaifuState.Loading)

        try {
            val waifus = mutableListOf<MyWaifuModelV1>()
            val response = nekosBestApiDataSource.getWaifu(amount)

            for (i in response.results) {
                waifus.add(
                    MyWaifuModelV1(
                        artistHref = i.artistHref,
                        artistName = i.artistName,
                        sourceUrl = i.sourceUrl,
                        url = i.url
                    )
                )
            }
            emit(MyWaifuState.Success(data = waifus))
        }
        // 4xx HTTP error.
        catch (exception: ClientRequestException) {
            emit(MyWaifuState.Error(message = "HTTP 400 Error: ${exception.message}"))
        }
        // 5xx HTTP error.
        catch (exception: ServerResponseException) {
            emit(MyWaifuState.Error(message = "HTTP 500 Error: ${exception.message}"))
        }
        // Serialisation
        catch (exception: SerializationException) {
            emit(MyWaifuState.Error(message = "Serialisation Error: ${exception.message.toString()}"))
        }
        // IO
        catch (exception: IOException) {
            emit(MyWaifuState.Error(message = "IO Error: ${exception.message.toString()}"))
        }
        // Bruh
        catch (exception: Exception) {
            emit(MyWaifuState.Error(message = "Unexpected Error: ${exception.message.toString()}"))
        }
    }
}
