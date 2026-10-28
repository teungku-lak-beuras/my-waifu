package zeef.mywaifu.repository

import kotlinx.coroutines.flow.flow
import zeef.mywaifu.model.MyWaifuModelV1
import zeef.mywaifu.model.MyWaifuState

actual class MyWaifuLocalRepository actual constructor() {
    actual fun getAllWaifu() = flow<MyWaifuState<List<MyWaifuModelV1>>> {
        emit(MyWaifuState.Loading)
        emit(
            MyWaifuState.Success(
                data = listOf(
                    MyWaifuModelV1(
                        artistHref = "Room not yet supported.",
                        artistName = "Room not yet supported.",
                        sourceUrl = "Room not yet supported.",
                        url = "Room not yet supported."
                    )
                )
            )
        )
    }

    actual fun insertWaifu(waifu: MyWaifuModelV1) {
    }
}
