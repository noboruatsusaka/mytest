package com.example.myapplication

enum class EnumStatus (val i: Int, val bit:Int,  val strNM:String){
    Nomal(0,0x00,"nasi"),Poison(1,0x01,"毒"),Stun(5,0x10,"スタン"),Sleep(2,0x02,"眠り"),Paralysis(6,0x20,"麻痺"),
    Darkness(3,0x04,"暗闇"),Faint(7,0x40,"気絶"),Confuse(4,0x08,"混乱"),Charm(8,0x80,"魅了");


    companion object {
        // enumへの変換を行う
        fun IntToSt(st: Int): EnumStatus {

            when(st){
                1 -> return Poison
                2 -> return Sleep
                3 -> return Darkness
                4 -> return Confuse
                5 -> return Stun
                6 -> return Paralysis
                7 -> return Faint
                8 -> return Charm
            }
            return Nomal
        }

    }


    /*fun IntToSt(st :Int):EnumStatus
    {
        when(st){
            1 -> return Poison
            2 -> return Sleep
            3 -> return Darkness
            4 -> return Confuse
            5 -> return Stun
            6 -> return Paralysis
            7 -> return Faint
            8 -> return Charm
        }

        return Nomal
    }*/

    fun PutListStatus():List<EnumStatus> {
        return listOf(EnumStatus.Nomal,EnumStatus.Poison,EnumStatus.Sleep, EnumStatus.Darkness,
            EnumStatus.Confuse,EnumStatus.Stun, EnumStatus.Paralysis,EnumStatus.Faint, EnumStatus.Charm)
    }



}