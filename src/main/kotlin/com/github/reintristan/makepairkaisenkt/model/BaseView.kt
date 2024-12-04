package com.github.reintristan.makepairkaisenkt.model

import javafx.scene.Scene
import javafx.scene.layout.AnchorPane

interface BaseView {
    var scene : Scene
    var root : AnchorPane
    fun initUI()
    fun initPane()
    fun initAnchors()
    fun initScene()

}