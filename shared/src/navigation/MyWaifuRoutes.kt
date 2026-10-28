package zeef.mywaifu.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import zeef.mywaifu.model.MyWaifuModelV2

@Serializable
sealed interface MyWaifuRoutes : NavKey {
    @Serializable
    data object HomeRoute : MyWaifuRoutes, NavKey

    @Serializable
    data class DetailRoute(val waifu: MyWaifuModelV2) : MyWaifuRoutes, NavKey

    @Serializable
    data object HelpRoute : MyWaifuRoutes, NavKey

    @Serializable
    data object SettingsRoute : MyWaifuRoutes, NavKey

    @Serializable
    data object AboutRoute : MyWaifuRoutes, NavKey
}
