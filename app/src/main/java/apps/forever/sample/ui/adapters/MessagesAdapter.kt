package apps.forever.sample.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import apps.forever.sample.R
import apps.forever.sample.databinding.ItemLayoutMessagesBinding
import apps.forever.sample.domain.model.Message
import java.util.Locale

/**
 * Created by Anil Kumar Reddy
 * on 02,February,2024
 */
class MessagesAdapter(
    private val messagesDtoData: List<Message>,
) : RecyclerView.Adapter<MessagesViewHolder>(), Filterable {
    private var mMessagesDatumDtos: List<Message>

    init {
        this.mMessagesDatumDtos = messagesDtoData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutMessagesBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_layout_messages, parent, false
        )
        return MessagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.binding.messageData = mMessagesDatumDtos[position]
    }

    override fun getItemCount(): Int {
        return mMessagesDatumDtos.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    mMessagesDatumDtos =
                        messagesDtoData
                } else {
                    val resultList =
                        ArrayList<Message>()
                    for (row in messagesDtoData) {
                        if (row.title.lowercase(Locale.ROOT).contains(
                                charSearch.lowercase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    mMessagesDatumDtos = resultList

                }
                val filterResults = FilterResults()
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results == null || results.values == null) {
                    messagesDtoData
                } else {
                    mMessagesDatumDtos = results.values as List<Message>
                }
                notifyDataSetChanged()
            }
        }
    }
}

class MessagesViewHolder(val binding: ItemLayoutMessagesBinding) :
    RecyclerView.ViewHolder(binding.root)