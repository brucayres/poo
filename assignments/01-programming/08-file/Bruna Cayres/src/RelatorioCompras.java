import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RelatorioCompras {
    public static void main(String[] args) {
        // Verifica se os parâmetros foram passados (entrada e saída)
        if (args.length < 2) {
            System.err.println("Uso: java RelatorioCompras <arquivo_entrada> <arquivo_saida>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        Path inputPath = Paths.get(inputFilePath);
        Path outputPath = Paths.get(outputFilePath);

        // Uso de listas paralelas (ArrayList)
        ArrayList<String> clientes = new ArrayList<>();
        ArrayList<Double> totais = new ArrayList<>();

        try {
            // Leitura do arquivo usando a classe Files
            List<String> linhas = Files.readAllLines(inputPath);

            for (String linha : linhas) {
                String[] campos = linha.split(",");
                
                // Ignora linhas malformadas (com menos de 3 campos)
                if (campos.length < 3) {
                    continue; 
                }

                String nomeCliente = campos[0].trim();
                double valor;

                try {
                    // Tenta converter o valor numérico
                    valor = Double.parseDouble(campos[2].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Ignorando linha devido a erro de conversão numérica: " + linha);
                    continue;
                }

                // Lógica de acumulação em listas paralelas
                int index = clientes.indexOf(nomeCliente);
                if (index != -1) {
                    // Cliente já existe, acumula o valor
                    double totalAtual = totais.get(index);
                    totais.set(index, totalAtual + valor);
                } else {
                    // Novo cliente, adiciona na lista
                    clientes.add(nomeCliente);
                    totais.add(valor);
                }
            }

            // Prepara as linhas de saída
            ArrayList<String> linhasSaida = new ArrayList<>();
            for (int i = 0; i < clientes.size(); i++) {
                linhasSaida.add(clientes.get(i) + ": " + totais.get(i));
            }

            // Grava o arquivo de saída
            Files.write(outputPath, linhasSaida);
            System.out.println("Relatório gerado com sucesso em: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Erro de leitura ou gravação de arquivo: " + e.getMessage());
        }
    }
}
