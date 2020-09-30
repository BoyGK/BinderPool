package com.nullpt.toolsservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.nullpt.toolsservice.manager.BinderPoolService

/**
 * @author BGQ
 */
class MainService : Service() {

    /**
     * 连接池服务
     */
    private val mBinder by lazy { BinderPoolService(this) }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}
