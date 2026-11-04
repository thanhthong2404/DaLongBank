
package com.example.studijetpack.feature.promotion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studijetpack.feature.promotion.PromotionModel

private val Navy = Color(0xFF174F91)


@Composable
fun PromotionDetailScreen(
    promotion: PromotionModel,
    onBackClick: () -> Unit,
    onUsePromotion: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
    ) {
        // Vùng nội dung cuộn
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Navy,
                        RoundedCornerShape(20.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.LocalOffer,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(42.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = promotion.discount,
                    color = Color(0xFFFFE28A),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = promotion.title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            DetailSection(title = "Thông tin ưu đãi") {
                DetailText("Đối tác", promotion.partner)
                DetailText("Danh mục", promotion.category.title)
                DetailText("Hạn sử dụng", promotion.expiryDate)
                DetailText("Mã ưu đãi", promotion.id)
            }

            DetailSection(title = "Mô tả chương trình") {
                Text(
                    text = promotion.description,
                    color = Color(0xFF606B78),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            DetailSection(title = "Điều kiện áp dụng") {
                promotion.terms.forEachIndexed { index, term ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "${index + 1}.",
                            color = Navy,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = term,
                            color = Color(0xFF606B78),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        // Nút cố định phía dưới
        Button(
            onClick = onUsePromotion,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Navy
            )
        ) {
            Text(
                text = "Sử dụng ưu đãi",
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
private fun DetailSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            color = Color(0xFF202A36),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )

        content()
    }
}

@Composable
private fun DetailText(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Color(0xFF929BA6),
            fontSize = 13.sp
        )

        Text(
            text = value,
            color = Color(0xFF303B48),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
