package com.example.atv_2_media_geral

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                    AlunoScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AlunoScreen(modifier: Modifier = Modifier) {
    var aluno by remember { mutableStateOf(Aluno("", mutableListOf(0.0, 0.0, 0.0))) }
    var nome by remember { mutableStateOf("") }
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }
    var resultadoMedia by remember { mutableStateOf("") }
    var resultadoStatus by remember { mutableStateOf("") }
    var erro by remember { mutableStateOf("") }
    Column(modifier = modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top) {
        Text(text = "Cadastro de Aluno", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = nome, onValueChange = {
            nome = it
            aluno = aluno.withNome(it)
        }, label = { Text("Nome completo") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Lançamento de Notas", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = nota1, onValueChange = {
            val v = it.replace(",", ".")
            nota1 = v
            val d = v.toDoubleOrNull() ?: 0.0
            aluno = aluno.withNota(0, d)
        }, label = { Text("TP1") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = nota2, onValueChange = {
            val v = it.replace(",", ".")
            nota2 = v
            val d = v.toDoubleOrNull() ?: 0.0
            aluno = aluno.withNota(1, d)
        }, label = { Text("TP2") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = nota3, onValueChange = {
            val v = it.replace(",", ".")
            nota3 = v
            val d = v.toDoubleOrNull() ?: 0.0
            aluno = aluno.withNota(2, d)
        }, label = { Text("TP3") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (aluno.nome.isBlank()) {
                erro = "Informe o nome do aluno"
            } else {
                erro = ""
                val media = aluno.media()
                resultadoMedia = String.format("%.2f", media)
                resultadoStatus = aluno.statusFinal()
            }
        }) { Text("Calcular Média") }
        Spacer(modifier = Modifier.height(16.dp))
        if (erro.isNotEmpty()) {
            Text(text = erro, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (resultadoMedia.isNotEmpty()) {
            Text(text = "Aluno: ${aluno.nome}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Notas: TP1=${String.format("%.2f", aluno.notas[0])} | TP2=${String.format("%.2f", aluno.notas[1])} | TP3=${String.format("%.2f", aluno.notas[2])}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Média Geral: $resultadoMedia", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Status: $resultadoStatus", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlunoScreenPreview() {
    Atv_2_media_geralTheme { AlunoScreen() }
}