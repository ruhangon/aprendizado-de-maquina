package analisa_imagens;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class VerificaPixels {
	/*
	 * mostra o quanto os pixels dentro de uma margem estão presentes na imagem
	 */
	public static void verificaCores(Scanner scan) {
		BufferedImage imagemBI = null;
		int quantidadeDeCoresProximas = 0; // armazenará a quantidade de cores próximas a margem da cor passada
		int quantidadeDePixels = 0; // armazenará a quantidade de pixels da imagem, altura x largura

		System.out.println("Digite o caminho da imagem a ser analisada");
		System.out.print("caminho: ");
		String caminhoImagem = scan.nextLine();

		try {
			imagemBI = ImageIO.read(new File(caminhoImagem));
		} catch (IOException e) {
			System.out.println("Erro com BufferedImage");
		}

		quantidadeDePixels = imagemBI.getWidth() * imagemBI.getHeight();

		System.out.println("Informações de pixel que será analisado");
		// pede red, green e blue
		System.out.println("Digite o valor do canal red");
		System.out.print("red: ");
		int red = scan.nextInt();
		scan.nextLine();
		System.out.println("Digite o valor do canal green");
		System.out.print("green: ");
		int green = scan.nextInt();
		scan.nextLine();
		System.out.println("Digite o valor do canal blue");
		System.out.print("blue: ");
		int blue = scan.nextInt();
		scan.nextLine();

		// verifica os pixels que serão usados para a análise
		int redMin = encontraValorMinimo(red);
		int redMax = encontraValorMaximo(red);
		int greenMin = encontraValorMinimo(green);
		int greenMax = encontraValorMaximo(green);
		int blueMin = encontraValorMinimo(blue);
		int blueMax = encontraValorMaximo(blue);

		// percorre a imagem e conta os pixels dentro da margem esperada
		for (int y = 0; y < imagemBI.getHeight(); y++) {
			for (int x = 0; x < imagemBI.getWidth(); x++) {
				int corRGB = imagemBI.getRGB(x, y);
				int redAtual = (corRGB >> 16) & 0xFF;
				int greenAtual = (corRGB >> 8) & 0xFF;
				int blueAtual = corRGB & 0xFF;
				if (((redAtual <= redMax) && (redAtual >= redMin))
						&& ((greenAtual <= greenMax) && (greenAtual >= greenMin))
						&& ((blueAtual <= blueMax) && (blueAtual >= blueMin))) {
					quantidadeDeCoresProximas++;
				}
			}
		}

		double percent = quantidadeDeCoresProximas / quantidadeDePixels;
		System.out.println("A quantidade de pixels encontrados, próximos ao valor passado, foi de: "
				+ (quantidadeDeCoresProximas));
		System.out.println("A imagem tem o total de " + (quantidadeDePixels) + " pixels");
		System.out.println("Os pixels contados correspondem a " + (percent) + "% do total da imagem");
	}

	/*
	 * retorna um valor mínimo para o canal, que será usado para descobrir a
	 * quantidade de pixels considerados
	 */
	public static int encontraValorMinimo(int canal) {
		if ((canal - 25) < 0)
			return 0;
		return (canal - 25);
	}

	/*
	 * retorna um valor máximo para o canal, que será usado para descobrir a
	 * quantidade de pixels considerados
	 */
	public static int encontraValorMaximo(int canal) {
		if ((canal + 25) > 255)
			return 255;
		return (canal + 25);
	}


}
