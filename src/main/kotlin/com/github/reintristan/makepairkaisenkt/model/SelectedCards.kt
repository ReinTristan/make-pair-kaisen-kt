package com.github.reintristan.makepairkaisenkt.model


data class SelectedCards(var card1: GameCards?, var card2: GameCards?) {
    fun isPair(): Boolean {
        return card1?.name == card2?.name
    }
}