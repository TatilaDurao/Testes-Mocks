package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Teste da classe {@link Conta} usando Spy para realizar o recurso de verificações
 */
@ExtendWith(MockitoExtension.class)
public class ContaTeste {
    /*Observa o comportamento do objeto conta com saldo */
    @Spy
    private Conta conta = new Conta(3000);

    @Test
    void verificaSeChamouMetodoDebita() {
        conta.pagaBoleto(300);
        Mockito.verify(conta).debita(300);
    }

    @Test
    void verificaSeNadaFoiChamado() {
        /*Não ouve interação na conta */
        Mockito.verifyNoInteractions(conta);
    }

    @Test
    void verificaAOrdemDasChamadas() {
        /*Objeto que estou espiando esta caminhando pela order que estou esperando/definindo a baixo */
        InOrder inOrder = Mockito.inOrder(conta);
        conta.pagaBoleto(300);
        conta.debita(300);
        conta.enviaCreditoParaEmissor(300);
        inOrder.verify(conta).pagaBoleto(300);
        inOrder.verify(conta).validaSaldo(300); 
        inOrder.verify(conta).debita(300);
        inOrder.verify(conta).enviaCreditoParaEmissor(300);
    }

    @Test
    void validaQuantidadeDeVezesQueMétodoFoiChamado() {

        conta.validaSaldo(100);
        conta.validaSaldo(100);
        conta.validaSaldo(100);
        /*Mockito.verify(conta, Mockito.times(3)).validaSaldo(ArgumentMatchers.anyInt()); */
        /* wantedNumberofinvocations - Quantas vezes voce validou saldo */
        Mockito.verify(conta, Mockito.times(3)).validaSaldo(100);
    }
    /**
     * 
     */
    @Test
    void retornaTruePraQualquerValorNaValidadcaoDeSaldo() {

       Mockito.doNothing().when(conta).validaSaldo(ArgumentMatchers.anyInt());
       conta.validaSaldo(3500);
    }
}
