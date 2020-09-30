package com.nullpt.toolsservice.manager

import com.nullpt.toolsservice.aidl.Book


/**
 * @author BGQ
 * 图数管理服务类：测试类
 * @tag TOOL_BOOK_MANAGER
 */
class BookManagerService : IBookManager.Stub() {

    private val mBookDataList = mutableListOf<Book>()

    override fun getBooks(): MutableList<Book> {
        return mBookDataList
    }

    override fun addBook(book: Book) {
        mBookDataList.add(book)
    }
}