package com.example.iosinteropkotlin

interface Platform {
    val name: String
    val num: String
}

expect fun getPlatform(): Platform
expect object PlayGroundUtils {
    fun stepUpdate(sourceData: Array<IntArray>): Array<IntArray>
}
