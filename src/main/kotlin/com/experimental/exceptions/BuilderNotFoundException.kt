package com.experimental.exceptions

import com.experimental.compilation.SyntaxType

class BuilderNotFoundException(type: SyntaxType) :CompilationException("Builder with type: $type not found")