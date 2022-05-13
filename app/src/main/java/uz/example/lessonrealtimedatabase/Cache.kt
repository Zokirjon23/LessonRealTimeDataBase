package uz.example.lessonrealtimedatabase

import android.content.Context

class Cache(private val context: Context) {

    private val shp = context.getSharedPreferences("cache", Context.MODE_PRIVATE)

    companion object {
        var cache: Cache? = null
        fun init(context: Context) {
            if (cache == null) cache = Cache(context)
        }
    }

    fun setId(id : Long){
        shp.edit().putLong("id",id).apply()
    }

    fun getId() : Long{
        return shp.getLong("id",0L)
    }

}