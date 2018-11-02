package br.com.getyourguide.review.data.entity

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ReviewEntity(@SerializedName("review_id") val id: BigDecimal?,
                        @SerializedName("rating") val rating: String?,
                        @SerializedName("title") val title: String?,
                        @SerializedName("message") val message: String?,
                        @SerializedName("author") val author: String?,
                        @SerializedName("date") val date: String?,
                        @SerializedName("foreignLanguage") val foreignLanguage: Boolean?,
                        @SerializedName("languageCode") val languageCode: String?,
                        @SerializedName("traveler_type") val travelerType: String?,
                        @SerializedName("reviewName") val reviewName: String?,
                        @SerializedName("reviewCountry") val reviewCountry: String?)

//date unformated