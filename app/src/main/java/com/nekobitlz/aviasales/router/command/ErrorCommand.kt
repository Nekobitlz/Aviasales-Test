package com.nekobitlz.aviasales.router.command

import androidx.fragment.app.Fragment
import com.nekobitlz.aviasales.router.IRouter

class ErrorCommand(private val errorText: String) : RouterCommand {

    override fun perform(fragment: Fragment, router: IRouter) {
        router.showSnackBar(fragment.view!!, errorText)
    }
}