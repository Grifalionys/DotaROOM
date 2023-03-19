package grifalion.ru.dotaroom.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.R
import grifalion.ru.dotaroom.databinding.ItemHeroBinding
import grifalion.ru.dotaroom.databinding.ItemHeroLayoutBinding
import grifalion.ru.dotaroom.models.HeroModel
import grifalion.ru.dotaroom.R.color.black as black1

class HeroAdapter: RecyclerView.Adapter<HeroAdapter.WallpaperViewHolder>() {
    private var listHeroes = mutableListOf<HeroModel>()

    class WallpaperViewHolder (val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(
            ItemHeroBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int  = listHeroes.size


    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val item = listHeroes[position]
        if(item.complexity.equals("Средняя",true)){
            holder.binding.imStarEmtpty2.visibility = View.INVISIBLE
            holder.binding.imStarFill2.visibility = View.VISIBLE
        } else if(item.complexity.equals("Сложная",true)){
            holder.binding.imStarEmtpty2.visibility = View.INVISIBLE
            holder.binding.imStarEmpty3.visibility = View.INVISIBLE
            holder.binding.imStarFill2.visibility = View.VISIBLE
            holder.binding.imStarFill3.visibility = View.VISIBLE
        }
        Glide.with(holder.binding.imIconHero)
            .load(item.icon)
            .into(holder.binding.imIconHero)
        holder.binding.tvName.text = item.name
        holder.itemView.rootView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("wall",item.icon)
            bundle.putString("name",item.name)
            bundle.putString("hp",item.hp)
            bundle.putString("mana",item.mana)
            bundle.putString("protection",item.protection)
            bundle.putString("attack",item.attack)
            bundle.putString("speed",item.speed)
            bundle.putString("typeAttack",item.typeAttack.toString().lowercase())
            bundle.putString("complexity",item.complexity.toString().lowercase())
            bundle.putString("strength",item.strength)
            bundle.putString("agility",item.agility)
            bundle.putString("intellect",item.intellect)
            bundle.putString("aghanimIcon",item.aghanimIcon)
            bundle.putString("aghanimName",item.aghanimName)
            bundle.putString("aghanimDescription",item.aghanimDescription)
            bundle.putString("aghanimManaLevel",item.aghanimManaLevel)
            bundle.putString("aghanimCooldown",item.aghanimCooldown)
            bundle.putString("shardIcon",item.shardIcon)
            bundle.putString("shardName",item.shardName)
            bundle.putString("shardDescription",item.shardDescription)
            bundle.putString("shardCooldown",item.shardCooldown)
            bundle.putString("shardManaLevel",item.shardManaLevel)
            bundle.putString("treeLeft10",item.treeLeft10)
            bundle.putString("treeLeft15",item.treeLeft15)
            bundle.putString("treeLeft20",item.treeLeft20)
            bundle.putString("treeLeft25",item. treeLeft25)
            bundle.putString("treeRight10",item.treeRight10)
            bundle.putString("treeRight15",item.treeRight15)
            bundle.putString("treeRight20",item.treeRight20)
            bundle.putString("treeLeft10",item.treeLeft10)
            bundle.putString("treeRight25",item.treeRight25)
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_strengthFragment_to_detailHeroFragment,bundle)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHero(heroes: List<HeroModel>){
        listHeroes.clear()
        listHeroes.addAll(heroes)
        notifyDataSetChanged()
    }


}