package extrai_caracteristicas;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ExtraiCaracteristicas {
	public static void extrair() {
		// Cabe�alho do arquivo Weka
		String exportacao = "@relation caracteristicas\n\n";
		exportacao += "@attribute homer_caracteristica_1 real\n";
		exportacao += "@attribute homer_caracteristica_2 real\n";
		exportacao += "@attribute homer_caracteristica_3 real\n";
		exportacao += "@attribute marge_caracteristica_1 real\n";
		exportacao += "@attribute marge_caracteristica_2 real\n";
		exportacao += "@attribute marge_caracteristica_3 real\n";
		exportacao += "@attribute classe {Homer, Marge}\n\n";
		exportacao += "@data\n";

		// Diret�rio onde est�o armazenadas as imagens
		File diretorio = new File("imagens");
		File[] arquivos = diretorio.listFiles();

		// Defini��o do vetor de caracter�sticas
		double[][] caracteristicas = new double[1000][7];

		// Percorre todas as imagens do diret�rio
		int cont = -1;
		for (File imagem : arquivos) {
			cont++;
			caracteristicas[cont] = extraiCaracteristicas(imagem);

			String classe = caracteristicas[cont][6] == 0 ? "Homer" : "Marge";

			System.out.println("Homer caracter�stica 1: " + caracteristicas[cont][0] + " - Homer caracter�stica 2: "
					+ caracteristicas[cont][1] + " - Homer caracter�stica 3: " + caracteristicas[cont][2]
					+ " - Marge caracter�stica 1: " + caracteristicas[cont][3] + " - Marge caracter�stica 2: "
					+ caracteristicas[cont][4] + " - Marge caracter�stica 3: " + caracteristicas[cont][5]
					+ " - Classe: " + classe);

			exportacao += caracteristicas[cont][0] + "," + caracteristicas[cont][1] + "," + caracteristicas[cont][2]
					+ "," + caracteristicas[cont][3] + "," + caracteristicas[cont][4] + "," + caracteristicas[cont][5]
					+ "," + classe + "\n";
		}

		// Grava o arquivo ARFF no disco
		try {
			File arquivo = new File("caracteristicas.arff");
			FileOutputStream f = new FileOutputStream(arquivo);
			f.write(exportacao.getBytes());
			f.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static double[] extraiCaracteristicas(File f) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		double[] caracteristicas = new double[7];
		double homerCaracteristica1 = 0;
		double homerCaracteristica2 = 0;
		double homerCaracteristica3 = 0;
		double margeCaracteristica1 = 0;
		double margeCaracteristica2 = 0;
		double margeCaracteristica3 = 0;

		// Image img = new Image(f.toURI().toString());
		// PixelReader pr = img.getPixelReader();

		BufferedImage img = null;
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println("Erro com BufferedImage");
		}

		Mat imagemOriginal = Imgcodecs.imread(f.getPath());
		Mat imagemProcessada = imagemOriginal.clone();

		int w = img.getWidth();
		int h = img.getHeight();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// Color cor = pr.getColor(j, i);
				Color cor = new Color(img.getRGB(j, i));
				double r = (double) cor.getRed();
				double g = (double) cor.getGreen();
				double b = (double) cor.getBlue();

				if (isHomerCaracteristica1(r, g, b)) {
					homerCaracteristica1++;
					imagemProcessada.put(i, j, new double[] { 0, 255, 128 });
				}
				if (isHomerCaracteristica2(r, g, b)) {
					homerCaracteristica2++;
					imagemProcessada.put(i, j, new double[] { 0, 255, 128 });
				}
				if (isHomerCaracteristica3(r, g, b)) {
					homerCaracteristica3++;
					imagemProcessada.put(i, j, new double[] { 0, 255, 128 });
				}
				if (isMargeCaracteristica1(r, g, b)) {
					margeCaracteristica1++;
					imagemProcessada.put(i, j, new double[] { 0, 255, 255 });
				}
				if (isMargeCaracteristica2(r, g, b)) {
					margeCaracteristica2++;
					imagemProcessada.put(i, j, new double[] { 0, 255, 255 });
				}
				if (isMargeCaracteristica3(r, g, b)) {
					margeCaracteristica3++;
					imagemProcessada.put(i, j, new double[] { 0, 255, 255 });
				}

			}
		}

		// Normaliza as caracter�sticas pelo n�mero de pixels totais da imagem para %
		homerCaracteristica1 = (homerCaracteristica1 / (w * h)) * 100;
		homerCaracteristica2 = (homerCaracteristica2 / (w * h)) * 100;
		homerCaracteristica3 = (homerCaracteristica3 / (w * h)) * 100;
		margeCaracteristica1 = (margeCaracteristica1 / (w * h)) * 100;
		margeCaracteristica2 = (margeCaracteristica2 / (w * h)) * 100;
		margeCaracteristica3 = (margeCaracteristica3 / (w * h)) * 100;

		caracteristicas[0] = homerCaracteristica1;
		caracteristicas[1] = homerCaracteristica2;
		caracteristicas[2] = homerCaracteristica3;
		caracteristicas[3] = margeCaracteristica1;
		caracteristicas[4] = margeCaracteristica2;
		caracteristicas[5] = margeCaracteristica3;

		// APRENDIZADO SUPERVISIONADO - J� SABE QUAL A CLASSE NAS IMAGENS DE TREINAMENTO
		caracteristicas[6] = f.getName().charAt(0) == 'h' ? 0 : 1;

		// HighGui.imshow("Imagem original", imagemOriginal);
		// HighGui.imshow("Imagem processada", imagemProcessada);

		// HighGui.waitKey(0);

		return caracteristicas;
	}

	/*
	 * valores dos pixels foram escolhidos de acordo com os pixels presentes,
	 * descobertos pelo programa
	 */
	public static boolean isHomerCaracteristica1(double r, double g, double b) {
		if (b >= 0 && b <= 20 && g >= 116 && g <= 156 && r >= 110 && r <= 150) {
			return true;
		}
		return false;
	}

	/*
	 * valores dos pixels foram escolhidos de acordo com os pixels presentes,
	 * descobertos pelo programa
	 */
	public static boolean isHomerCaracteristica2(double r, double g, double b) {
		if (b >= 81 && b <= 121 && g >= 131 && g <= 171 && r >= 161 && r <= 201) {
			return true;
		}
		return false;
	}

	/*
	 * valores dos pixels foram escolhidos de acordo com os pixels presentes,
	 * descobertos pelo programa
	 */
	public static boolean isHomerCaracteristica3(double r, double g, double b) {
		if (b >= 110 && b <= 150 && g >= 85 && g <= 125 && r >= 171 && r <= 211) {
			return true;
		}
		return false;
	}

	/*
	 * valores dos pixels foram escolhidos de acordo com os pixels presentes,
	 * descobertos pelo programa
	 */
	public static boolean isMargeCaracteristica1(double r, double g, double b) {
		if (b >= 162 && b <= 202 && g >= 112 && g <= 152 && r >= 46 && r <= 86) {
			return true;
		}
		return false;
	}

	/*
	 * valores dos pixels foram escolhidos de acordo com os pixels presentes,
	 * descobertos pelo programa. Segundo pixel presente da Marge foi igual ao
	 * primeiro, ent�o foi tirado outro com bastante apari��es da lista, encontrado
	 * anteriormente
	 */
	public static boolean isMargeCaracteristica2(double r, double g, double b) {
		if (b >= 171 && b <= 211 && g >= 191 && g <= 231 && r >= 168 && r <= 208) {
			return true;
		}
		return false;
	}

	/*
	 * valores dos pixels foram escolhidos de acordo com os pixels presentes,
	 * descobertos pelo programa
	 */
	public static boolean isMargeCaracteristica3(double r, double g, double b) {
		if (b >= 0 && b <= 29 && g >= 11 && g <= 51 && r >= 93 && r <= 133) {
			return true;
		}
		return false;
	}

}
