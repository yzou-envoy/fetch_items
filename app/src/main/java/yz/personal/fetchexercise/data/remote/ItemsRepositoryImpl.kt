package yz.personal.fetchexercise.data.remote

import yz.personal.fetchexercise.domain.model.Item
import yz.personal.fetchexercise.domain.repository.ItemsRepository
import yz.personal.fetchexercise.util.Resource

class ItemsRepositoryImpl(private val itemsApi: ItemsApi): ItemsRepository {

    override suspend fun getItems(): Resource<List<Item>> {
        val response = itemsApi.getItems()
        return if (response.code() == 200) {
            Resource.Success(response.body()?.map { it.convertToItem() })
        } else {
            Resource.Error(response.errorBody()?.toString() ?: "Unknown error")
        }
    }
}