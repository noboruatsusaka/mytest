package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    val EXTRA_MESSAGE: String = "com.example.myfirstapp.MESSAGE"
     val TAG = "MyActivity"

    //val myApp = MyApp.getInstance()

   // val myApp = MyApp.getInstance("桃太郎",Gogyo.Tree,)
    val momo = Character("桃太郎",EnumGogyo.Tree.rawValue,100,10,10,10)
    val kin = Character("金太郎",EnumGogyo.Gold.rawValue,100,10,10,10)
    val waka = Character("牛若丸",EnumGogyo.Sat.rawValue,100,10,10,10)
    val boshi = Character("一寸法師",EnumGogyo.Water.rawValue,100,10,10,10)
    val abe = Character("安倍晴明",EnumGogyo.Fire.rawValue,100,10,10,10)
    val parthy = arrayOf(momo,kin,waka,boshi,abe)

    val yo1 = Character("a",EnumGogyo.Tree.rawValue,100,10,10,10)
    val yo2 = Character("b",EnumGogyo.Gold.rawValue,100,10,10,10)
    val yo3 = Character("c",EnumGogyo.Sat.rawValue,100,10,10,10)
    val yo4 = Character("d",EnumGogyo.Water.rawValue,100,10,10,10)
    val yo5 = Character("e",EnumGogyo.Fire.rawValue,100,10,10,10)
    val EnemyList =  mutableListOf(yo1,yo2,yo3,yo4,yo5)


    //var dmg:Int =100
    val name = arrayOf("鈴木", "佐藤", "田中", "山田", "武田", "阿部", "諸藤", "藤田", "馬場", "高下", "高島" )

    var art: Array<String> = arrayOf("null", "null", "null" )
    var spell: Array<String> = arrayOf("null", "null", "null")
    var skill: Array<String> = arrayOf("null", "null", "null" )
    var item: Array<String> = arrayOf("null", "null", "null" )
    var strcmmandList = arrayListOf(art, spell,skill,item);

    //var CommnadList : ArrayList<Command> = ArrayList()



    var fullyokai: Array<String> = arrayOf("null", "null", "null" )

    var ArtCmd : ArrayList<Command> = ArrayList()
    var SpellCmd : ArrayList<Command> = ArrayList()
    var SkillCmd : ArrayList<Command> = ArrayList()
    var ItemCmd : ArrayList<Command> = ArrayList()
    var CommnadList = arrayListOf(ArtCmd, SpellCmd,SkillCmd,ItemCmd)

    var EffectList : ArrayList<OverallEffect> = ArrayList()


    var cmdTyp: Int =0;

    private val texts = arrayOf("abc ", "bcd", "cde")
    var txtfile  = mutableListOf("a","b","c")

    var yokai:MutableList<String> = mutableListOf("null","null","null")


    var init = BaseEffect(0,0)
    var baseEffect:MutableList<BaseEffect> = mutableListOf(init)

    val Log: ArrayList<String> = ArrayList()

    //var dmg2 :Int =0

    var sekPrg:Int =0

    var strEnemy = " "
    var strParty = " "
    var iodata : String = "  "
    var sltParthy:Int=0
    var sltEnemy:Int=0

    /***************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         art  = resources.getStringArray(R.array.art)
         spell = resources.getStringArray(R.array.spell)
         skill = resources.getStringArray(R.array.skill)
         item = resources.getStringArray(R.array.item)
         strcmmandList = arrayListOf(art, spell,skill,item)

        //テキストファイル読み込み
        ArtCmd = FileToCommandList("ArtData.txt")
        SpellCmd = FileToCommandList("SpellData.txt")
        SkillCmd = FileToCommandList("SkillData.txt")
        ItemCmd = FileToCommandList("ItemData.txt")
        CommnadList = arrayListOf(ArtCmd, SpellCmd,SkillCmd,ItemCmd)

        SetBaseEffect()
        EffectList = FileToEffectList("baseEffectData.txt")


        /*var strcmd:String =CommnadList[0][0].cmd.toString()
        var strmod:String =CommnadList[0][0].mod.toString()
        var strname:String =CommnadList[0][0].name
        var data1:String=" $strcmd , $strmod , $strname"
        txtfile.add(data1)
        //txtfile
         */

        //barEnemy = findviewById<ProgressBar>(r.id.barEnemy)
        barEnemy.setMax(100)
        barEnemy.setProgress(100)


        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                //ツマミがドラッグされると呼ばれる
                override fun onProgressChanged(
                    seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                    textParthy.text = CommnadList[cmdTyp][progress].name
                    sekPrg = progress

                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // ツマミがタッチされた時に呼ばれる
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // ツマミがリリースされた時に呼ばれる
                }

            })


        switch1.setOnCheckedChangeListener { buttonView, isChecked ->

            // ON/OFFの状態(isChecked)をToastで表示
            //Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()

            if(isChecked){
                textParthy.text = "転身"
            }else{
                textParthy.text = "通常"
            }
            //textView2.text = cmmandList[cmdTyp][progress]
        }


        // リスナーを登録
        fullyokai = resources.getStringArray(R.array.yokai)
        val list: MutableList <String> = fullyokai.toMutableList()
        val shuffl = list.shuffled()
        val  prelist = shuffl.subList(0,4)
         yokai= prelist.toMutableList()


        EnemyList[0].name = yokai[0]
        EnemyList[1].name = yokai[1]
        EnemyList[2].name = yokai[2]
        EnemyList[3].name = yokai[3]


        var EnemyArrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, yokai)
        spinner.setAdapter(EnemyArrayAdapter)


        // リスナーを登録
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val spinner = parent as Spinner
                val item = spinner.selectedItem as String
                strEnemy = item
                textEnemy.text = item
                sltEnemy = position

                barEnemy.setMax(EnemyList[position].MXHP)
                barEnemy.setProgress(EnemyList[position].HP)
                button.setEnabled(true)

            }

            //　アイテムが選択されなかった
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }

        // リスナーを登録

        // リスナーを登録
        spinner2.onItemSelectedListener = object : OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val spinner = parent as Spinner
                val item = spinner.selectedItem as String
                strParty = item
                //textView2.text = item
                sltParthy = position

                barParthy.setMax(parthy[position].MXHP)
                barParthy.setProgress(parthy[position].HP)
                button.setEnabled(true)
            }

            //　アイテムが選択されなかった
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }

    }

    /* Sendボタン押下時 */
    /*fun sendMessage(view: View) {
        val intent: Intent = Intent(this@MainActivity,
            DisplayMessageActivity::class.java)
        //val editText: EditText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message: String = editText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }*/


    fun btnActionClick(view: View ){

        Log.clear()
        //dmg -= 10
        //dmg2 += 10
        //barEnemy.setProgress(dmg)



        //味方行動



        //敵行動
        var ecmdTyp = (0..1).random()
        var esekPrg = (0..9).random()
        var strEAction = CommnadList[ecmdTyp][esekPrg].name
        var eeffectId =  CommnadList[ecmdTyp][esekPrg].Id
        var ebaseId = EffectList[eeffectId].baseId


        var strAction :String = textParthy.getText() as String

        var firstflg:Boolean = false
        var modeflg:Boolean = false

        firstflg =FirstChk(cmdTyp,ecmdTyp)

        var peffectId =  CommnadList[cmdTyp][sekPrg].Id
        var pbaseId = EffectList[peffectId].baseId

        //var effectId=  CommnadList[cmdTyp][sekPrg].Id


        if(modeflg){
            var pmod = CommnadList[cmdTyp][sekPrg].mod
            var emod = CommnadList[ecmdTyp][esekPrg].mod

            val res1 = ((pmod - emod) + 3 ) % 3
            when(res1){
                1 ->{firstflg = true}
            }
        }

        var edmg:Int=0
        var pdmg:Int=0

        parthy[sltParthy].self = false
        EnemyList[sltEnemy].self = false

        var pmsg = " "
        var emsg = " "

        if(firstflg){
            parthy[sltParthy].self = true
            pmsg = parthy[sltParthy].BeingActedOn(baseEffect[pbaseId],EffectList[peffectId])
            emsg = EnemyList[sltEnemy].BeingActedOn(baseEffect[pbaseId],EffectList[peffectId])
            Log.add("$strParty の $strAction！ $pmsg$emsg")

            if(!EnemyList[sltEnemy].HPChk()){

                parthy[sltParthy].self = false
                EnemyList[sltEnemy].self = true
                pmsg = parthy[sltParthy].BeingActedOn(baseEffect[ebaseId],EffectList[eeffectId])
                emsg = EnemyList[sltEnemy].BeingActedOn(baseEffect[ebaseId],EffectList[eeffectId])
                Log.add("$strEnemy の $strEAction！ $emsg$pmsg")
            }

        }else{

            EnemyList[sltEnemy].self = true
            pmsg = parthy[sltParthy].BeingActedOn(baseEffect[ebaseId],EffectList[eeffectId])
            emsg = EnemyList[sltEnemy].BeingActedOn(baseEffect[ebaseId],EffectList[eeffectId])
            Log.add("$strEnemy の $strEAction！ $emsg$pmsg")

            if(!parthy[sltParthy].HPChk()){
                EnemyList[sltEnemy].self = false
                parthy[sltParthy].self = true
                pmsg = parthy[sltParthy].BeingActedOn(baseEffect[pbaseId],EffectList[peffectId])
                emsg = EnemyList[sltEnemy].BeingActedOn(baseEffect[pbaseId],EffectList[peffectId])
                Log.add("$strParty の$strAction！ $pmsg$emsg")
            }
        }

        if(parthy[sltParthy].HPChk()){
            Log.add(" $strParty は倒れた！ ")
            button.setEnabled(false)
        }
        if(EnemyList[sltEnemy].HPChk()){
            yokai.remove(strEnemy)
            EnemyList.removeAt(sltEnemy)
            Log.add(" $strEnemy は倒れた！ ")
            button.setEnabled(false)
        }

        textEnemy.text = EnemyList[sltEnemy].strStatus()
        Log.add(parthy[sltParthy].strStatus())

        barParthy.setProgress(parthy[sltParthy].HP)
        barEnemy.setProgress(EnemyList[sltEnemy].HP)

        var arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, Log)//data txtfile

         LogMsg.setAdapter(arrayAdapter)
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rbArt ->   if (checked) {cmdTyp = 0}
                R.id.rbSpell -> if (checked) {cmdTyp = 1}
                R.id.rbSkill -> if (checked) {cmdTyp = 2}
                R.id.rbItem ->  if (checked) {cmdTyp = 3}
            }
            textParthy.text = CommnadList[cmdTyp][sekPrg].name
        }
    }

    /*************************************************************************/

    fun SetBaseEffect(){

        var effect = BaseEffect(0,0)
        for(typ1 in 1..6){
            effect = BaseEffect(typ1,0)
            baseEffect.add(effect)
        }

        for(typ in 7..8){
            for(att in 1..8){
                effect = BaseEffect(typ,att)
                baseEffect.add(effect)
            }
        }

    }

    fun FirstChk(Pcmd:Int,Ecmd:Int):Boolean {

        if( (Pcmd == EnumCommand.Skill.i )  || (Pcmd == EnumCommand.Item.i ) ){
            return true
        }else{
            val res = (cmdTyp-Ecmd+3) % 3
            when(res){
                2 ->{return true}
            }
        }
        return false
    }


    fun FileToCommandList(file : String  ):ArrayList<Command>{

        val assetManager = resources.assets
        val io = assetManager.open(file)
        val str = io.bufferedReader().use { it.readText()}

        var iodata1 = str.replace("\r\n","")
        var list = iodata1.split(",")

        var cmdlist:ArrayList<Command> = ArrayList()
        var cmd:Command = Command(0,0,"nasi")

        var ctr:Int =0
        var case:Int=0
        var Id:Int=0
        var mode:Int=0
        list.forEach{
            case = ctr % 3
            when(case)    {
                0 -> {mode = Integer.parseInt(it)}
                1 -> {Id =  Integer.parseInt(it)}
                2 -> {
                    cmd = Command(mode,Id,it)
                    cmdlist.add(cmd)
                }
            }
            ctr++
        }

        return cmdlist

    }

    fun FileToEffectList(file : String  ):ArrayList<OverallEffect>{

        val assetManager = resources.assets
        val io = assetManager.open(file)
        val str = io.bufferedReader().use { it.readText()}

        var iodata1 = str.replace("\r\n","")
        var list = iodata1.split(",")

        var effelist:ArrayList<OverallEffect> = ArrayList()
        //var effect = OverallEffect(0,0,0,0)

        var baseId:Int =0
        var range:Int=0
        var power:Int=0
        var subId:Int=0
        var ctr:Int =0
        list.forEach{
            var case = ctr % 4
            when(case)    {
                0 -> {baseId = Integer.parseInt(it)}
                1 -> {range = Integer.parseInt(it)}
                2 -> {power = Integer.parseInt(it)}
                3 -> {subId = Integer.parseInt(it)
                    var effect = OverallEffect(baseId,range,power,subId)
                    effelist.add(effect)
                }
            }
            ctr++
        }
        return effelist
    }


    /*************************************************/
}