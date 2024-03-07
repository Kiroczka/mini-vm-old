package com.experimental.impl

import com.experimental.FileReader
import org.junit.jupiter.api.Test

class CompilerTest {
    @Test
    fun testVariableDeclaration() {
        val fileReader = FileReader()
        val miniVM = MiniVMBuilder().build()
        miniVM.execute(fileReader.readFromResources("var-declaration.mvm"))
    }
}