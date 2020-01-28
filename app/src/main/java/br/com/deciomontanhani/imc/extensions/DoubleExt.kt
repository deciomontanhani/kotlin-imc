package br.com.deciomontanhani.imc.extensions

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)