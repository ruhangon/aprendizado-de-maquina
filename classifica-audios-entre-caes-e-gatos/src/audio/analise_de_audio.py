from sklearn.metrics import accuracy_score
from sklearn.neural_network import MLPClassifier
import librosa
import numpy as np

class ExtracaoDeCaracteristica:
    def retorna_valores_de_mediana(self, lista):
        lista_mfcc=[]
        for i in range(5):
            lista_i=lista[i]
            tam = len(lista_i)
            if (tam%2==0):
                mediana1 = lista_i[tam//2] 
                mediana2 = lista_i[tam//2 - 1]
                mediana = (mediana1 + mediana2)/2
                lista_mfcc.append(mediana)
            else:
                mediana = lista_i[tam//2] 
                lista_mfcc.append(mediana)
        return lista_mfcc

    def retorna_valores_mfcc(self, root_dir, x_names):
        lista_ret=[]
        for arq in range(len(x_names)):
            nome_de_arquivo_wav=root_dir+x_names[arq]
            dados_do_arquivo, rate_do_arquivo = librosa.load(nome_de_arquivo_wav)
            mfcc_2d = librosa.feature.mfcc(y=dados_do_arquivo, sr=rate_do_arquivo) # pega mfcc em 2d
            lista_mfcc = self.retorna_valores_de_mediana(mfcc_2d) # consegue lista de mediana de valores mfcc
            lista_ret.append(lista_mfcc)
        return lista_ret

    def exibe_valores_mfcc_de_um_arquivo(self):
        caminho=input("Digite o caminho de um arquivo a ser analisado")
        dados_do_arquivo, rate_do_arquivo = librosa.load(caminho)
        mfcc_2d = librosa.feature.mfcc(y=dados_do_arquivo, sr=rate_do_arquivo) # pega mfcc em 2d
        lista_mfcc = self.retorna_valores_de_mediana(mfcc_2d) # consegue lista de mediana de valores mfcc
        print("Informações de mediana do mfcc")
        for i in lista_mfcc:
            print(i)

    def preenche_listas(self, lista_mfcc):
        lista1=[]
        lista2=[]
        lista3=[]
        lista4=[]
        lista5=[]
        for i in range(len(lista_mfcc)):
            lista1.append(lista_mfcc[i][0])
            lista2.append(lista_mfcc[i][1])
            lista3.append(lista_mfcc[i][2])
            lista4.append(lista_mfcc[i][3])
            lista5.append(lista_mfcc[i][4])
        return lista1, lista2, lista3, lista4, lista5

class AlgoritmoDeMLP:
    def analisa_arquivos_de_teste(self, treino_x, treino_y, teste_x, teste_y):
        SEED = 10
        np.random.seed(SEED)
        modelo = MLPClassifier(learning_rate_init=0.003, max_iter=650)
        modelo.fit(treino_x, treino_y)
        previsoes = modelo.predict(teste_x)
        acuracia = accuracy_score(teste_y, previsoes) * 100
        print("A acurácia usando rede neural perceptron multicamadas foi %.2f%%" % acuracia)

