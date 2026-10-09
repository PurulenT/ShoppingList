package com.example.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,
    var id: Int = ShopItem.UNDEFINED_ID //cant give it a value immediately
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
