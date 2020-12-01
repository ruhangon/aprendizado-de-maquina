from agrupamento.kmeans import AlgoritmoDeKMeans
from sklearn.preprocessing import LabelEncoder
from sklearn.preprocessing import MinMaxScaler
import numpy as np
import pandas as pd

print("Agrupamento de vendas de jogos com k-means")
print("Receba indicações de games para jogar com base na plataforma e no gênero")
print()

file = "dataset/dataset.csv"
dataset = pd.read_csv(file)
pd.set_option('display.max_columns', 11)

# altera nomes de colunas
dataset.columns = ["ranking", "nome", "plataforma", "ano", "genero", "editora", "vendas_america_norte", "vendas_eu", "vendas_japao", "vendas_outros_paises", "vendas_totais"]

# info_dataset é um dataset para ser usado em opções do menu
info_dataset = dataset[["nome", "plataforma", "ano", "genero", "editora"]]

# mostra alguns itens do dataset
# print(dataset.head())

# confere valores vazios no dataset
# print(dataset.isna().sum())

# para usar com o k-means pega dataset de vendas com plataforma e gênero. Nome servirá para ser o índice
vendas_dataset = dataset[["nome", "plataforma", "genero"]]

# print(vendas_dataset.head())

# print(vendas_dataset.isna().sum())

# por existirem poucas informações vazias em plataforma e gênero, 26 para cada, retira as linhas com essas informações
vendas_dataset = vendas_dataset.dropna()

# salva a coluna de nomes
nomes = vendas_dataset["nome"]
nomes = nomes.values

# antes de continuar, seta índice como o nome, não existem linhas sem informação a respeito disso
vendas_dataset.set_index(keys="nome", inplace=True)

# faz o mesmo para info_dataset
info_dataset.set_index(keys="nome", inplace=True)

# transforma as colunas com valores não numéricos em valores numéricos
# primeiro com plataforma
vendas_dataset["plataforma"] = vendas_dataset["plataforma"].astype(str)
label_encoder_p = LabelEncoder()
label_encoder_p.fit(vendas_dataset["plataforma"])
vendas_dataset["plataforma"] = label_encoder_p.transform(vendas_dataset["plataforma"])

# agora com genero
vendas_dataset["genero"] = vendas_dataset["genero"].astype(str)
label_encoder_g = LabelEncoder()
label_encoder_g.fit(vendas_dataset["genero"])
vendas_dataset["genero"] = label_encoder_g.transform(vendas_dataset["genero"])

# print(vendas_dataset.info())

# print(vendas_dataset.isna().sum())

# scaler
scaler = MinMaxScaler()
vendas_dataset_escalado = scaler.fit_transform(vendas_dataset)

vendas_dataset_escalado = pd.DataFrame(vendas_dataset_escalado)
# adiciona novamente a coluna de nomes e seta de novo ela como índice
vendas_dataset_escalado["nome"] = nomes
vendas_dataset_escalado.set_index(keys="nome", inplace=True)

# inicia parte do k-means
k_means = AlgoritmoDeKMeans(vendas_dataset_escalado)
vendas_dataset_result = k_means.insere_coluna_de_grupo(vendas_dataset_escalado)

# menu
menu = "Menu \nop1. Encontra o erro em 30 possibilidades diferentes, de 1 a 30 grupos \nop2. Verifica os valores dos centros encontrados pelo algoritmo \nop3. Mostra indicações de um grupo a sua escolha (1 a 14) \nop0. Finaliza programa"
resposta = ""

while (True):
    print()
    print(menu)
    resposta = input("escolha uma alternativa: ")

    if (resposta=="op1"):
        print("Encontra o erro em 30 possibilidades diferentes, de 1 a 30 grupos")
        k_means.mostra_erro_com_30_grupos_diferentes(vendas_dataset_escalado)

    if (resposta=="op2"):
        print("Verifica os valores dos centros encontrados pelo algoritmo")
        print(k_means.retorna_centros())

    if (resposta=="op3"):
        print("Mostra indicações de um grupo a sua escolha (1 a 14)")
        n_grupo=-1
        while(True):
            str_n_grupo = input("escolha um grupo de 1 a 14")
            n_grupo = int(str_n_grupo)
            n_grupo = n_grupo-1
            if (n_grupo>=0 and n_grupo<=13):
                break
        print("Grupo escolhido: %d" % n_grupo)
        k_means.mostra_jogos_de_um_grupo(n=n_grupo, dataset_k_means=vendas_dataset_result, dataset_completo=info_dataset)

    if (resposta=="op0"):
        break

print("\n\nPrograma finalizado")

