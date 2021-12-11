package br.com.alex.imdbstudycase.router

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import br.com.alex.imdbstudycase.router.actions.Action
import br.com.alex.imdbstudycase.router.rule.ActionRule

typealias IntentParams = Bundle.() -> Unit

interface FeatureRouter {

    fun start(
        activity: Activity,
        action: Action,
        vararg flags: Int,
        args: IntentParams = {}
    )
}

internal class StandardFeatureRouter(private val actionRule: ActionRule) : FeatureRouter {

    override fun start(
        activity: Activity,
        action: Action,
        vararg flags: Int,
        args: IntentParams
    ) {
        if (actionRule.shouldAllowNavigation(action)) {
            activity.startActivity(createIntent(activity, action, flags, args))
        } else {
            actionRule.onNotAllowedNavigation(activity, action)
        }
    }

    private fun createIntent(
        activity: Activity,
        action: Action,
        flags: IntArray? = null,
        args: IntentParams = {}
    ) = Intent(action.name)
        .putExtras(Bundle().apply(args))
        .setPackage(activity.packageName)
        .apply {
            flags?.forEach { addFlags(it) }
        }
}