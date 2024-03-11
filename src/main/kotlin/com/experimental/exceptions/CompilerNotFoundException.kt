package com.experimental.exceptions

import com.experimental.compilation.SyntaxType

class CompilerNotFoundException(type: SyntaxType) : CompilationException("Compiler with type: $type not found")