package Test
import com.theucsrocha.service.MusicaService
import spock.lang.Specification

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


}
