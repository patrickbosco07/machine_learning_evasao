import json
from datetime import datetime
from config.database import SessionLocal, Base, engine
from models.execucao import Execucao
from models.importancia import Importancia

class ExperimentoRepository:
    def __init__(self):
        # Garante que as tabelas existem no SQL Server
        Base.metadata.create_all(engine)

    def salvar_experimento(self, nome_modelo, parametros, mae_cv, mae_final, importancia_perm):
        session = SessionLocal()
        try:
            nova_execucao = Execucao(
                data_execucao=datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
                nome_modelo=nome_modelo,
                hiperparametros=json.dumps(parametros),
                mae_cv=float(mae_cv),
                mae_final=float(mae_final)
            )

            for var, val in importancia_perm.items():
                nova_execucao.importancias.append(
                    Importancia(variavel=str(var), importancia_valor=float(val))
                )

            session.add(nova_execucao)
            session.commit()
            return nova_execucao.id
        except Exception as e:
            session.rollback()
            raise e
        finally:
            session.close()