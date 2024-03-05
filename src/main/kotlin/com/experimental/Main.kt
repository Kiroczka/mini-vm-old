package com.experimental

import com.experimental.impl.MiniVMBuilder

fun main(args: Array<String>) {
    val fileReader = FileReader()
    val miniVM = MiniVMBuilder().build()
    miniVM.execute(fileReader.readFromResources("sample-code.mvm"))
}