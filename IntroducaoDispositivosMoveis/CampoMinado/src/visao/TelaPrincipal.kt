package visao

import modelo.Tabuleiro
import modelo.TabuleiroEvento
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
    TelaPrincipal()
}

class TelaPrincipal: JFrame(){
    private val tabuleiro = Tabuleiro(qtdLinhas = 16, qtdColunas = 30, qtdMinas = 20)
    private val painelTabuleiro = PainelTabuleiro(tabuleiro)

    init{
        tabuleiro.onEvento(this::mostrarResultado)
        add(painelTabuleiro)

        setSize(690, 438) // ajustar o tamanho se necessário
        setLocationRelativeTo(null) // centraliza a tela
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Campo Minado"
        isVisible = true
    }

    private fun mostrarResultado(evento: TabuleiroEvento){
        SwingUtilities.invokeLater{
            val msg = when(evento){
                TabuleiroEvento.VITORIA -> "Você ganhou!"
                TabuleiroEvento.DERROTA -> "Você perdeu... :("
            }
            JOptionPane.showMessageDialog(this,msg)
            tabuleiro.reiniciar()

            painelTabuleiro.repaint()
            painelTabuleiro.validate()
        }
    }
}