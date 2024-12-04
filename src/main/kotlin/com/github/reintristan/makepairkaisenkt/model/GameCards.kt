package com.github.reintristan.makepairkaisenkt.model

import javafx.scene.image.Image

data class GameCards(
    val sprite: Image,
    var isFlipped: Boolean,
    val name: String,
    val position: Int,
    val imageNumner: Int
) {
}