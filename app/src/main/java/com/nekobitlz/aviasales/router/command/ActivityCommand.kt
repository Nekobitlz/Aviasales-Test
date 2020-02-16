package com.nekobitlz.aviasales.router.command

import androidx.fragment.app.Fragment
import com.nekobitlz.aviasales.router.IRouter

class ActivityCommand : RouterCommand {

    override fun perform(fragment: Fragment, router: IRouter) {
        router.replaceFragment(fragment)
    }

}