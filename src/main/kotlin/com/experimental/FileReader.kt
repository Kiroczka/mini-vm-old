package com.experimental

import java.io.FileNotFoundException

class FileReader {

    fun readFromResources(fileName: String): String {
        return FileReader::class.java.getResource("/$fileName")?.readText() ?: throw FileNotFoundException(fileName)
    }
}