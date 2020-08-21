package com.example.myapplication

enum class EnumGogyo(val rawValue: Int){

    Tree(1) ,Fire(2) ,Sat(3) ,Gold(4) ,Water(5) ;

    fun toEnuString() : String {
        when(this) {
            Tree ->
                return "木"
            Fire ->
                return "火"
            Sat ->
                return "土"
            Gold ->
                return "金"
            Water ->
                return "水"
            else ->
                return rawValue.toString()
        }
    }
}