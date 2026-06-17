import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;

public class HealthProfileApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu primeiro nome: ");
        String firstName = scanner.nextLine();

        System.out.print("Digite seu sobrenome: ");
        String lastName = scanner.nextLine();

        char gender;
        while (true) {
            System.out.print("Digite seu gênero (M/F): ");
            String genderInput = scanner.next().trim().toUpperCase();
            if (genderInput.length() == 1 && (genderInput.charAt(0) == 'M' || genderInput.charAt(0) == 'F')) {
                gender = genderInput.charAt(0);
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite 'M' para Masculino ou 'F' para Feminino.");
            }
        }
        scanner.nextLine(); // Consume the rest of the line

        int day, month, year;
        while (true) {
            System.out.print("Digite sua data de nascimento (dia, mês e ano separados por espaço): ");
            try {
                day = scanner.nextInt();
                month = scanner.nextInt();
                year = scanner.nextInt();
                // Attempt to create LocalDate to validate the date
                LocalDate.of(year, month, day);
                break;
            } catch (DateTimeException e) {
                System.out.println("Data de nascimento inválida. Por favor, digite uma data válida (ex: 15 08 1990).");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite números para dia, mês e ano.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the rest of the line

        double height;
        while (true) {
            System.out.print("Digite sua altura em polegadas: ");
            try {
                height = scanner.nextDouble();
                if (height <= 0) {
                    System.out.println("Altura deve ser um valor positivo.");
                } else {
                    break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para a altura.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the rest of the line

        double weight;
        while (true) {
            System.out.print("Digite seu peso em libras: ");
            try {
                weight = scanner.nextDouble();
                if (weight <= 0) {
                    System.out.println("Peso deve ser um valor positivo.");
                } else {
                    break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para o peso.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume the rest of the line

        HealthProfile profile = new HealthProfile(firstName, lastName, gender, day, month, year, height, weight);

        String genderString = (profile.getGender() == 'M' || profile.getGender() == 'm') ? "Masculino" : "Feminino";

        System.out.println("\n--- Perfil de Saúde ---");
        System.out.println("Nome: " + profile.getFirstName() + " " + profile.getLastName());
        System.out.println("Gênero: " + genderString);
        System.out.printf("Data de nascimento: %d/%d/%d%n", profile.getDayOfBirth(), profile.getMonthOfBirth(), profile.getYearOfBirth());
        System.out.println("Idade: " + profile.calculateAge() + " anos");
        System.out.println("Altura: " + profile.getHeightInInches() + " polegadas");
        System.out.println("Peso: " + profile.getWeightInPounds() + " libras");
        System.out.printf("Índice de Massa Corporal (BMI): %.1f%n", profile.calculateBMI());
        System.out.println("Frequência cardíaca máxima: " + profile.calculateMaxHeartRate() + " bpm");
        double[] targetHeartRateRange = profile.calculateTargetHeartRateRange(); // Assuming this method exists and returns double[]
        System.out.printf("Faixa de frequência cardíaca alvo: %.0f - %.0f bpm%n", targetHeartRateRange[0], targetHeartRateRange[1]); // Assuming this method exists and returns double[]

        System.out.println("\n--- Tabela de Valores do IMC ---");
        System.out.println("| BMI             | Classificação  |");
        System.out.println("|-----------------|----------------|");
        System.out.println("| Menos de 18.5   | Abaixo do peso |");
        System.out.println("| 18.5 – 24.9     | Peso normal    |");
        System.out.println("| 25.0 – 29.9     | Sobrepeso      |");
        System.out.println("| 30.0 ou mais    | Obesidade      |");

        scanner.close();
    }
}