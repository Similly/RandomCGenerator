import java.util.Random;

public class generator {

    static Random r = new Random();
    static String characterAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    static String digitAlphabet = "1234567890";

    public static void main(String args[]){
        System.out.println(prog());
    }

    static String prog(){
        String program = "int main() { \n" + stat_list() + "return 0; }";
        return program;
    }

    static String stat_list(){
        int nextStep = r.nextInt(2);

        String statList = "";

        statList = statList + stat();

        if (nextStep == 1){
            statList = statList + stat_list();
        }
        return statList;
    }

    static String stat(){
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

    static String cmpd_stat(){
        return "{ \n" + stat_list() + " }";
    }

    static String if_stat(){
        int nextStep = r.nextInt(6);
        String ifstat = "";

        switch(nextStep){
            case 0:
                ifstat = "if ( " + exp() + " )" + stat();
                break;
            case 1:
                ifstat = "if ( " + exp() + " )" + cmpd_stat();
                break;
            case 2:
                ifstat = "if ( " + exp() + " )" + stat() + " else " + stat();
                break;
            case 3:
                ifstat = "if ( " + exp() + " )" + cmpd_stat() + " else " + stat();
                break;
            case 4:
                ifstat = "if ( " + exp() + " )" + stat() + " else " + cmpd_stat();
                break;
            case 5:
                ifstat = "if ( " + exp() + " )" + cmpd_stat() + " else " + cmpd_stat();
                break;
        }

        return ifstat;
    }

    static String iter_stat(){
        int nextStep = r.nextInt(2);
        String iterstat = "";

        switch(nextStep){
            case 0:
                iterstat = "while ( " + exp() + " ) " + stat();
                break;
            case 1:
                iterstat = "while ( " + exp() + " ) " + cmpd_stat();
                break;
        }

        return iterstat;
    }

    static String assign_stat(){
        return id() + " = " + exp() + ";";
    }

    static String exp() {
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

    static String op() {
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

    static String decl_stat(){
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

    static String id() {
        return character() + chardigit_sequence();
    }

    static String type() {
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

    static String chardigit_sequence() {
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

    static String digit_sequence(){
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

    static String character(){
        int nextStep = r.nextInt(characterAlphabet.length());
        String character = Character.toString(characterAlphabet.charAt(nextStep));

        return character;
    }

    static String digit(){
        int nextStep = r.nextInt(digitAlphabet.length());
        String digit = Character.toString(digitAlphabet.charAt(nextStep));

        return digit;
    }
}
