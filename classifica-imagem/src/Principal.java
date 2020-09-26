import java.util.InputMismatchException;
import java.util.Scanner;

import analisa_imagens.AnalisaImagens;
import analisa_imagens.VerificaPixels;

public class Principal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--    Classifica imagens dos Simpsons    --");
		System.out.println("Homer x Marge");
		String menu = "\nMenu \n1. Analisa os pixels mais presentes em 10 imagens diferentes, para cada personagem \n2. Digita um pixel e verifica quantos dentro de uma margem próxima a ele estão presentes na imagem passada"
				+ "\n3. ... \n0. Sai do programa";
		int op = -1;

		do {
			try {
				System.out.println(menu);
				System.out.print("resposta: ");
				op = scan.nextInt();
				scan.nextLine();
				switch (op) {
				case 1:
					System.out
							.println("Analisa os pixels mais presentes em 10 imagens diferentes, para cada personagem");
					System.out.println("Salva pixels mais presentes de ambos os personagens em arquivos txt");
					boolean ePersonagem1 = true;
					AnalisaImagens.descobrePixelsMaisPresentes(ePersonagem1);
					ePersonagem1 = false;
					AnalisaImagens.descobrePixelsMaisPresentes(ePersonagem1);
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
