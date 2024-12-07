package com.johnysoft.bytes_parse


private infix fun ByteArray.`verify Boundaries`(range: IntRange) {
    if(range.first < 0)
        throw ArrayIndexOutOfBoundsException("trying to get access to index less than 0")
    if(range.last >= size)
        throw ArrayIndexOutOfBoundsException("trying to get access to index greater than size")
}

private fun<T> ByteArray.extractValue(range: IntRange, call: (IntRange) -> T): T {
    `verify Boundaries`(range)
    return call(range)
}

private infix fun ByteArray.signedDwordLe(range: IntRange) = this[range.last].toInt().and(0xff)
    .rotateLeft(24)
    .or(this[range.last - 1].rotateLeft(16).toInt().and(0xff))
    .or(this[range.first + 1].rotateLeft(8).toInt().and(0xff))
    .or(this[range.first].toInt().and(0xff))

infix fun ByteArray.signedDwordLe(startIndex: Int): Int {
    val range = startIndex until startIndex + 4
    return extractValue(range, ::signedDwordLe)
}

infix fun ByteArray.unsignedDwordLe(startIndex: Int) = signedDwordLe(startIndex).toUInt()

private infix fun ByteArray.signedDwordBe(range: IntRange) = this[range.first].toInt().and(0xff)
    .rotateLeft(24)
    .or(this[range.first + 1].rotateLeft(16).toInt().and(0xff))
    .or(this[range.last - 1].rotateLeft(8).toInt().and(0xff))
    .or(this[range.last].toInt().and(0xff))

infix fun ByteArray.signedDwordBe(startIndex: Int): Int {
    val range = startIndex until startIndex + 4
    return extractValue(range, ::signedDwordBe)
}

infix fun ByteArray.unsignedDwordBe(startIndex: Int) = signedDwordBe(startIndex).toUInt()

private infix fun ByteArray.signedWordLe(range: IntRange) = this[range.last].toInt().and(0xff).rotateLeft(8).or(this[range.first].toInt().and(0xff)).toShort()

infix fun ByteArray.signedWordLe(startIndex: Int): Short {
    val range = startIndex until startIndex + 2
    return extractValue(range, ::signedWordLe)
}

infix fun ByteArray.unsignedWordLe(startIndex: Int) = signedWordLe(startIndex).toUShort()

private infix fun ByteArray.signedWordBe(range: IntRange) = this[range.first].toInt().and(0xff).rotateLeft(8).or(this[range.last].toInt().and(0xff)).toShort()

infix fun ByteArray.signedWordBe(startIndex: Int): Short {
    val range = startIndex until startIndex + 2
    return extractValue(range, ::signedWordBe)
}

infix fun ByteArray.unsignedWordBe(startIndex: Int) = signedWordBe(startIndex).toUShort()
