package grifalion.ru.dotaroom.ui.fragments.wallpaper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import grifalion.ru.dotaroom.adapters.WallpaperAdapter
import grifalion.ru.dotaroom.databinding.FragmentWallpaperBinding

class WallpaperFragment : Fragment() {
   private lateinit var binding: FragmentWallpaperBinding
    private lateinit var dataBase: DatabaseReference
    private val adapter = WallpaperAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWallpaperBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rcViewImages.layoutManager = GridLayoutManager(requireActivity(),2)
        binding.rcViewImages.adapter =  adapter

        dataBase = FirebaseDatabase.getInstance().getReference("image")
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    (snapshot.value as? List<String>)?.let {
                        adapter.setImage(it)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}