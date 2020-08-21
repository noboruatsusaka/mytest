package com.example.myapplication

class BaseEffect (typ:Int, attribute:Int){

    var Typ:Int
    var Attribute:Int

    var strRes:String = " "

    init {
        this.Typ = typ
        this.Attribute = attribute
    }

    fun EffectMsg( name:String, pow:Int ):String{

        val strAtklist:List<String> =listOf(" ","斬","打","突","熱","冷","雷","陽","陰")
        val strAtk = strAtklist[this.Attribute]
        val stat = EnumStatus.IntToSt(this.Attribute)
        val strSt = stat.strNM

        when(this.Typ){
            1 ->{ return " $name は体力を回復した！" }
            2 ->{ return " $name は状態を回復した！" }
            3 ->{ return " $name は攻撃力が上がった！" }
            4 ->{ return " $name は防御力が上がった！" }
            5 ->{ return " $name は攻撃力が下がった！" }
            6 ->{ return " $name は防御力が下がった！" }
            7 ->{ return " $name は　$strAtk　攻撃で　$pow を与えた！" }
            8 ->{ return " $name は　$strSt　異常をした！" }
            else->  {return "error"}
        }

        return  "non"
    }


}