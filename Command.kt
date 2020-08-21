package com.example.myapplication

class Command( mode:Int,id:Int, name: String)
{//name: String, gogyo:Int, HP:Int, Spirit:Int, technique:Int, body:Int
    var name : String
    var Id : Int
    var mod : Int

    init {
        this.name = name
        this.Id = id
        this.mod = mode
    }

}