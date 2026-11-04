import pyodbc

# Teste 1: Autenticação do Windows (Trusted Connection)
# Se usar SQL Authentication, troque por: "UID=seu_usuario;PWD=sua_senha;"
conn_str = (
    "DRIVER={ODBC Driver 17 for SQL Server};"
    "SERVER=ImRich01\\MSSQLSERVER;"  # Ou "127.0.0.1,1433" ou "localhost"
    "DATABASE=TCC;"              # Usamos 'master' para testar a conexão base
    "Trusted_Connection=yes;"
    "TrustServerCertificate=yes;"
    "Connection Timeout=5;"          # Define limite de 5 segundos para não travar
)

try:
    print("Tentando conectar...")
    conn = pyodbc.connect(conn_str)
    cursor = conn.cursor()
    
    # Executa uma consulta simples para validar a sessão
    cursor.execute("SELECT @@VERSION;")
    row = cursor.fetchone()
    
    print("\n Conexão estabelecida com sucesso!")
    print(f"Versão do SQL Server:\n{row[0]}")
    
    conn.close()

except pyodbc.Error as e:
    print("\n Falha na conexão com o SQL Server:")
    print(e)