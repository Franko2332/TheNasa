package ru.gb.thenasa

import android.app.Application
import android.util.Log
import androidx.room.Room
import ru.gb.thenasa.model.room.NasaDataBase

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("app", "hello")
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var nasaDataBase: NasaDataBase? = null
        private const val DB_NAME = "NasaDataBase.db"

        fun getMovieDatabase(): NasaDataBase {
            if (nasaDataBase == null) {
                synchronized(NasaDataBase::class.java) {
                    if (nasaDataBase == null) {
                        if (appInstance == null) throw
                        IllegalStateException("Application is null while creating DataBase")
                        nasaDataBase = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            NasaDataBase::class.java, DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
                return nasaDataBase!!
            }
        }
    }