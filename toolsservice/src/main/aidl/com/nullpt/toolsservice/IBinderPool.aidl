// IBinderPool.aidl
package com.nullpt.toolsservice;

// Declare any non-default types here with import statements

interface IBinderPool {

    IBinder queryBinder(String tag);
}
