package se.codeboss.slacklin.internal

private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ"

fun String.toLowerCaseWithUnderscores(): String {
    val strBuilder = StringBuilder()
    strBuilder.append(this[0].toLowerCase())

    this.slice(IntRange(1, this.length - 1))
        .forEach {
            if (alphabet.contains(it))
                strBuilder.append('_')
            strBuilder.append(it.toLowerCase())
        }

    return strBuilder.toString()
}