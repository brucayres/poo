import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

public class HealthProfile {
    private String firstName;
    private String lastName;
    private char gender;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private double heightInInches;
    private double weightInPounds;

    public HealthProfile(String firstName, String lastName, char gender, int dayOfBirth, int monthOfBirth, int yearOfBirth, double heightInInches, double weightInPounds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.heightInInches = heightInInches;
        this.weightInPounds = weightInPounds;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public char getGender() { return gender; }
    public void setGender(char gender) { this.gender = gender; }
    public int getDayOfBirth() { return dayOfBirth; }
    public void setDayOfBirth(int dayOfBirth) { this.dayOfBirth = dayOfBirth; }
    public int getMonthOfBirth() { return monthOfBirth; }
    public void setMonthOfBirth(int monthOfBirth) { this.monthOfBirth = monthOfBirth; }
    public int getYearOfBirth() { return yearOfBirth; }
    public void setYearOfBirth(int yearOfBirth) { this.yearOfBirth = yearOfBirth; }
    public double getHeightInInches() { return heightInInches; }
    public void setHeightInInches(double heightInInches) { this.heightInInches = heightInInches; }
    public double getWeightInPounds() { return weightInPounds; }
    public void setWeightInPounds(double weightInPounds) { this.weightInPounds = weightInPounds; }

    public int calculateAge() {
        LocalDate birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    public int calculateMaxHeartRate() {
        return 220 - calculateAge();
    }

    public String calculateTargetHeartRate() {
        int maxHeartRate = calculateMaxHeartRate();
        double minTarget = maxHeartRate * 0.50;
        double maxTarget = maxHeartRate * 0.85;
        return String.format("%.0f - %.0f", minTarget, maxTarget);
    }

    public double calculateBMI() {
        return (getWeightInPounds() * 703) / (getHeightInInches() * getHeightInInches());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu primeiro nome: ");
        String firstName = scanner.nextLine();

        System.out.print("Digite seu sobrenome: ");
        String lastName = scanner.nextLine();

        System.out.print("Digite seu gênero (M/F): ");
        char gender = scanner.next().charAt(0);

        System.out.print("Digite sua data de nascimento (dia, mês e ano separados por espaço): ");
        int day = scanner.nextInt();
        int month = scanner.nextInt();
        int year = scanner.nextInt();

        System.out.print("Digite sua altura em polegadas: ");
        double height = scanner.nextDouble();

        System.out.print("Digite seu peso em libras: ");
        double weight = scanner.nextDouble();

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
        System.out.printf("Faixa de frequência cardíaca alvo: %s%n", profile.calculateTargetHeartRate());

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