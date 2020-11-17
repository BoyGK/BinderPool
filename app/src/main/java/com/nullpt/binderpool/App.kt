package com.nullpt.binderpool

import android.app.Application
import com.nullpt.toolsservice.Tools


/**
 * @author BGQ
 * Application公共基类
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Tools.init(this, R.xml.aidls, true)
    }


}