import java.util.*;

public class Jogo {

    private final Integer[] linhaUm = {1,0,0,0};
    private final Integer[] linhaDois = {1,1,0,0};
    private final Integer[] linhaTres = {1, 1, 1, 1};
    private final Integer[] linhaQuatro = {1,0,0,0};
    private final Integer[][] mapa = {linhaUm,linhaDois,linhaTres,linhaQuatro};

    public void start(){

        int jogadasErradas = 0;

        do{
            imprimeMapa();

            String movimento = getMovimento();



            switch (movimento) {
                case "C" ->{
                    if (isJogadasParaCima()) {
                        paraCima();
                        recolheNumerosCima();
                        adicionaNumeroAleatorio();
                        System.out.println("Jogada finalizada!");
                    } else {
                        System.out.println("Jogada não permitida");
                        jogadasErradas++;
                    }
                }
                case "B" -> {
                    if (isJogadasParaBaixo()) {
                        paraBaixo();
                        recolheNumerosBaixo();
                        adicionaNumeroAleatorio();
                        System.out.println("Jogada finalizada!");
                    } else {
                        System.out.println("Jogada não permitida");
                        jogadasErradas++;
                    }
                }
                case "D" -> {
                    if (isJogadasParaDireita()) {
                        paraDireita();
                        recolheNumerosDireita();
                        adicionaNumeroAleatorio();
                        System.out.println("Jogada finalizada!");
                    } else {
                        System.out.println("Jogada não permitida");
                        jogadasErradas++;
                    }
                }
                case "E" -> {
                    if (isJogadasParaEsquerda()) {
                        paraEsquerda();
                        recolheNumerosEsquerda();
                        adicionaNumeroAleatorio();
                        System.out.println("Jogada finalizada!");
                    } else {
                        System.out.println("Jogada não permitida");
                        jogadasErradas++;
                    }
                }
            }

            if(jogadasErradas == 3){
                System.out.println("Você errou 3 vezes jogo acabou");
                break;
            }

        }while(isMapaCheio());
    }

    private void recolheNumerosBaixo() {
        for(int coluna=0;coluna<4;coluna++){
            Integer[] colunaTemporaria = Arrays.stream(new Integer[]{mapa[0][coluna], mapa[1][coluna], mapa[2][coluna], mapa[3][coluna]}).filter(item->item!=0).toArray(Integer[]::new);
            List<Integer> listaTemporaria = Arrays.asList(colunaTemporaria);
            Collections.reverse(listaTemporaria);
            colunaTemporaria = listaTemporaria.toArray(Integer[]::new);
            for (int linha=3,i=0;linha>=0;linha--,i++){
                if(colunaTemporaria.length>i){
                    mapa[linha][coluna] = colunaTemporaria[i];
                }else{
                    mapa[linha][coluna] = 0;
                }
            }
        }
    }

    private void paraBaixo() {
        for(int coluna = 3;coluna >= 0;coluna--){
            for (int linha = 3;linha > 0;linha--){
                if(mapa[linha][coluna] != 0){
                    if(mapa[linha - 1][coluna] != 0){
                        if(Objects.equals(mapa[linha][coluna], mapa[linha - 1][coluna])){
                            mapa[linha][coluna] *= 2;
                            mapa[linha - 1][coluna] = 0;
                        }
                    } else {
                        if (linha > 2 && Objects.equals(mapa[linha][coluna], mapa[linha - 2][coluna]) && Objects.equals(mapa[linha][coluna], mapa[linha - 2][coluna])) {
                            mapa[linha][coluna] *= 2;
                            mapa[linha - 2][coluna] = 0;
                        } else if (linha > 3 && Objects.equals(mapa[linha][coluna], mapa[linha - 3][coluna]) && Objects.equals(mapa[linha][coluna], mapa[linha - 3][coluna])) {
                            mapa[linha][coluna] *= 2;
                            mapa[linha - 3][coluna] = 0;
                        }
                    }
                }
            }
        }
    }

    private boolean isJogadasParaBaixo() {
        for(int coluna = 0;coluna < 4;coluna++){
            if(mapa[0][coluna] == 0){
                return true;
            }
            for(int linha = 3;linha > 0;linha--){
                if(linha > 3 && mapa[linha][coluna] != 0 && Objects.equals(mapa[linha][coluna], mapa[linha - 1][coluna])){
                    return true;
                }
                if (linha > 2 && mapa[linha][coluna] != 0 && mapa[linha - 1][coluna] == 0 && mapa[linha - 2][coluna] != 0){
                    return true;
                }
                if (linha > 1 && mapa[linha][coluna] != 0 && mapa[linha - 1][coluna] == 0 && mapa[linha - 2][coluna] == 0 && mapa[linha - 3][coluna] != 0){
                    return true;
                }
                if (linha > 1 && mapa[linha][coluna] == 0 && mapa[linha - 1][coluna] == 0 && mapa[linha - 2][coluna] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private void recolheNumerosCima() {
        for(int coluna=0;coluna<4;coluna++){
            Integer[] colunaTemporaria = Arrays.stream(new Integer[]{mapa[0][coluna], mapa[1][coluna], mapa[2][coluna], mapa[3][coluna]}).filter(item->item!=0).toArray(Integer[]::new);

            for (int linha=0;linha<4;linha++){
                if(colunaTemporaria.length<=linha){
                    mapa[linha][coluna] = 0;
                }else{
                    mapa[linha][coluna] = colunaTemporaria[linha];
                }
            }
        }
    }

    private void paraCima() {
        for(int coluna = 0;coluna < 4;coluna++){
            for (int linha = 0;linha < 3;linha++){
                if(mapa[linha][coluna] != 0){
                    if(mapa[linha + 1][coluna] != 0){
                        if(Objects.equals(mapa[linha][coluna], mapa[linha + 1][coluna])){
                            mapa[linha][coluna] *= 2;
                            mapa[linha + 1][coluna] = 0;
                        }
                    } else {
                        if (linha < 2 && Objects.equals(mapa[linha][coluna], mapa[linha + 2][coluna]) && Objects.equals(mapa[linha][coluna], mapa[linha + 2][coluna])) {
                            mapa[linha][coluna] *= 2;
                            mapa[linha + 2][coluna] = 0;
                        } else if (linha < 1 && Objects.equals(mapa[linha][coluna], mapa[linha + 3][coluna]) && Objects.equals(mapa[linha][coluna], mapa[linha + 3][coluna])) {
                            mapa[linha][coluna] *= 2;
                            mapa[linha + 3][coluna] = 0;
                        }
                    }
                }
            }
        }
    }

    private boolean isJogadasParaCima() {
        for(int coluna = 0;coluna < 4;coluna++){
            if(mapa[0][coluna] == 0){
                return true;
            }
            for(int linha =0;linha < 4;linha++){
                if(linha < 3 && mapa[linha][coluna] != 0 && Objects.equals(mapa[linha][coluna], mapa[linha + 1][coluna])){
                    return true;
                }
                if (linha < 2 && mapa[linha][coluna] != 0 && mapa[linha + 1][coluna] == 0 && mapa[linha + 2][coluna] != 0){
                    return true;
                }
                if (linha < 1 && mapa[linha][coluna] != 0 && mapa[linha + 1][coluna] == 0 && mapa[linha + 2][coluna] == 0 && mapa[linha + 3][coluna] != 0){
                    return true;
                }
                if (linha < 1 && mapa[linha][coluna] == 0 && mapa[linha + 1][coluna] == 0 && mapa[linha + 2][coluna] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private void recolheNumerosDireita() {
        for(int linha = 0;linha < 4;linha++){
            Integer[] linhaTemporaria = Arrays.stream(mapa[linha]).filter(item->item!=0).toArray(Integer[]::new);
            List<Integer> listaTemporaria = Arrays.asList(linhaTemporaria);
            Collections.reverse(listaTemporaria);
            linhaTemporaria = listaTemporaria.toArray(Integer[]::new);
            for (int coluna = 3,i=0;coluna >= 0;coluna--,i++){
                if(linhaTemporaria.length>i){
                    mapa[linha][coluna] = linhaTemporaria[i];
                }else{
                    mapa[linha][coluna] = 0;
                }
            }
        }
    }

    private void paraDireita() {
        for(int linha = 3;linha >= 0;linha--){
            for (int coluna = 3;coluna > 0;coluna--){
                if(mapa[linha][coluna] != 0){
                    if(mapa[linha][coluna - 1] != 0){
                        if(Objects.equals(mapa[linha][coluna], mapa[linha][coluna - 1])){
                            mapa[linha][coluna] *= 2;
                            mapa[linha][coluna - 1] = 0;
                        }
                    }else {
                        if (coluna > 1 && Objects.equals(mapa[linha][coluna], mapa[linha][coluna - 2]) && Objects.equals(mapa[linha][coluna], mapa[linha][coluna - 2])) {
                            mapa[linha][coluna] *= 2;
                            mapa[linha][coluna - 2] = 0;
                        }else if (coluna > 2 && Objects.equals(mapa[linha][coluna], mapa[linha][coluna - 3])&&Objects.equals(mapa[linha][coluna], mapa[linha][coluna - 3])) {
                            mapa[linha][coluna] *= 2;
                            mapa[linha][coluna - 3] = 0;
                        }
                    }
                }
            }
        }
    }

    private boolean isJogadasParaDireita() {
        for(int linha = 3;linha >= 0;linha--){
            if(mapa[linha][3] == 0){
                return true;
            }
            for(int coluna = 3;coluna >= 0;coluna--){
                if(coluna > 0 && mapa[linha][coluna]!= 0 && Objects.equals(mapa[linha][coluna], mapa[linha][coluna - 1])){
                    return true;
                }
                if (coluna > 1 && mapa[linha][coluna]!= 0 && mapa[linha][coluna - 1] == 0 && mapa[linha][coluna - 2] != 0){
                    return true;
                }
                if (coluna > 2 && mapa[linha][coluna]!= 0 && mapa[linha][coluna - 1] == 0 && mapa[linha][coluna - 2] == 0 && mapa[linha][coluna - 3] != 0){
                    return true;
                }
                if (coluna > 2 && mapa[linha][coluna] == 0 && mapa[linha][coluna - 1] == 0 && mapa[linha][coluna - 2] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private void paraEsquerda(){
        for(int linha = 0;linha < 4;linha++){
            Integer[] linhaAtual = mapa[linha];
            for (int coluna = 0;coluna < 3;coluna++){
                if(linhaAtual[coluna] != 0){
                    if(linhaAtual[coluna + 1] != 0){
                        if(Objects.equals(linhaAtual[coluna], linhaAtual[coluna + 1])){
                            linhaAtual[coluna] *= 2;
                            linhaAtual[coluna + 1] = 0;
                        }
                    } else {
                        if (coluna < 2 && Objects.equals(linhaAtual[coluna], linhaAtual[coluna + 2]) && Objects.equals(linhaAtual[coluna], linhaAtual[coluna + 2])) {
                            linhaAtual[coluna] *= 2;
                            linhaAtual[coluna + 2] = 0;
                        } else if (coluna < 1 && Objects.equals(linhaAtual[coluna], linhaAtual[coluna + 3]) && Objects.equals(linhaAtual[coluna], linhaAtual[coluna + 3])) {
                            linhaAtual[coluna] *= 2;
                            linhaAtual[coluna + 3] = 0;
                        }
                    }
                }
            }
        }
    }

    private void imprimeMapa(){
        for(int i =0;i<4;i++){
            for(int k =0;k<4;k++){
                System.out.print((mapa[i][k]==0?"_":mapa[i][k]) + " ");
            }
            System.out.println();
        }
    }

    private boolean isMapaCheio(){
//        for(int i =0;i<4;i++){
//            for(int k =0;k<4;k++){
//                if(mapa[i][k]==0){
                    return true;
//                }
//            }
//        }
//        return false;
    }

    private void recolheNumerosEsquerda(){
        for(int i=0;i<4;i++){
            Integer[] linhaTemporaria = Arrays.stream(mapa[i]).filter(item->item!=0).toArray(Integer[]::new);

            for (int j=0;j<4;j++){
                if(linhaTemporaria.length<=j){
                    mapa[i][j] = 0;
                }else{
                    mapa[i][j] = linhaTemporaria[j];
                }
            }
        }
    }

    private void adicionaNumeroAleatorio(){
        Random gerador = new Random();
        int linha, coluna;
        do {
            linha = gerador.nextInt(4);
            coluna = gerador.nextInt(4);
        }while (mapa[linha][coluna]!=0);
        mapa[linha][coluna] = gerador.nextInt(2) + 1;
    }

    private boolean isJogadasParaEsquerda(){
        for(int linha = 0;linha < 4;linha++){
            if(mapa[linha][0] == 0){
                return true;
            }
            for(int coluna =0;coluna < 4;coluna++){
                if(coluna < 3 && mapa[linha][coluna] != 0 && Objects.equals(mapa[linha][coluna], mapa[linha][coluna + 1])){
                    return true;
                }
                if (coluna < 2 && mapa[linha][coluna] != 0 && mapa[linha][coluna + 1] == 0 && mapa[linha][coluna + 2] != 0){
                    return true;
                }
                if (coluna < 1 && mapa[linha][coluna] != 0 && mapa[linha][coluna + 1] == 0 && mapa[linha][coluna + 2] == 0 && mapa[linha][coluna + 3] != 0){
                    return true;
                }
                if (coluna < 1 && mapa[linha][coluna] == 0 && mapa[linha][coluna + 1] == 0 && mapa[linha][coluna + 2] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    private String getMovimento(){
        boolean tamanhoMovimentoDiferenteUm;
        boolean movimentoDiferenteCBDE;

        Scanner input = new Scanner(System.in);

        String movimento;

        do{
            System.out.println("Escolha o próximo movimento:");
            System.out.println("C - Para cima");
            System.out.println("B - Para baixo");
            System.out.println("D - Para direita");
            System.out.println("E - Para esquerda");
            movimento = input.nextLine().toUpperCase();

            tamanhoMovimentoDiferenteUm = movimento.length()!=1;
            movimentoDiferenteCBDE = !movimento.equals("C") && !movimento.equals("D") && !movimento.equals("E") && !movimento.equals("B");
        }while(tamanhoMovimentoDiferenteUm || movimentoDiferenteCBDE);

        return movimento;
    }
}
