package zeef.mywaifu.local

import zeef.mywaifu.local.entity.MyWaifuEntity

interface IMyWaifuLocalDataSource {
    suspend fun getAllWaifu(): List<MyWaifuEntity>
    suspend fun insertWaifu(waifu: MyWaifuEntity)
}
