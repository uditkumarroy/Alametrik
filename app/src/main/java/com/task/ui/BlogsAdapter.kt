package com.task.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.task.R
import com.task.models.Result
import com.task.utils.GenericViewHolder
import kotlinx.android.synthetic.main.layout_list_item.view.*

class BlogListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var count:Int = 0
    private val TAG = "ListAdapter"
    private val NO_MORE_RESULTS = -1
    private val BLOG_ITEM = 0
    private val NO_MORE_RESULTS_BLOG_MARKER = Result(
        0,
        "",
        "",
        "",
        "",
        0.0,
        ""
    )

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(
        BlogRecyclerChangeCallback(this),
        AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
    )


    override fun getItemViewType(position: Int): Int {
        if (differ.currentList.get(position).trackId.equals("null")) {
            return BLOG_ITEM
        }
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {

            NO_MORE_RESULTS -> {
                Log.e(TAG, "onCreateViewHolder: No more results...")
                return GenericViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_nomore_item,
                        parent,
                        false
                    )
                )
            }

            BLOG_ITEM -> {
                return BlogViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_list_item, parent, false),
                    interaction = interaction//,
                    //  requestManager = requestManager
                )
            }
            else -> {
                return BlogViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.layout_list_item, parent, false),
                    interaction = interaction//,
//                    requestManager = requestManager
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(blogList: List<Result>?, isQueryExhausted: Boolean) {
        val newList = blogList?.toMutableList()
        if (isQueryExhausted)
            newList?.add(NO_MORE_RESULTS_BLOG_MARKER)
        differ.submitList(newList)
    }

    class BlogViewHolder
    constructor(
        itemView: View,
        //  val requestManager: RequestManager,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Result) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            /* requestManager
                 .load(item.imageHref)
                 .transition(DrawableTransitionOptions.withCrossFade())
                 .into(itemView.img_post)*/
            val image = item.previewUrl
            if (image.equals("null")) {
                itemView.img_post.visibility = View.GONE
            } else {
                itemView.img_post.visibility = View.VISIBLE
                Glide.with(this).load(image).placeholder(R.drawable.ic_launcher_background)
                    .into(itemView.img_post)
            }
            val title = item.artistName
            if (title.equals("null")) {
                itemView.tv_title.visibility = View.GONE
            } else {
                itemView.tv_title.text = item.artistName
            }
            val desc1 = item.trackCensoredName
            if (desc1.equals("null")) {
                itemView.textView.visibility = View.GONE
            } else {
                itemView.textView.text = item.trackCensoredName
            }
            val desc = item.trackCensoredName
            if (desc.equals("null")) {
                itemView.tv_des.visibility = View.GONE
            } else {
                itemView.tv_des.text = item.trackCensoredName
            }
            val price = item.trackPrice
            val date = item.releaseDate
            if (desc.equals("null")) {
                itemView.textView2.visibility = View.GONE
            } else {
                itemView.textView2.text = "rs. ${price}\n\n${date}"
            }
            itemView.checkBox.setOnCheckedChangeListener { compoundButton, b ->
                interaction?.onItemChecked(b)
            }
        }
    }

    internal inner class BlogRecyclerChangeCallback(
        private val adapter: BlogListAdapter
    ) : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Result)
        fun onItemChecked(isChecked:Boolean)
    }
}