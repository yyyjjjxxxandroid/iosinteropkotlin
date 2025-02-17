package com.example.iosinteropkotlin

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {

        return "Hello, ${platform.name},${platform.num}!"
    }
    fun kotlin():String{
        return "245"
    }
}