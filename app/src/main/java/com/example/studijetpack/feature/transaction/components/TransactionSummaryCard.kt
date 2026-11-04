package com.example.studijetpack.feature.transaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

private val PrimaryBlue = Color(0xFF174F91)

@Composable
fun TransactionSummaryCard(
    income: Long,
    expense: Long
) {
    val balance = income - expense

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryBlue
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "TỔNG QUAN GIAO DỊCH",
                color = Color.White.copy(alpha = 0.75f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SummaryItem(
                    title = "Tiền vào",
                    amount = income,
                    isIncome = true
                )

                SummaryItem(
                    title = "Tiền ra",
                    amount = expense,
                    isIncome = false
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            HorizontalDivider(
                color = Color.White.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chênh lệch thu chi",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )

                Text(
                    text = "${if (balance >= 0) "+" else "-"}${formatMoney(kotlin.math.abs(balance))} ₫",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SummaryItem(
    title: String,
    amount: Long,
    isIncome: Boolean
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = if (isIncome) {
                    Icons.Default.ArrowDownward
                } else {
                    Icons.Default.ArrowUpward
                },
                contentDescription = null,
                tint = if (isIncome) {
                    Color(0xFF86EFAC)
                } else {
                    Color(0xFFFCA5A5)
                },
                modifier = Modifier.size(17.dp)
            )

            Text(
                text = title,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp
            )
        }

        Text(
            text = "${formatMoney(amount)} ₫",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun formatMoney(amount: Long): String {
    return "%,d".format(amount).replace(',', '.')
}