package analisa_imagens;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class AnalisaImagens {
	/*
	 * percorre imagens para encontrar o pixel mais presente na parte superior, o
	 * pixel mais presente na parte central e o pixel mais presente na parte
	 * inferior. Retorna um int[][] com as informações
	 */
	public static int[][] descobrePixelsMaisPresentes(boolean ePersonagem1) {
		PixelsPresentes pixelsMP1 = new PixelsPresentes(); // armazenará os mais presentes do personagem 1
		PixelsPresentes pixelsMP2 = new PixelsPresentes(); // armazenará os mais presentes do personagem 2

		File pasta = new File("imagens de treino");
		File[] arquivos = pasta.listFiles();

		BufferedImage imagemBI = null;

		if (ePersonagem1) {
			for (int c = 100; c < 200; c++) {
				try {
					imagemBI = ImageIO.read(arquivos[c]);
				} catch (IOException e) {
					System.out.println("Erro com BufferedImage");
				}

				System.out.println((c - 99) + "ª imagem analisada no momento: " + arquivos[c]);
				System.out.println("Tamanho: " + (imagemBI.getWidth()) + "x" + (imagemBI.getHeight()));
				// primeiro coleta o pixel mais presente na parte superior
				pixelsMP1 = retornaMaisPresenteNaParteSuperior(pixelsMP1, imagemBI);
				System.out.println("Pixel atual mais presente na parte superior: " + pixelsMP1.pixelPresente1[0] + " - "
						+ pixelsMP1.pixelPresente1[1] + " - " + pixelsMP1.pixelPresente1[2]);
				System.out.println("Quantidade desse pixel: "
						+ pixelsMP1.contaPixelsSuperior[pixelsMP1.pixelPresente1[0]][pixelsMP1.pixelPresente1[1]][pixelsMP1.pixelPresente1[2]]);
				// agora coleta o pixel mais presente na parte central
				pixelsMP1 = retornaMaisPresenteNaParteCentral(pixelsMP1, imagemBI);
				System.out.println("Pixel atual mais presente na parte central: " + pixelsMP1.pixelPresente2[0] + " - "
						+ pixelsMP1.pixelPresente2[1] + " - " + pixelsMP1.pixelPresente2[2]);
				System.out.println("Quantidade desse pixel: "
						+ pixelsMP1.contaPixelsCentral[pixelsMP1.pixelPresente2[0]][pixelsMP1.pixelPresente2[1]][pixelsMP1.pixelPresente2[2]]);
				// agora coleta o pixel mais presente na parte inferior
				pixelsMP1 = retornaMaisPresenteNaParteInferior(pixelsMP1, imagemBI);
				System.out.println("Pixel atual mais presente na parte inferior: " + pixelsMP1.pixelPresente3[0] + " - "
						+ pixelsMP1.pixelPresente3[1] + " - " + pixelsMP1.pixelPresente3[2]);
				System.out.println("Quantidade desse pixel: "
						+ pixelsMP1.contaPixelsInferior[pixelsMP1.pixelPresente3[0]][pixelsMP1.pixelPresente3[1]][pixelsMP1.pixelPresente3[2]]);
			}
		} else {
			for (int c = 900; c < 1000; c++) {
				try {
					imagemBI = ImageIO.read(arquivos[c]);
				} catch (IOException e) {
					System.out.println("Erro com BufferedImage");
				}

				System.out.println((c - 899) + "ª imagem analisada no momento: " + arquivos[c]);
				System.out.println("Tamanho: " + (imagemBI.getWidth()) + "x" + (imagemBI.getHeight()));
				// primeiro coleta o pixel mais presente na parte superior
				pixelsMP2 = retornaMaisPresenteNaParteSuperior(pixelsMP2, imagemBI);
				System.out.println("Pixel atual mais presente na parte superior: " + pixelsMP2.pixelPresente1[0] + " - "
						+ pixelsMP2.pixelPresente1[1] + " - " + pixelsMP2.pixelPresente1[2]);
				System.out.println("Quantidade desse pixel: "
						+ pixelsMP2.contaPixelsSuperior[pixelsMP2.pixelPresente1[0]][pixelsMP2.pixelPresente1[1]][pixelsMP2.pixelPresente1[2]]);
				// agora coleta o pixel mais presente na parte central
				pixelsMP2 = retornaMaisPresenteNaParteCentral(pixelsMP2, imagemBI);
				System.out.println("Pixel atual mais presente na parte central: " + pixelsMP2.pixelPresente2[0] + " - "
						+ pixelsMP2.pixelPresente2[1] + " - " + pixelsMP2.pixelPresente2[2]);
				System.out.println("Quantidade desse pixel: "
						+ pixelsMP2.contaPixelsCentral[pixelsMP2.pixelPresente2[0]][pixelsMP2.pixelPresente2[1]][pixelsMP2.pixelPresente2[2]]);
				// agora coleta o pixel mais presente na parte inferior
				pixelsMP2 = retornaMaisPresenteNaParteInferior(pixelsMP2, imagemBI);
				System.out.println("Pixel atual mais presente na parte inferior: " + pixelsMP2.pixelPresente3[0] + " - "
						+ pixelsMP2.pixelPresente3[1] + " - " + pixelsMP2.pixelPresente3[2]);
				System.out.println("Quantidade desse pixel: "
						+ pixelsMP2.contaPixelsInferior[pixelsMP2.pixelPresente3[0]][pixelsMP2.pixelPresente3[1]][pixelsMP2.pixelPresente3[2]]);
			}
		}

		/*
		 * salva informações de pixels mais presentes das imagens do personagem
		 * analisado
		 */
		int[][] infoPixels = new int[3][3];
		if (ePersonagem1) {
			infoPixels[0][0] = pixelsMP1.pixelPresente1[0];
			infoPixels[0][1] = pixelsMP1.pixelPresente1[1];
			infoPixels[0][2] = pixelsMP1.pixelPresente1[2];
			infoPixels[1][0] = pixelsMP1.pixelPresente2[0];
			infoPixels[1][1] = pixelsMP1.pixelPresente2[1];
			infoPixels[1][2] = pixelsMP1.pixelPresente2[2];
			infoPixels[2][0] = pixelsMP1.pixelPresente3[0];
			infoPixels[2][1] = pixelsMP1.pixelPresente3[1];
			infoPixels[2][2] = pixelsMP1.pixelPresente3[2];
		} else {
			infoPixels[0][0] = pixelsMP2.pixelPresente1[0];
			infoPixels[0][1] = pixelsMP2.pixelPresente1[1];
			infoPixels[0][2] = pixelsMP2.pixelPresente1[2];
			infoPixels[1][0] = pixelsMP2.pixelPresente2[0];
			infoPixels[1][1] = pixelsMP2.pixelPresente2[1];
			infoPixels[1][2] = pixelsMP2.pixelPresente2[2];
			infoPixels[2][0] = pixelsMP2.pixelPresente3[0];
			infoPixels[2][1] = pixelsMP2.pixelPresente3[1];
			infoPixels[2][2] = pixelsMP2.pixelPresente3[2];
		}
		return infoPixels;
	}

	/*
	 * descobre o pixel mais presente na parte mais a cima da imagem analisada e
	 * retorna ele
	 */
	public static PixelsPresentes retornaMaisPresenteNaParteSuperior(PixelsPresentes pixelsMP, BufferedImage imagemBI) {
		int larguraA = 0;
		int larguraB = imagemBI.getWidth();
		int alturaA = 0;
		int alturaB = (int) (imagemBI.getHeight() * 0.34);
		for (int y = alturaA; y < alturaB; y++) {
			for (int x = larguraA; x < larguraB; x++) {
				Color cor = new Color(imagemBI.getRGB(x, y));
				int red = cor.getRed();
				int green = cor.getGreen();
				int blue = cor.getBlue();
				pixelsMP.contaPixelsSuperior[red][green][blue]++;
				if (pixelsMP.eMaiorQuePixelSuperiorSalvo(red, green, blue, pixelsMP.pixelPresente1[0],
						pixelsMP.pixelPresente1[1], pixelsMP.pixelPresente1[2])) {
					pixelsMP.pixelPresente1[0] = red;
					pixelsMP.pixelPresente1[1] = green;
					pixelsMP.pixelPresente1[2] = blue;
				}
			}
		}
		return pixelsMP;
	}

	/*
	 * descobre o pixel mais presente na parte central da imagem analisada e retorna
	 * ele
	 */
	public static PixelsPresentes retornaMaisPresenteNaParteCentral(PixelsPresentes pixelsMP, BufferedImage imagemBI) {
		int larguraA = 0;
		int larguraB = imagemBI.getWidth();
		int alturaA = (int) (imagemBI.getHeight() * 0.35);
		int alturaB = (int) (imagemBI.getHeight() * 0.67);
		for (int y = alturaA; y < alturaB; y++) {
			for (int x = larguraA; x < larguraB; x++) {
				Color cor = new Color(imagemBI.getRGB(x, y));
				int red = cor.getRed();
				int green = cor.getGreen();
				int blue = cor.getBlue();
				pixelsMP.contaPixelsCentral[red][green][blue]++;
				if (pixelsMP.eMaiorQuePixelCentralSalvo(red, green, blue, pixelsMP.pixelPresente2[0],
						pixelsMP.pixelPresente2[1], pixelsMP.pixelPresente2[2])) {
					pixelsMP.pixelPresente2[0] = red;
					pixelsMP.pixelPresente2[1] = green;
					pixelsMP.pixelPresente2[2] = blue;
				}
			}
		}
		return pixelsMP;
	}

	/*
	 * descobre o pixel mais presente na parte inferior da imagem analisada e
	 * retorna ele
	 */
	public static PixelsPresentes retornaMaisPresenteNaParteInferior(PixelsPresentes pixelsMP, BufferedImage imagemBI) {
		int larguraA = 0;
		int larguraB = imagemBI.getWidth();
		int alturaA = (int) (imagemBI.getHeight() * 0.68);
		int alturaB = imagemBI.getHeight();
		for (int y = alturaA; y < alturaB; y++) {
			for (int x = larguraA; x < larguraB; x++) {
				Color cor = new Color(imagemBI.getRGB(x, y));
				int red = cor.getRed();
				int green = cor.getGreen();
				int blue = cor.getBlue();
				pixelsMP.contaPixelsInferior[red][green][blue]++;
				if (pixelsMP.eMaiorQuePixelInferiorSalvo(red, green, blue, pixelsMP.pixelPresente3[0],
						pixelsMP.pixelPresente3[1], pixelsMP.pixelPresente3[2])) {
					pixelsMP.pixelPresente3[0] = red;
					pixelsMP.pixelPresente3[1] = green;
					pixelsMP.pixelPresente3[2] = blue;
				}
			}
		}
		return pixelsMP;
	}

	/*
	 * se o int[][] que guarda as informações dos pixels mais presentes estiver com
	 * elas, mostra as informações
	 */
	public static void mostraPixelsMaisPresentes(int[][] infoPixels, boolean ePersonagem1) {
		System.out.println("\nPixels mais presentes");
		if (ePersonagem1) {
			System.out.println("Homer");
			System.out.println("1º: " + infoPixels[0][0] + " - " + infoPixels[0][1] + " - " + infoPixels[0][2]);
			System.out.println("2º: " + infoPixels[1][0] + " - " + infoPixels[1][1] + " - " + infoPixels[1][2]);
			System.out.println("3º: " + infoPixels[2][0] + " - " + infoPixels[2][1] + " - " + infoPixels[2][2]);
		} else {
			System.out.println("Marge");
			System.out.println("1º: " + infoPixels[0][0] + " - " + infoPixels[0][1] + " - " + infoPixels[0][2]);
			System.out.println("2º: " + infoPixels[1][0] + " - " + infoPixels[1][1] + " - " + infoPixels[1][2]);
			System.out.println("3º: " + infoPixels[2][0] + " - " + infoPixels[2][1] + " - " + infoPixels[2][2]);
		}
	}

	/*
	 * pede uma imagem passada pelo usuário e analisa quais características
	 * escolhidas para o programa estão presentes nela
	 */
	public static void examinaCaracteristicasEmImagem(Scanner scan) {
		System.out.println("Digite o caminho da imagem");
		System.out
				.println("Preferencialmente faça com as imagens de treino, encontradas na pasta de imagens de treino");
		System.out.print("caminho: ");
		String caminhoImagem = scan.nextLine();
		BufferedImage imagemBI = null;
		try {
			imagemBI = ImageIO.read(new File(caminhoImagem));
		} catch (IOException e) {
			System.out.println("Erro com buffered image");
		}
		int contModificacoes = 0; // conta o número de pixels que foram modificados na imagem

		/*
		 * se encontrar um pixel com as características do Homer usa a cor abaixo para
		 * substituir. Se encontrar uma característica da Marge usa a cor dela para
		 * substituir.
		 */
		Color corHomer = new Color(0, 255, 128);
		Color corMarge = new Color(0, 255, 255);
		for (int y = 0; y < imagemBI.getHeight(); y++) {
			for (int x = 0; x < imagemBI.getWidth(); x++) {
				Color cor = new Color(imagemBI.getRGB(x, y));
				int r = cor.getRed();
				int g = cor.getGreen();
				int b = cor.getBlue();

				// caracteristica 1 do Homer
				if (b >= 0 && b <= 20 && g >= 116 && g <= 156 && r >= 110 && r <= 150) {
					imagemBI.setRGB(x, y, corHomer.getRGB());
					contModificacoes++;
				}

				// característica 2 do Homer
				if (b >= 81 && b <= 121 && g >= 131 && g <= 171 && r >= 161 && r <= 201) {
					imagemBI.setRGB(x, y, corHomer.getRGB());
					contModificacoes++;
				}

				// característica 3 do Homer
				if (b >= 110 && b <= 150 && g >= 85 && g <= 125 && r >= 171 && r <= 211) {
					imagemBI.setRGB(x, y, corHomer.getRGB());
					contModificacoes++;
				}

				// característica 1 da Marge
				if (b >= 162 && b <= 202 && g >= 112 && g <= 152 && r >= 46 && r <= 86) {
					imagemBI.setRGB(x, y, corMarge.getRGB());
					contModificacoes++;
				}

				// característica 2 da Marge
				if (b >= 171 && b <= 211 && g >= 191 && g <= 231 && r >= 168 && r <= 208) {
					imagemBI.setRGB(x, y, corMarge.getRGB());
					contModificacoes++;
				}

				// característica 3 da Marge
				if (b >= 0 && b <= 29 && g >= 11 && g <= 51 && r >= 93 && r <= 133) {
					imagemBI.setRGB(x, y, corMarge.getRGB());
					contModificacoes++;
				}
			}
		}

		System.out.println("Número de modificações feitas: " + contModificacoes);

		System.out.println("Digite o nome da nova imagem, que será salva");
		System.out.println("ex.: novaimagem.jpg");
		System.out.print("nome: ");
		String nomeNovaImagem = scan.nextLine();
		String novoCaminho = "imagens analisadas/";
		novoCaminho = novoCaminho.concat(nomeNovaImagem);
		File novoArquivo = new File(novoCaminho);
		try {
			ImageIO.write(imagemBI, "jpg", novoArquivo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
