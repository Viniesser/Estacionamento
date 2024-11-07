package fag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public class Estacionamento {
    private List<Vaga> vagas;
    private List<Registro> registros;

    public Estacionamento(int quantidadeVagas) {
        vagas = new ArrayList<>();
      
        for (int i = 1; i <= quantidadeVagas; i++) {
            String tamanho = (i % 3 == 0) ? "Grande" : (i % 2 == 0) ? "Médio" : "Pequeno";
            vagas.add(new Vaga(i, tamanho));
        }
        registros = new ArrayList<>();
    }

   
    public boolean registrarEntrada(String placa, String modelo, String tamanho) {
        Optional<Vaga> vagaDisponivel = vagas.stream()
                .filter(vaga -> vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho))
                .findFirst();

        if (vagaDisponivel.isPresent()) {
            Vaga vaga = vagaDisponivel.get();
            Veiculo veiculo = new Veiculo(placa, modelo, tamanho);
            veiculo.setHoraEntrada(LocalDateTime.now());

            Registro registro = new Registro(veiculo, vaga, veiculo.getHoraEntrada());
            registros.add(registro);
            vaga.ocupar();
            System.out.println("Entrada registrada com sucesso!");
            return true;
        } else {
            System.out.println("Não há vagas disponíveis para esse tipo de veículo.");
            return false;
        }
    }

   
    public boolean registrarSaida(String placa) {
        Optional<Registro> registroOpt = registros.stream()
                .filter(registro -> registro.getVeiculo().getPlaca().equals(placa) && registro.getHoraSaida() == null)
                .findFirst();

        if (registroOpt.isPresent()) {
            Registro registro = registroOpt.get();
            registro.setHoraSaida(LocalDateTime.now()); 
            Vaga vaga = registro.getVaga();
            double valor = registro.calcularValor(); 
            vaga.liberar(); 

            System.out.println("Saída registrada com sucesso!");
            System.out.println("Valor a pagar: R$ " + valor);
            return true;
        } else {
            System.out.println("Veículo não encontrado ou já saiu.");
            return false;
        }
    }

   
    public void relatorioVagasOcupadas() {
        System.out.println("Vagas Ocupadas:");
        for (Registro registro : registros) {
            if (registro.getHoraSaida() == null) {
                System.out.println("Vaga " + registro.getVaga().getNumero() + " - " + registro.getVeiculo().getPlaca());
            }
        }
    }

    public void relatorioHistorico() {
        System.out.println("Histórico de Permanência:");
        for (Registro registro : registros) {
            if (registro.getHoraSaida() != null) {
                System.out.println(registro);
            }
        }
    }

 
    public void exibirVagas() {
        for (Vaga vaga : vagas) {
            vaga.exibirVaga();
        }
    }
}
