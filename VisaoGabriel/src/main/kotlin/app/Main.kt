package app

import CPU
import com.github.britooo.looca.api.core.Looca
import repositorio
import repositorioAWS
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            fun main() {
                while (true) {
                    val repositorio = repositorio()
                    val repositorioAWS = repositorioAWS()
                    repositorio.iniciar()
                    repositorioAWS.iniciar()

                    val formato = DecimalFormat("#.##")
                    val simbolo = DecimalFormatSymbols()
                    simbolo.decimalSeparator = '.'
                    formato.decimalFormatSymbols = simbolo

                    val looca = Looca()
                    val porcentagemCpu = formato.format(looca.processador.uso).toDouble()
                    println("Porcentagem: ${porcentagemCpu}%")

                    val dataHoraAtual = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val dataHoraFormatada = dataHoraAtual.format(formatter)

                    val novaMedicao = CPU()
                    novaMedicao.dado = porcentagemCpu
                    novaMedicao.dataHora = dataHoraFormatada

                    val fkServidor = repositorio.servidor()
                    val fkBanco = repositorio.banco()
                    val fkEspecificacao = repositorio.especificacoes()
                    val fkPlano = repositorio.plano()
                    val fkMetrica = repositorio.metrica()
                    val fkParticao = repositorio.particao()

                    val fkServidorAWS = repositorioAWS.servidor()
                    val fkBancoAWS = repositorioAWS.banco()
                    val fkEspecificacaoAWS = repositorioAWS.especificacoes()
                    val fkPlanoAWS = repositorioAWS.plano()
                    val fkMetricaAWS = repositorioAWS.metrica()
                    val fkParticaoAWS = repositorioAWS.particao()

                    repositorio.cadastrarComp(fkServidor, fkBanco, fkEspecificacao, fkPlano, fkMetrica)
                    val fkComponente = repositorio.getidCPU()
                    repositorio.add(novaMedicao, fkServidor, fkBanco, fkEspecificacao, fkPlano, fkComponente, fkMetrica, fkParticao)

                    repositorioAWS.cadastrarComp(fkServidorAWS, fkBancoAWS, fkEspecificacaoAWS, fkPlanoAWS, fkMetricaAWS)
                    val fkComponenteAWS = repositorioAWS.getidCPU()
                    repositorioAWS.add(novaMedicao, fkServidorAWS, fkBancoAWS, fkEspecificacaoAWS, fkPlanoAWS, fkComponenteAWS, fkMetricaAWS, fkParticaoAWS)

                    Thread.sleep(2000)
                }

            }

        }
    }
}