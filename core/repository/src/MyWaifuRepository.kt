package zeef.mywaifu.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import zeef.mywaifu.model.MyWaifuModelV1
import zeef.mywaifu.model.MyWaifuModelV2
import zeef.mywaifu.model.MyWaifuState

class MyWaifuRepository(
    private val myWaifuLocalRepository: MyWaifuLocalRepository,
    private val nekosBestApiRepository: NekosBestApiRepository,
    private val nekosiaCatApiRepository: NekosiaCatApiRepository
) {
    fun getNetworkWaifu(amount: Int): Flow<MyWaifuState<List<MyWaifuModelV1>>> {
        return nekosBestApiRepository
            .getWaifu(amount = amount)
            .flowOn(Dispatchers.Default)
    }

    fun getLocalWaifu(): Flow<MyWaifuState<List<MyWaifuModelV1>>> {
        return myWaifuLocalRepository
            .getAllWaifu()
            .flowOn(Dispatchers.Default)
    }

    fun getNekosiaWaifu(amount: Int): Flow<MyWaifuState<List<MyWaifuModelV2>>> {
        return nekosiaCatApiRepository
            .getWaifu(amount = amount)
            .flowOn(Dispatchers.Default)
    }
}
