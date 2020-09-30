package com.nullpt.toolsservice

import android.content.Context
import android.os.IInterface

/**
 * @author BGQ
 * 工具服务主类:可以轻松的扩展aidl跨进程服务
 * 截至取消注册，跟随整个应用生命周期
 */
object Tools {

    /**
     * 服务链接器
     */
    private val mToolsConnection by lazy { ToolsConnection() }

    /**
     * 初始化
     * @param context
     * @param waitFinish 是否阻塞服主线程，直到服务绑定成功（参数暂时无用）
     */
    fun init(context: Context, waitFinish: Boolean = false) {
        mToolsConnection.initTools(context)
    }

    /**
     * 释放资源
     */
    fun release() {
        mToolsConnection.release()
    }

    /**
     * 获取工具服务
     */
    fun <S : IInterface> getToolsService(tag: String): S? {
        return if (mToolsConnection.getToolsService(tag) == null) {
            null
        } else {
            mToolsConnection.getToolsService(tag) as S
        }
    }

    /**
     * 判断是否绑定成功
     */
    fun isBindSuccess(): Boolean {
        return mToolsConnection.isBindSuccess()
    }

}