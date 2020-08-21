package com.example.myapplication

class Character (name: String, gogyo:Int, HP:Int, Spirit:Int, technique:Int, body:Int){
    var numberOfSides: Int = 0
    var name: String = ""
    var gogyo:Int=0
    var HP:Int=0
    var MXHP:Int=0
    var Attack : Int=0
    var definece : Int=0
    var spirit: Int=0
    var technique: Int=0
    var body: Int=0
    var self:Boolean= false
    var status:Int = 0
    var regist:MutableList<Int> = mutableListOf(0,0,0,0,0,0,0,0)

    init {
        this.name = name
        this.gogyo = gogyo
        this.HP = HP
        this.MXHP = HP
        this.spirit = Spirit
        this.technique = technique
        this.body = body
    }



    /*fun strStatus():String{
        return
    }*/


    fun SetStatus( addSt: EnumStatus  ){
        //val stat:EnumStatus = EnumStatus
        //val addst = stat.IntToSt(stId)
        if( addSt == EnumStatus.Nomal )this.status = 0
        else {
            //val a = this.status + addSt.i
            var chk = this.status and addSt.bit

            if( chk != addSt.bit   ){
                this.status =  this.status + addSt.bit
            }
        }
    }


    fun strStatus():String{

        var str = "{$name}:"

        var init =1
        var bit = init
        for(i in 1..8 ){
            var chk = this.status and bit
            val ebit = EnumStatus.IntToSt(i)
            if( chk == ebit.bit  ){
                val sbit = ebit.strNM
                str = "$str$sbit"
            }
            bit = init shl i
        }

        return str
    }


    fun Damege(pt:Int ):Int {
        this.HP -= pt
        return this.HP
    }

    fun HPChk():Boolean{
        if(this.HP <= 0 ){
            return true
        }
        return false
    }


    fun simpleDescription() : String {
        return "A shape with ${numberOfSides} sides."
    }
    
    fun BeingActedOn( base:BaseEffect ,Overall:OverallEffect  ):String{

        var typ = base.Typ
        var att = base.Attribute
        var pow = Overall.Power

        val strAtklist:List<String> =listOf(" ","斬","打","突","熱","冷","雷","陽","陰")
        val strAtk = strAtklist[att]
        val stat = EnumStatus.IntToSt(att)
        val strSt = stat.strNM

        if(self){
            when(typ){
                1 -> {//HP回復
                    this.HP += pow
                    if( this.HP > this.MXHP ) this.HP = this.MXHP
                    //msg =
                    return " $name は体力を $pow 回復した！"
                }
                2 -> {//ST回復
                    this.status = 0
                    return " $name は状態を回復した！"
                }
                3 -> {//攻撃バフ
                    return " $name は攻撃力が上がった！"
                }
                4 -> {//防御バフ
                    return " $name は防御力が上がった！"
                }
                else ->{
                    return ""
                }
            }

        }else{
                when(typ){
                    5 -> {//攻撃デバフ
                        return " $name は攻撃力が下がった！"
                    }
                    6 -> {//防御デバフ
                        return " $name は防御力が下がった！"
                    }
                    7 -> {//属性ダメージ
                        this.HP -= pow
                        if( this.HP < 0 ) this.HP = 0
                        return "$name は$strAtk 攻撃で$pow を受けた！"
                    }
                    8 -> {//ST異常
                        val addSt = EnumStatus.IntToSt(att)
                        var chk = this.status and addSt.bit
                        if( chk != addSt.bit ){
                            this.status = this.status + addSt.bit
                        }
                        return "$name は$strSt　異常を受けた！"
                    }
                    else ->{
                        return ""
                    }

                }
        }

    }


    /*fun put(cmd: EnumCommand, prg :Int ) :Int {
        when(cmd) {
            Art ->{


                return }
            Spell -> {
                return}
            Skill ->{
                return}
            Item ->{
                return}
        }
        return
    }*/


    /*fun  getInstance(): Character {
        if (instance == null)
            instance = Character()

        return instance!!
    }*/

}