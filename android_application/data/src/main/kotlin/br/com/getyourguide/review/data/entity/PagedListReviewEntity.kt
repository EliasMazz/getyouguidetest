package br.com.getyourguide.review.data.entity

import com.google.gson.annotations.SerializedName

data class PagedListReviewEntity<out T>(@SerializedName("data") val result: T,
                                        @SerializedName("total_reviews_comments") val total: Int)
