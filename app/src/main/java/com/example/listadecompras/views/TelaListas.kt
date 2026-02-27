package com.example.listadecompras.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listadecompras.ListaCompras
import com.example.listadecompras.R
import com.example.listadecompras.components.MarcaDagua

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaListas(
    listas: List<ListaCompras>,
    temaEscuro: Boolean,
    aoAlternarTema: () -> Unit,
    aoClicarLista: (Int) -> Unit,
    aoAdicionarLista: (String) -> Unit,
    aoDeletarLista: (Int) -> Unit
) {
    var exibirDialogo by remember { mutableStateOf(false) }
    var nomeLista by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cart_shopping_solid_full),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text("Unasp Listas")
                    }
                },
                actions = {
                    IconButton(onClick = aoAlternarTema) {
                        Icon(
                            painter = painterResource(id = if (temaEscuro) R.drawable.sun_solid_full else R.drawable.moon_regular_full),
                            contentDescription = if (temaEscuro) "Tema claro" else "Tema escuro",
                            tint = Color.Unspecified
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { exibirDialogo = true }) {
                Icon(Icons.Default.Add, contentDescription = "Nova lista")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            MarcaDagua()
            LazyColumn(contentPadding = padding) {
                items(listas.size) { indice ->
                    ListItem(
                        headlineContent = { Text(listas[indice].nome, fontSize = 18.sp) },
                        supportingContent = { Text("${listas[indice].itens.size} itens") },
                        trailingContent = {
                            IconButton(onClick = { aoDeletarLista(indice) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Excluir")
                            }
                        },
                        modifier = Modifier.clickable { aoClicarLista(indice) }
                    )
                    HorizontalDivider()
                }
            }
        }
    }

    if (exibirDialogo) {
        AlertDialog(
            onDismissRequest = { exibirDialogo = false },
            title = { Text("Nova Lista") },
            text = {
                OutlinedTextField(
                    value = nomeLista,
                    onValueChange = { nomeLista = it },
                    label = { Text("Nome da lista") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (nomeLista.isNotBlank()) {
                        aoAdicionarLista(nomeLista)
                        nomeLista = ""
                        exibirDialogo = false
                    }
                }) { Text("Criar") }
            },
            dismissButton = {
                TextButton(onClick = { exibirDialogo = false }) { Text("Cancelar") }
            }
        )
    }
}