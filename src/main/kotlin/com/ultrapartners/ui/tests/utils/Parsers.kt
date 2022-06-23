package com.ultrapartners.ui.tests.utils

fun getLinkFromClipBoard(element: String): String {
    val regex = """(?<=href=\")[^\"]*""".toRegex() // (https://ultrapartners.com/)[^\"]*
    return regex.find(element)?.value ?: throw Exception("ClipBoardButton returns null")
}

fun linkParser(link: String): MutableMap<String, String> {
    val regex = """(?<=https://ultrapartners.com/redirect).*""".toRegex()
    val splitToList = regex.find(link)!!.value.split("/")
    val elementsMap = mutableMapOf<String, String>()
    splitToList.forEach{ e-> if(splitToList.indexOf(e)%2!=0) {
        elementsMap[e] = splitToList[splitToList.indexOf(e)+1]
    } }
    return elementsMap

}