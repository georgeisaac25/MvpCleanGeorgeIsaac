package com.example.georgeissac.mvp.presentationLayer.interfaces

import com.example.georgeissac.mvp.presentationLayer.interfaces.ViewInterface

interface CommunicateWithPresenterFromView {
        fun onViewAttached(view: ViewInterface)
        fun onViewDetached()
        fun onDestroyed()
}
