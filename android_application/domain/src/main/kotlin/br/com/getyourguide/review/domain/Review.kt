package br.com.getyourguide.review.domain

import java.math.BigDecimal

class Review(val id: BigDecimal?,
             val rating: String?,
             val title: String?,
             val message: String?,
             val date: String?,
             val author: String?,
             val foreignLanguage: Boolean?,
             val languageCode: String?,
             val travelerType: String?,
             val reviewName: String?,
             val reviewCountry: String?) {

    //val transactionDateFormatted = if (transactionDate!!.isCurrentDay()) "Hoje" else transactionDate.toDayAndMonthString()

}
