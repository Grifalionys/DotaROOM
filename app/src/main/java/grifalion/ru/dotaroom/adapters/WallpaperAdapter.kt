package grifalion.ru.dotaroom.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.R
import grifalion.ru.dotaroom.databinding.ItemImagesBinding

class WallpaperAdapter: RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {
    private var listImages = mutableListOf<String>()

    class WallpaperViewHolder (val binding: ItemImagesBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        return WallpaperViewHolder(
            ItemImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int  = listImages.size

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        val item = listImages[position]
        Glide.with(holder.binding.imWallpaper)
            .load(item)
            .into(holder.binding.imWallpaper)
        holder.itemView.rootView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("wall",item)
            Navigation.findNavController(holder.itemView)
                .navigate(R.id.action_wallpaperFragment_to_wallpaperDetailFragment,bundle)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setImage(images: List<String>){
        listImages.clear()
        listImages.addAll(images)
        notifyDataSetChanged()
    }
}