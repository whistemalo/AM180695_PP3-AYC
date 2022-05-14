import java.io.PrintStream;
import java.util.Hashtable;
import java.lang.String;
import java.util.ArrayList;

class TokenAsignaciones
{
    //variable para validar asignaciones a caracteres(ichr)
    public static int segunda = 0;
    //tabla que almacenara los tokens declarados
    private static Hashtable tabla = new Hashtable();
    //listas que guardaran los tipos compatibles de las variables
    private static ArrayList<Integer> intComp = new ArrayList();
    private static ArrayList<Integer> decComp = new ArrayList();
    private static ArrayList<Integer> strComp = new ArrayList();
    private static ArrayList<Integer> chrComp = new ArrayList();
                                            //variable         //tipoDato
    public static void InsertarSimbolo (Token identificador, int tipo)
    {
        //en este metodo se agrega a la tabla de tokens el identificador que esta siendo declarado junto con su tipo de dato
        tabla.put(identificador.image, tipo);
    }
    public static void SetTables()
    {
        /*en este metodo se inicializan las tablas, las cuales almacenaran los tipos de datos compatibles con:
        entero= intcomp     decimal=deccomp     cadena=strcomp      caracter=chrcomp
        */
        intComp.add(44);
        intComp.add(48);

        decComp.add(44);
        decComp.add(45);
        decComp.add(48);
        decComp.add(50);

        chrComp.add(46);
        chrComp.add(52);

        strComp.add(47);
        strComp.add(51);
    }

    public static String checkAsing(Token TokenIzq, Token TokenAsig)
    {
        //variables en las cuales se almacenara el tipo de dato del identificador y de las asignaciones (ejemplo: n1(tipoIdent2)+ 3(tipoIdent2))
        int tipoIdent1;
        int tipoIdent2;

        if(TokenIzq.kind !=48 && TokenIzq.kind!=50)
        {
            try {
                tipoIdent1 = (Integer)tabla.get(TokenIzq.image);
            } catch (Exception e) {
                return "Error: El identificador "+ TokenIzq.image + " no ha sido declarado \r\nLinea: "+ TokenIzq.beginLine;
            }
        }
        else
            tipoIdent1=0;

        if (TokenAsig.kind ==49) {
            try {
                tipoIdent2=(Integer)tabla.get(TokenAsig.image);
            } catch (Exception e) {
                return "Error: el identificador "+ TokenAsig.image +" no ha sido declarado \r\nLinea: "+TokenIzq.beginLine;
            }
        } 
        else if(TokenAsig.kind==48 || TokenAsig.kind==50 || TokenAsig.kind==51 || TokenAsig.kind==52) 
            tipoIdent2= TokenAsig.kind;
        else 
            tipoIdent2=0;

        if (tipoIdent1==44) //int
        {
            //si la lista de enteros contiene el valor de tipoindent2, entonces es compatible y se puede hacer la asignacion
            if (intComp.contains(tipoIdent2)) 
            {
                return " ";                
            } 
            else 
            {
                return "Error: no se puede convertir "+ TokenAsig.image + " a entero \r\nLinea: "+ TokenIzq.beginLine;
            }            
        } 
        else if (tipoIdent1==45) 
        {
            if (decComp.contains(tipoIdent2)) 
            {
                return " ";                
            } 
            else 
            {
                return "Error: no se puede convertir "+ TokenAsig.image + " a decimal \r\nLinea: "+ TokenIzq.beginLine;    
            }            
        } 
        else if (tipoIdent1==46) 
        {
            segunda++;
            if (segunda<2)
            {
                if (chrComp.contains(tipoIdent2)) 
                {
                    return " ";                    
                } 
                else
                return "Error: no se puede convertir "+ TokenAsig.image + " a caracter \r\nLinea: "+ TokenIzq.beginLine;          
            } 
            else 
                return "Error: no se puede asignar mas de un valor a un caracter \r\nLinea: "+TokenIzq.beginLine;
        } 
        else if (tipoIdent1==47) 
        {
            if (strComp.contains(tipoIdent2)) 
            {
                return " ";    
            } else 
            return "Error: no se puede convertir "+ TokenAsig.image + " a cadena \r\nLinea: "+ TokenIzq.beginLine;
        } 
        else 
        {
            return "El identificador "+TokenIzq.image + " no ha sido declarado " + " Linea : "+ TokenIzq.beginLine;
        }
    }
    public static String checkVariable(Token checkTok)
    {
        try {
            //intenta obtener el token a verificar(checktok) de la tabla de los tokens
            int tipoIdent1=(Integer)tabla.get(checkTok.image);
            return " ";
        } 
        catch (Exception e) 
        {
            //si no lo puede obtener, manda el error
            return "Error: El identificador "+checkTok.image+" no ha sido declarado \r\nLinea: "+checkTok.beginLine;
        }
    }
}
