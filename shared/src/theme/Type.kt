package zeef.mywaifu.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import zeef.mywaifu.resources.Res
import zeef.mywaifu.resources.balsamiq_sans_bold
import zeef.mywaifu.resources.balsamiq_sans_bolditalic
import zeef.mywaifu.resources.balsamiq_sans_italic
import zeef.mywaifu.resources.balsamiq_sans_regular

@Composable
fun fontFamily() = FontFamily(
    Font(
        resource = Res.font.balsamiq_sans_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resource = Res.font.balsamiq_sans_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resource = Res.font.balsamiq_sans_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        resource = Res.font.balsamiq_sans_bolditalic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    )
)

@Composable
fun typography() = Typography(
    titleSmall = TextStyle(
        fontFamily = fontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontFamily(),
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 11.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontFamily(),
        fontSize = 14.sp
    )
)
