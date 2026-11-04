package com.example.studijetpack.feature.account.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AccountLogoutDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Đăng xuất tài khoản?")
        },
        text = {
            Text("Bạn có chắc chắn muốn đăng xuất khỏi Tài chính Đa Long?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Đăng xuất")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}