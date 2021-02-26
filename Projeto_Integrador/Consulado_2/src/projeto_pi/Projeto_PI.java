package projeto_pi;

import Model.Bean.Funcionario;
import Model.Bean.Servico;
import Model.DAO.FuncionarioDAO;
import Model.DAO.ServicoDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showOptionDialog;

public class Projeto_PI {

    public static void main(String[] args) throws SQLException {

        int b;
        String a;

        //-----------------------------------
        Funcionario func1 = new Funcionario();
        Servico servico1 = new Servico();
        ServicoDAO servicoDAO = new ServicoDAO();

        //----------------------------------
        while (true) {
            a = JOptionPane.showInputDialog("Informe o ID de Funcionario");
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            try {

                b = Integer.parseInt(a);
                func1 = funcionarioDAO.pesquisar(b);
                JOptionPane.showMessageDialog(null, "Bem vindo(a): " + func1.getNome());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ID inválida, Encerrando o programa!", "Erro!", JOptionPane.ERROR_MESSAGE);
                break;
            }
            Funcionario:
            while (true) {

                int i = showOptionDialog(null, "Selecione o serviço desejado: ",
                        "Controle de Solicitações", 0, 2, null, new String[]{"Inserir novo pedido",
                            "Gerenciar pedidos existentes", "Trocar de Usuário"}, "Inserir novo pedido");
                if (i == -1) {
                    System.exit(0);
                }
                switch (i) {
                    case 0:
                        Inserimento:
                        while (true) {

                            a = JOptionPane.showInputDialog("Informe nome do Solicitante: ");
                            servico1.setNome(a);
                            a = JOptionPane.showInputDialog("Informe o e-mail: ");
                            servico1.setEmail(a);
                            servico1.setFuncionario(func1.getNome());

                            Tipo:
                            while (true) {
                                a = JOptionPane.showInputDialog("Informe tipo de servico:\n 1 - Cidadania\n 2 - Nascimento\n"
                                        + " 3 - Casamento\n 4 - Divorcio\n 5 - Obito\n 6 - Naturalização\n 7 - Passaporte\n 8 - Visto");
                                b = Integer.parseInt(a);
                                servico1.setSetor(servico1.obterSetor(b));
                                if (b > 0 && b < 9) {
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamete", "Erro!", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            break Inserimento;

                        }
                        servicoDAO.inserir(servico1);
                        JOptionPane.showMessageDialog(null, "Numero do Protocolo: " + servicoDAO.selecionarSolicitacao());

                        break;

                    case 1:
                        Gerenciar:
                        while (true) {
                            int j = showOptionDialog(null, "Selecione uma opção: ",
                                    "Verificar pedidos", 0, 2, null, new String[]{"Lista de protocolos",
                                        "Pesquisar por protocolo", "Voltar ao menu principal"}, "Lista de protocolos");
                            if (j == -1) {
                                System.exit(0);
                            }
                            switch (j) {
                                case 0:
                                    List<Servico> lista = servicoDAO.pesquisarPorSetor(func1.getSetor());
                                    JOptionPane.showMessageDialog(null, "Quantidade: " + lista.size());
                                    if (lista.isEmpty()) {
                                        JOptionPane.showMessageDialog(null, "Lista de protocolos vazia");
                                    }
                                    String retorno = "";
                                    for (Servico s : lista) {
                                        retorno += s.toString();
                                    }

                                    JOptionPane.showMessageDialog(null, retorno);
                                    break Gerenciar;
                                case 1:

                                    a = JOptionPane.showInputDialog("Informe o numero do protocolo");
                                    b = Integer.parseInt(a);
                                    servico1 = servicoDAO.pesquisarPorSolicitacao(b);
                                    JOptionPane.showMessageDialog(null, servico1.toString());
                                    break Gerenciar;
                                case 2:
                                    break Gerenciar;

                            }
                        }

                    default:
                        break;
                    case 2:
                        break Funcionario;

                }

            }
        }
    }
}
