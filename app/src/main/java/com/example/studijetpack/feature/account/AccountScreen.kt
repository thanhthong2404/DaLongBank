package com.example.studijetpack.feature.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.studijetpack.feature.account.components.*

private val PageBackground = Color(0xFFF5F7FA)
private val PrimaryBlue = Color(0xFF174F91)

@Composable
fun AccountScreen(
    user: AccountUser = AccountSampleData.user,
    bankAccounts: List<BankAccount> = AccountSampleData.bankAccounts,
    onMenuClick: (AccountMenuType) -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var biometricEnabled by remember { mutableStateOf(true) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var selectedMenu by remember { mutableStateOf<AccountMenu?>(null) }
    var selectedBank by remember { mutableStateOf<BankAccount?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBackground),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Header
        item {
            AccountHeader(user = user)
        }

        // Thông tin cá nhân
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AccountMenuSection(
                    title = "THÔNG TIN CÁ NHÂN",
                    menus = AccountSampleData.profileMenus,
                    onMenuClick = { menu ->
                        when (menu.type) {
                            AccountMenuType.PROFILE -> {
                                onMenuClick(menu.type)
                                print("onMenuClick(menu.type) ${menu.type}")
                            }

                            AccountMenuType.BANK_ACCOUNTS -> {
                                selectedMenu = menu
                            }

                            else -> {
                                selectedMenu = menu
                            }
                        }
                    }
                )
            }
        }

        // Tài khoản ngân hàng
        item {
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp
                )
            ) {
                Text(
                    text = "TÀI KHOẢN NGÂN HÀNG LIÊN KẾT",
                    color = PrimaryBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 10.dp
                    )
                )

                bankAccounts.forEach { account ->
                    AccountBankCard(
                        account = account,
                        onClick = {
                            selectedBank = account
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // Bảo mật
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AccountMenuSection(
                    title = "BẢO MẬT",
                    menus = AccountSampleData.securityMenus,
                    onMenuClick = { menu ->
                        selectedMenu = menu
                        onMenuClick(menu.type)
                    }
                )

                AccountMenuItem(
                    title = "Đăng nhập sinh trắc học",
                    subtitle = "Sử dụng vân tay hoặc nhận diện khuôn mặt",
                    icon = Icons.Default.Fingerprint,
                    onClick = {
                        biometricEnabled = !biometricEnabled
                    },
                    trailing = {
                        Switch(
                            checked = biometricEnabled,
                            onCheckedChange = {
                                biometricEnabled = it
                            }
                        )
                    }
                )
            }
        }

        // Hỗ trợ
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AccountMenuSection(
                    title = "HỖ TRỢ KHÁCH HÀNG",
                    menus = AccountSampleData.supportMenus,
                    onMenuClick = { menu ->
                        selectedMenu = menu
                        onMenuClick(menu.type)
                    }
                )
            }
        }

        // Đăng xuất
        item {
            Column(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 20.dp
                )
            ) {
                OutlinedButton(
                    onClick = {
                        showLogoutDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFD64545)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Đăng xuất",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "Tài chính Đa Long • Phiên bản 1.0.0",
                    color = Color(0xFF98A2B3),
                    fontSize = 11.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }

    // Dialog xác nhận đăng xuất
    if (showLogoutDialog) {
        AccountLogoutDialog(
            onDismiss = {
                showLogoutDialog = false
            },
            onConfirm = {
                showLogoutDialog = false
                onLogout()
            }
        )
    }

    // Dialog thông tin chức năng
    selectedMenu?.let { menu ->
        AlertDialog(
            onDismissRequest = {
                selectedMenu = null
            },
            title = {
                Text(menu.title)
            },
            text = {
                Text(getMenuDescription(menu.type))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedMenu = null
                    }
                ) {
                    Text("Đóng")
                }
            }
        )
    }

    // Dialog tài khoản ngân hàng
    selectedBank?.let { bank ->
        AlertDialog(
            onDismissRequest = {
                selectedBank = null
            },
            title = {
                Text(bank.bankName)
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Số tài khoản: ${bank.accountNumber}")
                    Text("Chủ tài khoản: ${bank.accountHolder}")

                    if (bank.isDefault) {
                        Text(
                            text = "Đây là tài khoản mặc định",
                            color = Color(0xFF16865B)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedBank = null
                    }
                ) {
                    Text("Đóng")
                }
            }
        )
    }
}

private fun getMenuDescription(type: AccountMenuType): String {
    return when (type) {
        AccountMenuType.PROFILE ->
            "Thông tin hồ sơ cá nhân và định danh khách hàng."

        AccountMenuType.CHANGE_PASSWORD ->
            "Tại đây bạn có thể cập nhật mật khẩu đăng nhập."

        AccountMenuType.BIOMETRIC ->
            "Quản lý các phương thức xác thực và bảo vệ tài khoản."

        AccountMenuType.DEVICES ->
            "Xem và quản lý các thiết bị đã đăng nhập tài khoản."

        AccountMenuType.BANK_ACCOUNTS ->
            "Quản lý các tài khoản ngân hàng liên kết."

        AccountMenuType.CONTACT_SUPPORT ->
            "Liên hệ bộ phận chăm sóc khách hàng để được hỗ trợ."

        AccountMenuType.FAQ ->
            "Tìm câu trả lời cho các câu hỏi thường gặp."

        AccountMenuType.TERMS ->
            "Xem các điều khoản sử dụng dịch vụ."

        AccountMenuType.PRIVACY ->
            "Xem chính sách bảo mật thông tin khách hàng."
    }
}