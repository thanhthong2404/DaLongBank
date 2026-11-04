package com.example.studijetpack.feature.promotion

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studijetpack.core.component.BaseTopBar
import com.example.studijetpack.feature.promotion.components.PromotionBanner
import com.example.studijetpack.feature.promotion.components.PromotionCard
import com.example.studijetpack.feature.promotion.components.PromotionCategorySection
import com.example.studijetpack.feature.promotion.components.PromotionDetailScreen

private val Navy = Color(0xFF174F91)

@Composable
fun PromotionScreen() {
    var searchQuery by remember { mutableStateOf("") }

    var selectedCategory by remember {
        mutableStateOf(PromotionCategory.ALL)
    }

    var selectedPromotion by remember {
        mutableStateOf<PromotionModel?>(null)
    }

    var showSavedOnly by remember {
        mutableStateOf(false)
    }

    val savedPromotionIds = remember {
        mutableStateListOf<String>()
    }

    BackHandler(enabled = selectedPromotion != null) {
        selectedPromotion = null
    }

    val allPromotions = PromotionSampleData.promotions

    val filteredPromotions = allPromotions.filter { promotion ->
        val matchesCategory =
            selectedCategory == PromotionCategory.ALL ||
                    promotion.category == selectedCategory

        val matchesSearch =
            promotion.title.contains(searchQuery, ignoreCase = true) ||
                    promotion.description.contains(searchQuery, ignoreCase = true) ||
                    promotion.partner.contains(searchQuery, ignoreCase = true)

        val matchesSaved =
            !showSavedOnly || promotion.id in savedPromotionIds

        matchesCategory && matchesSearch && matchesSaved
    }

    Scaffold(
        topBar = {
            BaseTopBar(
                title = if (selectedPromotion == null) {
                    "Ưu đãi"
                } else {
                    "Chi tiết ưu đãi"
                },
                showTitle = true,
                showBackButton = selectedPromotion != null,
                showCallButton = false,
                onBackClick = {
                    selectedPromotion = null
                }
            )
        },
        containerColor = Color(0xFFF5F7FA)
    ) { innerPadding ->

        if (selectedPromotion != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                PromotionDetailScreen(
                    promotion = selectedPromotion!!,
                    onBackClick = {
                        selectedPromotion = null
                    },
                    onUsePromotion = {
                        // TODO: Kết nối API nhận hoặc sử dụng ưu đãi.
                    }
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 20.dp
                ),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                item {
                    PromotionBanner(
                        banners = PromotionSampleData.banners,
                        onBannerClick = { promotionId ->
                            selectedPromotion = allPromotions.find {
                                it.id == promotionId
                            }
                        }
                    )
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Khám phá ưu đãi",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF202A36)
                        )

                        PromotionCategorySection(
                            selectedCategory = selectedCategory,
                            onCategorySelected = {
                                selectedCategory = it
                                showSavedOnly = false
                            }
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Tìm kiếm ưu đãi...")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Tìm kiếm"
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Navy,
                            unfocusedBorderColor = Color(0xFFE0E5EB),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (showSavedOnly) {
                                "Ưu đãi đã lưu"
                            } else {
                                "Ưu đãi dành cho bạn"
                            },
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF202A36)
                        )

                        TextButton(
                            onClick = {
                                showSavedOnly = !showSavedOnly
                                selectedCategory = PromotionCategory.ALL
                            }
                        ) {
                            Text(
                                text = if (showSavedOnly) {
                                    "Tất cả"
                                } else {
                                    "Đã lưu (${savedPromotionIds.size})"
                                },
                                color = Navy
                            )
                        }
                    }
                }

                if (filteredPromotions.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 32.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Không tìm thấy ưu đãi",
                                color = Color(0xFF606B78),
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = "Thử thay đổi từ khóa hoặc danh mục tìm kiếm.",
                                color = Color(0xFF929BA6),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                } else {
                    items(
                        items = filteredPromotions,
                        key = { it.id }
                    ) { promotion ->
                        PromotionCard(
                            promotion = promotion,
                            isSaved = promotion.id in savedPromotionIds,
                            onClick = {
                                selectedPromotion = promotion
                            },
                            onSaveClick = {
                                if (promotion.id in savedPromotionIds) {
                                    savedPromotionIds.remove(promotion.id)
                                } else {
                                    savedPromotionIds.add(promotion.id)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
