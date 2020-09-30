package com.nullpt.toolsservice.expand

import android.content.res.XmlResourceParser

/**
 * @author BGQ
 * 扩展配置文件读取
 */
class ToolsXMLReader {

    fun parser(xml: XmlResourceParser) {
        println("BGQ->" + xml.attributeCount)
    }

}