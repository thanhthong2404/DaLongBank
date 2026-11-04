package com.example.studijetpack.feature.account.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studijetpack.feature.account.BankAccount

@Composable
fun AccountBankCard(
    account: BankAccount,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(
                        Color(0xFFEAF2FC),
                        RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = account.bankLogoText,
                    color = Color(0xFF174F91),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = account.bankName,
                        color = Color(0xFF202938),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )

                    if (account.isDefault) {
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = "Mặc định",
                            color = Color(0xFF16865B),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Text(
                    text = account.accountNumber,
                    color = Color(0xFF667085),
                    fontSize = 13.sp
                )

                Text(
                    text = account.accountHolder,
                    color = Color(0xFF98A2B3),
                    fontSize = 10.sp
                )
            }
        }
    }
}