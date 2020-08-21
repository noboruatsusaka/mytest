package com.example.myapplication

enum class EnumCommand(val i: Int) {
    Art(0) ,Spell(1) ,Skill(2) ,Item(3) ;

    fun toEnuString() : String {
        when(this) {
            Art ->
                return "技"
            Spell ->
                return "術"
            Skill ->
                return "技能"
            Item ->
                return "道具"
            else ->
                return i.toString()
        }
    }


}