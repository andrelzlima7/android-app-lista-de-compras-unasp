package com.example.listadecompras.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.listadecompras.ItemCompra
import com.example.listadecompras.ListaCompras
import com.example.listadecompras.components.MarcaDagua

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDetalhe(
    lista: ListaCompras,
    aoVoltar: () -> Unit,
    aoAdicionarItem: () -> Unit,
    aoDeletarItem: (ItemCompra) -> Unit
) {
    val itens = lista.itens.toList()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(lista.nome) },
                navigationIcon = {
                    TextButton(onClick = aoVoltar) { Text("← Voltar") }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = aoAdicionarItem) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar item")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            MarcaDagua()
            if (itens.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum item ainda.\nToque em + para adicionar.", fontSize = 16.sp)
                }
            } else {
                LazyColumn {
                    items(itens) { item ->
                        ListItem(
                            headlineContent = { Text(item.nome, fontSize = 18.sp) },
                            supportingContent = { Text("Quantidade: ${item.quantidade}") },
                            trailingContent = {
                                IconButton(onClick = { aoDeletarItem(item) }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Remover")
                                }
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}