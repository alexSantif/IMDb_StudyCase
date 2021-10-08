package br.com.alex.imdbstudycase.router.di

import br.com.alex.imdbstudycase.router.FeatureRouter
import br.com.alex.imdbstudycase.router.StandardFeatureRouter
import br.com.alex.imdbstudycase.router.rule.ActionRule
import br.com.alex.imdbstudycase.router.rule.StandardActionRule
import org.koin.dsl.module

object RouterModule {
    val routerModule = module {
        factory<ActionRule> {
            StandardActionRule()
        }
        factory<FeatureRouter> {
            StandardFeatureRouter(get())
        }
    }
}