package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Teste da classe {@link ServicoEnvioEmail} exemplificando testes usando Argument Captor
 */
@ExtendWith(MockitoExtension.class)
public class ServicoEnvioEmailTeste {
    /*Mock para simular o envio do email a plataforma */
    @Mock
    private PlataformaDeEnvio plataforma;
    /*injeta o serviço porque a plataforma é uma dependencia da classe serviço */
    @InjectMocks
    private ServicoEnvioEmail servico;
    /*a classe de um mock que consegue capturar um argumento de um metodo */
    @Captor
    private ArgumentCaptor<Email> emailCaptor;

    @Test
    public void validaSeEmailEstaComDadosCorretos() {

        String email = "jose.alve@provedor.com";
        String mensagem = "Mensagem de Teste 123";

        servico.enviaEmail(email, mensagem, true);
        /*captura o argumento que esta dentro da classe serviço na linha 17 aonde é passado
         * um email para o metodo enviar email da classe plataforma
         */
        Mockito.verify(plataforma).enviaEmail(emailCaptor.capture());

        Email emailCapturado = emailCaptor.getValue();
        Assertions.assertEquals(Formato.HTML, emailCapturado.getFormato());
        /* Assertions.assertEquals(mensagem, emailCapturado.getMensagem());*/
        /* Assertions.assertEquals(email, emailCapturado.getEnderecoEmail());*/
    }

}
