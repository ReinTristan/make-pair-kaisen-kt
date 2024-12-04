package com.github.reintristan.makepairkaisenkt.view

import com.github.reintristan.makepairkaisenkt.model.Constants
import com.github.reintristan.makepairkaisenkt.model.Images
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Font
import javafx.scene.text.Text

class InfiniteModeView(): GameView() {
    lateinit var livesTitle: Text
    lateinit var livesText: Text
    lateinit var livesImg: ImageView
    init {
        initUIChild()
        initPaneChild()
        initAnchorsChild()
        initSceneChild()
    }
    fun initUIChild() {
        super.initUI()
        this.title = Text(Constants.INFINITE_MODE).apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(150.0)
        }
        this.livesTitle = Text("Lives").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(100.0)
        }
        this.livesText = Text("x4").apply {
            styleClass.addAll("text-kaisen","text-kaisen-title")
            font = Font.font(100.0)
        }
        this.livesImg = ImageView(Images.livesImage).apply {
            fitHeight = 100.0
            fitWidth = 100.0
        }

    }
    fun initPaneChild() {
        super.initPane()
        this.root.children.add(this.title)
        this.root.children.add(this.livesTitle)
        this.root.children.add(this.livesText)
        this.root.children.add(this.livesImg)

    }
    fun initAnchorsChild() {
        super.initAnchors()
        AnchorPane.setTopAnchor(this.title, 50.0)
        AnchorPane.setLeftAnchor(this.title, Constants.WIDTH / 2 - this.title.layoutBounds.width / 4)
        AnchorPane.setTopAnchor(this.livesTitle, 25.0)
        AnchorPane.setRightAnchor(this.livesTitle, 90.0)
        AnchorPane.setTopAnchor(this.livesText, 100.0)
        AnchorPane.setRightAnchor(this.livesText, 75.0)
        AnchorPane.setTopAnchor(this.livesImg, 90.0)
        AnchorPane.setRightAnchor(this.livesImg, 140.0)
    }
    fun initSceneChild() {
        super.initScene()
    }
}