package com.example.georgeissac.mvp.presenter

import android.content.Context
import com.example.georgeissac.mvp.view.ViewInterface

interface CommunicateWithPresenterFromView {

        fun onViewAttached(view : ViewInterface)
        fun onViewDetached()
        fun onDestroyed()

}