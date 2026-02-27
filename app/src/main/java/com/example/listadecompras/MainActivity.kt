package com.example.listadecompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import com.example.listadecompras.views.TelaAdicionarItem
import com.example.listadecompras.views.TelaDetalhe
import com.example.listadecompras.views.TelaListas
import com.example.listadecompras.ui.theme.ListaDeComprasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var temaEscuro by remember { mutableStateOf(false) }

            ListaDeComprasTheme(temaEscuro = temaEscuro) {
                AppNavegacao(
                    temaEscuro = temaEscuro,
                    aoAlternarTema = { temaEscuro = !temaEscuro }
                )
            }
        }
    }
}

@Composable
fun AppNavegacao(
    temaEscuro: Boolean,
    aoAlternarTema: () -> Unit
) {
    val listas = remember { mutableStateListOf<ListaCompras>() }
    var telaAtual by remember { mutableStateOf(Tela.LISTAS) }
    var indiceListaSelecionada by remember { mutableIntStateOf(0) }

    when (telaAtual) {
        Tela.LISTAS -> TelaListas(
            listas = listas,
            temaEscuro = temaEscuro,
            aoAlternarTema = aoAlternarTema,
            aoClicarLista = { indice ->
                indiceListaSelecionada = indice
                telaAtual = Tela.DETALHE_LISTA
            },
            aoAdicionarLista = { nome ->
                listas.add(ListaCompras(nome))
            },
            aoDeletarLista = { indice ->
                listas.removeAt(indice)
            }
        )

        Tela.DETALHE_LISTA -> TelaDetalhe(
            lista = listas[indiceListaSelecionada],
            aoVoltar = { telaAtual = Tela.LISTAS },
            aoAdicionarItem = { telaAtual = Tela.ADICIONAR_ITEM },
            aoDeletarItem = { item ->
                listas[indiceListaSelecionada].itens.remove(item)
            }
        )

        Tela.ADICIONAR_ITEM -> TelaAdicionarItem(
            aoVoltar = { telaAtual = Tela.DETALHE_LISTA },
            aoSalvar = { nome, quantidade ->
                listas[indiceListaSelecionada].itens.add(ItemCompra(nome, quantidade))
                telaAtual = Tela.DETALHE_LISTA
            }
        )
    }
}