package com.example.studijetpack.feature.transaction

enum class TransactionType {
    INCOME,
    EXPENSE
}

enum class TransactionStatus {
    SUCCESS,
    PENDING,
    FAILED
}

data class TransactionModel(
    val id: String,
    val title: String,
    val description: String,
    val amount: Long,
    val type: TransactionType,
    val status: TransactionStatus,
    val date: String,
    val time: String,
    val transactionCode: String,
    val accountNumber: String,
    val daysAgo: Int
)