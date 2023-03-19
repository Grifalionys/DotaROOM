package grifalion.ru.dotaroom.models

import org.json.JSONObject
import java.io.ObjectInput
import java.util.Objects

data class HeroModel(
    val id: Int? = null,
    val name: String? = null,
    val icon: String? = null,
    val hp: String? = null,
    val mana: String? = null,
    val protection: String? = null,
    val attack: String? = null,
    val speed: String? = null,
    val typeAttack: String? = null,
    val complexity: String? = null,
    val strength: String? = null,
    val agility: String? = null,
    val intellect: String? = null,
    val abilitiesList: List<AbilitiesModel>? = null,
    val alliesList: List<AllyModel>? = null,
    val itemList: List<ItemModel>? = null,
    val aghanimIcon: String? = null,
    val aghanimName: String? = null,
    val aghanimCooldown: String? = null,
    val aghanimManaLevel: String?  = null,
    val aghanimDescription: String? = null,
    val shardIcon: String?= null,
    val shardName: String?= null,
    val shardCooldown: String? = null,
    val shardManaLevel: String? = null,
    val shardDescription: String? = null,
    val treeLeft10: String? = null,
    val treeLeft15: String? = null,
    val treeLeft20: String? = null,
    val treeLeft25: String? = null,
    val treeRight10: String? = null,
    val treeRight15: String? = null,
    val treeRight20: String? = null,
    val treeRight25: String? = null

)
