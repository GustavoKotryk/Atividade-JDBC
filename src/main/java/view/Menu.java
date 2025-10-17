package view;

import service.MaquinasService;
import dao.MaquinasDAO;
import dao.TecnicosDAO;
import model.StatusMaquinas;
import model.Tecnicos;
import service.TecnicosService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	private static final Scanner sc = new Scanner(System.in);
	private static MaquinasService maquinasService;
	private static TecnicosService tecnicosService;

	public static void exibir() throws SQLException {
		maquinasService = new MaquinasService(new MaquinasDAO());
		tecnicosService = new TecnicosService(new TecnicosDAO());

		int opcao;
		do {
			System.out.println("\n=== Sistema de manutenção Industrial ===");
			System.out.println("1 - Cadastrar Máquina");
			System.out.println("2 - Cadastrar Técnico");
			System.out.println("3 - Cadastrar Peça");
			System.out.println("4 - Criar Ordem de Manutenção");
			System.out.println("5 - Associar Peças à Ordem");
			System.out.println("6 - Executar Manutenção");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
				case 1:
					adicionarMaquinas();
					break;
				case 2:
					adicionarTecnico();
					break;
				case 0:
					System.out.println("Saindo do sistema...");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		} while (opcao != 0);

		sc.close();
	}

	private static void adicionarMaquinas() throws SQLException {
		System.out.println("\n=== Cadastro de Máquina ===");

		String nomeMaquina;
		do {
			System.out.print("Nome da máquina: ");
			nomeMaquina = sc.nextLine().trim();
			if (nomeMaquina.isEmpty()) {
				System.out.println("Nome é obrigatório!");
			}
		} while (nomeMaquina.isEmpty());

		String setorMaquina;
		do {
			System.out.print("Setor da máquina: ");
			setorMaquina = sc.nextLine().trim();
			if (setorMaquina.isEmpty()) {
				System.out.println("Setor é obrigatório!");
			}
		} while (setorMaquina.isEmpty());

		StatusMaquinas status = null;
		boolean statusValido = false;
		while (!statusValido) {
			System.out.print("Status (OPERACIONAL, EM_MANUTENCAO, PARADA): ");
			String statusStr = sc.nextLine().toUpperCase();

			try {
				status = StatusMaquinas.valueOf(statusStr);
				statusValido = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Status inválido! Use: OPERACIONAL, EM_MANUTENCAO ou PARADA");
			}
		}

		try {
			maquinasService.criarMaquina(nomeMaquina, setorMaquina, status);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro no banco de dados: " + e.getMessage());
		}
	}

	private static void adicionarTecnico() throws SQLException {
		System.out.println("\n=== Cadastro de Técnico ===");

		String nomeTecnico;
		do {
			System.out.print("Nome do técnico: ");
			nomeTecnico = sc.nextLine().trim();

			if (nomeTecnico.isEmpty()) {
				System.out.println("O nome é um campo obrigatório. Por favor, digite novamente.");
			}
		} while (nomeTecnico.isEmpty());

		String especialidade;
		do {
			System.out.print("Especialidade: ");
			especialidade = sc.nextLine().trim();

			if (especialidade.isEmpty()) {
				System.out.println("A especialidade é obrigatória. Por favor, digite novamente.");
			}
		} while (especialidade.isEmpty());

		try {
			tecnicosService.criarTecnico(nomeTecnico, especialidade);

		} catch (IllegalArgumentException | SQLException e) {
			System.out.println("Erro ao salvar o técnico: " + e.getMessage());
		}
	}
}