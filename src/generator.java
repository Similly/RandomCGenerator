import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class generator {

    private static Random r = new Random();

    public static void main(String[] args){

        String code = prog();
        //System.out.println(code);

        try {
            File file = new File("generatedFile.cpp");
            FileOutputStream fos = new FileOutputStream(file);

            byte[] codeInBytes = code.getBytes();

            fos.write(codeInBytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String prog(){
        return "int main() { \n" + stat_list() + "return 0; }";
    }

    private static String stat_list(){
        int nextStep = r.nextInt(2);

        String statList = "";

        statList = statList + stat();

        if (nextStep == 1){
            statList = statList + stat_list();
        }
        return statList;
    }

    private static String stat(){
        int nextStep = r.nextInt(100);
        String statement = "";

        if(nextStep >= 0 && nextStep <= 9){
            statement = cmpd_stat() + "\n";
        } else if(nextStep >= 10 && nextStep <= 24){
            statement = if_stat() + "\n";
        } else if(nextStep >= 25 && nextStep <= 39){
            statement = iter_stat() + "\n";
        } else if(nextStep >= 40 && nextStep <= 69){
            statement = assign_stat() + "\n";
        } else if(nextStep >= 70 && nextStep <= 99){
            statement = decl_stat() + "\n";
        }
        return statement;
    }

    private static String cmpd_stat(){
        return "{ \n" + stat_list() + "}";
    }

    private static String if_stat(){
        int nextStep = r.nextInt(6);
        String ifstat = "";

        switch(nextStep){
            case 0:
                ifstat = "if ( " + exp() + " )\n" + stat();
                break;
            case 1:
                ifstat = "if ( " + exp() + " )" + cmpd_stat();
                break;
            case 2:
                ifstat = "if ( " + exp() + " )\n" + stat() + "else " + stat();
                break;
            case 3:
                ifstat = "if ( " + exp() + " )" + cmpd_stat() + "else " + stat();
                break;
            case 4:
                ifstat = "if ( " + exp() + " )\n" + stat() + "else " + cmpd_stat();
                break;
            case 5:
                ifstat = "if ( " + exp() + " )" + cmpd_stat() + "else " + cmpd_stat();
                break;
        }

        return ifstat;
    }

    private static String iter_stat(){
        int nextStep = r.nextInt(2);
        String iterstat = "";

        switch(nextStep){
            case 0:
                iterstat = "while ( " + exp() + " ) \n" + stat();
                break;
            case 1:
                iterstat = "while ( " + exp() + " ) " + cmpd_stat();
                break;
        }

        return iterstat;
    }

    private static String assign_stat(){
        return id() + " = " + exp() + ";";
    }

    private static String exp() {
        int nextStep = r.nextInt(3);
        String exp = "";

        switch (nextStep){
            case 0:
                exp = exp() + op() + exp();
                break;
            case 1:
                exp = id();
                break;
            case 2:
                exp = constant();
                break;
        }

        return exp;
    }

    private static String constant() {
        return digit() + digit_sequence();
    }

    private static String op() {
        int nextStep = r.nextInt(4);
        String op = "";

        switch(nextStep){
            case 0:
                op = " + ";
                break;
            case 1:
                op = " - ";
                break;
            case 2:
                op = " * ";
                break;
            case 3:
                op = " / ";
                break;
        }

        return op;
    }

    private static String decl_stat(){
        int nextStep = r.nextInt(2);
        String declstat = "";

        switch (nextStep){
            case 0:
                declstat = type() + " " + id() + ";";
                break;
            case 1:
                declstat = type() + " " + assign_stat();
                break;
        }

        return declstat;
    }

    private static String id() {
        return character() + chardigit_sequence();
    }

    private static String type() {
        int nextStep = r.nextInt(2);
        String type = "";

        switch(nextStep){
            case 0:
                type = "int ";
                break;
            case 1:
                type = "double ";
                break;
        }

        return type;
    }

    private static String chardigit_sequence() {
        int nextStep = r.nextInt(3);
        String chardigitsequence = "";

        switch(nextStep){
            case 0:
                chardigitsequence = "";
                break;
            case 1:
                chardigitsequence = character() + chardigit_sequence();
                break;
            case 2:
                chardigitsequence = digit() + chardigit_sequence();
                break;
        }

        return chardigitsequence;
    }

    private static String digit_sequence(){
        int nextStep = r.nextInt(2);
        String digitsequence = "";

        switch (nextStep){
            case 0:
                digitsequence = "";
                break;
            case 1:
                digitsequence = digit() + digit_sequence();
                break;
        }

        return digitsequence;
    }

    private static String character(){
        String characterAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
        int nextStep = r.nextInt(characterAlphabet.length());

        return Character.toString(characterAlphabet.charAt(nextStep));
    }

    private static String digit(){
        String digitAlphabet = "1234567890";
        int nextStep = r.nextInt(digitAlphabet.length());

        return Character.toString(digitAlphabet.charAt(nextStep));
    }
}