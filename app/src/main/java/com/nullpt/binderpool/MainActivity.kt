package com.nullpt.binderpool

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nullpt.toolsservice.Tools
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_BOOK_MANAGER
import com.nullpt.toolsservice.ToolsManagerTag.TOOL_MEDIA_MANAGER
import com.nullpt.toolsservice.aidl.Book
import com.nullpt.toolsservice.callback.OnMediaProgressListener
import com.nullpt.toolsservice.expand.ToolsXMLReader
import com.nullpt.toolsservice.manager.IBookManager
import com.nullpt.toolsservice.manager.IMediaPlayerManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

/**
 * @author BGQ
 * 主进程Ui
 */
class MainActivity : AppCompatActivity() {

    private val mBookManager by lazy {
        Tools.getToolsService<IBookManager>(TOOL_BOOK_MANAGER)
    }

    private val mMediaManager by lazy {
        Tools.getToolsService<IMediaPlayerManager>(TOOL_MEDIA_MANAGER)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //测试跨进程服务通信
        addBook.setOnClickListener {
            mBookManager?.addBook(Book("热爱Android", "BGQ"))
        }
        getBooks.setOnClickListener {
            Toast.makeText(this, mBookManager?.books.toString(), Toast.LENGTH_SHORT).show()
        }

        //媒体服务测试，可前台进度
        register.setOnClickListener {
            mMediaManager?.registerProgress(object : OnMediaProgressListener() {
                override fun progress(firstProgress: Float, secondaryProgress: Float) {
                    runOnUiThread {
                        progress.progress = (1000 * firstProgress).toInt()
                        progress.secondaryProgress = (1000 * secondaryProgress).toInt()
                    }
                }
            })
        }

        //可后台息屏播放
        play.setOnClickListener {
            mMediaManager?.reset("/storage/emulated/0/netease/cloudmusic/Music/凤凰传奇 - 中国喜事.mp3")
            mMediaManager?.play()
        }

        pause.setOnClickListener {
            mMediaManager?.pause()
        }


    }
}
