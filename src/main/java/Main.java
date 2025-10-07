import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        int opcao;
        do{
            System.out.println("Sistema de manutenção Industrial");

            System.out.println("1 - Cadastrar Máquina");
            System.out.println("2 - Cadastrar Técnico");
            System.out.println("3 - Cadastrar Peça");
            System.out.println("4 - Criar Ordem de Manutenção");
            System.out.println("5 - Associar Peças à Ordem");
            System.out.println("6 - Executar Manutenção");

            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:

            }
        } while (opcao != 0);

        sc.close();
    }
}