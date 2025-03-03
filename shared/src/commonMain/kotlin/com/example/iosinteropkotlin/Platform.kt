package com.example.iosinteropkotlin

interface Platform {
    val name: String
    val num: String
    val message:String
    val mt:String
    val swift:String
}

expect fun getPlatform(): Platform
expect object PlayGroundUtils {
    fun stepUpdate(sourceData: Array<IntArray>): Array<IntArray>
}
expect fun getMessage():String
expect fun getMT():String
expect fun getSwift():String