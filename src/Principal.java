import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        int continuar;
        Scanner input = new Scanner(System.in);

        do{
            Jogo jogo = new Jogo();
            jogo.start();
            System.out.println("Deseja recomeçar? \n1 - sim \n2 - não");
            continuar = input.nextInt();
        }while(continuar==1);
    }
}
