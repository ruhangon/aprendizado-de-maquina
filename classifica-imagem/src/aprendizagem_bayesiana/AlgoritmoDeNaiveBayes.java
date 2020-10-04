package aprendizagem_bayesiana;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import extrai_caracteristicas.ExtraiCaracteristicas;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AlgoritmoDeNaiveBayes {
	public static double[] naiveBayes(double[] caracteristicas) {
		double[] retorno = { 0, 0 };
		try {
			// *******carregar arquivo de características
			DataSource ds = new DataSource("caracteristicas.arff");
			Instances instancias = ds.getDataSet();
			// instancias.setClassIndex(6);
			instancias.setClassIndex(instancias.numAttributes() - 1);

			// Classifica com base nas características da imagem selecionada
			NaiveBayes nb = new NaiveBayes();
			nb.buildClassifier(instancias);// aprendizagem (tabela de probabilidades)

			Instance novo = new DenseInstance(instancias.numAttributes());
			novo.setDataset(instancias);
			novo.setValue(0, caracteristicas[0]);
			novo.setValue(1, caracteristicas[1]);
			novo.setValue(2, caracteristicas[2]);
			novo.setValue(3, caracteristicas[3]);
			novo.setValue(4, caracteristicas[4]);
			novo.setValue(5, caracteristicas[5]);

			retorno = nb.distributionForInstance(novo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * aplica o método de naive Bayes nas imagens de teste e retorna probabilidade
	 * de ser um ou outro
	 */
	public static void aplicaNaiveBayesEmTeste() {
		File pastaTeste = new File("imagens de teste");
		File[] arquivos = pastaTeste.listFiles();
		int c = 1;
		int contAcertosHomer = 0;
		int contAcertosMarge = 0;
		System.out.println("Primeiras 100 imagens são Homer, últimas 100 são Marge");
		System.out.println();
		for (File f : arquivos) {
			System.out.println(c + "ª imagem");
			double[] caracteristicasImagem = ExtraiCaracteristicas.extraiCaracteristicas(f);
			double[] qualPersonagem = naiveBayes(caracteristicasImagem);
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
			decimalFormat.setRoundingMode(RoundingMode.DOWN);
			System.out.println(
					"A probabilidade de ser Homer é: " + (decimalFormat.format(100 * qualPersonagem[0])) + "%");
			System.out.println(
					"A probabilidade de ser Marge é: " + (decimalFormat.format(100 * qualPersonagem[1])) + "%");

			if ((c <= 200) && (qualPersonagem[0] > qualPersonagem[1]))
				contAcertosHomer++;
			if ((c > 200) && (qualPersonagem[1] > qualPersonagem[0]))
				contAcertosMarge++;

			c++;
		}

		// mostra quantos acertos teve para cada personagem
		System.out.println("Homer teve " + contAcertosHomer + " acertos");
		System.out.println("Marge teve " + contAcertosMarge + " acertos");
	}

	/*
	 * mostra matriz de confusão dos dados
	 */
	public static void mostraMatrizDeConfusao() {
		try {
			DataSource ds = new DataSource("caracteristicas.arff");
			Instances inst = ds.getDataSet();
			inst.setClassIndex(inst.numAttributes() - 1);

			Classifier cls = new NaiveBayes();

			Evaluation evaluation = new Evaluation(inst);
			Random rand = new Random(1);
			int folds = 10;
			evaluation.crossValidateModel(cls, inst, folds, rand);

			System.out.println(evaluation.toMatrixString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
