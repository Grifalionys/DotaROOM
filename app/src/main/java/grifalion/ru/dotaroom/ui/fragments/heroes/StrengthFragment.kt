package grifalion.ru.dotaroom.ui.fragments.heroes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import grifalion.ru.dotaroom.adapters.HeroAdapter
import grifalion.ru.dotaroom.databinding.FragmentStrengthBinding
import grifalion.ru.dotaroom.models.HeroModel
import java.util.*
import kotlin.collections.ArrayList


class StrengthFragment : Fragment() {
    lateinit var binding: FragmentStrengthBinding
    private lateinit var dataBase: DatabaseReference
    private val adapter = HeroAdapter()
    private lateinit var heroList: MutableList<HeroModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentStrengthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()


    }

    private fun initRV() {
        binding.rcViewHeroes.layoutManager = LinearLayoutManager(requireContext())
        binding.rcViewHeroes.adapter =  adapter
        heroList = mutableListOf<HeroModel>()
        dataBase = FirebaseDatabase.getInstance().getReference("heroes/strength")
        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(HeroModel::class.java)
                        heroList.add(user!!)
                    }
                    adapter.setHero(heroList)
                    binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            val searchList = ArrayList<HeroModel>()
                            if(newText != null){
                                for(i in heroList){
                                    if(newText.equals(i.name,true)== true){
                                       adapter.setHero(heroList)
                                    }
                                }
                                if(searchList.isEmpty()){
                                    Toast.makeText(requireContext(),"Пустая строка",Toast.LENGTH_SHORT).show()
                                } else{
                                    adapter.setHero(heroList)
                                }
                            }
                            return true
                        }


                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun easyHero(){
    }

}