import java.util.*;

//Autor: Carlos Eduardo Corrêa

class Main {
  public static void main(String[] args) {

    Baralho a = new Baralho();
	  Jogo j = new Jogo();
	  Scanner sc = new Scanner(System.in);

	  String e = null;

	  do {
	  	System.out.print("Escolha cara ou coroa para saber quem começa jogando: ");
		  e = j.caraCoroa(j.entraDadosString());
		  System.out.println(e);
	  } while (e.compareToIgnoreCase("Erro") == 0);

	  a.distribui(a.embaralha());

	  int c = 0;
	  j.b = a;

	  do{
	  	if (j.j1.vez == true) {
	  		j.imprimeCarta();
	    	do {
	    		System.out.print("\nEscolha uma das características: ");
	    		c = sc.nextInt();
	    	} while (c != 1 && c != 2 && c != 3 && c != 4 && c != 5);
        j.imprimeCaracteristica(c);
        sc.nextLine();
	  		System.out.print("\nAperte enter para continuar");
	  		sc.nextLine();
        if (a.monte1[0].superTrunfo == true){
          j.superTrunfo(a.monte2[0], c);
        } else if (a.monte2[0].superTrunfo == true){
          j.superTrunfo(a.monte1[0], c);
        } else {
          j.compara(c);
        }
		    a.limpaTela();
	  	} else if (j.pc.vez == true) {
        j.escolhaPc();
        a.limpaTela();
	  	}
	  } while(a.ganhador() == false);

	  if (a.contaCarta(a.monte1) == 0){
	    System.out.println("Que pena, você perdeu.");
	  } else if (a.contaCarta(a.monte2) == 0){
	    System.out.println("Parabens você ganhou!");
	  }
	  sc.close();
	}
}

class Jogador{
	boolean vez;
	  
	public Jogador(){
	  this.vez = false;
	}
}

class Jogo{
  Jogador j1;
	Jogador pc;
	Baralho b;

	public Jogo(){
    j1 = new Jogador();
    pc = new Jogador();
	  b = new Baralho();
	  j1.vez = false;
	  pc.vez = false;
  }

  public String entraDadosString(){
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    return s;
  }

  public void superTrunfo(Carta c, int i){
    if (c.grupo == "1A" || c.grupo == "2A" || c.grupo == "3A" || c.grupo == "4A" || c.grupo == "5A" || c.grupo == "6A" || c.grupo == "7A" || c.grupo == "8A"){
      compara(i);
    } else {
      if (c == b.monte2[0]){
        pc.vez = false;
        j1.vez = true;
        b.transfereCarta(b.monte1, b.monte2);
      }
      if (c == b.monte1[0]){
        j1.vez = false;
        pc.vez = true;
        b.transfereCarta(b.monte2, b.monte1);
      }
    }
  }

  public void imprimeCaracteristica (int i){
    if (i == 1){
      System.out.println("\nCarta e caracteristica do computador: ");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Area (km²): "+b.monte2[0].area);
    }
    if (i == 2){
      System.out.println("\nCarta e caracteristica do computador: ");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("População (milhões): "+b.monte2[0].populacao);
    }
    if (i == 3){
      System.out.println("\nCarta e caracteristica do computador: ");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Pessoas por km²: "+b.monte2[0].pessoasPorKm);
    }
    if (i == 4){
      System.out.println("\nCarta e caracteristica do computador: ");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Expectativa de vida (anos): "+b.monte2[0].expectativa);
    }
     if (i == 5){
      System.out.println("\nCarta e caracteristica do computador: ");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Renda per capita (US$): "+b.monte2[0].renda);
    }
  }
  
  public String caraCoroa(String e){
    Random gerador = new Random();
    //cara = true e coroa = false;
    boolean resultado = gerador.nextBoolean();
    if ("Cara".compareToIgnoreCase(e) == 0 && resultado == true){
      j1.vez = true;
      pc.vez = false;
      return "Você começa";
    } else if ("Coroa".compareToIgnoreCase(e) == 0 && resultado == false){
      pc.vez = false;
      j1.vez = true;
      return "Você começa";
    }
    if ("Cara".compareToIgnoreCase(e) == 0 && resultado == false){
      pc.vez = true;
      j1.vez = false;
      return "O computador começa";
    } else if ("Coroa".compareToIgnoreCase(e) == 0 && resultado == true){
      pc.vez = true;
      j1.vez = false;
      return "O computador começa";
    }
    return "Erro";
  }

  public void imprimeCarta(){
	  System.out.println("\nComputador possui "+b.contaCarta(b.monte2)+" cartas");
	  System.out.println("Você possui "+b.contaCarta(b.monte1)+" cartas\n");
    System.out.println(b.monte1[0].grupo+"  "+b.monte1[0].nome);
	  System.out.println("1: Area (km²): "+b.monte1[0].area);
	  System.out.println("2: População (milhões): "+b.monte1[0].populacao);
	  System.out.println("3: Pessoas por km²: "+b.monte1[0].pessoasPorKm);
	  System.out.println("4: Expectativa de vida (anos): "+b.monte1[0].expectativa);
	  System.out.println("5: Renda per capita (US$): "+b.monte1[0].renda);
	}

  public void escolhaPc(){ //adcionado peso para esolher a melhor opção.
    Scanner sc = new Scanner(System.in);
	  int area = b.monte2[0].area;
	  double populacao = b.monte2[0].populacao * 10000;
	  double pPorKm = b.monte2[0].pessoasPorKm * 1000;
	  double expec = b.monte2[0].expectativa * 1000;
	  int renda = b.monte2[0].renda * 10;
	  
	  if (area > populacao && area > pPorKm && area > expec && area > renda)  {
      System.out.println("\nO computador escolheu Area da carta abaixo:");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Area (km²): "+b.monte2[0].area+"\n");
      imprimeCarta();
    	System.out.print("\nAperte enter para continuar");
      sc.nextLine();
      if (b.monte2[0].superTrunfo == true){
        superTrunfo(b.monte1[0], 1);
      } else if (b.monte1[0].superTrunfo == true){
        superTrunfo(b.monte2[0], 1);
      } else {
        compara(1);
      }
	  }
	  if (populacao > area && populacao > pPorKm && populacao > expec && populacao > renda) {
      System.out.println("\nO computador escolheu População da carta abaixo:");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("População (milhões): "+b.monte2[0].populacao+"\n");
      imprimeCarta();
      System.out.print("\nAperte enter para continuar");
      sc.nextLine();
		  compara(2);
	  }
	  if (pPorKm > area && pPorKm > populacao && pPorKm > expec && pPorKm > renda) {
      System.out.println("\nO computador escolheu Pessoas por km² da carta abaixo:");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Pessoas por km²: "+b.monte2[0].pessoasPorKm+"\n");
      imprimeCarta();
      System.out.print("\nAperte enter para continuar");
      sc.nextLine();
		  compara(3);
	  }
	  if (expec > area && expec > populacao && expec > pPorKm && expec > renda) {
      System.out.println("\nO computador escolheu Expectativa de vida da carta abaixo:");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Expectativa de vida (anos): "+b.monte2[0].expectativa+"\n");
      imprimeCarta();
      System.out.print("\nAperte enter para continuar");
      sc.nextLine();
		  compara(4);
	  }
	  if (renda > area && renda > populacao && renda > pPorKm && renda > expec) {
      System.out.println("\nO computador escolheu Renda per capita da carta abaixo:");
      System.out.println(b.monte2[0].grupo+"  "+b.monte2[0].nome);
      System.out.println("Renda per capita (US$): "+b.monte2[0].renda+"\n");
      imprimeCarta();
      System.out.print("\nAperte enter para continuar");
      sc.nextLine();
		  compara(5);
	  }
  }

  public void compara(int i){
   switch (i) {
     case 1:
     if (b.monte1[0].area > b.monte2[0].area){
       j1.vez = true;
       pc.vez = false;
	     b.transfereCarta(b.monte1,b.monte2);
     } else if(b.monte1[0].area < b.monte2[0].area){
       pc.vez = true;
       j1.vez = false;
	    b.transfereCarta(b.monte2,b.monte1);
     }
     break;
     case 2:
     if (b.monte1[0].populacao > b.monte2[0].populacao){
       j1.vez = true;
       pc.vez = false;
	      b.transfereCarta(b.monte1,b.monte2);
     } else if(b.monte1[0].populacao < b.monte2[0].populacao){
       pc.vez = true;
       j1.vez = false;
       b.transfereCarta(b.monte2,b.monte1);
     }
     break;
     case 3:
     if (b.monte1[0].pessoasPorKm > b.monte2[0].pessoasPorKm){
       j1.vez = true;
       pc.vez = false;
	     b.transfereCarta(b.monte1, b.monte2);
     } else if (b.monte1[0].pessoasPorKm < b.monte2[0].pessoasPorKm){
       pc.vez = true;
       j1.vez = false;
	     b.transfereCarta(b.monte2, b.monte1);
     }
     break;	     
     case 4:
     if (b.monte1[0].expectativa > b.monte2[0].expectativa){
       j1.vez = true;
       pc.vez = false;
       b.transfereCarta(b.monte1, b.monte2);
     } else if (b.monte1[0].expectativa < b.monte2[0].expectativa){
       pc.vez = true;
       j1.vez = false;
       b.transfereCarta(b.monte2, b.monte1);
     }
     break;
	   case 5:
     if (b.monte1[0].renda > b.monte2[0].renda){
       j1.vez = true;
       pc.vez = false;
       b.transfereCarta(b.monte1, b.monte2);
	   } else if (b.monte1[0].renda < b.monte2[0].renda){
       pc.vez = true;
       j1.vez = false;
       b.transfereCarta(b.monte2, b.monte1);
     }	    
     break;
    }
  }
}

class Baralho{
  Carta[] cartas;
  Carta[] monte1;
  Carta[] monte2;
 
  public Baralho(){
    cartas = new Carta[32];
    monte1 = new Carta[32];
    monte2 = new Carta[32];

	  Carta a1 = new Carta();
	  cartas[0] = a1;
	  cartas[0].nome = "Africa do Sul";
	  cartas[0].grupo = "1A";
	  cartas[0].superTrunfo = false;
	  cartas[0].area = 1221037;
	  cartas[0].populacao = 57.72;
	  cartas[0].pessoasPorKm = 42.4;
	  cartas[0].expectativa = 71;
	  cartas[0].renda = 6459;

	  Carta a2 = new Carta();
	  cartas[1] = a2;
	  cartas[1].nome = "Alemanha";
	  cartas[1].grupo = "2A";
	  cartas[1].superTrunfo = false;
	  cartas[1].area = 357051;
	  cartas[1].populacao = 82;
	  cartas[1].pessoasPorKm = 229.8;
	  cartas[1].expectativa = 81.1;
	  cartas[1].renda = 40996;

	  Carta a3 = new Carta();
	  cartas[2] = a3;
	  cartas[2].nome = "Arábia Saudita";
	  cartas[2].grupo = "3A";
    cartas[2].superTrunfo = false;
	  cartas[2].area = 2149690;
	  cartas[2].populacao = 33.5;
	  cartas[2].pessoasPorKm = 11;
	  cartas[2].expectativa = 73.7;
	  cartas[2].renda = 23239;

	  Carta a4 = new Carta();
	  cartas[3] = a4;
	  cartas[3].nome = "Argentina";
	  cartas[3].grupo = "4A";
	  cartas[3].superTrunfo = false;
	  cartas[3].area = 2780400;
	  cartas[3].populacao = 43.59;
	  cartas[3].pessoasPorKm = 15.6;
	  cartas[3].expectativa = 77.7;
	  cartas[3].renda = 12777;

	  Carta a5 = new Carta();
	  cartas[4] = a5;
	  cartas[4].nome = "Austrália";
	  cartas[4].grupo = "5A";
	  cartas[4].superTrunfo = false;
	  cartas[4].area = 7692024;
	  cartas[4].populacao = 25.08;
	  cartas[4].pessoasPorKm = 2.9;
	  cartas[4].expectativa = 82;
	  cartas[4].renda = 59665;

	  Carta a6 = new Carta();
	  cartas[5] = a6;
	  cartas[5].nome = "Canadá";
	  cartas[5].grupo = "6A";
	  cartas[5].superTrunfo = false;
	  cartas[5].area = 9984670;
	  cartas[5].populacao = 37.24;
	  cartas[5].pessoasPorKm = 3.9;
	  cartas[5].expectativa = 81.8;
	  cartas[5].renda = 47657;

	  Carta a7 = new Carta();
	  cartas[6] = a7;
	  cartas[6].nome = "China";
	  cartas[6].grupo = "7A";
	  cartas[6].superTrunfo = false;
	  cartas[6].area = 9596961;
	  cartas[6].populacao = 1379;
	  cartas[6].pessoasPorKm = 139.6;
	  cartas[6].expectativa = 73.5;
	  cartas[6].renda = 7989;

	  Carta a8 = new Carta();
	  cartas[7] = a8;
	  cartas[7].nome = "Cingapura";
	  cartas[7].grupo = "8A";
	  cartas[7].superTrunfo = false;
	  cartas[7].area = 716;
	  cartas[7].populacao = 5.63;
	  cartas[7].pessoasPorKm = 7540;
	  cartas[7].expectativa = 80.3;
	  cartas[7].renda = 56112;

    Carta b1 = new Carta();
	  cartas[8] = b1;
	  cartas[8].nome = "Brasil SUPER TRUNFO";
	  cartas[8].grupo = "1B";
	  cartas[8].superTrunfo = true;
	  cartas[8].area = 8515767;
	  cartas[8].populacao = 208.49;
	  cartas[8].pessoasPorKm = 23.8;
	  cartas[8].expectativa = 75.8;
	  cartas[8].renda = 10309;

    Carta b2 = new Carta();
	  cartas[9] = b2;
	  cartas[9].nome = "Colômbia";
	  cartas[9].grupo = "2B";
	  cartas[9].superTrunfo = false;
	  cartas[9].area = 1138914;
	  cartas[9].populacao = 48.74;
	  cartas[9].pessoasPorKm = 42.7;
	  cartas[9].expectativa = 72.3;
	  cartas[9].renda = 8030;

    Carta b3 = new Carta();
	  cartas[10] = b3;
	  cartas[10].nome = "Coréia do Sul";
	  cartas[10].grupo = "3B";
	  cartas[10].superTrunfo = false;
	  cartas[10].area = 99720;
	  cartas[10].populacao = 51.44;
	  cartas[10].pessoasPorKm = 507;
	  cartas[10].expectativa = 79.1;
	  cartas[10].renda = 28738;

    Carta b4 = new Carta();
	  cartas[11] = b4;
	  cartas[11].nome = "Egito";
	  cartas[11].grupo = "4B";
	  cartas[11].superTrunfo = false;
	  cartas[11].area = 1002450;
	  cartas[11].populacao = 92.15;
	  cartas[11].pessoasPorKm = 90;
	  cartas[11].expectativa = 73.2;
	  cartas[11].renda = 3336;

    Carta b5 = new Carta();
	  cartas[12] = b5;
	  cartas[12].nome = "Espanha";
	  cartas[12].grupo = "5B";
	  cartas[12].superTrunfo = false;
	  cartas[12].area = 504030;
	  cartas[12].populacao = 46.52;
	  cartas[12].pessoasPorKm = 92.1;
	  cartas[12].expectativa = 82.8;
	  cartas[12].renda = 25864;

    Carta b6 = new Carta();
	  cartas[13] = b6;
	  cartas[13].nome = "Etiópia";
	  cartas[13].grupo = "6B";
	  cartas[13].superTrunfo = false;
	  cartas[13].area = 1104300;
	  cartas[13].populacao = 91.19;
	  cartas[13].pessoasPorKm = 75;
	  cartas[13].expectativa = 56;
	  cartas[13].renda = 547;

    Carta b7 = new Carta();
	  cartas[14] = b7;
	  cartas[14].nome = "Estados Unidos";
	  cartas[14].grupo = "7B";
	  cartas[14].superTrunfo = false;
	  cartas[14].area = 9371175;
	  cartas[14].populacao = 325.71;
	  cartas[14].pessoasPorKm = 33;
	  cartas[14].expectativa = 77.8;
	  cartas[14].renda = 59501;

    Carta b8 = new Carta();
	  cartas[15] = b8;
	  cartas[15].nome = "França";
	  cartas[15].grupo = "7B";
	  cartas[15].superTrunfo = false;
	  cartas[15].area = 543965;
	  cartas[15].populacao = 67.34;
	  cartas[15].pessoasPorKm = 105;
	  cartas[15].expectativa = 82.5;
	  cartas[15].renda = 45383;

    Carta c1 = new Carta();
	  cartas[16] = c1;
	  cartas[16].nome = "Índia";
	  cartas[16].grupo = "1C";
	  cartas[16].superTrunfo = false;
	  cartas[16].area = 3287590;
	  cartas[16].populacao = 1281;
	  cartas[16].pessoasPorKm = 329;
	  cartas[16].expectativa = 64.9;
	  cartas[16].renda = 1617;

    Carta c2 = new Carta();
	  cartas[17] = c2;
	  cartas[17].nome = "Indonésia";
	  cartas[17].grupo = "2C";
	  cartas[17].superTrunfo = false;
	  cartas[17].area = 1904569;
	  cartas[17].populacao = 260.58;
	  cartas[17].pessoasPorKm = 126;
	  cartas[17].expectativa = 71;
	  cartas[17].renda = 3403;

    Carta c3 = new Carta();
	  cartas[18] = c3;
	  cartas[18].nome = "Israel";
	  cartas[18].grupo = "3C";
	  cartas[18].superTrunfo = false;
	  cartas[18].area = 20770;
	  cartas[18].populacao = 8.86;
	  cartas[18].pessoasPorKm = 368.5;
	  cartas[18].expectativa = 82.1;
	  cartas[18].renda = 42115;

    Carta c4 = new Carta();
	  cartas[19] = c4;
	  cartas[19].nome = "Itália";
	  cartas[19].grupo = "4C";
	  cartas[19].superTrunfo = false;
	  cartas[19].area = 301338;
	  cartas[19].populacao = 60.66;
	  cartas[19].pessoasPorKm = 201.1;
	  cartas[19].expectativa = 83.1;
	  cartas[19].renda = 35913;

    Carta c5 = new Carta();
	  cartas[20] = c5;
	  cartas[20].nome = "Japão";
	  cartas[20].grupo = "5C";
	  cartas[20].superTrunfo = false;
	  cartas[20].area = 377873;
	  cartas[20].populacao = 126.44;
	  cartas[20].pessoasPorKm = 337;
	  cartas[20].expectativa = 87;
	  cartas[20].renda = 32485;

    Carta c6 = new Carta();
	  cartas[21] = c6;
	  cartas[21].nome = "México";
	  cartas[21].grupo = "6C";
	  cartas[21].superTrunfo = false;
	  cartas[21].area = 1958201;
	  cartas[21].populacao = 123.67;
	  cartas[21].pessoasPorKm = 55;
	  cartas[21].expectativa = 77.2;
	  cartas[21].renda = 10021;

    Carta c7 = new Carta();
	  cartas[22] = c7;
	  cartas[22].nome = "Nicarágua";
	  cartas[22].grupo = "7C";
	  cartas[22].superTrunfo = false;
	  cartas[22].area = 129494;
	  cartas[22].populacao = 6.16;
	  cartas[22].pessoasPorKm = 39;
	  cartas[22].expectativa = 72.9;
	  cartas[22].renda = 2126;

    Carta c8 = new Carta();
	  cartas[23] = c8;
	  cartas[23].nome = "Nigéria";
	  cartas[23].grupo = "8C";
	  cartas[23].superTrunfo = false;
	  cartas[23].area = 923768;
	  cartas[23].populacao = 199.31;
	  cartas[23].pessoasPorKm = 188.9;
	  cartas[23].expectativa = 53;
	  cartas[23].renda = 2244;

    Carta d1 = new Carta();
	  cartas[24] = d1;
	  cartas[24].nome = "Polônia";
	  cartas[24].grupo = "1D";
	  cartas[24].superTrunfo = false;
	  cartas[24].area = 312679;
	  cartas[24].populacao = 38.42;
	  cartas[24].pessoasPorKm = 122;
	  cartas[24].expectativa = 77.5;
	  cartas[24].renda = 16179;

    Carta d2 = new Carta();
	  cartas[25] = d2;
	  cartas[25].nome = "Portugal";
	  cartas[25].grupo = "2D";
	  cartas[25].superTrunfo = false;
	  cartas[25].area = 92256;
	  cartas[25].populacao = 10.37;
	  cartas[25].pessoasPorKm = 115.3;
	  cartas[25].expectativa = 78.6;
	  cartas[25].renda = 23976;

    Carta d3 = new Carta();
	  cartas[26] = d3;
	  cartas[26].nome = "Reino Unido";
	  cartas[26].grupo = "3D";
	  cartas[26].superTrunfo = false;
	  cartas[26].area = 244820;
	  cartas[26].populacao = 63.18;
	  cartas[26].pessoasPorKm = 255.6;
	  cartas[26].expectativa = 81;
	  cartas[26].renda = 44141;

    Carta d4 = new Carta();
	  cartas[27] = d4;
	  cartas[27].nome = "Rússia";
	  cartas[27].grupo = "4D";
	  cartas[27].superTrunfo = false;
	  cartas[27].area = 17124442;
	  cartas[27].populacao = 144.52;
	  cartas[27].pessoasPorKm = 8.3;
	  cartas[27].expectativa = 70.5;
	  cartas[27].renda = 7742;

    Carta d5 = new Carta();
	  cartas[28] = d5;
	  cartas[28].nome = "Serra Leoa";
	  cartas[28].grupo = "5D";
	  cartas[28].superTrunfo = false;
	  cartas[28].area = 71740;
	  cartas[28].populacao = 6.1;
	  cartas[28].pessoasPorKm = 83;
	  cartas[28].expectativa = 57.3;
	  cartas[28].renda = 505;

    Carta d6 = new Carta();
	  cartas[29] = d6;
	  cartas[29].nome = "Suécia";
	  cartas[29].grupo = "6D";
	  cartas[29].superTrunfo = false;
	  cartas[29].area = 449964;
	  cartas[29].populacao = 10.17;
	  cartas[29].pessoasPorKm = 20;
	  cartas[29].expectativa = 83;
	  cartas[29].renda = 58345;

    Carta d7 = new Carta();
	  cartas[30] = d7;
	  cartas[30].nome = "Suiça";
	  cartas[30].grupo = "7D";
	  cartas[30].superTrunfo = false;
	  cartas[30].area = 41285;
	  cartas[30].populacao = 8.5;
	  cartas[30].pessoasPorKm = 186;
	  cartas[30].expectativa = 82.8;
	  cartas[30].renda = 80837;

    Carta d8 = new Carta();
	  cartas[31] = d8;
	  cartas[31].nome = "Turquia";
	  cartas[31].grupo = "8D";
	  cartas[31].superTrunfo = false;
	  cartas[31].area = 783562;
	  cartas[31].populacao = 75.62;
	  cartas[31].pessoasPorKm = 96.5;
	  cartas[31].expectativa = 74.4;
	  cartas[31].renda = 1053;
	}

	public List <Integer> embaralha(){
	  List<Integer> numeros = new ArrayList<Integer>();
	  for (int i=0; i<cartas.length; i++){
	    numeros.add(i);
	  }
	  Collections.shuffle(numeros);
	  return numeros;
	}

	public void distribui(List<Integer> numeros){
	  for(int i=0, aux1=0,aux2=0;i<cartas.length;i++){
	    if (i % 2 == 0){
	      monte2[aux1] = cartas[numeros.get(i)];
	      aux1++;
	    } else {
	      monte1[aux2] = cartas[numeros.get(i)];
	      aux2++;
	    }
	  }
	}

	public void limpaTela(){
	  for (int i=0;i<50;i++){
	    System.out.println();
	  }
	}

	public int contaCarta(Carta a[]){
	  int cont = 0;
	  for (int i=0; i<a.length; i++){
	    if (a[i] != null){
	      cont++;
	    }
	  }
	  return cont;
	}

	public void transfereCarta(Carta ganhador[],Carta perdedor[]){
	  Carta aux1 = ganhador[0];
	  Carta aux2 = perdedor[0];
	  for (int i=0;i<ganhador.length;i++){
	    if (ganhador[i] != null){
	      ganhador[i] = ganhador[i+1];
	    }
	  }
	  ganhador[contaCarta(ganhador)] = aux2;
	  ganhador[contaCarta(ganhador)] = aux1;
	  for (int i=0;i<perdedor.length;i++){
	    if (perdedor[i] != null){
	      perdedor[i] = perdedor[i+1];
	    }
	  }
	}

	public boolean ganhador(){
	  if (contaCarta(monte1) == 0 || contaCarta(monte2) == 0){
	    return true;
	  }
	  else{
	    return false;
	  }
	}
}

class Carta{
  String nome;
  String grupo;
  boolean superTrunfo;
  int area;
  double populacao;
  double pessoasPorKm;
  double expectativa;
  int renda;

  public Carta(){
    this.nome = "";
    this.grupo = "";
    this.superTrunfo = false;
    this.area = 0;
    this.populacao = 0;
    this.pessoasPorKm = 0;
    this.expectativa = 0;
    this.renda = 0;
	}
}
