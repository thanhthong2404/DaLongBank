
package com.example.studijetpack.feature.promotion.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studijetpack.feature.promotion.PromotionBannerModel
import kotlinx.coroutines.delay

private val Navy = Color(0xFF174F91)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromotionBanner(
    banners: List<PromotionBannerModel>,
    onBannerClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(
        pageCount = { banners.size }
    )

    LaunchedEffect(pagerState, banners.size) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % banners.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(178.dp),
            pageSpacing = 12.dp
        ) { page ->
            val banner = banners[page]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF174F91),
                                Color(0xFF287FC1),
                                Color(0xFF43A5CE)
                            )
                        )
                    )
                    .clickable {
                        onBannerClick(banner.promotionId)
                    }
                    .padding(20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .align(Alignment.CenterEnd)
                        .offset(x = 35.dp, y = 35.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.10f))
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.78f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                        Text(
                            text = banner.title,
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Text(
                            text = banner.subtitle,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 21.sp
                        )
                    }

                    Text(
                        text = banner.discount,
                        color = Color(0xFFFFE28A),
                        fontSize = 23.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Text(
                    text = "›",
                    color = Color.White,
                    fontSize = 42.sp,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(banners.size) { index ->
                val selected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(
                            width = if (selected) 20.dp else 6.dp,
                            height = 6.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (selected) Navy
                            else Color(0xFFD5DDE7)
                        )
                )
            }
        }
    }
}
