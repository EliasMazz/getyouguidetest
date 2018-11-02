package br.com.getyourguide.review.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.getyourguide.review.R
import br.com.getyourguide.review.model.*
import com.bumptech.glide.Glide

abstract class BaseReviewListAdapter<T>(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_VIEW_TYPE_LIST_HEADER = 0
        const val ITEM_VIEW_TYPE_SECTION_HEADER = 1
        const val ITEM_VIEW_TYPE_SECTION_BODY = 2
    }

    //TODO
    interface OnItemClickListener<in T> {
        fun onItemClick(value: T)
    }

    private var mItems: ArrayList<SectionedListItem> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<T>
    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    abstract val itemLayoutResource: Int
    abstract val itemHeaderLayoutResource: Int

    abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, sectionBody: SectionBody)


    fun setItemsCollection(itemsCollection: Collection<SectionedListItem>) {
        checkNotNull(itemsCollection)
        mItems.clear()
        this.mItems = itemsCollection as ArrayList<SectionedListItem>
        this.notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (mItems[position]) {
            is ListHeader -> ITEM_VIEW_TYPE_LIST_HEADER
            is SectionHeader -> ITEM_VIEW_TYPE_SECTION_HEADER
            is SectionBody -> ITEM_VIEW_TYPE_SECTION_BODY
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    protected fun inflateViewByViewType(parent: ViewGroup?, viewType: Int): View {
        return this.layoutInflater.inflate(getLayoutResourceByViewType(viewType), parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sectionedListItem = mItems[position]
        when (sectionedListItem) {
            is SectionHeader -> bindHeaderViewHolder(holder as HeaderViewHolder, sectionedListItem)
            is SectionBody -> bindItemViewHolder(holder, sectionedListItem)
            is ListHeader -> bindListHeaderViewHolder(holder as ListHeaderViewHolder, sectionedListItem)
        }
    }

    private fun bindListHeaderViewHolder(holder: ListHeaderViewHolder, listHeader: ListHeader) {
        holder.tvReviewTitle.text = (listHeader.values as ReviewHeaderModel).title
        holder.tvReviewTotalCount.text = "${listHeader.values.totalReviewCount} Reviews "
        Glide.with(context)
                .load(listHeader.values.imageLink)
                .into(holder.ivLocationImagge)

    }

    private fun bindHeaderViewHolder(holder: HeaderViewHolder, sectionHeader: SectionHeader) {
        holder.tvTitle.text = sectionHeader.title
    }

    private fun getLayoutResourceByViewType(viewType: Int): Int {
        if (viewType == ITEM_VIEW_TYPE_LIST_HEADER) return R.layout.header_review_total
        if (viewType == ITEM_VIEW_TYPE_SECTION_HEADER) return itemHeaderLayoutResource
        return itemLayoutResource
    }

    internal class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_header_title)
    }

     class ListHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvReviewTitle: TextView = itemView.findViewById(R.id.tv_review_title)
        val tvReviewTotalCount: TextView = itemView.findViewById(R.id.tv_review_total_count)
        val ivLocationImagge: ImageView = itemView.findViewById(R.id.iv_location)
    }


}