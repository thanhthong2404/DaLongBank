
package com.example.studijetpack.feature.promotion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
fun PromotionCard(
    promotion: PromotionModel,
    isSaved: Boolean,
    onClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(66.dp)
                    .background(
                        color = Color(0xFFEAF2FC),
                        shape = RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocalOffer,
                    contentDescription = null,
                    tint = Navy,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = promotion.discount,
                    color = Color(0xFF168354),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = promotion.title,
                    color = Color(0xFF202A36),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )

                Text(
                    text = promotion.partner,
                    color = Color(0xFF788391),
                    fontSize = 11.sp
                )

                Text(
                    text = "HSD: ${promotion.expiryDate}",
                    color = Color(0xFF929BA6),
                    fontSize = 11.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .background(
                            color = Color(0xFFF4F6F8),
                            shape = CircleShape
                        )
                        .clickable(onClick = onSaveClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isSaved) {
                            Icons.Default.Bookmark
                        } else {
                            Icons.Default.BookmarkBorder
                        },
                        contentDescription = "Lưu ưu đãi",
                        tint = if (isSaved) Navy else Color(0xFF7D8792),
                        modifier = Modifier.size(19.dp)
                    )
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "Xem chi tiết",
                    tint = Color(0xFF929BA6)
                )
            }
        }
    }
}
