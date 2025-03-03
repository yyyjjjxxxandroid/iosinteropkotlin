package com.example.iosinteropkotlin

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {

        return " (${platform.num})这是调c++改的数组（有具体实现）"+"\n"+"\n"+"\n"+"${platform.name}"
    }
    fun kotlin():String{
        return  "原数组\n" +
                "(1, 2, 3),\n" +
                "(4, 5, 6),\n" +
                "(7, 8, 9)"
    }
    fun messageee():String{
        return "这是调用c语言（有具体实现）"+platform.message+"\n"+"\n"+
                "这是调用MTGenerateSign只有.h没有.m所说的闭源库但是内部都是objective-c写的（1.2.1版本） MTGenerateSign().toString()这个方法出来的东西："+
                platform.mt
    }

     fun  getSwift():String{
         return platform.swift
     }
}