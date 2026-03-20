package com.theucsrocha.service

class MusicaService {
    String letra = '''
    Amou daquela vez como se fosse a última
Beijou sua mulher como se fosse a última
E cada filho seu como se fosse o único
E atravessou a rua com seu passo tímido

Subiu a construção como se fosse máquina
Ergueu no patamar quatro paredes sólidas
Tijolo com tijolo num desenho mágico
Seus olhos embotados de cimento e lágrima

Sentou pra descansar como se fosse sábado
Comeu feijão com arroz como se fosse um príncipe
Bebeu e soluçou como se fosse um náufrago
Dançou e gargalhou como se ouvisse música
E tropeçou no céu como se fosse um bêbado

E flutuou no ar como se fosse um pássaro
E se acabou no chão feito um pacote flácido
Agonizou no meio do passeio público
Morreu na contramão atrapalhando o tráfego

Amou daquela vez como se fosse o último
Beijou sua mulher como se fosse a única
E cada filho seu como se fosse o pródigo
E atravessou a rua com seu passo bêbado

Subiu a construção como se fosse sólido
Ergueu no patamar quatro paredes mágicas
Tijolo com tijolo num desenho lógico
Seus olhos embotados de cimento e tráfego

Sentou pra descansar como se fosse um príncipe
Comeu feijão com arroz como se fosse o máximo
Bebeu e soluçou como se fosse máquina
Dançou e gargalhou como se fosse o próximo
E tropeçou no céu como se ouvisse música

E flutuou no ar como se fosse sábado
E se acabou no chão feito um pacote tímido
Agonizou no meio do passeio náufrago
Morreu na contramão atrapalhando o público

Amou daquela vez como se fosse máquina
Beijou sua mulher como se fosse lógico
Ergueu no patamar quatro paredes flácidas
Sentou pra descansar como se fosse um pássaro
E flutuou no ar como se fosse um príncipe
E se acabou no chão feito um pacote bêbado
Morreu na contramão atrapalhando o sábado

Por esse pão pra comer, por esse chão pra dormir
A certidão pra nascer, a concessão pra sorrir
Por me deixar respirar, por me deixar existir
Deus lhe pague

Pela cachaça de graça que a gente tem que engolir
Pela fumaça desgraça que a gente tem que tossir
Pelos andaimes pingentes que a gente tem que cair
Deus lhe pague

Pela mulher carpideira pra nos louvar e cuspir
E pelas moscas bicheiras a nos beijar e cobrir
E pela paz derradeira que enfim vai nos redimir
Deus lhe pague
''';


    List<String> palavrasComCAO(String texto){
        def regexC = /(?i)\b\w*[çãõ]\w*\b/
        List<String> listaDePalavrasComCAO = texto.findAll(regexC)
        return listaDePalavrasComCAO
    }

    List<String> palavrasDitongo(String texto){
        def regexDitongos = /(?i)\b\w*(?:[aá]i|[aã]u|[eé]i|[eé]u|[oó]i|ou|[uú]i|ão|õe|ãe|am|em|en)(?![rl]\b)(?![íú])\w*\b/
        List<String> ditongos = texto.findAll(regexDitongos)
        return ditongos
    }

    List<String> palavrasTritongo(String texto){
        def regexTritongos = /(?i)\b\w*[gq](?:uai|uei|uio|uiu|uão|uões|uam|uem)\w*\b/
        List<String> tritongos = texto.findAll(regexTritongos)
        return tritongos
    }

    List<String> palavrasHiato(String texto) {
        List<String> resultado = []

        // 1. Pega todas as palavras que tenham 2 ou mais vogais juntas
        def palavrasComVogais = texto.findAll(/(?i)\b\p{L}*[aeiouáéíóúâêôãõ]{2,}\p{L}*\b/)

        for (String palavra : palavrasComVogais) {
            String p = palavra.toLowerCase()

            // REGRA 1: Hiatos Clássicos e Garantidos
            // Vogais idênticas, encontros de abertas (oa, eo) e acentuadas (aí, aú)
            if (p =~ /(aa|ee|ii|oo|uu|ae|ao|ea|eo|oa|oe|aí|aú|eí|eú|oí|oú|uí)/) {
                resultado.add(palavra)
                continue
            }

            // REGRA 2: Palavras terminadas em 'ua', 'uo', 'ia', 'io' (como su-a, ru-a, mei-o)
            // O (?<![qg]) garante que vamos ignorar se vier depois de Q ou G
            if (p =~ /(?<![qg])(ua|uo|ia|io)s?$/) {
                resultado.add(palavra)
                continue
            }

            // REGRA 3: O caso do verbo no infinitivo (ca-ir, sa-ir, tra-ir)
            if (p =~ /air$/) {
                resultado.add(palavra)
                continue
            }

            // O caso específico de "flutuou" (hiato no 'uo' seguido de ditongo 'ou')
            if (p =~ /(?<![qg])uou$/) {
                resultado.add(palavra)
                continue
            }
        }
        return resultado.unique()
    }


    String getLetra(){
        return letra
    }

    Map<String,Integer> retornarFrases(String texto){
        List<String> frases = texto.findAll(/.+\n/)
        Map<String,Integer> mapDeFrases = [:]
        frases.forEach { frase->
            int contadorFrases = 0
            for(String fraseTeste:frases){
                if(frase==fraseTeste){
                    contadorFrases++
                }
            }
            if(contadorFrases>1){
                mapDeFrases.put(frase,contadorFrases)
            }

        }
    return mapDeFrases
    }

    Map<String, Integer> retornarFrases4palavras(String texto){
        List<String> frases = texto.findAll(/(?Um)^[^\S\r\n]*\b\p{L}+(?:[^\S\r\n]+\p{L}+){3}\b/)
        Map<String, Integer> mapDeFrases = [:]

        frases.forEach { frase ->

            String fraseLimpa = frase.trim()

            int contadorFrases = 0
            for (String fraseTeste : frases) {
                if (fraseLimpa == fraseTeste.trim()) {
                    contadorFrases++
                }
            }

            if (contadorFrases > 0) {

                mapDeFrases.put(fraseLimpa, contadorFrases)
            }
        }
        return mapDeFrases
    }



}
