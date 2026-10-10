package com.example.shoppinglist.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository{
//    private val shopList = mutableListOf<ShopItem>();
    private val shopList = sortedSetOf<ShopItem>({o1, o2 -> o1.id.compareTo(o2.id)})
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0;

    init{
        for(i in 0 until 15){
            val item = ShopItem("item " + i, i, true)
            addShopItem(item)
        }

    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw RuntimeException("Element with id $id not found")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldShopItem = getShopItem(shopItem.id);
        shopList.remove(oldShopItem);
        addShopItem(shopItem);
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem);
        updateList()
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++;
        }
        shopList.add(shopItem);
        updateList()
    }

    private fun updateList(){
        shopListLD.value = shopList.toList();
    }
}