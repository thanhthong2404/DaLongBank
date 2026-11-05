package com.example.studijetpack.core.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val PrimaryBlue = Color(0xFF174F91)
private val SelectedBlue = Color(0xFF3985C8)

private data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun BaseBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("home", "Trang chủ", Icons.Default.Home),
        BottomNavItem("transaction", "Giao dịch", Icons.Default.History),
        BottomNavItem("promotion", "Ưu đãi", Icons.Default.CardGiftcard),
        BottomNavItem("account", "Tài khoản", Icons.Default.Person)
    )

    NavigationBar(
        modifier = Modifier.height(90.dp),
        containerColor = PrimaryBlue,
        contentColor = Color.White) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            val scale by animateFloatAsState(
                targetValue = if (selected) 1.25f else 1.0f, // Khi chọn phóng to 25%
                label = "iconScale"
            )
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) onNavigate(item.route)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.scale(scale)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    indicatorColor = SelectedBlue,
                    unselectedIconColor = Color.White.copy(alpha = 0.65f),
                    unselectedTextColor = Color.White.copy(alpha = 0.65f)
                )
            )
        }
    }
}