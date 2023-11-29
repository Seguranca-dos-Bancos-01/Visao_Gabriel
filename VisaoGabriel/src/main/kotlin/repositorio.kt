import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
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
    fun cadastrarComp(fkServidor:Int,fkBanco:Int,fkEspecificacao:Int,fkPlano:Int,fkMetrica:Int){
        jdbcTemplate.execute("""
       insert into componentes values
        (null,'CPU','porcentagem de uso da CPU',$fkMetrica, $fkServidor,$fkBanco,$fkEspecificacao,$fkPlano)
    """)
    }
    fun getidCPU():Int{
        val idTemp = jdbcTemplate.queryForObject("""
       select min(idComponentes) from componentes where nome = "CPU" 
    """,Int::class.java)
        return idTemp
    }

    fun particao() : Int {
        val particao = jdbcTemplate.queryForObject("""
            select idParticao from particao where idParticao = 1
        """,Int::class.java)
        return particao
    }

    fun add(novaMedicao:CPU,fkServidor:Int,fkBanco:Int,fkEspecificacao: Int,fkPlano:Int,fkComponente:Int,fkMetrica: Int, fkParticao: Int){
        jdbcTemplate.execute("""
        insert into registros values (null,'${novaMedicao.dataHora}','${novaMedicao.dado}',$fkServidor,$fkBanco,$fkEspecificacao,$fkComponente,$fkMetrica, $fkPlano, $fkParticao )
    """)
    }
}