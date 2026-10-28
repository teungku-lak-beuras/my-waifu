package zeef.mywaifu.repository

import kotlinx.coroutines.flow.Flow
import zeef.mywaifu.model.MyWaifuModelV1
import zeef.mywaifu.model.MyWaifuState

expect class MyWaifuLocalRepository() {
    fun getAllWaifu(): Flow<MyWaifuState<List<MyWaifuModelV1>>>
    fun insertWaifu(waifu: MyWaifuModelV1)
}
