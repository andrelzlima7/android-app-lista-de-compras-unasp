package com.example.listadecompras

data class ItemCompra(val nome: String, val quantidade: Int)

data class ListaCompras(val nome: String, val itens: MutableList<ItemCompra> = mutableListOf())