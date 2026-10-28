package zeef.mywaifu.provider

import android.content.Context
import heaven.from.network.provider.NekosBestApiProvider
import heaven.from.network.provider.NekosiaCatApiProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.io.IOException
import zeef.mywaifu.model.MyWaifuState
import zeef.mywaifu.network.NekosBestApiDataSource
import zeef.mywaifu.network.NekosiaCatApiDataSource
import zeef.mywaifu.repository.MyWaifuLocalRepository
import zeef.mywaifu.repository.MyWaifuRepository
import zeef.mywaifu.repository.NekosBestApiRepository
import zeef.mywaifu.repository.NekosiaCatApiRepository

actual object MyWaifuRepositoryProvider {
    private val _repository = MutableStateFlow<MyWaifuState<MyWaifuRepository>>(
        MyWaifuState.Loading
    )
    actual val repository = _repository.asStateFlow()

    actual fun provideRepository(parameter: Any?) {
        // --- Multiple times reinstantiating prevention ---
        if (_repository.value is MyWaifuState.Success) {
            return
        }

        // --- Emission loading ---
        _repository.value = MyWaifuState.Loading

        try {
            if (parameter != null && parameter is Context) {
                // --- Network ---
                val engine = NekosBestApiProvider.provideEngine()
                val nekosBestApiDataSource = NekosBestApiDataSource(
                    client = NekosBestApiProvider.provideHttpClient(
                        engine = engine
                    )
                )
                val nekosBestApiRepository = NekosBestApiRepository(
                    nekosBestApiDataSource = nekosBestApiDataSource
                )

                val nekosiaCatApiEngine = NekosiaCatApiProvider.provideEngine()
                val nekosiaCatApiDataSource = NekosiaCatApiDataSource(
                    client = NekosiaCatApiProvider.provideHttpClient(
                        engine = nekosiaCatApiEngine
                    )
                )
                val nekosiaCatApiRepository = NekosiaCatApiRepository(
                    nekosiaCatApiDataSource = nekosiaCatApiDataSource
                )

                // --- Local ---
                // Put local data source here.
                val myWaifuLocalRepository = MyWaifuLocalRepository()

                // --- Emission success ---
                _repository.value = MyWaifuState.Success(
                    data = MyWaifuRepository(
                        myWaifuLocalRepository = myWaifuLocalRepository,
                        nekosBestApiRepository = nekosBestApiRepository,
                        nekosiaCatApiRepository = nekosiaCatApiRepository
                    )
                )
            }
            else {
                MyWaifuState.Error(
                    message = """
                        !!! STOP !!!

                        You provided `$parameter` with type of `${
                        parameter?.javaClass?.simpleName ?: null.toString()
                    } but the required parameter type is `android.content.Context`. Please
                        provide the value with correct data type.

                        This is a development fatal error and you need to recompile the project
                        with the given suggestion.
                    """.trimIndent()
                )
            }
        }
        catch (exception: IOException) {
            _repository.value = MyWaifuState.Error(
                message = """
                    !!! ALERT !!!

                    Undefined exception caught: ${exception.message.toString()}.

                    This is a fatal strange error that should not happen at all. Tell the developer
                    to investigate this.
                """.trimIndent()
            )
        }
    }
}
