package com.example.studijetpack.feature.account.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studijetpack.feature.account.profile.components.*

private val ProfileBackground = Color(0xFFF5F7FA)
private val ProfileNavy = Color(0xFF12345A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    profile: CustomerProfile = ProfileSampleData.customer,
    onBack: () -> Unit,
    onEdit: (ProfileField) -> Unit = {}
) {
    var showQrDialog by remember { mutableStateOf(false) }

    BackHandler {
        onBack()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ProfileBackground)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .statusBarsPadding()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Quay lại",
                    tint = ProfileNavy
                )
            }

            Text(
                text = "Hồ sơ cá nhân",
                modifier = Modifier.weight(1f),
                color = Color(0xFF172B45),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { onEdit(ProfileField.FULL_NAME) }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Chỉnh sửa hồ sơ",
                    tint = ProfileNavy
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                ProfileHeader(
                    fullName = profile.fullName,
                    customerId = profile.customerId,
                    isVerified = profile.isVerified,
                    onQrClick = {
                        showQrDialog = true
                    }
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    ProfileVerificationCard(
                        isVerified = profile.isVerified
                    )
                }
            }

            item {
                ProfileSection(
                    title = "Thông tin cá nhân",
                    subtitle = "Thông tin cơ bản của khách hàng"
                ) {
                    ProfileInfoRow(
                        icon = Icons.Default.Person,
                        label = "Họ và tên",
                        value = profile.fullName,
                        editable = true,
                        onClick = { onEdit(ProfileField.FULL_NAME) }
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.CalendarMonth,
                        label = "Ngày sinh",
                        value = profile.dateOfBirth,
                        editable = true,
                        onClick = { onEdit(ProfileField.DATE_OF_BIRTH) }
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.Wc,
                        label = "Giới tính",
                        value = profile.gender,
                        editable = true,
                        onClick = { onEdit(ProfileField.GENDER) }
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.Phone,
                        label = "Số điện thoại",
                        value = profile.phone,
                        editable = true,
                        onClick = { onEdit(ProfileField.PHONE) }
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.Email,
                        label = "Email",
                        value = profile.email,
                        editable = true,
                        onClick = { onEdit(ProfileField.EMAIL) }
                    )
                }
            }

            item {
                ProfileSection(
                    title = "Thông tin định danh",
                    subtitle = "Thông tin giấy tờ tùy thân đã đăng ký"
                ) {
                    ProfileInfoRow(
                        icon = Icons.Default.CreditCard,
                        label = "Số CCCD",
                        value = profile.identityNumber
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.CalendarToday,
                        label = "Ngày cấp",
                        value = profile.identityIssueDate
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.AccountBalance,
                        label = "Nơi cấp",
                        value = profile.identityIssuePlace
                    )
                }
            }

            item {
                ProfileSection(
                    title = "Địa chỉ",
                    subtitle = "Thông tin địa chỉ liên hệ"
                ) {
                    ProfileInfoRow(
                        icon = Icons.Default.Home,
                        label = "Địa chỉ thường trú",
                        value = profile.permanentAddress,
                        editable = true,
                        onClick = {
                            onEdit(ProfileField.PERMANENT_ADDRESS)
                        }
                    )

                    ProfileInfoRow(
                        icon = Icons.Default.LocationOn,
                        label = "Địa chỉ liên hệ",
                        value = profile.contactAddress,
                        editable = true,
                        onClick = {
                            onEdit(ProfileField.CONTACT_ADDRESS)
                        }
                    )
                }
            }

            item {
                ProfileSection(
                    title = "Thông tin tài khoản"
                ) {
                    ProfileStatusRow(
                        icon = Icons.Default.Badge,
                        label = "Mã khách hàng",
                        value = profile.customerId
                    )

                    ProfileStatusRow(
                        icon = Icons.Default.CalendarMonth,
                        label = "Ngày đăng ký",
                        value = profile.registrationDate
                    )

                    ProfileStatusRow(
                        icon = Icons.Default.VerifiedUser,
                        label = "Trạng thái tài khoản",
                        value = if (profile.isActive) {
                            "Đang hoạt động"
                        } else {
                            "Tạm khóa"
                        }
                    )
                }
            }
        }
    }

    if (showQrDialog) {
        AlertDialog(
            onDismissRequest = { showQrDialog = false },
            icon = {
                Icon(
                    imageVector = Icons.Default.QrCode2,
                    contentDescription = null,
                    tint = ProfileNavy,
                    modifier = Modifier.size(36.dp)
                )
            },
            title = {
                Text("Mã khách hàng")
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = profile.customerId,
                        fontWeight = FontWeight.Bold,
                        color = ProfileNavy
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Mã QR sẽ được tích hợp khi kết nối dịch vụ QR khách hàng.",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showQrDialog = false }) {
                    Text("Đóng")
                }
            }
        )
    }
}