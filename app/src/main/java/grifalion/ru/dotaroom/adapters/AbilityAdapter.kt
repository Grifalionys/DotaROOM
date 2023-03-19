package grifalion.ru.dotaroom.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.R
import grifalion.ru.dotaroom.databinding.ItemAbilityBinding
import grifalion.ru.dotaroom.models.AbilitiesModel

class AbilityAdapter: RecyclerView.Adapter<AbilityAdapter.AbilityViewHolder>() {
    private val listAbilities = mutableListOf<AbilitiesModel>()
    class AbilityViewHolder(val binding: ItemAbilityBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        return AbilityViewHolder(
            ItemAbilityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int  = listAbilities.size

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val item = listAbilities[position]
        if(item.descriptionAbility.toString().length<130){
            holder.binding.tvDescAbility.text = item.descriptionAbility.toString() + "..."
        } else {
            holder.binding.tvDescAbility.text = item.descriptionAbility.toString().substring(0,140) + "..."
        }
        holder.binding.tvNameAbility.text = item.nameAbility
        Glide.with(holder.binding.imgAbility)
            .load(item.imIconAbilitiesModel)
            .into(holder.binding.imgAbility)
        holder.itemView.rootView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("iconAbilityDetail",item.imIconAbilitiesModel)
            bundle.putString("nameAbilityDetail",item.nameAbility)
            bundle.putString("descriptionAbilityDetail",item.descriptionAbility)
            bundle.putString("manaLevel",item.manaLevel)
            bundle.putString("cooldown",item.cooldown)
            Navigation.findNavController(holder.itemView).navigate(R.id.action_detailHeroFragment_to_detailAbilitiesFragment,bundle)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setHero(abilities: List<AbilitiesModel>){
        listAbilities.clear()
        listAbilities.addAll(abilities)
        notifyDataSetChanged()
    }

}