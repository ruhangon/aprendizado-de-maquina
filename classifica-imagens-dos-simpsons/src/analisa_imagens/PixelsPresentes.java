package analisa_imagens;

public class PixelsPresentes {
	/*
	 * a matriz [][][] contaPixelsSuperior irá salvar os pixels encontrados na parte
	 * superior da imagem
	 */
	protected int[][][] contaPixelsSuperior;
	/*
	 * a matriz [][][] contaPixelsCentral irá salvar os pixels encontrados na parte
	 * central da imagem
	 */
	protected int[][][] contaPixelsCentral;
	/*
	 * a matriz [][][] contaPixelsInferior irá salvar os pixels encontrados na parte
	 * inferior da imagem
	 */
	protected int[][][] contaPixelsInferior;
	/*
	 * pixel presente 1 procura o pixel mais presente na imagem analisada levando em
	 * conta a parte superior da imagem
	 */
	protected int[] pixelPresente1;
	/*
	 * pixel presente 2 procura o pixel mais presente na imagem analisada levando em
	 * conta a parte central da imagem
	 */
	protected int[] pixelPresente2;
	/*
	 * pixel presente 3 procura o pixel mais presente na imagem analisada levando em
	 * conta a parte inferior da imagem
	 */
	protected int[] pixelPresente3;

	public PixelsPresentes() {
		contaPixelsSuperior = new int[256][256][256];
		contaPixelsCentral = new int[256][256][256];
		contaPixelsInferior = new int[256][256][256];
		pixelPresente1 = new int[3];
		pixelPresente2 = new int[3];
		pixelPresente3 = new int[3];
	}

	public boolean eMaiorQuePixelSuperiorSalvo(int redAtual, int greenAtual, int blueAtual, int redSalvo,
			int greenSalvo, int blueSalvo) {
		if ((this.contaPixelsSuperior[redAtual][greenAtual][blueAtual]) > (this.contaPixelsSuperior[redSalvo][greenSalvo][blueSalvo]))
			return true;
		return false;
	}

	public boolean eMaiorQuePixelCentralSalvo(int redAtual, int greenAtual, int blueAtual, int redSalvo, int greenSalvo,
			int blueSalvo) {
		if ((this.contaPixelsCentral[redAtual][greenAtual][blueAtual]) > (this.contaPixelsCentral[redSalvo][greenSalvo][blueSalvo]))
			return true;
		return false;
	}

	public boolean eMaiorQuePixelInferiorSalvo(int redAtual, int greenAtual, int blueAtual, int redSalvo,
			int greenSalvo, int blueSalvo) {
		if ((this.contaPixelsInferior[redAtual][greenAtual][blueAtual]) > (this.contaPixelsInferior[redSalvo][greenSalvo][blueSalvo]))
			return true;
		return false;
	}

}
