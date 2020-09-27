import java.util.InputMismatchException;
import java.util.Scanner;

import analisa_imagens.AnalisaImagens;
import analisa_imagens.VerificaPixels;

public class Principal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--    Classifica imagens dos Simpsons    --");
		System.out.println("Homer x Marge");
		String menu = "\nMenu \n1. Analisa os pixels mais presentes em 40 imagens diferentes, para cada personagem \n2. Digita um pixel e verifica quantos dentro de uma margem próxima a ele estão presentes na imagem passada"
				+ "\n3. ... \n0. Sai do programa";
		int op = -1;
		int[][] pixelsPresentesPersonagem1 = new int[3][3];
		int[][] pixelsPresentesPersonagem2 = new int[3][3];
		/*
		 * algumas opções do menu só estarão acessíveis se os pixels mais presentes
		 * forem encontrados
		 */
		boolean pixelsPresentesEncontrados = false;

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
					pixelsPresentesEncontrados = true;
					break;

				case 2:
					System.out.println(
							"Digita um pixel e verifica quantos dentro de uma margem próxima a ele estão presentes na imagem passada");
					VerificaPixels.verificaCores(scan);
					break;

				case 3:
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
