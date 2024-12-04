package com.github.reintristan.makepairkaisenkt.controller

import javafx.scene.SnapshotParameters
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class Utils {
    companion object {
        fun decorateCardImage(sprite: Image): ImageView {
            val imgView = ImageView(sprite).apply {
                styleClass.add("card-kaisen")
                fitHeight = 100.0
                fitWidth = 100.0
            }
            val clip = Rectangle(imgView.getFitWidth(), imgView.getFitHeight())
            clip.setArcWidth(20.0)
            clip.setArcHeight(20.0)
            val params = SnapshotParameters()
            params.fill= Color.TRANSPARENT
            val roundedImage = imgView.snapshot(params, null)
            imgView.clip = clip
            imgView.image = roundedImage
            return imgView
        }
        fun formatTime (time: Int): String {
            val minutes = Math.floor((time / 60).toDouble()).toInt()
            val seconds = time % 60
            return "${minutes}:${if (seconds < 10) "0${seconds}" else seconds}"
        }
    }
}