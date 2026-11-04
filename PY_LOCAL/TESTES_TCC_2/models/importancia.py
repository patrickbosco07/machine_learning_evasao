# models/importancia.py
from sqlalchemy import Column, Integer, String, Float, ForeignKey
from sqlalchemy.orm import relationship
from config.database import Base

class Importancia(Base):
    __tablename__ = 'importancias'

    id = Column(Integer, primary_key=True, autoincrement=True)
    id_execucao = Column(Integer, ForeignKey('execucoes.id'))
    variavel = Column(String(100))
    importancia_valor = Column(Float)

    execucao = relationship("Execucao", back_populates="importancias")