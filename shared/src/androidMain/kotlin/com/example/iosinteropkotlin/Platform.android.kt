package com.example.iosinteropkotlin

import com.equationl.nativelib.NativeLib

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val num: String
        get() {
            // 处理二维数组，将其转换为字符串
            return PlayGroundUtils.stepUpdate(
                arrayOf(
                    intArrayOf(1, 2, 3),
                    intArrayOf(4, 5, 6),
                    intArrayOf(7, 8, 9)
                )
            ).joinToString { it.joinToString() }
        }
    override val message: String
        get() = getMessage()
    override val mt: String
        get() = ""
    override val swift: String
        get() = ""
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual object PlayGroundUtils {
    actual fun stepUpdate(sourceData: Array<IntArray>): Array<IntArray> {
        return NativeLib().stepUpdate(sourceData)
    }
}


actual fun getMessage(): String {
    return "eee"
}

actual fun getMT():String {
    return "eee"
}

actual fun getSwift(): String {
    return "eee"
}