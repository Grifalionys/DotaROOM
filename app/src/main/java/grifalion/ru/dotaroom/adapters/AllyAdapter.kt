package grifalion.ru.dotaroom.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.databinding.ItemAllyBinding

import grifalion.ru.dotaroom.models.AllyModel

class AllyAdapter: RecyclerView.Adapter<AllyAdapter.AllyViewHolder>(){
    private val listAlies = mutableListOf<AllyModel>()

    class AllyViewHolder (val binding: ItemAllyBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllyViewHolder {
        return AllyViewHolder(
            ItemAllyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int = listAlies.size

    override fun onBindViewHolder(holder: AllyViewHolder, position: Int) {
        val item = listAlies[position]
        holder.binding.tvNameHeroAlly.text = item.nameHeroAlly
        Glide.with(holder.binding.imIconHeroAlly)
            .load(item.iconHeroAlly)
            .into(holder.binding.imIconHeroAlly)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAlly(allies: List<AllyModel>){
        listAlies.clear()
        listAlies.addAll(allies)
        notifyDataSetChanged()
    }

}