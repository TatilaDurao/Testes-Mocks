package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

/**
 * Teste da classe {@link EnviarMensagem} exemplificando Spy
 */
@ExtendWith(MockitoExtension.class)
public class EnviarMensagemTeste {
    /*
    spy referencia um objeto real
    Observar o comportamento de uma classe usando o mock */
    @Spy
    EnviarMensagem enviarMensagem = new EnviarMensagem();

    @Test
    void adicionarMensagem() {
        /*Mokito.verifyNoInteractions  se não ouve iteração com a classe*/
        /*cria objeto com mensagem */
        Mensagem mensagem = new Mensagem("Hello World");
        /*spy usa o enviar menssagem para chamar a função add e passar o objeto para ela */
        enviarMensagem.adicionarMensagem(mensagem);
        /*mock verifica se o spy do enviar mensagem recebeu na função add o obj mensagem */
        verify(enviarMensagem).adicionarMensagem(mensagem);
        /*Mensagem vazia como falso */
        /* Assertions.assertFalse(enviarMensagem.getMensagens().isEmpty()) */
        Assertions.assertEquals(1, enviarMensagem.getMensagens().size());
    }

}
