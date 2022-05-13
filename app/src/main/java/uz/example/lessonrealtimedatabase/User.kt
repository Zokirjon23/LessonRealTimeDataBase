package uz.example.lessonrealtimedatabase

import java.io.Serializable

data class User(val id : Long,val name : String,val lastName : String? = null) : Serializable{
    constructor() : this(0,"")
}