import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("\nДобро пожаловать в магазин игрушек!");
        
        while (true) {
            System.out.println("\n 1 - добавить игрушку \n 2 - разыграть игрушку \n");
            System.out.print("Введите число: ");
            userInput = scanner.nextLine();
            
            if (userInput.equals("1")) {
                System.out.println("\nВы выбрали добавить игрушку");
                break;  
            } else if (userInput.equals("2")) {
                System.out.println("\nВы выбрали разыграть игрушку");
                break; 
            } else {
                System.out.println("\nНекорректный ввод! Пожалуйста, попробуйте еще раз.");
            }
        }

        scanner.close();
        System.out.println("\nСпасибо за визит в магазин игрушек!\n");
    }
}