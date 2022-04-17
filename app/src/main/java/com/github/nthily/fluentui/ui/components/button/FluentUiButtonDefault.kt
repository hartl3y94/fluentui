package com.github.nthily.fluentui.ui.components.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.nthily.fluentui.ui.components.theme.FluentUi

@Stable
interface BaseButtonInterface {
    val paddingValues: PaddingValues
    val minHeight: Dp
    val shape: Shape
    val rippleColor: Color
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>
    @Composable
    fun contentColor(enabled: Boolean): State<Color>
}

// Reference Source: https://github.com/microsoft/fluentui-android/blob/master/fluentui_others/src/main/res/values/styles.xml
class BaseButtonImpl(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color,
    override val paddingValues: PaddingValues = PaddingValues(start = 8.dp, end = 8.dp, top = (8.5).dp, bottom = (8.5).dp),
    override val minHeight: Dp = 36.dp,
    override val shape: Shape = RoundedCornerShape(2.dp),
    override val rippleColor: Color,
) : BaseButtonInterface {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }
    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }
}

object FluentUiButtonDefault {

    @Composable
    fun buttonValues(
        backgroundColor: Color = FluentUi.colors.buttonBackgroundDefaultColor,
        contentColor: Color = FluentUi.colors.buttonTextDefaultColor,
        disabledBackgroundColor: Color = FluentUi.colors.buttonBackgroundDisabledColor,
        disabledContentColor: Color = FluentUi.colors.buttonTextDisabledColor,
        minHeight: Dp = 36.dp,
        rippleColor: Color = FluentUi.colors.buttonBackgroundPressedColor,
    ): BaseButtonInterface {
        return BaseButtonImpl(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
            minHeight = minHeight,
            paddingValues = PaddingValues(horizontal = 16.dp, vertical = (8.5).dp),
            rippleColor = rippleColor,
        )
    }

    @Composable
    fun borderlessButtonValues(
        backgroundColor: Color = FluentUi.colors.buttonBorderlessBackgroundDefaultColor,
        contentColor: Color = FluentUi.colors.buttonBorderlessTextDefaultColor,
        disabledBackgroundColor: Color = FluentUi.colors.buttonBorderlessBackgroundDisabledColor,
        disabledContentColor: Color = FluentUi.colors.buttonBorderlessTextDisabledColor,
        rippleColor: Color = FluentUi.colors.buttonBorderlessBackgroundPressedColor,
    ): BaseButtonInterface {
        return BaseButtonImpl(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
            rippleColor = rippleColor,
        )
    }
}