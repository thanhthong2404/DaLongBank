
package com.example.studijetpack.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studijetpack.core.component.BaseTopBar
import com.example.studijetpack.feature.home.components.AccountOverview
import com.example.studijetpack.feature.home.components.BannerSlider
import com.example.studijetpack.feature.home.components.ServiceGrid

@Composable
fun HomeScreen(
    onServiceClick: (String) -> Unit,
    onPromotionClick: () -> Unit,
    onCallClick: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),        topBar = {
            BaseTopBar(
                title = "Trang chủ",
                showTitle = true,
                showBackButton = false,
                showCallButton = true,
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 12.dp)
        ) {
            item {
                AccountOverview(
                    balance = "125.680.000",
                    accountNumber = "8291",
                    accountName = "VÕ THÀNH THÔNG"
                )
            }

            item {
                ServiceGrid(
                    onServiceClick = onServiceClick
                )
            }

            item {
                BannerSlider(
                    onPromotionClick = onPromotionClick
                )
            }
        }
    }
}
