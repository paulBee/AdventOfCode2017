package week4

import isSingleton
import splitWhitespace

class PasswordValidator {

    fun isValidPassword(password: String): Boolean =
        password.splitWhitespace().groupBy({it}, {it}).values.all { it.isSingleton() }

    fun isValidPassword2(password: String): Boolean =
    password.splitWhitespace().map{ it.alphabeticalOrder() }.groupBy({it}, {it}).values.all { it.isSingleton() }

    fun String.alphabeticalOrder(): String = this.toCharArray().sorted().toString()
}
