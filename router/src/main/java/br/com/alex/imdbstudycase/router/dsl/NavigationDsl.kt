package br.com.alex.imdbstudycase.router.dsl

import br.com.alex.imdbstudycase.router.direction.NavDirection
import br.com.alex.imdbstudycase.router.direction.ScreenDirection
import br.com.alex.imdbstudycase.router.ktx.isFragment
import br.com.alex.imdbstudycase.router.ktx.isFragmentDialog
import br.com.alex.imdbstudycase.router.ktx.isIntent

internal typealias ScreenBlock = (ScreenDirection.() -> Unit)

fun NavDirection.doWhen(
    block: (NavigationDsl.() -> OtherDirectionDsl)
) = block(NavigationDsl(this))

object OtherDirectionDsl

class NavigationDsl(
    private val direction: NavDirection
) {

    private var isFragmentDialog: ScreenBlock? = null
    private var isFragment: ScreenBlock? = null
    private var isIntent: ScreenBlock? = null
    private lateinit var otherDirection: () -> Unit

    fun isFragmentDialog(block: ScreenBlock) = this.apply { isFragmentDialog = block }
    fun isFragment(block: ScreenBlock) = this.apply { isFragment = block }
    fun isIntent(block: ScreenBlock) = this.apply { isIntent = block }
    fun otherDirection(block: () -> Unit) = this.apply { otherDirection = block }
        .run { build() }
        .let { OtherDirectionDsl }

    private fun build() {
        when {
            direction.isFragmentDialog() -> {
                isFragmentDialog?.invoke(direction as ScreenDirection) ?: otherDirection()
            }
            direction.isFragment() -> {
                isFragment?.invoke(direction as ScreenDirection) ?: otherDirection()
            }
            direction.isIntent() -> {
                isIntent?.invoke(direction as ScreenDirection) ?: otherDirection()
            }

            else -> otherDirection()
        }
    }
}
