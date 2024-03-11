package com.experimental.impl

import com.experimental.FileReader
import com.experimental.context.Context
import com.experimental.context.Function
import com.experimental.context.Variable
import kotlin.test.assertEquals

abstract class MiniVMTest {
    private val fileReader = FileReader()
    private val compiler = MiniVMBuilder().buildCompiler()

    fun runTest(fileName: String, expectedContext: Context) {
        val actualContext = compileContext(fileName)
        assertEquals(expectedContext, actualContext)
    }

    private fun compileContext(fileName: String): Context {
        val code = fileReader.readFromResources(fileName)
        val program = compiler.compile(code).value
        val context = Context()
        program.execute(context)
        return context
    }

    fun buildContext(variables: List<Variable> = listOf(), functions: List<Function> = listOf()) =
        Context()
            .withVariables(variables)
            .withFunctions(functions)
}