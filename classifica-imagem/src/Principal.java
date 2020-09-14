import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Principal {
	public static int quaisImagensAnalisar(Scanner scan) {
		int op = 0;
		System.out.println(
				"Selecione uma opção para escolher quais imagens serão utilizadas para extrair as três características");
		do {
			try {
				System.out.println("Escolha 1 para selecionar as 100 primeiras imagens de cada personagem");
				System.out.println("Escolha 2 para selecionar 100 imagens aleatóriamente de cada personagem");
				System.out.print("resposta: ");
				op = scan.nextInt();
				scan.nextLine();
				if ((op != 1) && (op != 2))
					System.out.println("opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("opção inválida");
				op = 0;
				scan.nextLine();
			}
		} while ((op != 1) && (op != 2));
		return op;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Set<Integer> indiceImagens = new HashSet<Integer>();
		System.out.println("--    Classifica imagens dos Simpsons    --");
		System.out.println("Bart x Milhouse");

		System.out.println();

		int iniciaisOuAleatorias = quaisImagensAnalisar(scan);

		if (iniciaisOuAleatorias == 1) {
			for (int i = 0; i < 100; i++)
				indiceImagens.add(i);
		} else {
			Random aleatorio = new Random();
			while (indiceImagens.size() != 100) {
				int num = aleatorio.nextInt(399);
				indiceImagens.add(num);
			}
		}


	}
}
