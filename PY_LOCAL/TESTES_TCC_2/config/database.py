from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

NOME_SERVIDOR = r'ImRich01\\MSSQLSERVER'
NOME_BANCO = 'TCC'
DRIVER = 'ODBC Driver 17 for SQL Server'

URL_BANCO = f"mssql+pyodbc://@{NOME_SERVIDOR}/{NOME_BANCO}?driver={DRIVER}&Trusted_Connection=yes"

engine = create_engine(URL_BANCO, echo=False)
SessionLocal = sessionmaker(bind=engine)
Base = declarative_base()