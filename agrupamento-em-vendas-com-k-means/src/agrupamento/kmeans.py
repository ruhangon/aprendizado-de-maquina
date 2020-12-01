from sklearn.cluster import KMeans
import numpy as np
import pandas as pd

class AlgoritmoDeKMeans:
    def __init__(self, dataset):
        self.model = KMeans(n_clusters=14) # escolhido 14 devido a análise dos valores de inertia em 30 grupos diferentes
        self.model.fit(dataset)

    # insere coluna grupo no dataset
    def insere_coluna_de_grupo(self, dataset):
        grupo = pd.Series(self.model.labels_)
        dataset["grupo"] = grupo.values
        return dataset

    # retorna os centros de cada grupo
    def retorna_centros(self):
        return self.model.cluster_centers_

    # mostra inertia de 30 grupos diferentes, para encontrar o número de clusters a ser usado no programa
    def mostra_erro_com_30_grupos_diferentes(self, dataset):
        for n in range(1, 31):
            new_model = KMeans(n_clusters=n)
            new_model.fit(dataset)
            print("inertia com %d grupos" % n)
            print(new_model.inertia_)

    # mostra indicações de um grupo passado pelo usuário
    def mostra_jogos_de_um_grupo(self, n, dataset_k_means, dataset_completo):
        str_n = str(n)
        qual_grupo = "grupo=="
        qual_grupo = qual_grupo+str_n
        info_grupo = dataset_k_means.query(qual_grupo)
        print("Se você gostou do seguinte jogo:")
        jogo_inicial = info_grupo.index[0]
        print(dataset_completo.loc[jogo_inicial])
        print()
        print("Você pode gostar dos seguintes jogos:")
        for num in range(1, 6):
            indicacao = info_grupo.index[num]
            print(dataset_completo.loc[indicacao])

