import java.sql.SQLException;
import view.Menu;

public class Main{
    public static void main(String[] args) throws SQLException {
        try{
            Menu.exibir();
            } catch (SQLException e){
            System.err.println("ERRO");
            e.printStackTrace();
        }
        System.out.print("Aplicação encerrada.");
    }
}