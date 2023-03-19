package grifalion.ru.dotaroom.ui.fragments.heroes

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.R
import com.google.firebase.database.*
import grifalion.ru.dotaroom.adapters.AbilityAdapter
import grifalion.ru.dotaroom.adapters.AllyAdapter
import grifalion.ru.dotaroom.adapters.ItemAdapter
import grifalion.ru.dotaroom.databinding.FragmentDetailHeroBinding
import grifalion.ru.dotaroom.models.AbilitiesModel
import grifalion.ru.dotaroom.models.AllyModel
import grifalion.ru.dotaroom.models.ItemModel


class DetailHeroFragment : Fragment() {
    private lateinit var binding: FragmentDetailHeroBinding
    private val adapter = AbilityAdapter()
    private val allyAdapter = AllyAdapter()
    private val itemAdapter = ItemAdapter()
    private lateinit var dataBase: DatabaseReference
    private lateinit var abilitiesList: MutableList<AbilitiesModel>
    private lateinit var alliesList: MutableList<AllyModel>
    private lateinit var itemsList: MutableList<ItemModel>
    private lateinit var iconHeroImg: String
    private lateinit var name: String
    private lateinit var hp: String
    private lateinit var mana: String
    private lateinit var protection: String
    private lateinit var attack: String
    private lateinit var speed: String
    private lateinit var typeAttack: String
    private lateinit var complexity: String
    private lateinit var strength: String
    private lateinit var agility: String
    private lateinit var intellect: String
    private lateinit var aghanimIcon: String
    private lateinit var aghanimName: String
    private lateinit var aghanimDescription: String
    private lateinit var aghanimManaLevel: String
    private lateinit var aghanimCooldown: String
    private lateinit var shardIcon: String
    private lateinit var shardName: String
    private lateinit var shardDescription: String
    private lateinit var shardCooldown: String
    private lateinit var shardManaLevel: String
    private lateinit var treeLeft10: String
    private lateinit var treeLeft15: String
    private lateinit var treeLeft20: String
    private lateinit var treeLeft25: String
    private lateinit var treeRight10: String
    private lateinit var treeRight15: String
    private lateinit var treeRight20: String
    private lateinit var treeRight25: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iconHeroImg = arguments?.getString("wall")!!
        name = arguments?.getString("name")!!
        hp = arguments?.getString("hp")!!
        mana = arguments?.getString("mana")!!
        protection = arguments?.getString("protection")!!
        attack = arguments?.getString("attack")!!
        speed = arguments?.getString("speed")!!
        typeAttack = arguments?.getString("typeAttack")!!
        complexity = arguments?.getString("complexity")!!
        strength = arguments?.getString("strength")!!
        agility = arguments?.getString("agility")!!
        intellect = arguments?.getString("intellect")!!
        aghanimIcon = arguments?.getString("aghanimIcon")!!
        aghanimName = arguments?.getString("aghanimName")!!
        aghanimDescription = arguments?.getString("aghanimDescription")!!
        aghanimManaLevel = arguments?.getString("aghanimManaLevel")!!
        aghanimCooldown = arguments?.getString("aghanimCooldown")!!
        shardIcon = arguments?.getString("shardIcon")!!
        shardName = arguments?.getString("shardName")!!
        shardDescription = arguments?.getString("shardDescription")!!
        shardCooldown = arguments?.getString("shardCooldown")!!
        shardManaLevel = arguments?.getString("shardManaLevel")!!
        treeLeft10 = arguments?.getString("treeLeft10")!!
        treeLeft15 = arguments?.getString("treeLeft15")!!
        treeLeft20 = arguments?.getString("treeLeft20")!!
        treeLeft25 = arguments?.getString("treeLeft25")!!
        treeRight10 = arguments?.getString("treeRight10")!!
        treeRight15 = arguments?.getString("treeRight15")!!
        treeRight20 = arguments?.getString("treeRight20")!!
        treeRight25 = arguments?.getString("treeRight25")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailHeroBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickTree()
        clickShard()
        clickAganim()
        initDetailHero()
        initAbilities()
        clickAllies()
        clickItems()
    }

    private fun clickTree(){
        binding.btnTree.setOnClickListener {
            val dialogTree= layoutInflater.inflate(R.layout.dilog_tree_info,null)
            val myDialog = Dialog(requireActivity())
            val close = dialogTree.findViewById<Button>(R.id.btnClose)
            val tvTenLeft = dialogTree.findViewById<TextView>(R.id.tvTenLeft)
            val tvFifteenLeft = dialogTree.findViewById<TextView>(R.id.tvFifteenLeft)
            val tvTwentyLeft = dialogTree.findViewById<TextView>(R.id.tvTwentyLeft)
            val tvTwentyFiveLeft = dialogTree.findViewById<TextView>(R.id.tvTwentyFiveLeft)
            val tvTenRight = dialogTree.findViewById<TextView>(R.id.tvTenRight)
            val tvFifteenRight = dialogTree.findViewById<TextView>(R.id.tvFifteenRight)
            val tvTwentyRight = dialogTree.findViewById<TextView>(R.id.tvTwentyRight)
            val tvTwentyFiveRight = dialogTree.findViewById<TextView>(R.id.tvTwentyFiveRight)

            tvTenLeft.text = treeLeft10
            tvFifteenLeft.text = treeLeft15
            tvTwentyLeft.text = treeLeft20
            tvTwentyFiveLeft.text = treeLeft25

            tvTenRight.text = treeRight10
            tvFifteenRight.text = treeRight15
            tvTwentyRight.text = treeRight20
            tvTwentyFiveRight.text = treeRight25


            close.setOnClickListener {
                myDialog.dismiss()
            }

            myDialog.setContentView(dialogTree)
            myDialog.show()
        }
    }

    private fun clickShard(){
        binding.btnShard.setOnClickListener {
            val dialogAganim = layoutInflater.inflate(R.layout.dilog_hero_info,null)
            val icon = dialogAganim.findViewById<ImageView>(R.id.imIconDesc)
            val name = dialogAganim.findViewById<TextView>(R.id.tvNameDialog)
            val description = dialogAganim.findViewById<TextView>(R.id.tvDescDialog)
            val close = dialogAganim.findViewById<Button>(R.id.btnClose)
            val mana = dialogAganim.findViewById<TextView>(R.id.tvManaDialog)
            val cooldown = dialogAganim.findViewById<TextView>(R.id.tvCooldownDialog)
            val textViewMana = dialogAganim.findViewById<TextView>(R.id.tvManaText)
            val textViewCooldown = dialogAganim.findViewById<TextView>(R.id.tvCooldownText)

            if(shardManaLevel == 0.toString() && shardCooldown == 0.toString()){
                textViewMana.visibility = View.GONE
                textViewCooldown.visibility = View.GONE
                name.text = shardName
                description.text = shardDescription
                Glide.with(icon)
                    .load(shardIcon)
                    .into(icon)

                } else if(shardManaLevel == 0.toString()){
                textViewMana.visibility = View.GONE
                cooldown.text = shardCooldown
                name.text = shardName
                description.text = shardDescription
                Glide.with(icon)
                    .load(shardIcon)
                    .into(icon)

                }  else if(shardCooldown == 0.toString()){
                    textViewCooldown.visibility = View.GONE
                    mana.text = shardManaLevel
                    name.text = shardName
                    description.text = shardDescription
                    Glide.with(icon)
                        .load(shardIcon)
                        .into(icon)
                }else {
                cooldown.text = shardCooldown
                mana.text = shardManaLevel
                name.text = shardName
                description.text = shardDescription
                Glide.with(icon)
                    .load(shardIcon)
                    .into(icon)
            }
            val myDialog = Dialog(requireActivity())
            close.setOnClickListener {
                myDialog.dismiss()
            }
            myDialog.setContentView(dialogAganim)
            myDialog.show()
        }
    }

    @SuppressLint("MissingInflatedId")
    private fun clickAganim(){
        binding.btnAganim.setOnClickListener {
            val dialogAganim = layoutInflater.inflate(R.layout.dilog_hero_info,null)
            val icon = dialogAganim.findViewById<ImageView>(R.id.imIconDesc)
            val name = dialogAganim.findViewById<TextView>(R.id.tvNameDialog)
            val description = dialogAganim.findViewById<TextView>(R.id.tvDescDialog)
            val close = dialogAganim.findViewById<Button>(R.id.btnClose)
            val mana = dialogAganim.findViewById<TextView>(R.id.tvManaDialog)
            val cooldown = dialogAganim.findViewById<TextView>(R.id.tvCooldownDialog)
            cooldown.text = aghanimCooldown
            mana.text = aghanimManaLevel
            name.text = aghanimName
            description.text = aghanimDescription
            Glide.with(icon)
                .load(aghanimIcon)
                .into(icon)
            val myDialog = Dialog(requireActivity())
            close.setOnClickListener {
                myDialog.dismiss()
            }
            myDialog.setContentView(dialogAganim)
            myDialog.show()

        }
    }

    private fun clickAllies(){
        binding.btnAllies.setOnClickListener {
            binding.rcViewAbilities.visibility = View.GONE
            binding.rcViewItems.visibility = View.GONE
            binding.btnAllies.setBackgroundResource(R.drawable.button_gradient_active)
            binding.btnThings.setBackgroundResource(R.drawable.button_gradient)
            binding.imBackProfile.visibility = View.VISIBLE
            initAllies()
            binding.rcViewAllies.visibility = View.VISIBLE
            returnMainPage()
        }
    }

    private fun clickItems() = with(binding){
        btnThings.setOnClickListener {
            rcViewAbilities.visibility = View.GONE
            rcViewAllies.visibility = View.GONE
            rcViewItems.visibility = View.VISIBLE
            imBackProfile.visibility = View.VISIBLE
            btnThings.setBackgroundResource(R.drawable.button_gradient_active)
            btnAllies.setBackgroundResource(R.drawable.button_gradient)
            initItems()
            returnMainPage()
        }
    }
    private fun initAllies(){
        binding.rcViewAllies.adapter = allyAdapter
        alliesList = mutableListOf<AllyModel>()

        dataBase = FirebaseDatabase.getInstance().getReference("heroes/strength/" + name + "/alliesList")

        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(userSnapshot in snapshot.children){
                        val allies = userSnapshot.getValue(AllyModel::class.java)
                        alliesList.add(allies!!)
                    }
                    allyAdapter.setAlly(alliesList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun initItems(){
        binding.rcViewItems.adapter = itemAdapter
        itemsList = mutableListOf<ItemModel>()

        dataBase = FirebaseDatabase.getInstance().getReference("heroes/strength/" + name + "/itemList")

        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(userSnapshot in snapshot.children){
                        val items = userSnapshot.getValue(ItemModel::class.java)
                        itemsList.add(items!!)
                    }
                    itemAdapter.setItem(itemsList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun initAbilities(){
        binding.rcViewAbilities.adapter = adapter
        abilitiesList = mutableListOf<AbilitiesModel>()

        dataBase = FirebaseDatabase.getInstance().getReference("heroes/strength/" + name + "/abilitiesList")

        dataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for(userSnapshot in snapshot.children){
                        val abilities = userSnapshot.getValue(AbilitiesModel::class.java)
                        abilitiesList.add(abilities!!)
                    }
                    adapter.setHero(abilitiesList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun returnMainPage(){
        binding.imBackProfile.setOnClickListener {
            binding.rcViewAllies.visibility = View.GONE
            binding.rcViewItems.visibility = View.GONE
            binding.imBackProfile.visibility = View.GONE
            binding.rcViewAbilities.visibility = View.VISIBLE
            binding.btnThings.setBackgroundResource(R.drawable.button_gradient)
            binding.btnAllies.setBackgroundResource(R.drawable.button_gradient)
        }
    }

    private fun initDetailHero(){
        binding.nameHero.text = name
        binding.tvHp.text = hp
        binding.tvMana.text = mana
        binding.tvProtection.text = protection
        binding.tvAttack.text = attack
        binding.tvSpeed.text = speed
        binding.tvTypeAttack.text = typeAttack
        binding.tvComplexity.text = complexity
        binding.tvStrength.text = strength
        binding.tvAgility.text = agility
        binding.tvIntellect.text = intellect
        Glide.with(binding.iconHero)
            .load(iconHeroImg)
            .into(binding.iconHero)
    }

}