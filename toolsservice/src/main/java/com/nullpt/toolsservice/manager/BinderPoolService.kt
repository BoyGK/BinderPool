package com.nullpt.toolsservice.manager

import android.content.Context
import android.os.IBinder
import com.nullpt.toolsservice.IBinderPool
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_BOOK_MANAGER
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_MEDIA_MANAGER


/**
 * @author BGQ
 * 服务连接池类
 */
class BinderPoolService(private val context: Context) : IBinderPool.Stub() {

    override fun queryBinder(tag: String): IBinder? {
        return when (tag) {
            TOOL_BOOK_MANAGER -> BookManagerService()
            TOOL_MEDIA_MANAGER -> MediaPlayerManagerService(context)
            else -> null
        }
    }
}