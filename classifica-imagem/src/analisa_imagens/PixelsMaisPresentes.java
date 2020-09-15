package analisa_imagens;

public class PixelsMaisPresentes {
	/*
	 * int[][][] contaPixels armazenará a contagem de cada pixel, primeiro se refere
	 * ao red, segundo green e terceiro blue. A cada vez que passar por um pixel
	 * novo, já irá comparar para saber se ele pega o lugar de um dos três que estão
	 * salvos como pixels presentes
	 */
	private int[][][] contaPixels;
	private int[] pixelPresente1; // se refere ao pixel mais presente nas imagens analisadas
	private int[] pixelPresente2; // se refere ao décimo pixel mais presente nas imagens analisadas
	private int[] pixelPresente3; // se refere ao vigésimo pixel mais presente nas imagens analisadas

	public PixelsMaisPresentes() {
		contaPixels = new int[256][256][256];
		pixelPresente1 = new int[3];
		pixelPresente2 = new int[3];
		pixelPresente3 = new int[3];
	}

	public int[][][] getContaPixels() {
		return contaPixels;
	}

	public void setContaPixels(int[][][] contaPixels) {
		this.contaPixels = contaPixels;
	}

	public int[] getPixelPresente1() {
		return pixelPresente1;
	}

	public void setPixelPresente1(int[] pixelPresente1) {
		this.pixelPresente1 = pixelPresente1;
	}

	public int[] getPixelPresente2() {
		return pixelPresente2;
	}

	public void setPixelPresente2(int[] pixelPresente2) {
		this.pixelPresente2 = pixelPresente2;
	}

	public int[] getPixelPresente3() {
		return pixelPresente3;
	}

	public void setPixelPresente3(int[] pixelPresente3) {
		this.pixelPresente3 = pixelPresente3;
	}

}
