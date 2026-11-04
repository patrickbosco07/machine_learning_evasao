import pandas as pd
from pathlib import Path
from imblearn.over_sampling import SMOTE
from imblearn.pipeline import Pipeline as ImbPipeline
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.metrics import accuracy_score, brier_score_loss, classification_report, fbeta_score, make_scorer, precision_score, recall_score, roc_auc_score
from sklearn.model_selection import GridSearchCV, StratifiedKFold, train_test_split

nomes_colunas = ["cod_municipio", "nome_municipio", "perc_doc_sobrecarga", "taxa_informal", "taxa_climatizacao", "taxa_internet", "taxa_lab", "media_alunos_por_sala", "renda_pc", "evasao", "tx_repet"]
pasta_dados = Path(r"C:\Users\patri\PY_LOCAL\TESTES_TCC_2\data")
df = pd.read_csv(pasta_dados / "TESTE_ML_COM_TAXAS_INFRA.csv", sep=";", header=None, names=nomes_colunas)

for coluna in ["taxa_informal", "tx_repet", "evasao", "renda_pc", "taxa_internet"]:
    df[coluna] = df[coluna].astype(str).str.replace(",", ".").astype(float).round(2)
df["enquadro"] = pd.NA
df.loc[df["evasao"].between(0, 5.99), "enquadro"] = 0
df.loc[df["evasao"] >= 6, "enquadro"] = 1
df["enquadro"] = df["enquadro"].astype("Int64")

colunas_ml = ["perc_doc_sobrecarga", "taxa_informal", "tx_repet", "renda_pc", "taxa_internet"]
X, y = df[colunas_ml], df["enquadro"]
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.20, random_state=42, stratify=y)

# GradientBoostingClassifier já treina probabilidades e possui predict_proba().
pipeline = ImbPipeline([
    ("smote", SMOTE(random_state=42)),
    ("clf", GradientBoostingClassifier(random_state=42)),
])
param_grid = {
    "smote__k_neighbors": [3, 5], "clf__n_estimators": [100, 200],
    "clf__learning_rate": [0.05, 0.1], "clf__max_depth": [2, 3],
    "clf__min_samples_leaf": [5, 10], "clf__subsample": [0.8, 1.0],
}
grid = GridSearchCV(
    pipeline, param_grid=param_grid,
    cv=StratifiedKFold(n_splits=5, shuffle=True, random_state=42),
    scoring=make_scorer(fbeta_score, beta=2, zero_division=0), n_jobs=-1, verbose=1,
)
grid.fit(X_train, y_train)

melhor_modelo = grid.best_estimator_
melhor_modelo.fit(X_train, y_train)

# Probabilidade da classe positiva (enquadro == 1).
y_proba = melhor_modelo.predict_proba(X_test)[:, 1]

# O limiar pode ser ajustado sem treinar novamente.
limiar = 0.50
y_pred = (y_proba >= limiar).astype(int)

metricas_finais = pd.DataFrame({
    "Métrica": ["F2-Score", "Acurácia", "Precisão", "Recall", "ROC-AUC", "Brier score"],
    "Valor": [
        fbeta_score(y_test, y_pred, beta=2, zero_division=0),
        accuracy_score(y_test, y_pred), precision_score(y_test, y_pred, zero_division=0),
        recall_score(y_test, y_pred, zero_division=0), roc_auc_score(y_test, y_proba),
        brier_score_loss(y_test, y_proba),
    ],
})
print("Melhores parâmetros:", grid.best_params_)
print(f"Melhor F2 (CV 5-fold): {grid.best_score_:.4f}")
print(f"Limiar usado: {limiar:.2f}")
display(metricas_finais)

# Probabilidade individual de cada observação do conjunto de teste.
resultado_probabilidades = X_test.copy()
resultado_probabilidades["classe_real"] = y_test.to_numpy()
resultado_probabilidades["probabilidade_classe_1"] = y_proba
resultado_probabilidades["classe_predita"] = y_pred
display(resultado_probabilidades.head())
print(classification_report(y_test, y_pred, zero_division=0))
