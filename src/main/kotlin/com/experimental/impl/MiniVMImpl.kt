package com.experimental.impl

import com.experimental.compilation.ProgramCompiler
import com.experimental.context.Context
import com.experimental.model.MiniVM
import com.experimental.model.Program

class MiniVMImpl(private val compiler: ProgramCompiler) : MiniVM {
    override fun execute(code: String) {
        val program = compiler.compile(code).value
        val context = Context()
        program.execute(context)
    }
}