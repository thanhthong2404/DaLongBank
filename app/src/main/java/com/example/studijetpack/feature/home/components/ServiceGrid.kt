package com.example.studijetpack.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

data class HomeService(
    val id: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun  ServiceGrid( onServiceClick: (String) -> Unit){
    val services = listOf(
        HomeService("wallet", "Ví điện tử", Icons.Default.Wallet),
        HomeService("card", "Thẻ tín dụng", Icons.Default.CreditCard),
        HomeService("payment", "Thanh toán", Icons.Default.Payment),
        HomeService("transfer", "Chuyển tiền", Icons.Default.SwapHoriz),
        HomeService("bill", "Hóa đơn", Icons.Default.Receipt),
        HomeService("saving", "Tiết kiệm", Icons.Default.Savings),
        HomeService("loan", "Vay vốn", Icons.Default.AccountBalance),
        HomeService("more", "Xem thêm", Icons.Default.GridView)
    )
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Dịch vụ",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight(weight = 700),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxSize().height(190.dp),
            userScrollEnabled = false
        ) {
            items(services.size) { index ->
                val service = services[index]
                ServiceItem(
                    service = service,
                    onClick = {
                        onServiceClick(service.id)
                    }
                )
            }
        }
    }

}