package zeef.mywaifu.provider

import kotlinx.coroutines.flow.StateFlow
import zeef.mywaifu.model.MyWaifuState
import zeef.mywaifu.repository.MyWaifuRepository

expect object MyWaifuRepositoryProvider {
    val repository: StateFlow<MyWaifuState<MyWaifuRepository>>
    fun provideRepository(parameter: Any? = null)
}
