package zeef.mywaifu.repository

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import zeef.mywaifu.model.MyWaifuModelV2
import zeef.mywaifu.model.MyWaifuState
import zeef.mywaifu.network.NekosiaCatApiDataSource

class NekosiaCatApiRepository(
    private val nekosiaCatApiDataSource: NekosiaCatApiDataSource
) {
    fun getWaifu(amount: Int): Flow<MyWaifuState<List<MyWaifuModelV2>>> = flow {
        emit(MyWaifuState.Loading)

        try {
            val waifus = mutableListOf<MyWaifuModelV2>()
            val response = nekosiaCatApiDataSource.getWaifu(amount)

            for (i in response.images) {
                waifus.add(
                    MyWaifuModelV2(
                        cdnImageUrl = i.image.original.url,
                        cdnCompressedImageUrl = i.image.compressed.url,
                        imageSourceUrl = i.source.url,
                        directImageSourceUrl = i.source.direct,
                        category = i.category,
                        rating = i.rating,
                        tags = i.tags,
                        artistName = i.attribution.artist.username,
                        artistUrl = i.attribution.artist.profile,
                        copyright = i.attribution.copyright
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
