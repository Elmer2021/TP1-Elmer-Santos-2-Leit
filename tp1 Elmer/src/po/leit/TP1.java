package po.leit;

import po.leit.ui.Le;
import po.leit.ui.MyCommand;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TP1 {

    private static MyCommand interC;
    static final int MAX_ALUNOS = 35;
    private static int alunosLidos=0;
    private static int notaMax = 0;
    private static int notaMin = 0;
    private static int notaAvg = 0;

    private static String[] nomeAlunos = new String[MAX_ALUNOS];
    private static int[] notasAlunos = new int[MAX_ALUNOS];

    public static void main(String[] args) {
        boolean querSair=false;

        interC=new MyCommand();

        do {
            interC.limparEcra();
            interC.showPrompt();
            String[] cmdEscrito = interC.lerComando();
            ArrayList<String> cmd = interC.validarComando(cmdEscrito);

            if (cmd == null) {
                interC.showMsg("Comando inválido. Digite help para ajuda");

            } else {
                if  ( cmd.get(0).equalsIgnoreCase("carregar") ) {
                    alunosLidos = loadData(nomeAlunos, "turmaLeit.txt");
                    int notA = loadData(notasAlunos);
                    if ( alunosLidos != notA ) {
                        System.out.println("alunos = " + alunosLidos);
                        System.out.println("notaA = " + notA);
                        interC.showMsg("Erro carregando dados");
                    }
                        
                    else

                        interC.showMsg("Dados carregados OK!");
                } else if (cmd.get(0).equalsIgnoreCase("listar") ) {
                    mostrarAlunos();

                } else if (cmd.get(0).equalsIgnoreCase("paginar") ) {
                    String input = JOptionPane.showInputDialog("Nũmeros estudantes por pãgina :");
                    int numeroU = Integer.parseInt(input);
                    mostrarAlunos(numeroU);

                } else if (cmd.get(0).equalsIgnoreCase("mostrarp") ) {
                    mostrarPauta();

                } else if (cmd.get(0).equalsIgnoreCase("mostrarr") ) {
                    mostraResumo();

                } else if (cmd.get(0).equalsIgnoreCase("top") ) {
                    mostrarTop();

                } else if (cmd.get(0).equalsIgnoreCase("pesquisarnome") ) {
                    String nomePesq = JOptionPane.showInputDialog("O que procura  :");
                    pesquisar(nomePesq);

                } else if (cmd.get(0).equalsIgnoreCase("pesquisarnota") ) {
                    String vaPesq = JOptionPane.showInputDialog("O que procura  :");
                    int notaPesq = Integer.parseInt(vaPesq);
                    pesquisar(notaPesq);
                } else if (cmd.get(0).equalsIgnoreCase("help") ) {
                    interC.showHelp();

                } else if (cmd.get(0).equalsIgnoreCase("terminar") ) {
                    querSair = true;
                }
            }

        } while (!querSair);

    }

    /**
     * Método implementado por Prof. Não devem alterar. Este método recebe
     * como parâmetros um array e um ficheiro
     * Lẽ cada linha do ficheiro e guarda no array. Retorna o número
     * de linhas que forma lidas do ficheiro.
     * @param lAlunos
     * @param nomeFicheiro
     * @return quantos nomes foram lidos do ficheiro -1 se não possível ler ficheiro
     */
    public static int loadData(String[] lAlunos, String nomeFicheiro) {
        Scanner in = null;
        File inputFile = new File(nomeFicheiro);
        //PrintWriter out = new PrintWriter(outputFileName);
        try {
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (in.hasNextLine()) {
            String nomeAl = in.nextLine();
            if ( (nomeAl != null) && !(nomeAl.isBlank()) && !(nomeAl.isEmpty() ) ) {
                lAlunos[i] = nomeAl;
                i++;
            }

        }
        in.close();
        return i;
    }

    /**
     * Método implementado por Prof. Não devem alterar. Este método recebe
     * como parâmetros um array de inteiros e vai gerar aleatoriamente valores inteiros entre
     * 0 e 20 que representam a nota de cada aluno.
     * @param lNotas
     * @return how much name was read from the files -1 if was not able to read the file
     */
    public static int loadData(int[] lNotas) {
        Random rand = new Random();
        int cont = 0;
        for (cont=0; cont < alunosLidos; cont++) {
            int randomNum = rand.nextInt(20) + 1;
            notasAlunos[cont] = randomNum;
        }
        return cont;
    }

    /**
     * Método a ser implementando no TP1.
     * O método deverá listar todos os nomes dos alunos guardados no array nomesAlunos.
     * O método deverá verificar se já foi carregado os dados para o array. Se sim mostra os
     * nomes dos alunos. Senão deve mostrar a mensagem "Não há dados"
     * @param
     * @return
     */
    public static void mostrarAlunos() {

        if (nomeAlunos[0] == null) {
            interC.showMsg("Nao ha dados");/**Se o primeiro elemento do vetor nao estiver com nada
                                            assumimos que o resto do vetor esta vazio,
                                            entao terminamos o metodo com a instruçao return */
            return;
        }

        int cont = 0;
        System.out.println("Codigo......Nome estudante");

        for (String aluno : nomeAlunos) {
            final DecimalFormat decimal = new DecimalFormat("00000");

            if (aluno == null) //Se  tentar ler um valor null para saltar as proximas instruçoes
                continue;
            System.out.print(decimal.format(cont++)+" ");
            System.out.printf(" "+ aluno);
            System.out.println();
        }
        interC.showMsg("Enter para sair");



    }


    /**
     * Método a ser implementando no TP1
     * O método deverá listar todos os nomes dos alunos guardados no array nomesAlunos.
     * O método deverá verificar se já foi carregado os dados para o array. Se sim mostra os
     * nomes dos alunos. Senão deve mostrar a mensagem "Não há dados".
     * Neste método os dados não são mostrados todos de uma só vez. Devem ser apresentados até encher a tela.
     * Vamos supor que 10 nomes enchem a tela. Então deverá ser apresentados de 10 em 10. Esse número
     * que indica quantos nomes enchem a tela é um parâmetro do método.
     * @param tela é um inteiro que indica quantos alunos são mostrados.
     */
    public static void mostrarAlunos(int tela){


 if (nomeAlunos[0] == null) {

        interC.showMsg("Nao ha dados");
        return;

    }
    int cont = 0, codg = 1;
    int calc= (alunosLidos / tela) + 1;
    /**
     * variavel que armazena o limite do primeiro loop, este que é responsavel pela
     * paginaçao este loop servira como paginas
     */
            for (int j = 1; j <= calc; j++) {
        System.out.println("Codigo.........Nome estudante");
        System.out.println();
        for (int i = cont; i < ((j * tela) < nomeAlunos.length ? j * tela : (j) * tela - 1); i++) {
            final DecimalFormat decimal = new DecimalFormat("00000");


            if (nomeAlunos[i] == null)

                break;

            System.out.print(decimal.format(codg++) + "  " );
            System.out.printf("  "+nomeAlunos[i]);
            System.out.println();


        }

        cont =cont + tela;

        interC.showMsg(" Pressione: Enter para continuar...");


    }

}



    /**
     * Método a ser implementando no TP1.
     * O método deverá percorrer o array de notas, calcular o valor da média aritmética de notas, a nota máximo e
     * a nota mínima.
     * Os valores calculados devem ser guaraddos na variáveis notaAVG (média),
     * notaMax (nota máxima) e notaMin(nota mínima)
     * Devem validar se o array de notas tem elementos. Se estiver vazio devem somente apresentar
     * a mensagem "Não há dados"
     */
    private static void calcularMaxMinAvg() {

        if (notasAlunos[0] == 0) {
            interC.showMsg("Nao ha dados");
            return;
        }
        int som = 0, max = 0, min = 0;
        for (int indice = 0; indice < notasAlunos.length; indice++) {
            if (notasAlunos[indice] > max) {
                max = notasAlunos[indice];



            } else if (notasAlunos[indice] < min) {
                min = notasAlunos[indice];

            }
            som=som+ notasAlunos[indice];
        }
        notaAvg = som / notasAlunos.length;
        notaMax = max;
        notaMin = min;

        System.out.println("Nota maxima = " + notaMax + " Nota minimia = " + notaMin);
        System.out.println("");
        System.out.println("Media da turma = " + notaAvg);

    }



    /**
     * Método a ser implementando no TP1.
     * O método deverá apresentar um resumo da avaliação;
     * Nota máxima, Nota mínima, Nota média. Número de alunos com nota superior a média e número de alunos com
     * nota inferior a média.
     * a mensagem "Não há dados"
     */
    public static void mostraResumo() {
        if(nomeAlunos[0]==null){


            interC.showMsg("Nao ha dados ...");
            return;
        }

        int contSup = 0, contInf = 0;
        System.out.println( " alunos presentes"+ alunosLidos );
        System.out.println("");
        System.out.println("");
        calcularMaxMinAvg();
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < notasAlunos.length; i++) {
            if (notasAlunos[i] > notaAvg) {
                contSup++;
            } else
                contInf++;

        }
        System.out.println(contSup + " alunos com nota superior a media");
        System.out.println(contInf + " alunos com nota inferior a media");
        System.out.println("");
        System.out.println("");


        interC.showMsg("Pressione: Enter para continuar ...");

    }




    /**
     * Método a ser implementando no TP1.
     * O método deverá apresentar o nome dos três alunos que têm as melhores notas.
     */
    public static void mostrarTop() {
        if (notasAlunos[0] == 0) {
            interC.showMsg("Nao ha dados");
            return;
        }
        String[] alunoTop = new String[3];

        int[] notasTop = new int[3];

        int maxValue = -1;
        int index = 0;

        int[] copyNum = new int[notasAlunos.length];

        for (int i = 0; i < copyNum.length; i++) {
            copyNum[i] = notasAlunos[i];
        }
        for (int j = 0; j < 3; j++) {

            for (int indice = 0; indice < copyNum.length; indice++) {
                if (copyNum[indice] > maxValue) {
                    maxValue = copyNum[indice];
                    index = indice;

                }
            }//fim do segundo for

            notasTop[j] = maxValue;
            alunoTop[j] = nomeAlunos[index];
            maxValue = -1;
            copyNum[index] = -1;

        }//fim do primeiro for

        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.printf("%-20s",alunoTop[i]);
            System.out.println(notasTop[i]);

        }

        interC.showMsg("Enter para continuar ...");

    }



    /**
     * Método a ser implementando no TP1.
     * Apresentar a pauta com nomes dos alunos e á frente cada nome a respectiva nota obtida.
     */
    public static void mostrarPauta() {

        if (notasAlunos[0] == 0) {
            interC.showMsg("Nao ha dados");
            return;

        }
        int jump = (alunosLidos / 10) + 1;
        int cont = 0, cod = 1;
        for (int j = 1; j <= jump; j++) {
            System.out.println("codigo........Nome estudante........Nota");
            for (int i = cont; i < ((j * 10) < nomeAlunos.length ? j * 10 : (j) * 10 - 1); i++) {//mesmo codigo do metodo paginar
                final DecimalFormat decimal = new DecimalFormat("00000");
                if (nomeAlunos[i] == null)

                    break;
                System.out.print(decimal.format(cod++)+" ");
                System.out.printf(" "+ nomeAlunos[i]);
                System.out.println(notasAlunos[i]);


            }

            cont =  cont + 10;

        }

    }



    /**
     * Método a ser implementando no TP1
     * Apresentar para um aluno específico em que o nome é dado como parâmetro a nota de avaliação
     * @param nome é uma string contendo o nome do aluno que queremos apresentar a sua nota
     * @return
     */
    public static void mostrarDetalhesAluno(String nome) {
        interC.showMsg("A ser implementado ...");

    }

    /**
     * Método a ser implementando no TP1
     * O método deverá pedir um nome e pesquisar o array de nomes. Caso existir ou caso existem nomes
     * parecidos apresentar a lista de nomes. Nomes parecidos são nomes que iniciam com as mesmas duas ou três
     * primeiras letras. Ou apelidos iguais.
     */
    public static void pesquisar(String nome) {
        if(nomeAlunos[0]==null){


            interC.showMsg("OBS: Nao ha dados ...");
            return;
        }
        for (String aluno : nomeAlunos) {



             String last;

            String str = new String();
            String[] strSp = str.split(" ",2);
             String first = strSp[0];
             String[] nameMiddle = strSp[1].split(" ", 2);



             if (nameMiddle.length > 1) {

             last = nameMiddle[1];
             } else {
             last = nameMiddle[0];//

             }

            if(aluno == null)continue;
            if ((aluno.toLowerCase()).contains(nome.toLowerCase())) {


                System.out.println(aluno);
            }


        }

        interC.showMsg("Enter para continuar ...");

    }




    /**
     * Método a ser implementando no TP1
     * O método deverá pedir um nome e pesquisar o array de nomes. Caso existir ou caso existem nomes
     * parecidos apresentar a lista de nomes. Nomes parecidos são nomes que iniciam com as mesmas duas ou três
     * primeiras letras. Ou apelidos iguais.
     */
    public static void pesquisar(int nota) {
        if (notasAlunos[0] == 0) {
            interC.showMsg("Nao ha dados ...");
            return;
        }

        System.out.println("Nome estudante.........Notas");
        for (int i = 0; i < notasAlunos.length; i++) {
            if (nota == notasAlunos[i]) {
                System.out.println(nomeAlunos[i] + "  " + notasAlunos[i]);
            }
        }

        interC.showMsg("Pressione:  Enter para continuar ...");

    }




    private String[] searchByName(String nome) {
        return null;
    }

}
