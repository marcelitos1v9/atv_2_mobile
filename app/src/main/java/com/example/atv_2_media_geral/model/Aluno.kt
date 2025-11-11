package com.example.atv_2_media_geral.model

data class Aluno(
    val nome: String,
    val notas: MutableList<Double>
) {
    fun media(): Double {
        if (notas.isEmpty()) return 0.0
        return notas.sum() / notas.size
    }
    fun statusFinal(): String {
        val m = media()
        return when {
            m < 6.0 -> "Reprovado"
            m <= 9.0 -> "Aprovado"
            else -> "Ótimo Aproveitamento"
        }
    }
    fun withNome(novoNome: String): Aluno {
        return copy(nome = novoNome)
    }
    fun withNota(index: Int, valor: Double): Aluno {
        val novaLista = notas.toMutableList()
        if (index in 0 until novaLista.size) {
            novaLista[index] = valor
        }
        return copy(notas = novaLista)
    }
}