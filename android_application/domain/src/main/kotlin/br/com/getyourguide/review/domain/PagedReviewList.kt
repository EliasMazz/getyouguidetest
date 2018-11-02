package br.com.getyourguide.review.domain

class PagedReviewList<out T>(val list: List<T>,
                             val total: Int

) {

    val lastListValues = list.subList(0, if (list.size > 20) 20 else list.size)
}