package com.example.studijetpack.feature.account.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF12345A)
private val PrimaryBlue = Color(0xFF2563EB)
private val TextPrimary = Color(0xFF172B45)
private val TextSecondary = Color(0xFF64748B)
private val SurfaceBackground = Color(0xFFF5F7FA)
private val BorderColor = Color(0xFFE5EAF1)
private val SuccessColor = Color(0xFF16865B)

@Composable
fun ProfileHeader(
    fullName: String,
    customerId: String,
    isVerified: Boolean,
    onQrClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Navy,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFE4EDF8), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = fullName
                        .split(" ")
                        .takeLast(2)
                        .mapNotNull { it.firstOrNull()?.uppercaseChar() }
                        .joinToString(""),
                    color = Navy,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = fullName,
                    color = Color.White,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(5.dp))

                Text(
                    text = "Mã khách hàng: $customerId",
                    color = Color(0xFFCBD8E8),
                    fontSize = 12.sp
                )

                Spacer(Modifier.height(7.dp))

                if (isVerified) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = Color(0xFF72E0AF),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = "Đã xác thực danh tính",
                            color = Color(0xFF72E0AF),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            IconButton(onClick = onQrClick) {
                Icon(
                    imageVector = Icons.Default.QrCode2,
                    contentDescription = "Mã QR khách hàng",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileSection(
    title: String,
    subtitle: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            color = TextPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

        subtitle?.let {
            Spacer(Modifier.height(4.dp))
            Text(
                text = it,
                color = TextSecondary,
                fontSize = 12.sp
            )
        }

        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 14.dp),
                content = content
            )
        }
    }
}

@Composable
fun ProfileInfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    editable: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (onClick != null) Modifier.clickable { onClick() }
                else Modifier
            )
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(
                    Color(0xFFEDF3FA),
                    RoundedCornerShape(11.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Navy,
                modifier = Modifier.size(19.dp)
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = value,
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        if (editable) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Chỉnh sửa",
                tint = TextSecondary,
                modifier = Modifier.size(20.dp)
            )
        }
    }

    HorizontalDivider(color = BorderColor, thickness = 0.7.dp)
}

@Composable
fun ProfileVerificationCard(
    isVerified: Boolean
) {
    val statusColor = if (isVerified) SuccessColor else Color(0xFFD97706)
    val backgroundColor = if (isVerified) {
        Color(0xFFEAF8F1)
    } else {
        Color(0xFFFFF7E8)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isVerified) {
                Icons.Default.VerifiedUser
            } else {
                Icons.Default.Info
            },
            contentDescription = null,
            tint = statusColor,
            modifier = Modifier.size(25.dp)
        )

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (isVerified) {
                    "Danh tính đã được xác thực"
                } else {
                    "Bạn chưa xác thực danh tính"
                },
                color = statusColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = if (isVerified) {
                    "Thông tin định danh của bạn đã được xác minh."
                } else {
                    "Vui lòng hoàn tất xác thực để sử dụng đầy đủ dịch vụ."
                },
                color = TextSecondary,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun ProfileStatusRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Navy,
            modifier = Modifier.size(20.dp)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = label,
            color = TextSecondary,
            fontSize = 13.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = value,
            color = TextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }

    HorizontalDivider(color = BorderColor, thickness = 0.7.dp)
}