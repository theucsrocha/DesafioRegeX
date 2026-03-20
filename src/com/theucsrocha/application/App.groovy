package com.theucsrocha.application

import com.theucsrocha.service.MusicaService


class App {
    static void main(String[] args) {
        MusicaService musicaService = new MusicaService()

        String letra = musicaService.letra

        println(musicaService.palavrasComCAO(letra))
        println(musicaService.palavrasDitongo(letra))
        println(musicaService.palavrasTritongo(letra))
        println(musicaService.palavrasHiato(letra))
        println(musicaService.retornarFrases(letra))
        println(musicaService.retornarFrases4palavras(letra))



    }

}
