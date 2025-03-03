package com.example.iosinteropkotlin

import cocoapods.MTGenerateSign.MTGenerateSign
import com.ttypic.objclibs.greeting.HelloWorld
import platform.UIKit.UIDevice

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.IntVar
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.cstr
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pin
import kotlinx.cinterop.toCValues
import kotlinx.cinterop.toKString
import libtest.get_message
import nativelib.update
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.stringWithUTF8String


class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion

    override val num: String
        get() =   PlayGroundUtils.stepUpdate(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            )
        ).joinToString { it.joinToString() }

    override val message: String
        get() = getMessage()
    override val mt: String
        get() = getMT()
    override val swift: String
        get() = getSwift()
}

actual fun getPlatform(): Platform = IOSPlatform()
actual object PlayGroundUtils {
    actual fun stepUpdate(sourceData: Array<IntArray>): Array<IntArray> {
        return stepUpdateNative(sourceData)
    }
}
@OptIn(ExperimentalForeignApi::class)
fun stepUpdateNative(sourceData: Array<IntArray>): Array<IntArray> {

    val row = sourceData.size
    val col = sourceData[0].size

    val list1 = sourceData.map { it.pin() }
    val passList = list1.map { it.addressOf(0) }
    val result = mutableListOf<IntArray>()

    memScoped {
        val arg = allocArray<IntVar>(row * col)
        //这里调用了native的updata方法
        val resultNative = update(passList.toCValues(), row, col, arg)
        for (i in 0 until row) {
            val line = IntArray(col)
            for (j in 0 until col) {
                val index = i * col + j
                line[j] = resultNative!![index]
                // println("current value from kotlin: result[$index] = ${resultNative?.get(index)}")
            }
            result.add(line)
        }
    }



    return result.toTypedArray()
}
@OptIn(ExperimentalForeignApi::class)
actual fun getMessage():String{
    return get_message("world and equationl".cstr)?.toKString()?: "Default Message"
}

@OptIn(ExperimentalForeignApi::class)
actual fun getMT():String {

  return  MTGenerateSign().toString()
}
@OptIn(ExperimentalForeignApi::class)
actual fun getSwift(): String {
    return HelloWorld.helloWorld()
}