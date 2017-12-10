package utils

import java.nio.file.Files
import java.nio.file.Paths

class FileOpener {
    fun getFile(fileName: String): List<String> = Files.readAllLines(Paths.get(javaClass.getResource(fileName).toURI()))
}