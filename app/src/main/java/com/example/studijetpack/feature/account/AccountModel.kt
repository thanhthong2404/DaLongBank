package com.example.studijetpack.feature.account

import androidx.compose.ui.graphics.vector.ImageVector

data class AccountUser(
    val fullName: String,
    val customerId: String,
    val phone: String,
    val email: String,
    val isVerified: Boolean
)

data class BankAccount(
    val bankName: String,
    val accountNumber: String,
    val accountHolder: String,
    val bankLogoText: String,
    val isDefault: Boolean
)

enum class AccountMenuType {
    PROFILE,
    CHANGE_PASSWORD,
    BIOMETRIC,
    DEVICES,
    BANK_ACCOUNTS,
    CONTACT_SUPPORT,
    FAQ,
    TERMS,
    PRIVACY
}

data class AccountMenu(
    val title: String,
    val subtitle: String? = null,
    val type: AccountMenuType,
    val icon: ImageVector
)