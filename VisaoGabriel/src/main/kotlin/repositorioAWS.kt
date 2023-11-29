import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class repositorioAWS {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = ConexaoAWS.jdbcTemplate!!
    }

    fun servidor():Int{
        val servidor = jdbcTemplate.queryForObject("""
       SELECT idServidor FROM servidor WHERE apelido = 'Server C'
    """,Int::class.java)
        return servidor
    }
    fun banco():Int{
        val banco = jdbcTemplate.queryForObject("""
        SELECT idBanco FROM banco WHERE nomeFantasia = 'Bank c'
    """,Int::class.java)
        return banco
    }
    fun especificacoes():Int{
        val espec = jdbcTemplate.queryForObject("""
        SELECT idEspecificacoes FROM especificacao WHERE idEspecificacoes = 1
    """,Int::class.java)
        return espec
    }
    fun plano():Int{
        val plano = jdbcTemplate.queryForObject("""
       SELECT idPlano FROM plano_contratado WHERE tipo = 1 
    """,Int::class.java)
        return plano
    }


    fun metrica():Int{
        val metrica = jdbcTemplate.queryForObject("""
       SELECT idMetrica FROM metrica WHERE idMetrica = 1 
    """,Int::class.java)
        return metrica
    }
    fun cadastrarComp(fkServidorAWS:Int,fkBancoAWS:Int,fkEspecificacaoAWS:Int,fkPlanoAWS:Int,fkMetricaAWS:Int){
        jdbcTemplate.execute("""
       insert into componentes (nome, modelo,fkMetrica, fkServidor, fkBanco, fkEspecificacoes, fkPlano)  values
        ('CPU','porcentagem de uso da CPU',$fkMetricaAWS, $fkServidorAWS,$fkBancoAWS,$fkEspecificacaoAWS,$fkPlanoAWS)
    """)
    }
    fun getidCPU():Int{
        val idTemp = jdbcTemplate.queryForObject("""
       select min(idComponentes) from componentes where modelo = 'porcentagem de uso da CPU'
    """,Int::class.java)
        return idTemp
    }

    fun particao() : Int {
        val particao = jdbcTemplate.queryForObject("""
            select idParticao from particao where idParticao = 2
        """,Int::class.java)
        return particao
    }

    fun add(novaMedicao:CPU,fkServidorAWS:Int,fkBancoAWS:Int,fkEspecificacaoAWS: Int,fkPlanoAWS:Int,fkComponenteAWS:Int,fkMetricaAWS: Int, fkParticaoAWS: Int){
        jdbcTemplate.execute("""
        insert into registros (dataHorario, dadoCaptado, fkServidorReg,fkBanco, fkEspecificacoes, fkComponentesReg, fkMetrica, fkPlano,fkParticao)  values
         ('${novaMedicao.dataHora}','${novaMedicao.dado}',$fkServidorAWS,$fkBancoAWS,$fkEspecificacaoAWS,$fkComponenteAWS,$fkMetricaAWS, $fkPlanoAWS, $fkParticaoAWS )
    """)
    }
}