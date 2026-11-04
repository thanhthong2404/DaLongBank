package com.example.studijetpack.feature.account.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.studijetpack.feature.account.AccountMenu

@Composable
fun AccountMenuSection(
    title: String,
    menus: List<AccountMenu>,
    onMenuClick: (AccountMenu) -> Unit,
    trailingContent: (@Composable (AccountMenu) -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = Color(0xFF174F91),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                top = 18.dp,
                bottom = 5.dp
            )
        )

        menus.forEachIndexed { index, menu ->
            AccountMenuItem(
                title = menu.title,
                subtitle = menu.subtitle,
                icon = menu.icon,
                onClick = { onMenuClick(menu) },
                trailing = trailingContent?.let { content ->
                    { content(menu) }
                }
            )

            if (index < menus.lastIndex) {
                HorizontalDivider(
                    color = Color(0xFFF0F2F5),
                    thickness = 1.dp
                )
            }
        }
    }
}