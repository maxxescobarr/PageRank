public class Main{

  public static void main(String[] args) {
      PageRank pg =  new PageRank();
      double [] matriz1 = new double[5];

      matriz1[0]= 1;
      matriz1[1]= 1;
      matriz1[2]= 1;
      matriz1[3]= 1;
      matriz1[4]= 1;


      matriz1 = pg.getRank("test1.html", matriz1);
      matriz1 = pg.getRank("test2.html", matriz1);
      matriz1 = pg.getRank("test3.html", matriz1);
      matriz1 = pg.getRank("test4.html", matriz1);
      matriz1 = pg.getRank("test5.html", matriz1);

      // String[] archivos = new String[9];
      // archivos= pg.setPages(archivos);
      // archivos =  pg.ordenar(matriz1, archivos);
      //
      // String palabra = request.getParameter("palabra");
      System.out.println("El page rank de test5 es : "+matriz1[pg.getidPage("test5.html")]);
      System.out.println("El page rank de test4 es : "+matriz1[pg.getidPage("test4.html")]);
      System.out.println("El page rank de test3 es : "+matriz1[pg.getidPage("test3.html")]);
      System.out.println("El page rank de test2 es : "+matriz1[pg.getidPage("test2.html")]);
      System.out.println("El page rank de test1 es : "+matriz1[pg.getidPage("test1.html")]);

      //out.println(matriz1[5]);
      // out.println(matriz1[6]);
      // out.println(matriz1[7]);
      // out.println(matriz1[8]);
  }

}