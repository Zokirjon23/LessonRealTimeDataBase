package uz.example.lessonrealtimedatabase

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Cache.init(this)
    }
}