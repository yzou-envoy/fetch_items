package yz.personal.fetchexercise.presentation.display_items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import yz.personal.fetchexercise.domain.model.Item
import yz.personal.fetchexercise.domain.repository.ItemsRepository
import yz.personal.fetchexercise.util.Resource
import javax.inject.Inject

@HiltViewModel
class DisplayItemsViewModel @Inject constructor(
    private val repository: ItemsRepository
): ViewModel() {
    private val _state = mutableStateOf(listOf<Item>())
    val state: State<List<Item>> = _state

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun getItems() {
        viewModelScope.launch {
            val response = repository.getItems()
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        _state.value = data.filter { it.name.isNotEmpty() }.sortedWith(compareBy<Item> { it.listId }.thenBy { it.name })
                    }
                }
                is Resource.Error -> {
                    _error.emit(response.message ?: "Unknown error")
                }
            }
        }
    }
}