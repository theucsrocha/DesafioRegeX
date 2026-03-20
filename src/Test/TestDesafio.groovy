package Test
import com.theucsrocha.service.MusicaService
import spock.lang.Specification

import java.rmi.MarshalledObject

class TestDesafio extends Specification {
    MusicaService musicaService = new MusicaService()

    void "Teste para palavras com a letra ç ou õ ou ã"(){
        given:
            String teste = "Calçado calcalarga çoça lão pao pão";
            int valorEsperado = 4;

        when:
            int resultado = musicaService.quantidadeDeCAO(teste)

        then:
            resultado == valorEsperado
    }

    void "Teste para ditongos"(){
        given:
        String teste = "leite calcalarga çoça pão saudade mau";
        List<String> valorEsperado = ['leite','pão','saudade','mau'];

        when:
        List<String> resultado = musicaService.palavrasDitongo(teste)

        then:
        resultado == valorEsperado
    }

    void "Teste para tritongos"(){
        given:
        String teste = "Paraguai enxaguei tuiuiú pão saudade mau";
        List<String> valorEsperado = ['Paraguai','enxaguei','tuiuiú'];

        when:
        List<String> resultado = musicaService.palavrasTritongo(teste)

        then:
        resultado == valorEsperado
    }
    void "Teste para hiato"(){
        given:
        String teste = "Paraguai enxaguei loa coelho tuiuiú pão saudade mau";
        List<String> valorEsperado = ['loa','coelho'];

        when:
        List<String> resultado = musicaService.palavrasHiato(teste)

        then:
        resultado == valorEsperado
    }

    void "Teste para retornar frases repetidas"(){
        given:
        String teste = '''testelele
testelele
telsj
ueef
''';
        Map<String,Integer> valorEsperado = ['testelele\n':2];

        when:
        Map<String,Integer> resultado = musicaService.retornarFrases(teste)

        then:
        valorEsperado.get('testelele') == resultado.get('testelele')
    }


    def "Deve retornar apenas as primeiras 4 palavras de cada linha e contar as repetições"() {
        given: "Um texto de exemplo com frases repetidas no início"
        String textoTeste = """\
        eu e voce agora
        eu e voce
        eu e voce agora com mais palavras depois
        sozinho no escuro hoje
    """.stripIndent()

        when: "Chamamos o método para contar as frases de 4 palavras"

        Map<String, Integer> resultado = musicaService.retornarFrases4palavras(textoTeste)

        then: "O mapa deve conter as contagens corretas"
        resultado.size() == 2
        resultado['eu e voce agora'] == 2
        resultado['sozinho no escuro hoje'] == 1
    }

}
