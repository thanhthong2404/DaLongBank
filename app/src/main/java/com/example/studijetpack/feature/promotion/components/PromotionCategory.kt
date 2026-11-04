
package com.example.studijetpack.feature.promotion.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studijetpack.feature.promotion.PromotionCategory

private data class CategoryItem(
    val category: PromotionCategory,
    val icon: ImageVector,
    val color: Color,
    val background: Color
)

@Composable
fun PromotionCategorySection(
    selectedCategory: PromotionCategory,
    onCategorySelected: (PromotionCategory) -> Unit
) {
    val categories = listOf(
        CategoryItem(
            PromotionCategory.ALL,
            Icons.Default.Apps,
            Color(0xFF174F91),
            Color(0xFFE8F1FF)
        ),
        CategoryItem(
            PromotionCategory.CASHBACK,
            Icons.Default.Wallet,
            Color(0xFF15945B),
            Color(0xFFE6F7EF)
        ),
        CategoryItem(
            PromotionCategory.SHOPPING,
            Icons.Default.ShoppingBag,
            Color(0xFFD97706),
            Color(0xFFFFF2E5)
        ),
        CategoryItem(
            PromotionCategory.FOOD,
            Icons.Default.Restaurant,
            Color(0xFFDB5470),
            Color(0xFFFFEDF1)
        ),
        CategoryItem(
            PromotionCategory.FINANCE,
            Icons.Default.CreditCard,
            Color(0xFF7C3AED),
            Color(0xFFF1EAFE)
        )
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 2.dp)
    ) {
        items(categories.size) { index ->
            val item = categories[index]
            val selected = selectedCategory == item.category

            Column(
                modifier = Modifier
                    .width(66.dp)
                    .clickable {
                        onCategorySelected(item.category)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(
                            color = if (selected) item.background
                            else Color(0xFFF5F7FA),
                            shape = RoundedCornerShape(17.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.category.title,
                        tint = item.color,
                        modifier = Modifier.size(25.dp)
                    )
                }

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = item.category.title,
                    fontSize = 11.sp,
                    fontWeight = if (selected) FontWeight.Bold
                    else FontWeight.Normal,
                    color = if (selected) item.color
                    else Color(0xFF606B78),
                    maxLines = 1
                )
            }
        }
    }
}
