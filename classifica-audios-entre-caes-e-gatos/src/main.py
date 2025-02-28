from audio.analise_de_audio import AlgoritmoDeMLP
from audio.analise_de_audio import ExtracaoDeCaracteristica
from sklearn.model_selection import train_test_split
import numpy as np
import os
import pandas as pd

print("Classifica áudios")
print("Cachorro x Gato")
menu = "\nMenu \nop1. Salva as características extraídas em um arquivo \nop2. Exibe características de um arquivo wav \nop3. Usa RNA perceptron multicamadas em arquivos de teste \nop4. Usa RNA perceptron multicamadas em arquivo passado \nop0. Sai do programa"
op=""
extracao=ExtracaoDeCaracteristica()
mlp=AlgoritmoDeMLP()

x_mfcc_1=[] # característica 1, valor 1 do mfcc
x_mfcc_2=[] # característica 2, valor 2 do mfcc
x_mfcc_3=[] # característica 3, valor 3 do mfcc
x_mfcc_4=[] # característica 4, valor 4 do mfcc
x_mfcc_5=[] # característica 5, valor 5 do mfcc

root_dir = "C:/sons_caes_e_gatos/sons/" # diretório das imagens no computador
x_names = os.listdir(root_dir)
x_mfcc = extracao.retorna_valores_mfcc(root_dir, x_names)

x_mfcc_1, x_mfcc_2, x_mfcc_3, x_mfcc_4, x_mfcc_5 = extracao.preenche_listas(x_mfcc)

y_results = [0 if 'cat' in f else 1 for f in x_names]
# 0 = gato
# 1 = cachorro

data = pd.DataFrame(list(zip(x_mfcc_1, x_mfcc_2, x_mfcc_3, x_mfcc_4, x_mfcc_5, y_results))) # cria dataframe com informações para x e y
data.columns = ["vl_mfcc_1", "vl_mfcc_2", "vl_mfcc_3", "vl_mfcc_4", "vl_mfcc_5", "result (0=gato, 1=cachorro)"] # nomeia as colunas

# print(data.info())

x = data[["vl_mfcc_1", "vl_mfcc_2", "vl_mfcc_3", "vl_mfcc_4", "vl_mfcc_5"]]
y = data["result (0=gato, 1=cachorro)"]

treino_x, teste_x, treino_y, teste_y = train_test_split(x, y, train_size=0.75) # separa o que será usado para treino e para teste, sendo 75% para treino e 25% para teste
print()

while(True):
	print(menu)
	op = input("escolha uma opção")

	if (op=="op1"):
		print("Salva as características extraídas em um arquivo")
		extracao.salva_caracteristicas_extraidas(data)

	if (op=="op2"):
		print("Exibe características de um arquivo wav")
		extracao.exibe_valores_mfcc_de_um_arquivo()

	if (op=="op3"):
		print("Usa RNA perceptron multicamadas em arquivos de teste")
		mlp.analisa_arquivos_de_teste(treino_x, treino_y, teste_x, teste_y)

	if (op=="op4"):
		print("Usa RNA perceptron multicamadas em arquivo passado")
		caminho, lista_x=extracao.retorna_valores_mfcc_de_um_arquivo()
		mlp.analisa_um_arquivo(treino_x, treino_y, caminho, lista_x)

	if (op=="op0"):
		break

print("Fim do programa")

