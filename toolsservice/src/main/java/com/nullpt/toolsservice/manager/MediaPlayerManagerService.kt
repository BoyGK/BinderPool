package com.nullpt.toolsservice.manager

import android.content.Context
import android.media.MediaPlayer
import com.nullpt.toolsservice.callback.IOnMediaProgressListener
import java.util.*


/**
 * @author BQG
 * 媒体后台播放服务：测试
 * @tag TOOL_MEDIA_MANAGER
 */
class MediaPlayerManagerService(private val context: Context) : IMediaPlayerManager.Stub() {

    private val player = MediaPlayer()

    override fun reset(path: String?) {
        player.reset()
        player.setDataSource(path)
        player.prepare()
    }

    override fun play() {
        player.start()
    }

    override fun pause() {
        player.pause()
    }

    override fun seekTo(ms: Long) {
        player.seekTo(ms.toInt())
    }

    override fun isPlaying(): Boolean {
        return player.isPlaying
    }

    override fun registerProgress(listener: IOnMediaProgressListener?) {
        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                listener?.progress(
                    player.currentPosition.toFloat() / player.duration.toFloat(),
                    1f
                )
            }

        }
        timer.schedule(timerTask, 0, 10)
        player.setOnCompletionListener {
            timer.cancel()
            listener?.progress(1f, 1f)
        }
    }

    override fun release() {
        player.release()
    }
}