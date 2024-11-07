package fag;

import java.time.Duration;
import java.time.LocalDateTime;

public class Registro {
    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public Registro(Veiculo veiculo, Vaga vaga, LocalDateTime horaEntrada) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public double calcularValor() {
        Duration duration = Duration.between(horaEntrada, horaSaida);
        long horas = duration.toHours();
        if (horas <= 1) {
            return 5.00;
        } else if (horas <= 3) {
            return 10.00;
        } else {
            return 15.00;
        }
    }

    @Override
    public String toString() {
        return String.format("Placa: %s | Vaga: #%d | Entrada: %s | Saída: %s | Valor a pagar: R$ %.2f", 
                             veiculo.getPlaca(), vaga.getNumero(), horaEntrada, horaSaida, calcularValor());
    }
}
