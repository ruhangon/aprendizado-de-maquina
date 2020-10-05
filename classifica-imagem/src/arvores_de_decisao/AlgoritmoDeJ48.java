package arvores_de_decisao;

import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class AlgoritmoDeJ48 {
	public static double[] j48(double[] caracteristicas) {
		double[] retorno = { 0, 0 };
		try {
			// *******carregar arquivo de caracter�sticas
			DataSource ds = new DataSource("caracteristicas.arff");
			Instances instancias = ds.getDataSet();
			instancias.setClassIndex(instancias.numAttributes() - 1);

			// cria a �rvore
			J48 arvore = new J48();
			arvore.buildClassifier(instancias);
			// arvore.setUnpruned(true);//sem podas

			// Novo registro
			Instance novo = new DenseInstance(instancias.numAttributes());
			novo.setDataset(instancias);
			novo.setValue(0, caracteristicas[0]);
			novo.setValue(1, caracteristicas[1]);
			novo.setValue(2, caracteristicas[2]);
			novo.setValue(3, caracteristicas[3]);
			novo.setValue(4, caracteristicas[4]);
			novo.setValue(5, caracteristicas[5]);

			retorno = arvore.distributionForInstance(novo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/*
	 * mostra matriz de confus�o para J48
	 */
	public static void mostraMatrizDeConfusao() {
		try {
			DataSource ds = new DataSource("caracteristicas.arff");
			Instances inst = ds.getDataSet();
			inst.setClassIndex(inst.numAttributes() - 1);

			Classifier cls = new J48();

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
