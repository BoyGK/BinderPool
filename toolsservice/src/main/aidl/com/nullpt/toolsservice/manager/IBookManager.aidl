// IBookManager.aidl
package com.nullpt.toolsservice.manager;

// Declare any non-default types here with import statements
import com.nullpt.toolsservice.aidl.Book;


interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<Book> getBooks();

    void addBook(in Book book);

}
