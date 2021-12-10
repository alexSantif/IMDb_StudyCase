package br.com.alex.imdbstudycase.core.navigation

class Event<out T>(
    private val content: T? = null
) {

    var consumed = false
        private set

    fun consume(): T? {
        return if (consumed) {
            null
        } else {
            consumed = true
            content
        }
    }

    fun consumeAndRun(block: (T?) -> Unit) {
        if (!consumed) {
            block(consume())
        }
    }

    fun consumeAndRunNonNull(block: (T) -> Unit) {
        consumeAndRun { if (it != null) block(it) }
    }

    fun peek(): T? = content

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Event<*>

        if (content != other.content) return false
        if (consumed != other.consumed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content?.hashCode() ?: 0
        result = 31 * result + consumed.hashCode()
        return result
    }
}
