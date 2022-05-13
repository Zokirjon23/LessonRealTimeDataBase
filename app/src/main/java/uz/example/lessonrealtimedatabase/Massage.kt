package uz.example.lessonrealtimedatabase

import android.os.SystemClock

data class Massage(val id : Long,val ownerId : Long,val massage: String){

    constructor() : this(0,0,"")
}
