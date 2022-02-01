package br.com.alex.imdbstudycase.home

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeFragmentTest {

    private fun robot(func: HomeRobot.() -> Unit) = HomeRobot().apply(func)

    @Test
    fun `when movies list is loaded, it should show list items`() {
        robot {
            // prepare mock aqui
            launch {
                // chamadas de funcoes aqui
            }
        }
    }

    @Test
    fun `when movie is searched, it should return found items`() {

    }

    @Test
    fun `when view receive error, it should show error message`() {

    }
}