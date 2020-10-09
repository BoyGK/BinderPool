package com.nullpt.toolsservice.expand

import android.content.res.XmlResourceParser


/**
 * @author BGQ
 * 扩展配置文件读取
 */
object ToolsXMLReader {

    val aidl = mutableListOf<AIDL>()

    fun parser(xml: XmlResourceParser) {

        while (xml.eventType != XmlResourceParser.END_DOCUMENT) {

            if (xml.name == "aidl" && xml.attributeCount == 3) {
                aidl.add(
                    AIDL(
                        xml.getAttributeValue(null, "tag"),
                        xml.getAttributeValue(null, "binder"),
                        xml.getAttributeValue(null, "stub")
                    )
                )
            }

            xml.next()
        }

    }

}