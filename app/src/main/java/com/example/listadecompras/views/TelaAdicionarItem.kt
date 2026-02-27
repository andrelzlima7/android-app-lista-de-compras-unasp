package com.example.listadecompras.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.listadecompras.components.MarcaDagua

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaAdicionarItem(
    aoVoltar: () -> Unit,
    aoSalvar: (String, Int) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("1") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Adicionar Item") },
                navigationIcon = {
                    TextButton(onClick = aoVoltar) { Text("← Voltar") }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MarcaDagua()
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome do item") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = quantidade,
                    onValueChange = { quantidade = it },
                    label = { Text("Quantidade") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        if (nome.isNotBlank()) {
                            aoSalvar(nome, quantidade.toIntOrNull() ?: 1)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}