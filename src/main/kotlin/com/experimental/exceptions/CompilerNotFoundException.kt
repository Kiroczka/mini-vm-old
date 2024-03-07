package com.experimental.exceptions

import com.experimental.compilation.PartType

class CompilerNotFoundException(type: PartType) :CompilationException("Compiler with type: $type not found")