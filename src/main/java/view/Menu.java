package view;

import dao.MaquinasDAO;
import dao.TecnicosDAO;
import model.Maquinas;
import model.StatusMaquinas;
import model.Tecnicos;

import java.sql.SQLException;
import java.util.Scanner;


public class Menu{
	private static final Scanner sc = new Scanner(System.in);

	public static void exibir() throws SQLException {

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
					adicionarMaquinas();
					break;
				case 2:
					adicionarTecnico();
			}
		} while (opcao != 0);

		sc.close();
	}

	private static void adicionarMaquinas() throws SQLException{
		System.out.print("Nome da máquina: ");
		String nomeMaquina = sc.nextLine();

		System.out.print("Setor da máquina: ");
		String setorMaquina = sc.nextLine();

		System.out.print("Status (OPERACIONAL, EM_MANUNTECAO): ");
		String statusStr = sc.nextLine().toUpperCase();
		StatusMaquinas status = StatusMaquinas.valueOf(statusStr);

		Maquinas maquinas = new Maquinas(nomeMaquina, setorMaquina, status);
		MaquinasDAO.adicionarMaquinas(maquinas);

		System.out.print("Máquina adicionada com sucesso!");
	}

	private static void adicionarTecnico() throws SQLException{
		String nomeTecnico;
		do {
			System.out.print("Nome do técnico: ");
			nomeTecnico = sc.nextLine();

			if (nomeTecnico.trim().isEmpty()) {
				System.out.println("Erro: O nome é um campo obrigatório. Por favor, digite novamente.");
			}
		} while (nomeTecnico.trim().isEmpty());

		System.out.print("Especialidade: ");
		String especialidade = sc.nextLine();

		try {
			Tecnicos novoTecnico = new Tecnicos(nomeTecnico, especialidade);
			TecnicosDAO.adicionarTecnico(novoTecnico);
			System.out.println("Técnico adicionado com sucesso!");
		} catch (IllegalArgumentException | SQLException e) {
			System.out.println("Erro ao salvar o técnico: " + e.getMessage());
		}
	}
	}
