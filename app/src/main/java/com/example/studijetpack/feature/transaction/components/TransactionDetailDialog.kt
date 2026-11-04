package com.example.studijetpack.feature.transaction.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studijetpack.feature.transaction.TransactionModel
import com.example.studijetpack.feature.transaction.TransactionType

@Composable
fun TransactionDetailDialog(
    transaction: TransactionModel,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Chi tiết giao dịch")
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                Text(
                    text = if (transaction.type == TransactionType.INCOME) {
                        "+${formatMoney(transaction.amount)} ₫"
                    } else {
                        "-${formatMoney(transaction.amount)} ₫"
                    },
                    color = if (transaction.type == TransactionType.INCOME) {
                        Color(0xFF16A34A)
                    } else {
                        Color(0xFF172B4D)
                    },
                    fontWeight = FontWeight.Bold
                )

                HorizontalDivider()

                DetailRow("Nội dung", transaction.description)
                DetailRow("Đối tượng", transaction.title)
                DetailRow("Ngày giao dịch", transaction.date)
                DetailRow("Thời gian", transaction.time)
                DetailRow("Mã giao dịch", transaction.transactionCode)
                DetailRow("Tài khoản", transaction.accountNumber)
                DetailRow(
                    "Trạng thái",
                    when (transaction.status) {
                        com.example.studijetpack.feature.transaction.TransactionStatus.SUCCESS -> "Thành công"
                        com.example.studijetpack.feature.transaction.TransactionStatus.PENDING -> "Đang xử lý"
                        com.example.studijetpack.feature.transaction.TransactionStatus.FAILED -> "Thất bại"
                    }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Đóng")
            }
        },
        containerColor = Color.White
    )
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            color = Color(0xFF8792A2),
            style = androidx.compose.material3.MaterialTheme.typography.labelSmall
        )

        Text(
            text = value,
            color = Color(0xFF172B4D),
            style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}