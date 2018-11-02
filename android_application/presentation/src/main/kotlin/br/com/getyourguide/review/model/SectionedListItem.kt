package br.com.getyourguide.review.model

sealed class SectionedListItem
data class ListHeader(val values: Any) : SectionedListItem()
data class SectionHeader(val title: String) : SectionedListItem()
data class SectionBody(val review: ReviewModel) : SectionedListItem()


