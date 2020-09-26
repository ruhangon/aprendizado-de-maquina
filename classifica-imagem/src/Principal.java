import java.util.Scanner;

import analisa_imagens.AnalisaImagens;

public class Principal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--    Classifica imagens dos Simpsons    --");
		// System.out.println("Homer x Marge");

		System.out.println();

		// VerificaPixels.verificaCores(scan);
		AnalisaImagens.descobrePixelsMaisPresentes(true);

		System.out.println();

		scan.close();
		System.out.println("\n\nFim do programa");

	}
}
