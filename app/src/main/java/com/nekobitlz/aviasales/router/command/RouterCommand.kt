package com.nekobitlz.aviasales.router.command

import androidx.fragment.app.Fragment
import com.nekobitlz.aviasales.router.IRouter

interface RouterCommand {
    fun perform(fragment: Fragment, router: IRouter)
}