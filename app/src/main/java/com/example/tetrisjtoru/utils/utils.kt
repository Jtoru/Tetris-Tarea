package com.example.tetrisjtoru.utils
//Array en la cual se van a meter las figuras
fun	array2dOfByte(sizeOuter: Int, sizeInner: Int): Array<ByteArray> = Array(sizeOuter) { ByteArray(sizeInner) }