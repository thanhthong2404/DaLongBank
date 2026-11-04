package com.example.studijetpack.core.component
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val PrimaryBlue = Color(0xFF174F91)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    title: String = "",
    showTitle: Boolean = true,
    showBackButton: Boolean = true,
    showCallButton: Boolean = true,
    onBackClick: () -> Unit = {},
    onCallClick: () -> Unit = {},
    backgroundColor: Color = Color.White,
    titleColor: Color = PrimaryBlue,
    iconColor: Color = PrimaryBlue
) {
    TopAppBar(
        modifier = Modifier,
        windowInsets = WindowInsets.statusBars,
        title = {
            if (showTitle && title.isNotBlank()) {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = iconColor
                    )
                }
            }
        },
        actions = {
            if (showCallButton) {
                IconButton(onClick = onCallClick) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Gọi điện",
                        tint = iconColor
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor
        )
    )
}
