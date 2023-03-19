package grifalion.ru.dotaroom.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.databinding.ItemItemsBinding
import grifalion.ru.dotaroom.models.ItemModel

class ItemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val listItems = mutableListOf<ItemModel>()

    class ItemViewHolder(val binding: ItemItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItems[position]
        Glide.with(holder.binding.imItemIcon)
            .load(item.imageItem)
            .into(holder.binding.imItemIcon)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(items: List<ItemModel>){
        listItems.clear()
        listItems.addAll(items)
        notifyDataSetChanged()
    }

}