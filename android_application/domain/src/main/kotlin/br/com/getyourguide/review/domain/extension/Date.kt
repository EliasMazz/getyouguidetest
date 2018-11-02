package br.com.getyourguide.review.domain.extension

import java.text.SimpleDateFormat
import java.util.*

val LOCALE_BRAZIL = Locale("pt", "BR")
const val ISO_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun Date.toDayAndMonthString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM", LOCALE_BRAZIL)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT-3")
    return simpleDateFormat.format(this)
}

fun Date.toBrazilString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", LOCALE_BRAZIL)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT-3")
    return simpleDateFormat.format(this)
}

fun Date.timeString(): String {
    val simpleDateFormat = SimpleDateFormat("HH:mm", LOCALE_BRAZIL)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT-3")
    return simpleDateFormat.format(this)
}

fun Date.isCurrentDay(): Boolean {
    return this.timeToZero().compareTo(Date().timeToZero()) == 0
}

fun Date.timeToZero(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}

fun Date.getTimeString(): String {
    val simpleDateFormat = SimpleDateFormat("HH:mm", LOCALE_BRAZIL)
    return simpleDateFormat.format(this)
}

fun Date.toIsoString(): String {
    val simpleDateFormat = SimpleDateFormat(ISO_DATE_PATTERN, LOCALE_BRAZIL)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT-3")
    return simpleDateFormat.format(this)
}




