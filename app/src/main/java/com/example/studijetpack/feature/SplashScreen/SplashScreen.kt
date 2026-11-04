package com.example.studijetpack.feature.SplashScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    // 1. Khai báo các Animation state (Scale và Alpha)
    val scale = remember { Animatable(0.4f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        // Chạy animation xuất hiện nhẹ nhàng trong 800ms
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 800)
        )

        // Giữ lại màn hình khoảng 1.5s trước khi chuyển route
        delay(1500)

        val isUserLoggedIn = false // Kiểm tra token/xác thực thực tế tại đây
        if (isUserLoggedIn) {
            onNavigateToHome()
        } else {
            onNavigateToLogin()
        }
    }

    // 2. Layout tổng quan với dải màu Gradient sang trọng
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E1E2C), // Xanh đen hiện đại
                        Color(0xFF2D1F3D), // Tím thẫm
                        Color(0xFF121212)  // Đen dịu
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Khối nội dung ở giữa màn hình
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            // Đổi R.drawable.ic_launcher_foreground thành Icon/Logo thực tế của app bạn
            Icon(
                painter = painterResource(id = android.R.drawable.ic_dialog_map), // Thay logo của bạn ở đây
                contentDescription = "App Logo",
                tint = Color(0xFF6C5CE7), // Màu tím pastel nổi bật
                modifier = Modifier.size(96.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Tài Chính Đa Long Chào Bạn",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Vay Đa Long Hết Long Bông",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.6f)
            )
        }

        // Circular Loading nằm tinh tế ở phía dưới cùng màn hình
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .alpha(alpha.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                color = Color(0xFF6C5CE7),
                strokeWidth = 2.5.dp
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}