package com.example.studijetpack.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private data class BannerItem(
    val tag: String,
    val title: String,
    val description: String,
    val action: String,
    val icon: String,
    val colors: List<Color>
)

@Composable
fun BannerSlider(
    onPromotionClick: () -> Unit
) {

    val banners = listOf(

        BannerItem(
            tag = "ƯU ĐÃI ĐẶC BIỆT",
            title = "Ưu đãi dành riêng cho bạn",
            description = "Khám phá các chương trình ưu đãi tài chính hấp dẫn.",
            action = "Khám phá ngay",
            icon = "🎁",
            colors = listOf(
                Color(0xFF174F91),
                Color(0xFF2878C8)
            )
        ),

        BannerItem(
            tag = "THANH TOÁN",
            title = "Thanh toán thông minh",
            description = "Giao dịch nhanh chóng, tiện lợi và an toàn.",
            action = "Tìm hiểu thêm",
            icon = "💳",
            colors = listOf(
                Color(0xFF087F83),
                Color(0xFF19AAA0)
            )
        ),

        BannerItem(
            tag = "TÀI CHÍNH CÁ NHÂN",
            title = "Quản lý tài chính dễ dàng",
            description = "Theo dõi chi tiêu và chủ động kế hoạch tài chính.",
            action = "Khám phá ngay",
            icon = "📊",
            colors = listOf(
                Color(0xFF6339A5),
                Color(0xFF9262CE)
            )
        ),

        BannerItem(
            tag = "DỊCH VỤ TIỆN ÍCH",
            title = "Trải nghiệm dịch vụ mới",
            description = "Tận hưởng nhiều tiện ích ngay trên ứng dụng.",
            action = "Xem chi tiết",
            icon = "✨",
            colors = listOf(
                Color(0xFFB45B24),
                Color(0xFFE99A43)
            )
        )
    )

    val pagerState = rememberPagerState(
        pageCount = { banners.size }
    )

    // Tự động chuyển slide mỗi 3 giây
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)

            val nextPage =
                (pagerState.currentPage + 1) % banners.size

            pagerState.animateScrollToPage(
                page = nextPage
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                bottom = 16.dp
            )
    ) {

        // Section title
        Text(
            text = "Ưu đãi dành cho bạn",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF172B4D)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Banner pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSize = PageSize.Fill,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->

            val banner = banners[page]

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            colors = banner.colors
                        )
                    )
                    .clickable {
                        onPromotionClick()
                    }
                    .padding(18.dp)
            ) {

                // Decorative circle
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .offset(x = 45.dp, y = (-65).dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(
                            Color.White.copy(alpha = 0.08f)
                        )
                )

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {

                        // Tag
                        Text(
                            text = banner.tag,
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    Color.White.copy(alpha = 0.2f)
                                )
                                .padding(
                                    horizontal = 9.dp,
                                    vertical = 5.dp
                                )
                        )

                        Spacer(modifier = Modifier.height(9.dp))

                        Text(
                            text = banner.title,
                            color = Color.White,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = banner.description,
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "${banner.action}  →",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Banner illustration
                    Box(
                        modifier = Modifier
                            .size(66.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(
                                Color.White.copy(alpha = 0.16f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = banner.icon,
                            fontSize = 34.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Page indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { index ->

                val selected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .width(if (selected) 20.dp else 6.dp)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(
                            if (selected) {
                                Color(0xFF174F91)
                            } else {
                                Color(0xFFD5DCE5)
                            }
                        )
                )
            }
        }
    }
}