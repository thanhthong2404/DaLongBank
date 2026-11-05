package com.example.studijetpack.core.component

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

private val PrimaryBlue = Color(0xFF174F91)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    title: String = "",
    showTitle: Boolean = true,
    showBackButton: Boolean = true,
    showCallButton: Boolean = true,
    backgroundColor: Color = Color.White,
    titleColor: Color = PrimaryBlue,
    iconColor: Color = PrimaryBlue
) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current
        ?.onBackPressedDispatcher

    TopAppBar(
        modifier = Modifier,
        windowInsets = WindowInsets.statusBars,
        title = {
            if (showTitle && title.isNotBlank()) {
                Text(
                    text = title,
                    color = titleColor,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    onClick = {
                        backDispatcher?.onBackPressed()
                    }
                ) {
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
                IconButton(
                    onClick = {
                        val intent = Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:19008686")
                        )
                        context.startActivity(intent)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Gọi hotline 19008686",
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