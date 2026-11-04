package com.example.studijetpack.feature.transaction

object TransactionSampleData {

    val transactions = listOf(
        TransactionModel(
            id = "1",
            title = "Nguyễn Minh Anh",
            description = "Nhận tiền chuyển khoản",
            amount = 5_000_000,
            type = TransactionType.INCOME,
            status = TransactionStatus.SUCCESS,
            date = "04/11/2026",
            time = "10:25",
            transactionCode = "FT2611040001",
            accountNumber = "****8291",
            daysAgo = 0
        ),
        TransactionModel(
            id = "2",
            title = "Thanh toán mua sắm",
            description = "Thanh toán hóa đơn",
            amount = 850_000,
            type = TransactionType.EXPENSE,
            status = TransactionStatus.SUCCESS,
            date = "04/11/2026",
            time = "09:15",
            transactionCode = "FT2611040002",
            accountNumber = "****8291",
            daysAgo = 0
        ),
        TransactionModel(
            id = "3",
            title = "Nạp tiền điện thoại",
            description = "Viettel - 098xxxxxxx",
            amount = 200_000,
            type = TransactionType.EXPENSE,
            status = TransactionStatus.SUCCESS,
            date = "04/11/2026",
            time = "08:30",
            transactionCode = "FT2611040003",
            accountNumber = "****8291",
            daysAgo = 0
        ),
        TransactionModel(
            id = "4",
            title = "Công ty ABC",
            description = "Nhận lương tháng 10",
            amount = 20_500_000,
            type = TransactionType.INCOME,
            status = TransactionStatus.SUCCESS,
            date = "03/11/2026",
            time = "15:30",
            transactionCode = "FT2611030001",
            accountNumber = "****8291",
            daysAgo = 1
        ),
        TransactionModel(
            id = "5",
            title = "Thanh toán tiền điện",
            description = "EVN - Hóa đơn điện",
            amount = 1_250_000,
            type = TransactionType.EXPENSE,
            status = TransactionStatus.SUCCESS,
            date = "02/11/2026",
            time = "11:20",
            transactionCode = "FT2611020001",
            accountNumber = "****8291",
            daysAgo = 2
        ),
        TransactionModel(
            id = "6",
            title = "Trần Văn Nam",
            description = "Chuyển tiền",
            amount = 2_000_000,
            type = TransactionType.EXPENSE,
            status = TransactionStatus.PENDING,
            date = "01/11/2026",
            time = "16:45",
            transactionCode = "FT2611010001",
            accountNumber = "****8291",
            daysAgo = 3
        ),
        TransactionModel(
            id = "7",
            title = "Hoàn tiền giao dịch",
            description = "Hoàn tiền mua sắm",
            amount = 350_000,
            type = TransactionType.INCOME,
            status = TransactionStatus.FAILED,
            date = "28/10/2026",
            time = "14:10",
            transactionCode = "FT2610280001",
            accountNumber = "****8291",
            daysAgo = 7
        )
    )
}