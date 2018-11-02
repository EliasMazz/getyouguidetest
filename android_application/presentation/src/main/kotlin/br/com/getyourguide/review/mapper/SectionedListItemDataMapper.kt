package br.com.getyourguide.review.mapper

import br.com.getyourguide.review.domain.Review
import br.com.getyourguide.review.model.ListHeader
import br.com.getyourguide.review.model.SectionBody
import br.com.getyourguide.review.model.SectionHeader
import br.com.getyourguide.review.model.SectionedListItem
import javax.inject.Inject

class SectionedListItemDataMapper
@Inject constructor(private val reviewModelDataMapper: ReviewModelDataMapper) {

    fun transformTransactionsList(transactions: List<Review>): Collection<SectionedListItem> {
        val sectionedListItemCollection = ArrayList<SectionedListItem>()
        val sections = transactions.groupBy { it.date}

        sections.forEach { it ->
            sectionedListItemCollection.add(createSectionHeader(it.key!!))
            sectionedListItemCollection.addAll(it.value.map(this::createSectionBody))
        }

        return sectionedListItemCollection
    }

    fun transformTransactionsListWithHeader(transactions: List<Review>, headerValue: Any): Collection<SectionedListItem> {
        val listItemCollection = ArrayList<SectionedListItem>()
        listItemCollection.add(createListHeader(headerValue))
        listItemCollection.addAll(transformTransactionsList(transactions))
        return listItemCollection
    }

    private fun createSectionHeader(sectionTitle: String): SectionHeader {
        return SectionHeader(sectionTitle)
    }

    private fun createListHeader(values: Any): ListHeader {
        return ListHeader(values)
    }

    private fun createSectionBody(review: Review): SectionBody {
        return SectionBody(reviewModelDataMapper.transform(review))
    }
}