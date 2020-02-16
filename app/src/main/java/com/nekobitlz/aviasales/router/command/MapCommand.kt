package com.nekobitlz.aviasales.router.command

import androidx.fragment.app.Fragment
import com.nekobitlz.aviasales.features.map.MapArguments
import com.nekobitlz.aviasales.features.map.MapFragment
import com.nekobitlz.aviasales.router.IRouter

class MapCommand(private val args: MapArguments) : RouterCommand {

    override fun perform(fragment: Fragment, router: IRouter) {
        fragment.arguments = MapFragment.createArgs(args)
        router.openFragment(fragment)
    }
}