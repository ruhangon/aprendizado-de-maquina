package analisa_imagens;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AnalisaImagens {
	/*
	 * percorre imagens para encontrar o pixel mais presente na parte superior, o
	 * pixel mais presente na parte central e o pixel mais presente na parte
	 * inferior
	 */
	public static void descobrePixelsMaisPresentes(boolean ePersonagem1) {
		PixelsPresentes pixelsMP1 = new PixelsPresentes(); // armazenará os mais presentes da imagem 1
		PixelsPresentes pixelsMP2 = new PixelsPresentes(); // armazenará os mais presentes da imagem 2

		File pastaPersonagem1 = new File("imagens/Homer");
		File pastaPersonagem2 = new File("imagens/Marge");
		String[] imagensPersonagem1 = pastaPersonagem1.list();
		String[] imagensPersonagem2 = pastaPersonagem2.list();
		String caminhoImagemAtual = ""; // mostrará a imagem atual com o seu caminho, para criar o buffered image
		BufferedImage imagemBI = null;

		int i = 0;
		// cria a buffered image do personagem atual para analisar
		for (int c = 0; c < 10; c++) {
			if (ePersonagem1) {
				caminhoImagemAtual = "";
				caminhoImagemAtual = caminhoImagemAtual.concat("imagens/Homer/");
				caminhoImagemAtual = caminhoImagemAtual.concat(imagensPersonagem1[i]);
				i++;
			} else {
				caminhoImagemAtual = "";
				caminhoImagemAtual = caminhoImagemAtual.concat("imagens/Marge/");
				caminhoImagemAtual = caminhoImagemAtual.concat(imagensPersonagem2[i]);
				i++;
			}

			try {
				imagemBI = ImageIO.read(new File(caminhoImagemAtual));
			} catch (IOException e) {
				System.out.println("Erro com BufferedImage");
			}

			int tamanhoTotal = imagemBI.getWidth() * imagemBI.getHeight();
			/*
			 * se a imagem tem um tamanho que se encaixe na condição, percorre a imagem para
			 * procurar os pixels mais presentes
			 */
			if ((tamanhoTotal > 300000) && (tamanhoTotal < 1000000)) {
				if (ePersonagem1) {
					System.out.println((c + 1) + "ª imagem analisada no momento: " + imagensPersonagem1[i]);
					System.out.println("Tamanho: " + (imagemBI.getWidth()) + "x" + (imagemBI.getHeight()));
					// primeiro coleta o pixel mais presente na parte superior
					pixelsMP1 = retornaMaisPresenteNaParteSuperior(pixelsMP1, imagemBI);
					System.out.println("Pixel atual mais presente na parte superior: " + pixelsMP1.pixelPresente1[0]
							+ " - " + pixelsMP1.pixelPresente1[1] + " - " + pixelsMP1.pixelPresente1[2]);
					System.out.println("Quantidade desse pixel: "
							+ pixelsMP1.contaPixelsSuperior[pixelsMP1.pixelPresente1[0]][pixelsMP1.pixelPresente1[1]][pixelsMP1.pixelPresente1[2]]);
					// agora coleta o pixel mais presente na parte central
					pixelsMP1 = retornaMaisPresenteNaParteCentral(pixelsMP1, imagemBI);
					System.out.println("Pixel atual mais presente na parte central: " + pixelsMP1.pixelPresente2[0]
							+ " - " + pixelsMP1.pixelPresente2[1] + " - " + pixelsMP1.pixelPresente2[2]);
					System.out.println("Quantidade desse pixel: "
							+ pixelsMP1.contaPixelsCentral[pixelsMP1.pixelPresente2[0]][pixelsMP1.pixelPresente2[1]][pixelsMP1.pixelPresente2[2]]);
					// agora coleta o pixel mais presente na parte inferior
					pixelsMP1 = retornaMaisPresenteNaParteInferior(pixelsMP1, imagemBI);
					System.out.println("Pixel atual mais presente na parte inferior: " + pixelsMP1.pixelPresente3[0]
							+ " - " + pixelsMP1.pixelPresente3[1] + " - " + pixelsMP1.pixelPresente3[2]);
					System.out.println("Quantidade desse pixel: "
							+ pixelsMP1.contaPixelsInferior[pixelsMP1.pixelPresente3[0]][pixelsMP1.pixelPresente3[1]][pixelsMP1.pixelPresente3[2]]);
				} else if (ePersonagem1 == false) {
					System.out.println((c + 1) + "ª imagem analisada no momento: " + imagensPersonagem2[i]);
					System.out.println("Tamanho: " + (imagemBI.getWidth()) + "x" + (imagemBI.getHeight()));
					// primeiro coleta o pixel mais presente na parte superior
					pixelsMP2 = retornaMaisPresenteNaParteSuperior(pixelsMP2, imagemBI);
					System.out.println("Pixel atual mais presente na parte superior: " + pixelsMP2.pixelPresente1[0]
							+ " - " + pixelsMP2.pixelPresente1[1] + " - " + pixelsMP2.pixelPresente1[2]);
					System.out.println("Quantidade desse pixel: "
							+ pixelsMP2.contaPixelsSuperior[pixelsMP2.pixelPresente1[0]][pixelsMP2.pixelPresente1[1]][pixelsMP2.pixelPresente1[2]]);
					// agora coleta o pixel mais presente na parte central
					pixelsMP2 = retornaMaisPresenteNaParteCentral(pixelsMP2, imagemBI);
					System.out.println("Pixel atual mais presente na parte central: " + pixelsMP2.pixelPresente2[0]
							+ " - " + pixelsMP2.pixelPresente2[1] + " - " + pixelsMP2.pixelPresente2[2]);
					System.out.println("Quantidade desse pixel: "
							+ pixelsMP2.contaPixelsCentral[pixelsMP2.pixelPresente2[0]][pixelsMP2.pixelPresente2[1]][pixelsMP2.pixelPresente2[2]]);
					// agora coleta o pixel mais presente na parte inferior
					pixelsMP2 = retornaMaisPresenteNaParteInferior(pixelsMP2, imagemBI);
					System.out.println("Pixel atual mais presente na parte inferior: " + pixelsMP2.pixelPresente3[0]
							+ " - " + pixelsMP2.pixelPresente3[1] + " - " + pixelsMP2.pixelPresente3[2]);
					System.out.println("Quantidade desse pixel: "
							+ pixelsMP2.contaPixelsInferior[pixelsMP2.pixelPresente3[0]][pixelsMP2.pixelPresente3[1]][pixelsMP2.pixelPresente3[2]]);
				}
			} else {
				c--;
			}
		}
	}

	/*
	 * descobre o pixel mais presente na parte mais a cima da imagem analisada e
	 * retorna ele
	 */
	public static PixelsPresentes retornaMaisPresenteNaParteSuperior(PixelsPresentes pixelsMP, BufferedImage imagemBI) {
		/*
		 * percorre a imagem ignorando 15% em relação a largura nas bordas e 10% em
		 * relação a altura nas bordas. O foco é procurar pixels mais ao centro,
		 * primeiro na parte superior analisada
		 */
		int larguraA = (int) (imagemBI.getWidth() * 0.16);
		int larguraB = (int) (imagemBI.getWidth() * 0.38);
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
		 * percorre a imagem ignorando 15% em relação a largura nas bordas e 10% em
		 * relação a altura nas bordas. O foco é procurar pixels mais ao centro, agora
		 * na parte central analisada
		 */
		int larguraA = (int) (imagemBI.getWidth() * 0.39);
		int larguraB = (int) (imagemBI.getWidth() * 0.61);
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
		 * percorre a imagem ignorando 15% em relação a largura nas bordas e 10% em
		 * relação a altura nas bordas. O foco é procurar pixels mais ao centro, agora
		 * na parte inferior analisada
		 */
		int larguraA = (int) (imagemBI.getWidth() * 0.62);
		int larguraB = (int) (imagemBI.getWidth() * 0.84);
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

}
