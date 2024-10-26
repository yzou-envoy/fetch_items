package yz.personal.fetchexercise.data.remote

import yz.personal.fetchexercise.domain.model.Item

data class ItemDTO(
    val id: Int,
    val listId: Int,
    val name: String?
)

fun ItemDTO.convertToItem(): Item {
    return Item(
        id = this.id,
        listId = this.listId,
        name = this.name ?: ""
    )
}