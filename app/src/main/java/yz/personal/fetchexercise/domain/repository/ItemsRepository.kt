package yz.personal.fetchexercise.domain.repository

import yz.personal.fetchexercise.domain.model.Item
import yz.personal.fetchexercise.util.Resource

interface ItemsRepository {
    suspend fun getItems(): Resource<List<Item>>
}