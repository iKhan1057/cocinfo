package com.cocdetails.utils

object PathUtils {

    fun getCompletePath(sec: String, name: String): String {
        return when (sec) {
            "hv" -> {
                when (name) {
                    "bowler", "golem", "headhunter", "hog", "ice_golem", "lava", "minion", "valkyrie", "witch" -> "/$name"
                    "archer", "baby_dragon", "balloon", "barbarian", "dragon", "dragon_rider", "electro_dragon", "giant", "goblin", "healer", "miner", "pekka", "wall_breaker", "wizard", "yeti" -> "/$name"
                    "archer_queen", "barbarian_king", "grand_warden", "royal_champion" -> "/$name"
                    "electro_owl", "lassi", "mighty_yak", "unicorn", "poison_lizard", "phoenix", "diggy", "frosty" -> "/$name"
                    "battle_blimp", "log_launcher", "siege_barracks", "stone_slammer", "flame_flinger", "battle_drill" -> "/$name"
                    "army", "army_camp", "barracks", "dark_barracks", "dark_spell_factory", "laboratory", "pet_house", "spell_factory", "workshop" -> "/$name"
                    "monolith","spell_tower","archer_tower","cannon","air_defense","air_sweeper","bomb_tower","builders_hut","eagle_artillery","giga_inferno_13","giga_inferno_14","giga_inferno_15","giga_tesla","hidden_tesla","inferno_tower","mortar","scattershot","walls","wizard_tower","x_bow"->"/$name"
                    "air_bomb","bomb","giant_bomb","seeking_air_mine","skeleton_trap","spring_trap","tornado_trap"->"/$name"
                    "resourse","gold_storage","gold_mine","elixir_storage","elixir_collector","dark_elixir_storage","dark_elixir_drill","clan_castle"-> "home/buildings/resource/$name"
                    "army_buildings","defensive_building","levels","resourse_building","storage","trap" -> "home/buildings/resource/th/$name"
                    "bat_spell","earthquake_spell","haste_spell","poison_spell","skeleton_spell"->"home/spells/dark/$name"
                    "recall_spell","clone_spell","freeze_spell","healing_spell","invisibility_spell","jump_spell","lightning_spell","rage_spell"->"home/spells/elixir/$name"
                    else -> ""
                }
            }
            "bb" -> ""
            "cc" -> ""
            else ->
                ""
        }
    }
}