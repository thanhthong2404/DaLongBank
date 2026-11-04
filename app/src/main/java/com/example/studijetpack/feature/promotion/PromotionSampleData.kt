
package com.example.studijetpack.feature.promotion

object PromotionSampleData {

    val promotions = listOf(
        PromotionModel(
            id = "P001",
            title = "Hoàn tiền khi thanh toán hóa đơn",
            description = "Nhận ưu đãi khi thanh toán điện, nước và Internet.",
            discount = "Hoàn tiền 10%",
            category = PromotionCategory.CASHBACK,
            expiryDate = "30/11/2026",
            partner = "Tài chính Đa Long",
            isFeatured = true,
            terms = listOf(
                "Áp dụng khi thanh toán hóa đơn trên ứng dụng.",
                "Mức hoàn tiền tối đa 100.000 VNĐ.",
                "Mỗi khách hàng được áp dụng một lần trong chương trình."
            )
        ),
        PromotionModel(
            id = "P002",
            title = "Ưu đãi mua sắm cuối tuần",
            description = "Tiết kiệm khi mua sắm tại các đối tác liên kết.",
            discount = "Giảm 20%",
            category = PromotionCategory.SHOPPING,
            expiryDate = "15/11/2026",
            partner = "Đối tác mua sắm",
            isFeatured = true,
            terms = listOf(
                "Áp dụng vào thứ Bảy và Chủ nhật.",
                "Đơn hàng tối thiểu 300.000 VNĐ.",
                "Không áp dụng đồng thời với ưu đãi khác."
            )
        ),
        PromotionModel(
            id = "P003",
            title = "Ăn ngon với ưu đãi đặc biệt",
            description = "Nhận ưu đãi tại các nhà hàng và quán ăn liên kết.",
            discount = "Giảm 50.000đ",
            category = PromotionCategory.FOOD,
            expiryDate = "20/11/2026",
            partner = "Đối tác ẩm thực",
            terms = listOf(
                "Áp dụng cho hóa đơn từ 200.000 VNĐ.",
                "Xuất trình mã ưu đãi trước khi thanh toán.",
                "Số lượng ưu đãi có giới hạn."
            )
        ),
        PromotionModel(
            id = "P004",
            title = "Ưu đãi dành cho khách hàng mới",
            description = "Trải nghiệm dịch vụ tài chính với nhiều quyền lợi.",
            discount = "Quà tặng 100.000đ",
            category = PromotionCategory.FINANCE,
            expiryDate = "31/12/2026",
            partner = "Tài chính Đa Long",
            isFeatured = true,
            terms = listOf(
                "Dành cho khách hàng lần đầu sử dụng dịch vụ.",
                "Hoàn thành các điều kiện của chương trình.",
                "Quà tặng được ghi nhận theo thể lệ chương trình."
            )
        ),
        PromotionModel(
            id = "P005",
            title = "Hoàn tiền khi mua sắm online",
            description = "Thanh toán trực tuyến và nhận lại một phần giá trị.",
            discount = "Hoàn tiền 5%",
            category = PromotionCategory.CASHBACK,
            expiryDate = "25/11/2026",
            partner = "Đối tác thương mại điện tử",
            terms = listOf(
                "Áp dụng cho giao dịch thanh toán trực tuyến hợp lệ.",
                "Mức hoàn tiền tối đa 50.000 VNĐ.",
                "Không áp dụng cho giao dịch bị hủy hoặc hoàn tiền."
            )
        ),
        PromotionModel(
            id = "P006",
            title = "Ưu đãi thanh toán tại cửa hàng",
            description = "Mua sắm tiện lợi và tiết kiệm hơn mỗi ngày.",
            discount = "Giảm 15%",
            category = PromotionCategory.SHOPPING,
            expiryDate = "10/12/2026",
            partner = "Hệ thống cửa hàng liên kết",
            terms = listOf(
                "Áp dụng tại cửa hàng tham gia chương trình.",
                "Mỗi khách hàng sử dụng tối đa một lần.",
                "Vui lòng kiểm tra điều kiện tại điểm bán."
            )
        )
    )

    val banners = listOf(
        PromotionBannerModel(
            promotionId = "P001",
            title = "THANH TOÁN THÔNG MINH",
            subtitle = "Nhận ưu đãi hoàn tiền khi thanh toán hóa đơn",
            discount = "HOÀN TIỀN 10%"
        ),
        PromotionBannerModel(
            promotionId = "P002",
            title = "MUA SẮM THẢ GA",
            subtitle = "Tận hưởng ưu đãi mua sắm cuối tuần",
            discount = "GIẢM ĐẾN 20%"
        ),
        PromotionBannerModel(
            promotionId = "P004",
            title = "ĐẶC QUYỀN KHÁCH HÀNG MỚI",
            subtitle = "Khám phá những quyền lợi dành riêng cho bạn",
            discount = "QUÀ TẶNG 100K"
        )
    )
}

