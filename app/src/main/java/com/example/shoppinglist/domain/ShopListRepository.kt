package com.example.shoppinglist.domain

interface ShopListRepository {
    fun getShopList() : List<ShopItem>

    fun getShopItem(id: Int) : ShopItem

    fun editShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun addShopItem(shopItem: ShopItem)
}