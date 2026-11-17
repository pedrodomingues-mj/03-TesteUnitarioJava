package contabancaria;

/**
 * Classe Conta Bancária — laboratório de Testes Unitários e TDD.
 *
 * INSTRUÇÕES:
 * 1. Leia os requisitos de cada método (javadoc + regras).
 * 2. Escreva os testes PRIMEIRO no arquivo ContaTest.java.
 * 3. Execute os testes e veja-os FALHAR (Red).
 * 4. Implemente o código mínimo para os testes PASSAREM (Green).
 * 5. Refatore se necessário (Refactor).
 */
public class Conta {

    private String titular;
    private double saldo;
    private boolean ativa;

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    /**
     * Cria uma conta bancária com saldo inicial.
     * Regras:
     * - O titular não pode ser nulo ou vazio (lançar IllegalArgumentException).
     * - O saldo inicial não pode ser negativo (lançar IllegalArgumentException).
     * - A conta deve ser criada como ativa.
     */
    public Conta(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("O titular não pode ser nulo ou vazio.");
        if (saldoInicial < 0)
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");

        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    /**
     * Cria uma conta bancária com saldo zero.
     */
    public Conta(String titular) {
        this(titular, 0);
    }

    /**
     * Deposita um valor na conta.
     * Regras:
     * - Valor deve ser maior que zero (lançar IllegalArgumentException).
     * - Conta deve estar ativa (lançar IllegalStateException).
     * - O saldo deve ser atualizado corretamente.
     */
    public void depositar(double valor) {
        validarContaAtiva();
        validarValorPositivo(valor);

        this.saldo += valor;
    }

    public void sacar(double valor) {
        validarContaAtiva();
        validarValorPositivo(valor);
        if (valor > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente.");
        }

        this.saldo -= valor;
    }

    public void transferir(Conta destino, double valor) {
        if (!this.ativa || !destino.isAtiva()) {
            throw new IllegalStateException("Ambas as contas devem estar ativas.");
        }
        validarValorPositivo(valor);
        if (valor > this.saldo) {
            throw new IllegalStateException("Saldo insuficiente para transferência.");
        }

        this.saldo -= valor;
        destino.saldo += valor;
    }

    public void encerrar() {
        validarContaAtiva();
        if (this.saldo > 0) {
            throw new IllegalStateException("Não é possível encerrar conta com saldo positivo.");
        }

        this.ativa = false;
    }

    private void validarContaAtiva() {
        if (!this.ativa) {
            throw new IllegalStateException("A conta está inativa.");
        }
    }

    private void validarValorPositivo(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

}
