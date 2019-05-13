import java.util.*;

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
        j.compara(c);
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

  public void escolhaPc() {
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
		  compara(1);
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
     } else if (b.monte1[0].pessoasPorKm < b.monte2[0].pessoasPorKm){
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
    cartas = new Carta[8];
    monte1 = new Carta[8];
    monte2 = new Carta[8];

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
  String grupo;//talvez separar;
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
