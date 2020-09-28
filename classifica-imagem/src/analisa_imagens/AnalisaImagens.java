package analisa_imagens;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

		File pastaPersonagens = new File("imagens");
		String[] imagensPersonagens = pastaPersonagens.list();
		String caminhoImagemAtual = ""; // mostrará a imagem atual com o seu caminho, para criar o buffered image
		BufferedImage imagemBI = null;

		if (ePersonagem1) {
			for (int c = 0; c < 40; c++) {
				caminhoImagemAtual = "";
				caminhoImagemAtual = caminhoImagemAtual.concat("imagens/");
				caminhoImagemAtual = caminhoImagemAtual.concat(imagensPersonagens[c]);

				try {
					imagemBI = ImageIO.read(new File(caminhoImagemAtual));
				} catch (IOException e) {
					System.out.println("Erro com BufferedImage");
				}

				System.out.println((c + 1) + "ª imagem analisada no momento: " + imagensPersonagens[c]);
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
			for (int c = 500; c < 540; c++) {
				caminhoImagemAtual = "";
				caminhoImagemAtual = caminhoImagemAtual.concat("imagens/");
				caminhoImagemAtual = caminhoImagemAtual.concat(imagensPersonagens[c]);

				try {
					imagemBI = ImageIO.read(new File(caminhoImagemAtual));
				} catch (IOException e) {
					System.out.println("Erro com BufferedImage");
				}

				System.out.println((c - 499) + "ª imagem analisada no momento: " + imagensPersonagens[c]);
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
		/*
		 * percorre a imagem ignorando 10% em relação a largura nas bordas e 10% em
		 * relação a altura nas bordas. O foco é procurar pixels mais ao centro,
		 * primeiro na parte superior analisada
		 */
		int larguraA = (int) (imagemBI.getWidth() * 0.11);
		int larguraB = (int) (imagemBI.getWidth() * 0.36);
		int alturaA = (int) (imagemBI.getHeight() * 0.11);
		int alturaB = (int) (imagemBI.getHeight() * 0.36);
		for (int y = alturaA; y < alturaB; y++) {
			for (int x = larguraA; x < larguraB; x++) {
				int corRGB = imagemBI.getRGB(x, y);
				int red = (corRGB >> 16) & 0xFF;
				int green = (corRGB >> 8) & 0xFF;
				int blue = corRGB & 0xFF;
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
		/*
		 * percorre a imagem ignorando 10% em relação a largura nas bordas e 10% em
		 * relação a altura nas bordas. O foco é procurar pixels mais ao centro, agora
		 * na parte central analisada
		 */
		int larguraA = (int) (imagemBI.getWidth() * 0.37);
		int larguraB = (int) (imagemBI.getWidth() * 0.63);
		int alturaA = (int) (imagemBI.getHeight() * 0.37);
		int alturaB = (int) (imagemBI.getHeight() * 0.63);
		for (int y = alturaA; y < alturaB; y++) {
			for (int x = larguraA; x < larguraB; x++) {
				int corRGB = imagemBI.getRGB(x, y);
				int red = (corRGB >> 16) & 0xFF;
				int green = (corRGB >> 8) & 0xFF;
				int blue = corRGB & 0xFF;
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
		/*
		 * percorre a imagem ignorando 10% em relação a largura nas bordas e 10% em
		 * relação a altura nas bordas. O foco é procurar pixels mais ao centro, agora
		 * na parte inferior analisada
		 */
		int larguraA = (int) (imagemBI.getWidth() * 0.64);
		int larguraB = (int) (imagemBI.getWidth() * 0.89);
		int alturaA = (int) (imagemBI.getHeight() * 0.64);
		int alturaB = (int) (imagemBI.getHeight() * 0.89);
		for (int y = alturaA; y < alturaB; y++) {
			for (int x = larguraA; x < larguraB; x++) {
				int corRGB = imagemBI.getRGB(x, y);
				int red = (corRGB >> 16) & 0xFF;
				int green = (corRGB >> 8) & 0xFF;
				int blue = corRGB & 0xFF;
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

}
