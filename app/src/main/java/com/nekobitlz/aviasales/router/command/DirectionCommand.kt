package com.nekobitlz.aviasales.router.command

import androidx.fragment.app.Fragment
import com.nekobitlz.aviasales.router.IRouter

class DirectionCommand : RouterCommand {

    override fun perform(fragment: Fragment, router: IRouter) {
        router.openFragment(fragment)
    }
}