import java.util.InputMismatchException;
import java.util.Scanner;

import analisa_imagens.AnalisaImagens;
import extrai_caracteristicas.ExtraiCaracteristicas;

public class Principal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--    Classifica imagens dos Simpsons    --");
		System.out.println("Homer x Marge");
		String menu = "\nMenu \n1. Analisa os pixels mais presentes em 40 imagens diferentes, para cada personagem \n2. Cria arquivo arff com características dos personagens"
				+ "\n3. Analisa uma imagem de treino, pinta as características que encontrar, e salva na pasta de imagens analisadas \n4. ...\n0. Sai do programa";
		int op = -1;
		int[][] pixelsPresentesPersonagem1 = new int[3][3];
		int[][] pixelsPresentesPersonagem2 = new int[3][3];

		do {
			try {
				System.out.println(menu);
				System.out.print("resposta: ");
				op = scan.nextInt();
				scan.nextLine();
				switch (op) {
				case 1:
					System.out
							.println("Analisa os pixels mais presentes em 40 imagens diferentes, para cada personagem");
					boolean ePersonagem1 = true;
					pixelsPresentesPersonagem1 = AnalisaImagens.descobrePixelsMaisPresentes(ePersonagem1);
					ePersonagem1 = false;
					pixelsPresentesPersonagem2 = AnalisaImagens.descobrePixelsMaisPresentes(ePersonagem1);
					// mostra os pixels que foram salvos
					AnalisaImagens.mostraPixelsMaisPresentes(pixelsPresentesPersonagem1, true);
					AnalisaImagens.mostraPixelsMaisPresentes(pixelsPresentesPersonagem2, false);
					System.out.println("Pixels encontrados estão salvos e serão usados para gerar arquivo arff");
					break;

				case 2:
					System.out.println("Cria arquivo arff com características dos personagens");
					ExtraiCaracteristicas.extrair();
					break;

				case 3:
					System.out.println(
							"Analisa uma imagem de treino, pinta as características que encontrar, e salva na pasta de imagens analisadas");
					// aqui
					break;

				case 4:
					System.out.println("...");
					break;

				case 0:
					break;

				default:
					System.out.println("opção inválida");
				}
			} catch (InputMismatchException e) {
				System.out.println("opção inválida");
				scan.nextLine();
				op = -1;
			}
		} while (op != 0);

		scan.close();
		System.out.println("\n\nFim do programa");

	}
}
