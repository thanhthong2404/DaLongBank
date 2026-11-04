package com.example.studijetpack.feature.transaction.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studijetpack.feature.transaction.TransactionModel
import com.example.studijetpack.feature.transaction.TransactionType
import com.example.studijetpack.feature.transaction.TransactionStatus

@Composable
fun TransactionItem(
    transaction: TransactionModel,
    onClick: () -> Unit
) {
    val isIncome = transaction.type == TransactionType.INCOME

    val amountColor = if (isIncome) {
        Color(0xFF16A34A)
    } else {
        Color(0xFF172B4D)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(44.dp),
            shape = CircleShape,
            color = if (isIncome) {
                Color(0xFFE8F7EF)
            } else {
                Color(0xFFEAF0F8)
            }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = if (isIncome) {
                        Icons.Default.ArrowDownward
                    } else {
                        Icons.Default.ArrowUpward
                    },
                    contentDescription = null,
                    tint = if (isIncome) {
                        Color(0xFF16A34A)
                    } else {
                        Color(0xFF174F91)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = transaction.title,
                color = Color(0xFF172B4D),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = transaction.description,
                color = Color(0xFF8792A2),
                fontSize = 11.sp
            )

            TransactionStatusLabel(transaction.status)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "${if (isIncome) "+" else "-"}${formatMoney(transaction.amount)} ₫",
                color = amountColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = transaction.time,
                color = Color(0xFF8792A2),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun TransactionStatusLabel(
    status: TransactionStatus
) {
    val (label, background, foreground) = when (status) {
        TransactionStatus.SUCCESS -> Triple(
            "Thành công",
            Color(0xFFE8F7EF),
            Color(0xFF168348)
        )

        TransactionStatus.PENDING -> Triple(
            "Đang xử lý",
            Color(0xFFFFF5DF),
            Color(0xFFB7791F)
        )

        TransactionStatus.FAILED -> Triple(
            "Thất bại",
            Color(0xFFFDECEC),
            Color(0xFFDC2626)
        )
    }

    Surface(
        shape = RoundedCornerShape(6.dp),
        color = background
    ) {
        Text(
            text = label,
            color = foreground,
            fontSize = 9.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                horizontal = 7.dp,
                vertical = 3.dp
            )
        )
    }
}