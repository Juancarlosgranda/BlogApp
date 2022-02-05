package com.jc.app.blog.data

/**
 * @see [https://medium.com/mobile-app-development-publication/android-reading-a-text-file-during-test-2815671e8b3b] (read on private mode)
 * @see [https://github.com/elye/demo_android_mock_web_service/blob/master/app/src/test/java/com/example/mockserverexperiment/ChatTest.kt]
 */
object FileReaderUtil {

    fun kotlinReadFileWithNewLineFromResources(fileName: String): String {
        return getInputStreamFromResource(fileName)?.bufferedReader()
            .use { bufferReader -> bufferReader?.readText() } ?: ""
    }

    private fun getInputStreamFromResource(fileName: String) =
        javaClass.classLoader?.getResourceAsStream(fileName)
}