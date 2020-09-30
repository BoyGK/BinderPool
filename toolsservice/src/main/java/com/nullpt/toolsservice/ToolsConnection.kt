package com.nullpt.toolsservice

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.IInterface
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_BOOK_MANAGER
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_MEDIA_MANAGER
import com.nullpt.toolsservice.manager.IBookManager
import com.nullpt.toolsservice.manager.IMediaPlayerManager

/**
 * @author BQG
 */
class ToolsConnection {

    /**
     * 应用全局上下文
     */
    private var mContext: Context? = null

    /**
     * 工具服务类集合
     */
    private val mToolsServiceMap by lazy { mutableMapOf<String, IInterface?>() }

    /**
     * 工具服务查询通道
     */
    private var mBinderPool: IBinderPool? = null

    /**
     * 主动释放标识
     */
    private var mNotDestroyTag = true

    /**
     * 链接回调
     */
    private var mConnectCall: (() -> Unit)? = null

    /**
     * 判断是否绑定成功
     */
    private var mBindSuccess = false

    /**
     * 服务层链接对象
     */
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mToolsServiceMap.clear()
            //非主动断开后重连
            if (!mNotDestroyTag) {
                initTools(mContext!!)
            }
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBinderPool = IBinderPool.Stub.asInterface(service)
            mConnectCall?.let { it() }
        }

    }

    /**
     * 服务方法扩展,对于新加的服务可以再这里扩展添加
     */
    private fun service(tag: String): IInterface? {
        return when (tag) {
            TOOL_BOOK_MANAGER -> {
                IBookManager.Stub.asInterface(mBinderPool?.queryBinder(tag))
            }
            TOOL_MEDIA_MANAGER -> {
                IMediaPlayerManager.Stub.asInterface(mBinderPool?.queryBinder(tag))
            }
            else -> null
        }
    }

    /**
     * 初始化工具服务
     */
    fun initTools(context: Context, call: (() -> Unit)? = null) {
        mContext = context
        mConnectCall = call
        val intent = Intent(mContext, MainService::class.java)
        mContext!!.bindService(intent, mServiceConnection, BIND_AUTO_CREATE)
    }

    /**
     * 释放服务
     */
    fun release() {
        mNotDestroyTag = false
        mContext?.unbindService(mServiceConnection)
        mContext = null
    }

    /**
     * 获取工具服务
     */
    fun getToolsService(tag: String): IInterface? {
        return if (mToolsServiceMap.containsKey(tag)) {
            mToolsServiceMap[tag]
        } else {
            //map存储binder跨进程服务
            mToolsServiceMap[tag] = service(tag)
            //map集合中不存null值
            if (mToolsServiceMap[tag] == null) {
                mToolsServiceMap.remove(tag)
            } else {
                mToolsServiceMap[tag]
            }
        }
    }

    /**
     * 是否绑定成功
     */
    fun isBindSuccess(): Boolean {
        return mBindSuccess
    }
}