package com.theucsrocha.application

import com.theucsrocha.service.MusicaService


class App {
    static void main(String[] args) {
        MusicaService musicaService = new MusicaService()

        String letra = musicaService.letra

        println(musicaService.palavrasComCAO(letra))
    }

}
