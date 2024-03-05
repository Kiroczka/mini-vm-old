package com.experimental.impl

import com.experimental.compilation.MultipleStatementsCompiler
import com.experimental.model.Compiler
import com.experimental.model.Program

class CompilerImpl(private val compiler: MultipleStatementsCompiler) : Compiler {
    override fun compile(code: String): Program {
        val statements = compiler.compile(code)
        return ProgramImpl(statements)
    }

}
