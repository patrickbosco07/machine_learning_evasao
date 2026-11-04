# models/execucao.py
from sqlalchemy import Column, Integer, String, Float
from sqlalchemy.orm import relationship
from config.database import Base

class Execucao(Base):
    __tablename__ = 'execucoes'

    id = Column(Integer, primary_key=True, autoincrement=True)
    data_execucao = Column(String(50))
    nome_modelo = Column(String(100))
    hiperparametros = Column(String(500))
    mae_cv = Column(Float)
    mae_final = Column(Float)

    importancias = relationship("Importancia", back_populates="execucao", cascade="all, delete-orphan")