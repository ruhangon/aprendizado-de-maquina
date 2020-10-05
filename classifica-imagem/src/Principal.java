import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import analisa_imagens.AnalisaImagens;
import aprendizagem_bayesiana.AlgoritmoDeNaiveBayes;
import arvores_de_decisao.AlgoritmoDeJ48;
import extrai_caracteristicas.ExtraiCaracteristicas;

public class Principal {
	public static File pedeArquivo(Scanner scan) {
		System.out.println("Digite o caminho para o arquivo de imagem");
		System.out.print(" caminho: ");
		String caminhoImagem = scan.nextLine();
		File f = new File(caminhoImagem);
		return f;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("--    Classifica imagens dos Simpsons    --");
		System.out.println("Homer x Marge");
		String menu = "\nMenu \n1. Analisa os pixels mais presentes em 100 imagens diferentes, para cada personagem \n2. Cria arquivo arff com características dos personagens"
				+ "\n3. Analisa uma imagem, pinta as características que encontrar, e salva na pasta de imagens analisadas \n4. Usa algoritmo de naive Bayes em uma imagem \n5. Usa algoritmo de naive Bayes nas imagens de teste"
				+ "\n6. Usa algoritmo de J48 em uma imagem \n7. Mostra matriz de confusão para algoritmo de naive Bayes \n8. Mostra matriz de confusão para algoritmo de J48 \n0. Sai do programa";
		int op = -1;
		int[][] pixelsPresentesPersonagem1 = new int[3][3];
		int[][] pixelsPresentesPersonagem2 = new int[3][3];
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		decimalFormat.setRoundingMode(RoundingMode.DOWN);

		do {
			try {
				System.out.println(menu);
				System.out.print("resposta: ");
				op = scan.nextInt();
				scan.nextLine();
				switch (op) {
				case 1:
					System.out.println(
							"Analisa os pixels mais presentes em 100 imagens diferentes, para cada personagem");
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
							"Analisa uma imagem, pinta as características que encontrar, e salva na pasta de imagens analisadas");
					AnalisaImagens.examinaCaracteristicasEmImagem(scan);
					break;

				case 4:
					System.out.println("Usa naive Bayes em uma imagem");
					File arquivoNB = pedeArquivo(scan);
					double[] caracteristicasImagemNB = ExtraiCaracteristicas.extraiCaracteristicas(arquivoNB);
					double[] qualPersonagemNB = AlgoritmoDeNaiveBayes.naiveBayes(caracteristicasImagemNB);
					System.out.println("A probabilidade de ser Homer é: "
							+ (decimalFormat.format(100 * qualPersonagemNB[0])) + "%");
					System.out.println("A probabilidade de ser Marge é: "
							+ (decimalFormat.format(100 * qualPersonagemNB[1])) + "%");
					break;

				case 5:
					System.out.println("Usa algoritmo de naive Bayes nas imagens de teste");
					AlgoritmoDeNaiveBayes.aplicaNaiveBayesEmTeste();
					break;

				case 6:
					System.out.println("Usa J48 em uma imagem");
					File arquivoDT = pedeArquivo(scan);
					double[] caracteristicasImagemDT = ExtraiCaracteristicas.extraiCaracteristicas(arquivoDT);
					double[] qualPersonagemDT = AlgoritmoDeJ48.j48(caracteristicasImagemDT);
					System.out.println("A probabilidade de ser Homer é: "
							+ (decimalFormat.format(100 * qualPersonagemDT[0])) + "%");
					System.out.println("A probabilidade de ser Marge é: "
							+ (decimalFormat.format(100 * qualPersonagemDT[1])) + "%");
					break;

				case 7:
					System.out.println("Mostra matriz de confusão para naive Bayes");
					AlgoritmoDeNaiveBayes.mostraMatrizDeConfusao();
					break;

				case 8:
					System.out.println("Mostra matriz de confusão para J48");
					AlgoritmoDeJ48.mostraMatrizDeConfusao();
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
