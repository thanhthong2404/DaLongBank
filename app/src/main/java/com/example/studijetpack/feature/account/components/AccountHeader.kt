package com.example.studijetpack.feature.account.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.studijetpack.feature.account.AccountUser

private val NavyBlue = Color(0xFF174F91)

@Composable
fun AccountHeader(
    user: AccountUser,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF123D70),
                        NavyBlue
                    )
                ),
                shape = RoundedCornerShape(
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )
            )
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Tài khoản của tôi",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = user.fullName
                        .split(" ")
                        .takeLast(2)
                        .mapNotNull { it.firstOrNull()?.toString() }
                        .joinToString(""),
                    color = NavyBlue,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = user.fullName,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Mã khách hàng: ${user.customerId}",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )

                if (user.isVerified) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White.copy(alpha = 0.15f))
                            .padding(horizontal = 9.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = Color(0xFF8BE2B0),
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Đã xác thực",
                            color = Color.White,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
}