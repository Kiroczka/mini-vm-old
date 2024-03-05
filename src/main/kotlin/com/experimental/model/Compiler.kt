package com.experimental.model

interface Compiler {

    fun compile(code: String): Program
}