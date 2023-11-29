import psutil
import time
from mysql.connector import connect
import pyodbc
import cpuinfo



def mysql_connection (host,user, passwd, database=None):
    connection = connect(
        host = host,
        user = user,
        passwd = passwd,
        database = database
        )
    return connection

connection = mysql_connection('localhost', 'root', 'gabs1232004', 'SecurityBank')

server = '34.206.192.7'
database = 'Securitybank'
username = 'sa'
password = 'UrubuDoGit123'

conn = pyodbc.connect('DRIVER={SQL Server};SERVER='+server+';DATABASE='+database+';UID='+username+';PWD='+password)

cursor1AWS = conn.cursor()
cursor2AWS = conn.cursor()
cursor3AWS = conn.cursor()
cursor4AWS = conn.cursor()
cursor5AWS = conn.cursor()
cursor6AWS = conn.cursor()
cursor7AWS = conn.cursor()

mycursor1 = connection.cursor()
mycursor2 = connection.cursor()
mycursor3 = connection.cursor()
mycursor4 = connection.cursor()
mycursor5 = connection.cursor()
mycursor6 = connection.cursor()
mycursor7 = connection.cursor()
mycursor1Comp = connection.cursor()

valoresAWS = []
valores = []

cursor1AWS = conn.cursor()
cursor2AWS = conn.cursor()
cursor3AWS = conn.cursor()
cursor4AWS = conn.cursor()
cursor5AWS = conn.cursor()
cursor6AWS = conn.cursor()
cursor1CompAWS = conn.cursor()


cursor1AWS.execute("select idMetrica from metrica where idMetrica = 1")
result1AWS = cursor1AWS.fetchall()
idMetricaAWS = [x[0] for x in result1AWS]
valoresAWS.extend(idMetricaAWS)
fkMetricaAWS = valoresAWS[0]

cursor2AWS.execute("select idServidor from servidor where idServidor = 1")
result2AWS = cursor2AWS.fetchall()
idServidorAWS = [x[0] for x in result2AWS]
valoresAWS.extend(idServidorAWS)
fkServidorAWS = valoresAWS[1]

cursor3AWS.execute("select idBanco from banco where idBanco = 1")
result3AWS = cursor3AWS.fetchall()
idBancoAWS = [x[0] for x in result3AWS]
valoresAWS.extend(idBancoAWS)
fkBancoAWS = valoresAWS[2]

cursor4AWS.execute("select idEspecificacoes from especificacao where idEspecificacoes = 1")
result4AWS = cursor4AWS.fetchall()
idEspecAWS = [x[0] for x in result4AWS]
valoresAWS.extend(idEspecAWS)
fkEspecAWS = valoresAWS[3]

cursor5AWS.execute("Select idPlano from plano_contratado where idPlano = 1")
result5AWS = cursor5AWS.fetchall()
idPlanoAWS = [x[0] for x in result5AWS]
valoresAWS.extend(idPlanoAWS)
fkPlanoAWS = valoresAWS[4]

cursor6AWS.execute("select idParticao from particao where idParticao = 2")
result6AWS = cursor6AWS.fetchall()
idParticaoAWS = [x[0] for x in result6AWS]
valoresAWS.extend(idParticaoAWS)
fkParticaoAWS = valoresAWS[5]

cursor7AWS.execute("select idComponentes from componentes where idComponentes = 1")
result7AWS = cursor7AWS.fetchall()
idComponenteAWS = [x[0] for x in result7AWS]
valoresAWS.extend(idComponenteAWS)
fkComponenteAWS = valoresAWS[6]
    

mycursor1.execute("select idMetrica from metrica where idMetrica = 1")
result1 = mycursor1.fetchall()
id_metrica_vetor1 = [x[0] for x in result1]
valores.extend(id_metrica_vetor1)
fkMetrica = valores[0]


mycursor2.execute("select idServidor from servidor where idServidor = 1")
result2 = mycursor2.fetchall()
id_servidor_vetor2 = [x[0] for x in result2]
valores.extend(id_servidor_vetor2)  
fkServidor = valores[1]


mycursor3.execute("select idBanco from banco where idBanco = 1")
result3 = mycursor3.fetchall()
id_banco_vetor3 = [x[0] for x in result3]
valores.extend(id_banco_vetor3)
fkBanco = valores[2]

mycursor4.execute("select idEspecificacoes from especificacao where idEspecificacoes = 1")
result4 = mycursor4.fetchall()
id_especificacoes_vetor4 = [x[0] for x in result4]
valores.extend(id_especificacoes_vetor4)
fkEspec = valores[3]

mycursor5.execute("Select idPlano from plano_contratado where idPlano = 1")
result5 = mycursor5.fetchall()
id_plano_vetor5 = [x[0] for x in result5]
valores.extend(id_plano_vetor5)
fkPlano = valores[4]

mycursor6.execute("select idComponentes from componentes where idComponentes = 1")
result6 = mycursor6.fetchall()
id_componente_vetor6 = [x[0] for x in result6]
valores.extend(id_componente_vetor6)
fkComponente = valores[5]



info = cpuinfo.get_cpu_info()
especificacao_cpu = info.get('brand_raw', 'Desconhecido')

num_cores_fisicos = psutil.cpu_count(logical=False)
num_cores_logicos = psutil.cpu_count(logical=True)


queryC1 = "INSERT INTO qtdNucleosThreads(qtdNucleos, qtdThreads, especificacaoCpu, fkComponentes, fkMetrica, fkServidor, fkBanco, fkEspecificacoes, fkPlano) VALUES (%s,%s, %s, %s, %s, %s, %s, %s, %s)"
queryC1AWS = "INSERT INTO qtdNucleosThreads(qtdNucleos, qtdThreads, especificacaoCpu, fkComponentes, fkMetrica, fkServidor, fkBanco, fkEspecificacoes, fkPlano) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)"

valuesC1 = (num_cores_fisicos, num_cores_logicos, especificacao_cpu, fkComponente, fkMetrica, fkServidor, fkBanco, fkEspec, fkPlano)
valuesC1AWS = (num_cores_fisicos, num_cores_logicos, especificacao_cpu, fkComponenteAWS, fkMetricaAWS, fkServidorAWS, fkBancoAWS, fkEspecAWS, fkPlanoAWS)

mycursor1Comp.execute(queryC1, valuesC1)
cursor1CompAWS.execute(queryC1AWS,valuesC1AWS)

connection.commit()
conn.commit()

cursorselect = connection.cursor()
cursorselectAWS = conn.cursor()

cursorselect.execute("select idThreads from qtdNucleosThreads where idThreads = 1")
cursorselectAWS.execute("select idThreads from qtdNucleosThreads where idThreads = 1")


result1 = cursorselect.fetchall()
result1AWS = cursorselectAWS.fetchall()

id_Therads_vetor1 = [x[0] for x in result1]
id_Threads_vetor1AWS = [x[0] for x in result1AWS]
idThreadAWS = id_Threads_vetor1AWS[0]
idThread= id_Therads_vetor1[0]
      
while True:

     print(f"Número de núcleos: {num_cores_fisicos}")
     print(f"Número de threads: {num_cores_logicos}")
     print(f"Especificação da CPU: {especificacao_cpu}")

     uso_threads = psutil.cpu_percent(percpu=True)
     

     for i in range(num_cores_logicos):
        print(f"Thread {i+1}: {uso_threads[i]:.2f}%")
        thread_number = i+1


        cursor = connection.cursor()
        cursorAWS = conn.cursor()

        query = "INSERT INTO MonitoramentoThreads (porcentagem, numeroThreads, fkNucleosThreds) VALUES (%s, %s, %s)"
        queryAWS = "INSERT INTO MonitoramentoThreads (porcentagem, numeroThreads, fkNucleosThreds) VALUES (?, ?, ?)"
        cursor.execute(query, (uso_threads[i], thread_number, idThread))
        cursorAWS.execute(queryAWS,(uso_threads[i], thread_number, idThreadAWS))
        
           

        connection.commit()
        conn.commit()

    
        time.sleep(1)
