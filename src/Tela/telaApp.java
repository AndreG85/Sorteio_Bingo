package Tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class telaApp extends JFrame {

    private JPanel contentPane;
    private List<Integer> numerosSorteados;
    private JComboBox<Integer> quantidadeNumerosComboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    telaApp frame = new telaApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public telaApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel ultimoNum = new JLabel("0");
        ultimoNum.setForeground(new Color(0, 0, 255));
        ultimoNum.setHorizontalAlignment(SwingConstants.CENTER);
        ultimoNum.setFont(new Font("Tahoma", Font.BOLD, 30));
        ultimoNum.setBounds(150, 174, 114, 45);
        contentPane.add(ultimoNum);

        JTextPane numSorteados = new JTextPane();
        numerosSorteados = new ArrayList<Integer>();

        quantidadeNumerosComboBox = new JComboBox<Integer>();
        quantidadeNumerosComboBox.addItem(75);
        quantidadeNumerosComboBox.addItem(80);
        quantidadeNumerosComboBox.addItem(90);
        quantidadeNumerosComboBox.setBounds(180, 60, 50, 25);
        contentPane.add(quantidadeNumerosComboBox);

        JButton botao = new JButton("Sortear");
        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
                quantidadeNumerosComboBox.setEnabled(false);

                int maximo = (int) quantidadeNumerosComboBox.getSelectedItem();
                int minimo = 1;
                int range = maximo - minimo + 1;
                int numeroSorteado;

                if (numerosSorteados.size() == range) {
                    JOptionPane.showMessageDialog(null, "Todos os números foram sorteados!");
                    UIManager.put("OptionPane.yesButtonText", "Sim");
                    UIManager.put("OptionPane.noButtonText", "Não");
                    
                    quantidadeNumerosComboBox.setEnabled(true);

                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja fazer um novo sorteio?",
                            "Novo Sorteio", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        numerosSorteados.clear();
                        ultimoNum.setText("0");
                        numSorteados.setText("");
                    } else {
                        System.exit(0);
                    }
                } else {
                    do {
                        numeroSorteado = (int) (Math.random() * range) + minimo;
                    } while (numerosSorteados.contains(numeroSorteado));

                    numerosSorteados.add(numeroSorteado);
                    ultimoNum.setText(Integer.toString(numeroSorteado));
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < numerosSorteados.size(); i++) {
                        sb.append(numerosSorteados.get(i));
                        if (i != numerosSorteados.size() - 1) {
                            sb.append(" - ");
                        }
                    }
                    numSorteados.setText(sb.toString());
                }

            }
        });


        botao.setBounds(160, 100, 90, 25);
        contentPane.add(botao);
        numSorteados.setFont(new Font("Tahoma", Font.ITALIC, 15));
        numSorteados.setBounds(10, 251, 414, 150);
        contentPane.add(numSorteados);

        JLabel lblNewLabel = new JLabel("BINGO");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        lblNewLabel.setBounds(150, 11, 115, 45);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Números Sorteados:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_1.setBounds(10, 226, 125, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Último Número Sorteado:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(133, 149, 160, 14);
        contentPane.add(lblNewLabel_2);

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.yesButtonText", "Sim");
                UIManager.put("OptionPane.noButtonText", "Não");

                int resposta = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja reinicializar o sorteio?", "Confirmar Reset",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    numerosSorteados.clear();
                    ultimoNum.setText("0");
                    numSorteados.setText("");

                    quantidadeNumerosComboBox.setEnabled(true);
                }
            }
        });

        reset.setForeground(new Color(255, 0, 0));
        reset.setBounds(354, 217, 70, 23);
        contentPane.add(reset);
    }
}
