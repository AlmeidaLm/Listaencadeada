import java.util.ArrayList;
import java.util.Scanner;

public class ListNames {
    private Element inicio=null; // Descritor inicial lista
    private Element endlist = null;//Descritor final da lista
    private class Element {
        private String nome;
        private Element prox;
    }

    public static void main(String[] args) {
        ListNames app = new ListNames();
        Scanner sc = new Scanner(System.in);
        int opcao=-1;
        String wsNome;

        while (opcao != 0) {
            System.out.println("Lista de Nomes\n1.Inserir inicio"
                    + "\n2.Inserir final\n3.Excluir Nome\n" +
                    "4.Listar Quantidade\n5.Listar quant inversa\n6.Sair");
            opcao = Integer.parseInt(sc.nextLine());
            switch (opcao) {
                case 1: // Inserir no inicio
                    System.out.println("Digite um nome:");
                    wsNome = sc.nextLine();
                    app.inserirInicio(wsNome);
                    break;
                case 2: // Inserir no final
                    System.out.println("Digite um nome:");
                    wsNome = sc.nextLine();
                    app.inserirFinal(wsNome);
                    break;
                case 3: //Excluir Nome
                    System.out.println("Digite um nome a ser excluído:");
                    wsNome = sc.nextLine();
                    app.excluiNome(wsNome);
                    break;
                case 4://Listar Quantidade
                    app.listar();
                    break;
                case 5://Listar quant inversa
                    app.listarInvert();
                    break;
                case 6://Sair
                    opcao = 0;
                    break;
            }
        }
        System.out.println("Obrigado por usar este programa :)!!!");
    }
    private void inserirInicio(String wsNome) {
        if (inicio == null) {
            Element novo = new Element();
            novo.nome = wsNome;
            novo.prox = inicio;
            inicio = novo;
            endlist = novo;
        } else {
            boolean comp = false;
            Element novo = new Element();
            novo.nome = wsNome;
            novo.prox = null;
            Element fim = inicio;
            if(wsNome.equalsIgnoreCase(inicio.nome)){//verifica se o nome digitado é igual ao primeiro elemento
                comp = true;// quando a lista tem apenas um elemento [k ]->,
            }
            while ((fim.prox != null)){
                if (wsNome.equalsIgnoreCase(fim.nome) || wsNome.equalsIgnoreCase(endlist.nome)){
                    comp = true;//verifica se o nome é igual aos demais elementos da lista
                    break;
                }
                fim = fim.prox;
            }
            if (comp){
                System.out.println("Este nome já existe na lista!");
            }
            else {// adiciona um novo elemento ao início da lista apenas se esta não o contiver.
                novo.prox = inicio;//Exemplo [N ]->[H ]->null digito [M ]
                inicio = novo;
                //como [M ]->null
                // apenas é necessário fazer [M ]->[N ] ou seja novo.prox = início
                // e fazer o início ser [M ] , o novo elemento.
            }
        }
    }
    private void inserirFinal(String wsNome) {
        boolean comp = false;
        Element novo = new Element();
        novo.nome = wsNome;
        novo.prox = null;
        if (inicio != null) {
            Element fim = inicio;
            if(wsNome.equalsIgnoreCase(inicio.nome)){
                comp = true;
            }
            while ((fim.prox != null)) {
                if (wsNome.equalsIgnoreCase(fim.nome) || wsNome.equalsIgnoreCase(endlist.nome)) {
                    comp = true;
                    break;
                }
                fim = fim.prox;
                if((fim.prox == null)) {// verifica se o ultimo elemento é igual ao elemento a ser adicionado
                    if (wsNome.equalsIgnoreCase(fim.nome) || wsNome.equalsIgnoreCase(endlist.nome)) {
                        comp = true;
                        break;
                    }
                }
            }
            if (comp) {
                System.out.println("Este nome já existe na lista!");
            }else {
               fim.prox = novo;//Exemplo [M ]->[N ]->[H ]->null, digito [K ]
                endlist = fim;
                // fim.prox = null, mas ao digitar [K ] eu faço o elemento H apontar para ele ficando fim.prox[H] = novo[K]
                // como não existem elementos após [K ] ele é o ultimo, então endlist = fim[K ].
            }
        }
        else {
            inicio = novo;
            endlist = novo;
        }
    }
    private void listar() {
        if (inicio!=null) {
            int qnt = 0;
            Element aux = inicio;
            System.out.print("Lista: ");
            while (aux != null) {// enquanto o ultimo elemento da lista não apontar para nulo ele printa os elementos
                System.out.print(aux.nome + " ->");
                aux = aux.prox;
                qnt++;
            }
            System.out.println("\nA quantidade de nomes na lista é: " + "[ " + qnt + " ]");
            System.out.println("\n");
        }else {
            System.out.println("Sem elementos na lista!!!");
        }
    }
    private void excluiNome(String wsNome){//quando objeto.prox apontar pra um elemento igual exclui e aponta para o proximo
        if (inicio == null) {
            System.out.println("ADICIONE UM NOME PRIMEIRO!!!");
        } else {
            boolean comp = false;
            Element elo = inicio;//apaga o primeiro nome ex; k->null

            if (wsNome.equalsIgnoreCase(inicio.nome)) {//exclui um elemento do inicio da lista
                comp = true;//exemplo : [k ]->[j ]->[l ]->null , se quero excluir k, fica [j ]->[l ]->null
                inicio = elo.prox;
            }
            else{
                Element aux = inicio;
                Element aux2;
                while ((elo.prox != null)) {//o laço exclui um elemento no meio da lista ou no fim dela
                    if (wsNome.equalsIgnoreCase(elo.nome)){//compara se a string é igual a existente na lista.
                        comp = true;
                        if (comp) {
                            elo = elo.prox;//salta o elemento igual
                            if (comp) {
                                aux.prox = elo;// faz o elemento anterior apontar para o proximo
                                endlist = elo;//exemplo : [k ]->[j ]->[l ]->null , e quero excluir j, fica [k ]->[l ]->null.
                                break;
                            }
                        }
                    }
                    aux = elo;//auxiliar para guardar o elemento anterior
                    elo = elo.prox;//elemento a ser comparado
                    aux2 = elo.prox;// auxiliar para guardar o próximo elemento
                    /*exemplo: se os elementos são [g ]->[h ]->[i ]->null
                    aux = [g ] isso na primeira iteração
                    elo = [h ]
                    aux2= [i ]
                     */
                    if (aux2.prox==null) {//verifica se o elemento a frente deste é nulo
                        if(wsNome.equalsIgnoreCase(aux2.nome)){//verifica se o nome do ultimo elemento é igual ao digitado
                            comp = true;
                            elo.prox = null;
                            /*exemplo: se os elementos são [M ]->[N ]->[H ]->null para excluir o ultimo [H ]
                                aux  [M ]
                                elo  [N ] =  elo.prox[H]
                                aux2 [H ] = aux2.prox[null]
                                quando aux2.prox é nula o if é true
                                então como quero excluir o elemento [H]
                                apenas faço o [N ]->[H ] (elo[N].prox = [H]) apontar para null assim [N ]->null (elo.prox =null).
                                restando apenas [M ]->[N ]->null
                            */
                            break;
                        }
                    }
                }
            }
            if (comp) {
                System.out.println("Nome apagado!");
            }else{
                System.out.println("O nome não está na lista!!!");
            }
        }
    }
    private void listarInvert(){
        if (inicio!=null) {
            ArrayList<Element>invert = new ArrayList<>();
            Element aux = inicio;
            Element aux2;
            while (aux != null) {// enquanto o ultimo elemento da lista não apontar para nulo ele printa os elementos
                aux2 = aux;// faz o elemento aux2 ser igual ao elemento anterior
                aux = aux.prox;
                if (aux!=null){
                    aux2.prox = null;//faz com que aux2 aponte para nulo deixando o elemento livre
                    invert.add(aux2);//adiciona o elemento no array
                }
                if (aux==null){
                    aux2.prox = null;
                    invert.add(aux2);//adiciona o último elemento da lista no array
                }
            }
            int b = 0;
            for (Element element : invert) {
                listinveraux(element.nome,b);//chama a função invert auxiliar a cada iteração
                b++;
            }
            listar();
        }else {
            System.out.println("Sem elementos na lista!!!");
        }
    }// dava pra criar uma lista nova aqui :( burrice
    public void listinveraux(String wsNome,int con){
        if (con==0) {//na primeira iteração faz o primeiro elemento apontar para nulo evitando duplicação
            Element novo = new Element();
            novo.nome = wsNome;
            novo.prox = inicio;
            inicio = novo;
            novo.prox = null;
            endlist = novo;
        }else {// nas demais iterações adiciona os elementos do array na ordem em que estão
            Element novo = new Element();
            novo.nome = wsNome;
            novo.prox = inicio;
            inicio = novo;
            endlist = novo;
            /*o funcionamento é igual à função inseririnício;
            * exemplo: o array salva A, B , C nessa ordem, como eu adiciono elemento por elemento
            * o primeiro elemento adicionado vira o último e o último o primeiro*/
        }
    }
}
