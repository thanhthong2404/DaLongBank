package com.example.studijetpack.feature.account

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Policy

object AccountSampleData {

    val user = AccountUser(
        fullName = "Nguyễn Văn An",
        customerId = "DL000123",
        phone = "098****123",
        email = "an***@gmail.com",
        isVerified = true
    )

    val bankAccounts = listOf(
        BankAccount(
            bankName = "Vietcombank",
            accountNumber = "**** **** 8291",
            accountHolder = "NGUYEN VAN AN",
            bankLogoText = "VCB",
            isDefault = true
        ),
        BankAccount(
            bankName = "BIDV",
            accountNumber = "**** **** 5628",
            accountHolder = "NGUYEN VAN AN",
            bankLogoText = "BIDV",
            isDefault = false
        )
    )

    val profileMenus = listOf(
        AccountMenu(
            title = "Hồ sơ cá nhân",
            subtitle = "Thông tin và định danh khách hàng",
            type = AccountMenuType.PROFILE,
            icon = Icons.Default.Badge
        ),
        AccountMenu(
            title = "Tài khoản ngân hàng",
            subtitle = "Quản lý tài khoản liên kết",
            type = AccountMenuType.BANK_ACCOUNTS,
            icon = Icons.Default.AccountBalance
        )
    )

    val securityMenus = listOf(
        AccountMenu(
            title = "Đổi mật khẩu",
            subtitle = "Cập nhật mật khẩu đăng nhập",
            type = AccountMenuType.CHANGE_PASSWORD,
            icon = Icons.Default.Lock
        ),
        AccountMenu(
            title = "Thiết bị đăng nhập",
            subtitle = "Quản lý thiết bị đã đăng nhập",
            type = AccountMenuType.DEVICES,
            icon = Icons.Default.Devices
        ),
        AccountMenu(
            title = "Cài đặt bảo mật",
            subtitle = "Quản lý các thiết lập bảo vệ tài khoản",
            type = AccountMenuType.BIOMETRIC,
            icon = Icons.Default.Fingerprint
        )
    )

    val supportMenus = listOf(
        AccountMenu(
            title = "Liên hệ hỗ trợ",
            subtitle = "Hotline và kênh hỗ trợ khách hàng",
            type = AccountMenuType.CONTACT_SUPPORT,
            icon = Icons.Default.SupportAgent
        ),
        AccountMenu(
            title = "Câu hỏi thường gặp",
            subtitle = "Giải đáp các vấn đề phổ biến",
            type = AccountMenuType.FAQ,
            icon = Icons.Default.HelpOutline
        ),
        AccountMenu(
            title = "Điều khoản sử dụng",
            type = AccountMenuType.TERMS,
            icon = Icons.Default.Policy
        ),
        AccountMenu(
            title = "Chính sách bảo mật",
            type = AccountMenuType.PRIVACY,
            icon = Icons.Default.PrivacyTip
        )
    )
}