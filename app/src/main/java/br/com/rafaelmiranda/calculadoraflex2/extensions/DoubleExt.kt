package br.com.rafaelmiranda.calculadoraflex2.extensions

fun Double.format(digits:Int) = java.lang.String.format("%.${digits}f", this)