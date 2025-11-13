package com.example.atv_2_media_geral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atv_2_media_geral.model.Aluno
import com.example.atv_2_media_geral.ui.theme.Atv_2_media_geralTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Atv_2_media_geralTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppPrincipal(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppPrincipal(modifier: Modifier = Modifier) {
    var abaAtual by remember { mutableStateOf(0) }
    val listaAlunos = remember { mutableStateListOf<Aluno>() }
    val abas = listOf("Cadastro", "Listagem")
    
    Column(modifier = modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = abaAtual) {
            abas.forEachIndexed { index, titulo ->
                Tab(
                    selected = abaAtual == index,
                    onClick = { abaAtual = index },
                    text = { Text(titulo) }
                )
            }
        }
        
        when (abaAtual) {
            0 -> CadastroScreen(
                onAlunoSalvo = { aluno ->
                    listaAlunos.add(aluno)
                    abaAtual = 1
                }
            )
            1 -> ListagemScreen(alunos = listaAlunos)
        }
    }
}

@Composable
fun CadastroScreen(modifier: Modifier = Modifier, onAlunoSalvo: (Aluno) -> Unit) {
    var aluno by remember { mutableStateOf(Aluno("", mutableListOf(0.0, 0.0, 0.0))) }
    var nome by remember { mutableStateOf("") }
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }
    
    Column(modifier = modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top) {
        Text(text = "Cadastro de Aluno", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nome, 
            onValueChange = {
                nome = it
                aluno = aluno.withNome(it)
            }, 
            label = { Text("Nome completo") }, 
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Lançamento de Notas", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nota1, 
            onValueChange = {
                val v = it.replace(",", ".")
                nota1 = v
                val d = v.toDoubleOrNull() ?: 0.0
                aluno = aluno.withNota(0, d)
            }, 
            label = { Text("TP1") }, 
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nota2, 
            onValueChange = {
                val v = it.replace(",", ".")
                nota2 = v
                val d = v.toDoubleOrNull() ?: 0.0
                aluno = aluno.withNota(1, d)
            }, 
            label = { Text("TP2") }, 
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nota3, 
            onValueChange = {
                val v = it.replace(",", ".")
                nota3 = v
                val d = v.toDoubleOrNull() ?: 0.0
                aluno = aluno.withNota(2, d)
            }, 
            label = { Text("TP3") }, 
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (aluno.nome.isBlank()) {
                    erro = "Informe o nome do aluno"
                } else {
                    erro = ""
                    onAlunoSalvo(aluno.copy())
                    nome = ""
                    nota1 = ""
                    nota2 = ""
                    nota3 = ""
                    aluno = Aluno("", mutableListOf(0.0, 0.0, 0.0))
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { 
            Text("Salvar e Ver Listagem") 
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (erro.isNotEmpty()) {
            Text(
                text = erro, 
                color = MaterialTheme.colorScheme.error, 
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ListagemScreen(modifier: Modifier = Modifier, alunos: List<Aluno>) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Alunos Cadastrados",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        if (alunos.isEmpty()) {
            Text(
                text = "Nenhum aluno cadastrado ainda",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(alunos) { aluno ->
                    AlunoCard(aluno = aluno)
                }
            }
        }
    }
}

@Composable
fun AlunoCard(aluno: Aluno) {
    val media = aluno.media()
    val status = aluno.statusFinal()
    val corStatus = when {
        media < 6.0 -> MaterialTheme.colorScheme.error
        media <= 9.0 -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.tertiary
    }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = aluno.nome,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Notas:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "TP1: ${String.format("%.2f", aluno.notas[0])}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "TP2: ${String.format("%.2f", aluno.notas[1])}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "TP3: ${String.format("%.2f", aluno.notas[2])}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Média:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = String.format("%.2f", media),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = status,
                        style = MaterialTheme.typography.titleMedium,
                        color = corStatus,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPrincipalPreview() {
    Atv_2_media_geralTheme { AppPrincipal() }
}