package analisa_imagens;

public class PixelsMaisPresentes {
	/*
	 * int[][][] contaPixels armazenará a contagem de cada pixel, primeiro se refere
	 * ao red, segundo green e terceiro blue. A cada vez que passar por um pixel
	 * novo, já irá comparar para saber se ele pega o lugar de um dos três que estão
	 * salvos como mais presentes
	 */
	private int[][][] contaPixels;
	private int[] red;
	private int[] green;
	private int[] blue;

	public PixelsMaisPresentes() {
		contaPixels = new int[256][256][256];
		red = new int[3];
		green = new int[3];
		blue = new int[3];
	}

	public int[][][] getContaPixels() {
		return contaPixels;
	}

	public void setContaPixels(int[][][] contaPixels) {
		this.contaPixels = contaPixels;
	}

	public int[] getRed() {
		return red;
	}

	public void setRed(int[] red) {
		this.red = red;
	}

	public int[] getGreen() {
		return green;
	}

	public void setGreen(int[] green) {
		this.green = green;
	}

	public int[] getBlue() {
		return blue;
	}

	public void setBlue(int[] blue) {
		this.blue = blue;
	}

}
