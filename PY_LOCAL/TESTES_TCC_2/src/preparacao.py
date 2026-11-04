import pandas as pd

nomes_colunas = [
    'cod_municipio', 
    'nome_municipio',
    'perc_doc_sobrecarga',          
    'taxa_informal', 
    'taxa_climatizacao', 
    'taxa_internet',
    'taxa_lab',
    'media_alunos_por_sala',
    'renda_pc',
    'evasao',
    'tx_repet' 
]

#pasta_dados = Path('C:\\Users\\patri\\PY_LOCAL\\TESTES_TCC_2\\data')
#caminho = pasta_dados / 'TESTE_ML_COM_TAXAS_INFRA.csv'

def transformar_dados(df, colunas):
    df = df.copy()
    for col in colunas:
        df[col] = df[col].astype(str).str.replace(',', '.').astype(float)
        df[col] = df[col].round(2)
    
    return df

def carregar_dados(caminho):
    df_base = pd.read_csv(caminho, sep=';', header=None, names=nomes_colunas, encoding='utf-8-sig')

    colunas = ['taxa_informal','tx_repet','taxa_climatizacao', 'taxa_lab', 'media_alunos_por_sala', 'evasao', 'renda_pc','taxa_internet']

    return transformar_dados(df_base, colunas)