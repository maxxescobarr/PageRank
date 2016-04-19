import java.util.*;
import java.io.*;
public class Main{
      ArrayList<String> contentFolder = new ArrayList<String>();

      public void listFilesForFolder(final File folder) {
            for (final File fileEntry : folder.listFiles()) {
                  if (fileEntry.isDirectory()) {
                        listFilesForFolder(fileEntry);
                  }else {
                       // System.out.println(fileEntry.getName());
                        contentFolder.add(fileEntry.getName());
                  }
            }
      }

      public void printList(){
            System.out.println(contentFolder);
      }

      public int sizeList(){
            return contentFolder.size();
      }

      public static void main(String[] args) {
            Main a = new Main();
            final File folder = new File("Files/");
            a.listFilesForFolder(folder);
           // a.printList();
            PageRank pg =  new PageRank();
            double [] matriz1 = new double[a.sizeList()];
            for(int i =0; i < a.sizeList(); i++){
                  matriz1[i] = 1;
            }

            for(int i =0; i < matriz1.length; i++){
                  matriz1 = pg.getRank(a.contentFolder.get(i), matriz1);
                  System.out.println("El page rank de "+a.contentFolder.get(i)+" es: "+matriz1[pg.getidPage(a.contentFolder.get(i))]);
            }

/*
      matriz1 = pg.getRank("test1.html", matriz1);
      System.out.println("El page rank de test1 es : "+matriz1[pg.getidPage("test1.html")]);

      matriz1 = pg.getRank("test2.html", matriz1);
      System.out.println("El page rank de test2 es : "+matriz1[pg.getidPage("test2.html")]);

      matriz1 = pg.getRank("test3.html", matriz1);
      System.out.println("El page rank de test3 es : "+matriz1[pg.getidPage("test3.html")]);

      matriz1 = pg.getRank("test4.html", matriz1);
      System.out.println("El page rank de test4 es : "+matriz1[pg.getidPage("test4.html")]);

      matriz1 = pg.getRank("test5.html", matriz1);
      System.out.println("El page rank de test5 es : "+matriz1[pg.getidPage("test5.html")]);*/

  }

}
