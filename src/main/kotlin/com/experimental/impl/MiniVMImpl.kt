package com.experimental.impl

import com.experimental.model.Compiler
import com.experimental.model.MiniVM

class MiniVMImpl(private val compiler: Compiler) : MiniVM {
    override fun execute(code: String) {
        val program = compiler.compile(code)
        program.execute();
    }
}