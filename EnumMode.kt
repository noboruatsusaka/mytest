package com.example.myapplication

enum class EnumMode(val rawValue: Int) {
    Trick(0),Power(1)  ,Speed(2)  ;

    fun toEnuString() : String {
        when(this) {
            Power ->
                return "力"
            Trick ->
                return "かく乱"
            Speed ->
                return "速"
            else ->
                return rawValue.toString()
        }
    }

}