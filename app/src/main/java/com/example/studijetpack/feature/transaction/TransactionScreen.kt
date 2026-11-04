package com.example.studijetpack.feature.transaction

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.studijetpack.core.component.BaseTopBar
import com.example.studijetpack.feature.transaction.components.TransactionDetailDialog
import com.example.studijetpack.feature.transaction.components.TransactionFilter
import com.example.studijetpack.feature.transaction.components.TransactionItem
//import com.example.studijetpack.feature.transaction.components.TransactionPeriod
import com.example.studijetpack.feature.transaction.components.TransactionSearchBar
import com.example.studijetpack.feature.transaction.components.TransactionSummaryCard

@Composable
fun TransactionScreen(
    onBackClick: () -> Unit = {},
    onCallClick: () -> Unit = {}
) {
    var searchQuery by remember {
        mutableStateOf("")
    }

    var selectedType by remember {
        mutableStateOf<TransactionType?>(null)
    }

//    var selectedPeriod by remember {
//        mutableStateOf(TransactionPeriod.ALL)
//    }

    var selectedTransaction by remember {
        mutableStateOf<TransactionModel?>(null)
    }

    val allTransactions = TransactionSampleData.transactions

    // Lọc theo khoảng thời gian
//    val periodTransactions = remember(
//        allTransactions,
//        selectedPeriod
//    ) {
//        allTransactions.filter { transaction ->
//            val days = selectedPeriod.days
//
//            days == null || transaction.daysAgo < days
//        }
//    }

    // Lọc theo loại giao dịch và từ khóa
    val filteredTransactions = remember(
//        periodTransactions,
        selectedType,
        searchQuery
    ) {
        allTransactions.filter { transaction ->

            val matchesType =
                selectedType == null ||
                        transaction.type == selectedType

            val query = searchQuery.trim()

            val matchesSearch =
                query.isBlank() ||
                        transaction.title.contains(
                            query,
                            ignoreCase = true
                        ) ||
                        transaction.description.contains(
                            query,
                            ignoreCase = true
                        ) ||
                        transaction.transactionCode.contains(
                            query,
                            ignoreCase = true
                        )

            matchesType && matchesSearch
        }
    }

    // Tổng thu chi chỉ tính giao dịch thành công
    val income = allTransactions
        .filter {
            it.type == TransactionType.INCOME &&
                    it.status == TransactionStatus.SUCCESS
        }
        .sumOf { it.amount }

    val expense = allTransactions
        .filter {
            it.type == TransactionType.EXPENSE &&
                    it.status == TransactionStatus.SUCCESS
        }
        .sumOf { it.amount }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            BaseTopBar(
                title = "Giao dịch",
                showTitle = true,
                showBackButton = false,
                showCallButton = true,
                onBackClick = onBackClick,
                onCallClick = onCallClick
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Summary
            item {
                TransactionSummaryCard(
                    income = income,
                    expense = expense
                )
            }

            // Search
            item {
                TransactionSearchBar(
                    query = searchQuery,
                    onQueryChange = {
                        searchQuery = it
                    }
                )
            }

            // Filter
            item {
                TransactionFilter(
                    selectedType = selectedType,
                    onTypeChange = {
                        selectedType = it
                    },
//                    selectedPeriod = selectedPeriod,
//                    onPeriodChange = {
//                        selectedPeriod = it
//                    }
                )
            }

            // Section title
            item {
                Text(
                    text = "Lịch sử giao dịch",
                    color = Color(0xFF172B4D),
                    fontWeight = FontWeight.Bold,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
            }

            if (filteredTransactions.isEmpty()) {

                item {
                    Text(
                        text = "Không tìm thấy giao dịch phù hợp.",
                        color = Color(0xFF8792A2),
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                }

            } else {

                // Group by date
                val groupedTransactions =
                    filteredTransactions.groupBy { it.date }

                groupedTransactions.forEach { (date, transactions) ->

                    item(key = "date_$date") {
                        Text(
                            text = date,
                            color = Color(0xFF8792A2),
                            fontWeight = FontWeight.SemiBold,
                            style = androidx.compose.material3.MaterialTheme.typography.labelMedium
                        )
                    }

                    items(
                        items = transactions,
                        key = { it.id }
                    ) { transaction ->

                        Column {
                            TransactionItem(
                                transaction = transaction,
                                onClick = {
                                    selectedTransaction = transaction
                                }
                            )

                            androidx.compose.material3.HorizontalDivider(
                                color = Color(0xFFEDF0F4)
                            )
                        }
                    }
                }
            }
        }
    }

    selectedTransaction?.let { transaction ->
        TransactionDetailDialog(
            transaction = transaction,
            onDismiss = {
                selectedTransaction = null
            }
        )
    }
}