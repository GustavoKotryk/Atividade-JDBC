package view;

import dao.OrdemManutencaoDAO;
import dao.PecaDAO;
import model.Maquinas;
import service.MaquinasService;
import dao.MaquinasDAO;
import dao.TecnicosDAO;
import model.StatusMaquinas;
import model.Tecnicos;
import service.OrdemManutencaoService;
import service.TecnicosService;
import service.PecaService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {
	private static final Scanner sc = new Scanner(System.in);
	private static MaquinasService maquinasService;
	private static TecnicosService tecnicosService;
	private static PecaService pecaService;
	private static OrdemManutencaoService ordemManutencaoService;
	private static MaquinasDAO maquinasDAO;
	private static TecnicosDAO tecnicosDAO;
	private static PecaDAO pecaDAO;
	private static OrdemManutencaoDAO ordemManutencaoDAO;

	public static void exibir() throws SQLException {
		maquinasDAO = new MaquinasDAO();
		tecnicosDAO = new TecnicosDAO();
		pecaDAO = new PecaDAO();
		ordemManutencaoDAO = new OrdemManutencaoDAO();

		maquinasService = new MaquinasService(maquinasDAO);
		tecnicosService = new TecnicosService(tecnicosDAO);
		pecaService = new PecaService(pecaDAO);
		ordemManutencaoService = new OrdemManutencaoService(ordemManutencaoDAO, maquinasDAO);

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
				case 3:
					adicionarPeca();
					break;
				case 4:
					criarOrdemDeManutencao();
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


	private static void adicionarPeca() {
		System.out.println("\n=== Cadastro de Peças ===");

		String nomePeca;
		do {
			System.out.print("Nome da peça: ");
			nomePeca = sc.nextLine().trim();

			if (nomePeca.isEmpty()) {
				System.out.println("O nome é um campo obrigatório. Por favor, digite novamente.");
			}
		} while (nomePeca.isEmpty());

		double estoque = -1;
		do {
			System.out.print("Quantidade em estoque: ");
			try {
				estoque = Double.parseDouble(sc.nextLine());

				if (estoque < 0) {
					System.out.println("O estoque deve ser um número maior ou igual a zero.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, digite um número.");
				estoque = -1;
			}
		} while (estoque < 0);

		try {
			pecaService.criarPeca(nomePeca, estoque);
		} catch (IllegalArgumentException e) {
			System.out.println("Erro: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro no banco de dados: " + e.getMessage());
		}
	}

	private static void criarOrdemDeManutencao() {
		System.out.println("\n=== Criar Ordem de Manutenção ===");
		try {
			List<Maquinas> maquinasOperacionais = maquinasDAO.listarMaquinasOperacionais();
			if (maquinasOperacionais.isEmpty()) {
				System.out.println("Nenhuma máquina operacional disponível para manutenção.");
				return;
			}

			System.out.println("Máquinas operacionais disponíveis:");
			maquinasOperacionais.forEach(m -> System.out.println("- " + m.getNome()));

			System.out.print("\nDigite o NOME da máquina: ");
			String nomeMaquinaSelecionada = sc.nextLine();

			Maquinas maquinaEscolhida = maquinasOperacionais.stream()

					.filter(m -> m.getNome().equalsIgnoreCase(nomeMaquinaSelecionada))
					.findFirst()
					.orElse(null);

			if (maquinaEscolhida == null) {
				System.out.println("Nome de máquina inválido ou máquina não está operacional!");
				return;
			}

			List<Tecnicos> todosTecnicos = tecnicosDAO.listarTecnicos();
			if (todosTecnicos.isEmpty()){
				System.out.println("Nenhum técnico cadastrado no sistema.");
				return;
			}

			System.out.println("\nTécnicos disponíveis:");
			todosTecnicos.forEach(t -> System.out.println("- " + t.getNome()));

			System.out.print("\nDigite o NOME do técnico: ");
			String nomeTecnicoSelecionado = sc.nextLine();

			Tecnicos tecnicoEscolhido = todosTecnicos.stream()
					.filter(t -> t.getNome().equalsIgnoreCase(nomeTecnicoSelecionado))
					.findFirst()
					.orElse(null);

			if (tecnicoEscolhido == null) {
				System.out.println("Nome de técnico inválido!");
				return;
			}

			ordemManutencaoService.criarOrdem(maquinaEscolhida.getId(), tecnicoEscolhido.getId());

			System.out.println("\nOrdem de manutenção criada com sucesso para a máquina '"
					+ maquinaEscolhida.getNome() + "'!");
			System.out.println("O status da máquina foi atualizado para EM_MANUTENCAO.");

		} catch (SQLException e) {
			System.out.println("Erro de banco de dados: " + e.getMessage());
		}
	}
	}