package com.nullpt.toolsservice.manager

import android.content.Context
import android.os.IBinder
import com.nullpt.toolsservice.IBinderPool
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_BOOK_MANAGER
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_MEDIA_MANAGER
import com.nullpt.toolsservice.expand.ToolsXMLReader


/**
 * @author BGQ
 * 服务连接池类
 */
class BinderPoolService(private val context: Context) : IBinderPool.Stub() {

    override fun queryBinder(tag: String): IBinder? {
        return when (tag) {
            TOOL_BOOK_MANAGER -> BookManagerService()
            TOOL_MEDIA_MANAGER -> MediaPlayerManagerService(context)
            else -> queryExpandBinder(tag)
        }
    }

    /**
     * 自定义扩展binder
     */
    private fun queryExpandBinder(tag: String): IBinder? {
        val binderClass = ToolsXMLReader.aidl.find { it.tag == tag }?.binder ?: ""
        if (binderClass == "") {
            return null
        }
        return Class.forName(binderClass).newInstance() as IBinder?
    }
}