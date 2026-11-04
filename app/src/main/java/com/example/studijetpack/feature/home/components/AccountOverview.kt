package com.example.studijetpack.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AccountOverview(
    accountName : String,
    balance: String,
    accountNumber: String,
) {

    var isBalanceVisible by rememberSaveable {
        mutableStateOf(true)
    }

    val primaryBlue = Color(0xFF174F91)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        primaryBlue,
                        Color(0xFF2875B9),
                        Color(0xFF123D73)
                    )
                )
            )
            .padding(12.dp)
    ) {

        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AccountBalanceWallet,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "TÀI KHOẢN THANH TOÁN",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "TÀI CHÍNH ĐA LONG",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 10.sp
                )
            }

            Surface(
                color = Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Đang hoạt động",
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Balance
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Số dư khả dụng",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    isBalanceVisible = !isBalanceVisible
                },
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = if (isBalanceVisible) {
                        Icons.Default.Visibility
                    } else {
                        Icons.Default.VisibilityOff
                    },
                    contentDescription = if (isBalanceVisible) {
                        "Ẩn số dư"
                    } else {
                        "Hiện số dư"
                    },
                    tint = Color.White,
                    modifier = Modifier.size(19.dp)
                )
            }
        }

        Text(
            text = if (isBalanceVisible) {
                "$balance ₫"
            } else {
                "•••••••••• ₫"
            },
            color = Color.White,
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.3.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Số dư tài khoản của bạn",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        HorizontalDivider(
            color = Color.White.copy(alpha = 0.25f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Account information
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Số tài khoản",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "•••• •••• $accountNumber",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Chủ tài khoản",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = accountName,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}