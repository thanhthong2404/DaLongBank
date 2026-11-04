package com.example.studijetpack.feature.transaction.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studijetpack.feature.transaction.TransactionType

//enum class TransactionPeriod(
//    val title: String,
//    val days: Int?
//) {
//    ALL("Tất cả", null),
//    SEVEN_DAYS("7 ngày", 7),
//    THIRTY_DAYS("30 ngày", 30)
//}

@Composable
fun TransactionFilter(
    selectedType: TransactionType?,
    onTypeChange: (TransactionType?) -> Unit,
//    selectedPeriod: TransactionPeriod,
//    onPeriodChange: (TransactionPeriod) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.horizontalScroll(
                rememberScrollState()
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedType == null,
                onClick = { onTypeChange(null) },
                label = { Text("Tất cả") }
            )

            FilterChip(
                selected = selectedType == TransactionType.INCOME,
                onClick = {
                    onTypeChange(TransactionType.INCOME)
                },
                label = { Text("Tiền vào") }
            )

            FilterChip(
                selected = selectedType == TransactionType.EXPENSE,
                onClick = {
                    onTypeChange(TransactionType.EXPENSE)
                },
                label = { Text("Tiền ra") }
            )
        }

//        Row(
//            horizontalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            TransactionPeriod.entries.forEach { period ->
//                FilterChip(
//                    selected = selectedPeriod == period,
//                    onClick = {
//                        onPeriodChange(period)
//                    },
//                    label = {
//                        Text(period.title)
//                    }
//                )
//            }
//        }
    }
}