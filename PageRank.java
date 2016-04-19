import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PageRank{

    public double[] matriz(double[] matriz1){

        File dir = new File("/Files");
        String[] ficheros = dir.list();

        double sumPR = 0.0; int sumC =0;
        for (int archivo=0;archivo<=ficheros.length;archivo++){
        //System.out.println(ficheros[x]);
        //matriz1[archivo][0]= archivo;
        try{
            // Abrimos el archivo

            FileInputStream fstream = new FileInputStream("C:\\Users\\Angel Sierra\\Documents\\11Cuatri\\Avanzadas\\SEGUNTOCORTE\\pageRank\\src\\java\\Clases\\Pages\\"+ficheros[archivo]+"");
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
            // Leer el archivo linea por linea

            while ((strLinea = buffer.readLine()) != null)   {
                // Imprimimos la linea por pantalla

                if(strLinea.contains("<a href=\"index")){
                    char[] numero = strLinea.toCharArray();
                      sumPR = sumPR + matriz1[Integer.parseInt(""+numero[22])];
                      sumC ++;


                    }
            }
             float d = (float) 0.98;
             matriz1[archivo+1] = (1-d)+ d* (sumPR/sumC);

            // Cerramos el archivo
            entrada.close();
        }catch (Exception e){ //Catch de excepciones
            System.err.println("Ocurrio un error: " + e.getMessage());
        }


      }


        return  matriz1;
}

     public String[] setPages(String[] archivos){

         File dir = new File("Files/");
        String[] ficheros = dir.list();


        for (int archivo=0;archivo<ficheros.length;archivo++){
        //System.out.println(ficheros[x]);
       // matriz1[archivo][0]= archivo;
       archivos[archivo] = ficheros[archivo];

      }

        return archivos;
 }

 public int getidPage(String indexPage){
        int id = 0;

         File dir = new File("Files/");
        String[] ficheros = dir.list();


        for (int archivo=0;archivo<ficheros.length;archivo++){
        //System.out.println(ficheros[archivo]);
        //matriz1[archivo][0]= archivo;
        if(ficheros[archivo].equals(indexPage)){
        id=archivo;
        }

      }

        return id;
 }
public String[] getlisttoMe(String indexPage){

    int id = 0;
    int i=0;
    Document doc;

    File dir = new File("Files/");
    String[] ficheros = dir.list();
    String[] nombres=new String[ficheros.length];


    for (int archivo=0;archivo<ficheros.length;archivo++){
      File input = new File("Files/"+ficheros[archivo]);
      try{
      doc = Jsoup.parse(input, "UTF-8");


          // get page title
          String title = doc.title();

          //get all links

          Elements links = doc.select("a[href]");
          for(Element link : links){
            String ref=link.attr("href");
            if(ref.equals(indexPage)){
              nombres[i]=title+".html";
              i++;
            }
          }



        }catch(Exception e){}

  }

  String[] links = new String[i];

  for (int j=0;j<i ;j++ ) {
    links[j]=nombres[j];
  }



  return links;
}


 public int getcontPage(String indexPage){
   Document doc;
   int sumC=0;
   File input = new File("Files/"+indexPage);
   try{
   doc = Jsoup.parse(input, "UTF-8");


       // get page title
       String title = doc.title();

       //get all links

       Elements links = doc.select("a[href]");
       for(Element link : links){
         sumC++;
       }
     }catch(Exception e){}

        return sumC;
 }

 public double[] getRank(String page,  double[] matriz1){

        Document doc;
        double sumPR = 0.0; int sumC =0;
        double sumatoria=0;
	       try {

		// need http protocol
    File input = new File("Files/"+page);
    doc = Jsoup.parse(input, "UTF-8");


		// get page title
		String title = doc.title();
    System.out.println(title);
    String[] links = getlisttoMe(page);

		//get all links



    for (int i=0;i<links.length;i++) {

			   // get the value from href attribute
          // System.out.println("\nlink : " + link.attr("href"));
          // System.out.println("text : " + link.text());
          System.out.println("la pagina que apunta hacia mi es :"+links[i]);
          int thisId = getidPage(links[i]);
          //System.out.println("el valor del id de la pagina es"+thisId);
          int thisCont=getcontPage(links[i]);
          System.out.println(matriz1[thisId]+"/"+thisCont);
          sumatoria+=((double)matriz1[thisId]/(double)thisCont);
          System.out.println("La sumatoria es : "+sumatoria);

     }
                float d = (float) 0.85;
                int pageId = getidPage(page);
             matriz1[pageId] = (1-d) + d * sumatoria;
	} catch (IOException e) {
		e.printStackTrace();
	}
        return matriz1;
  }

 public String getTittle(String page){
        Document doc;

          String tittle1 = null;
	try {

		// need http protocol
		doc = Jsoup.connect("http://localhost:8080/pageRank/view/Pages/"+page+"").get();

		// get page title
		 tittle1 = doc.title();


		// get all links

	} catch (IOException e) {
		e.printStackTrace();
	}
        return tittle1;
  }


        public String[] ordenar(double[] matriz1, String[] archivos){

        //Usamos un bucle anidado
        for(int i=0;i<(matriz1.length-1);i++){
            for(int j=i+1;j<matriz1.length;j++){
                if(matriz1[i]>matriz1[j]){
                    //Intercambiamos valores
                    double variableauxiliar=matriz1[i];
                    String auxiliar =archivos[i];
                    matriz1[i]=matriz1[j];
                    archivos[i]=archivos[j];
                    matriz1[j]=variableauxiliar;
                    archivos[j]= auxiliar;

                }
            }
        }
        return archivos;
        }

}
