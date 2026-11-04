package com.example.studijetpack.feature.promotion

enum class PromotionCategory(val title: String) {
    ALL("Tất cả"),
    CASHBACK("Hoàn tiền"),
    SHOPPING("Mua sắm"),
    FOOD("Ăn uống"),
    FINANCE("Tài chính")
}

data class PromotionModel(
    val id: String,
    val title: String,
    val description: String,
    val discount: String,
    val category: PromotionCategory,
    val expiryDate: String,
    val partner: String,
    val terms: List<String>,
    val isFeatured: Boolean = false
)

data class PromotionBannerModel(
    val promotionId: String,
    val title: String,
    val subtitle: String,
    val discount: String
)
