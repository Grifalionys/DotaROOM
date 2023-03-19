package grifalion.ru.dotaroom.ui.fragments.heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import grifalion.ru.dotaroom.databinding.FragmentDetailsAbilityBinding

class DetailAbilitiesFragment: Fragment() {
    private lateinit var binding: FragmentDetailsAbilityBinding
    private lateinit var nameAbility: String
    private lateinit var descriptionAbility: String
    private lateinit var iconAbility: String
    private lateinit var manaLevel: String
    private lateinit var cooldown: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nameAbility = arguments?.getString("nameAbilityDetail")!!
        descriptionAbility = arguments?.getString("descriptionAbilityDetail")!!
        iconAbility = arguments?.getString("iconAbilityDetail")!!
        manaLevel = arguments?.getString("manaLevel")!!
        cooldown = arguments?.getString("cooldown")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsAbilityBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nameAbilities.text = nameAbility
        binding.tvFullDescriptionAbility.text = descriptionAbility
        binding.textMana.text = manaLevel
        binding.textCoolDown.text = cooldown
        Glide.with(binding.iconAbility)
            .load(iconAbility)
            .into(binding.iconAbility)
    }
}