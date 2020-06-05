package com.studying.storedemo.model

private const val BUY = "Куплено: "
private const val RECEIVED = "Получено: "

open class Models(val type: String, val number: Int)

class Provider(number: Int) : Models(RECEIVED, number)
class Buyer (number: Int) : Models(BUY, number)
