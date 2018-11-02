package br.com.getyourguide.review.model

import java.io.Serializable
import java.math.BigDecimal

data class ReviewModel(val id: BigDecimal?,
                       val rating: String?,
                       val title: String?,
                       val message: String?,
                       val date: String?,
                       val author: String?,
                       val foreignLanguage: Boolean?,
                       val languageCode: String?,
                       val travelerType: String?,
                       val reviewName: String?,
                       val reviewCountry: String?): Serializable