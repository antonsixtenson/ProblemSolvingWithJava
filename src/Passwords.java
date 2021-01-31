import java.util.Random;

/*
*   PROBLEM:
*   Create a password-generator that generates a password
*   containing lower-case letter, upper-case letter and digit.
*   Passwords is considered "Passed" when they contain at least
*   one of each above mentioned chars. (one lower-case, one upper-case, one digit).
*
*/


public class Passwords {
    int len;
    String alphL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphS = "abcdefghijklmnopqrstuvwxyz";
    String dig = "1234567890";
    char [] digChars = dig.toCharArray();
    char [] alphCharsL = alphL.toCharArray();
    char [] alphCharsS = alphS.toCharArray();
    char [][] fullArr = {digChars, alphCharsS, alphCharsL};
    Random rand = new Random();

    // Will always gen "passed" password if len > 2
    public char[] generatePasswordModulo(int len){

        char [] pwd = new char[len];

        for(int i = 0; i < len; i++){
            pwd[i] = fullArr[i % fullArr.length][rand.nextInt((fullArr[i % fullArr.length].length) - 1)];
        }

        return pwd;
    }

    public char[] genPasswordRandom(int len){
        char [] pwd = new char[len];

        for(int i = 0; i < len; i++){
            int randomNum = rand.nextInt(3);
            pwd[i] = fullArr[randomNum][rand.nextInt(fullArr[randomNum].length)];
        }

        return pwd;

    }

    public boolean testPassword(char[] pwd){
        boolean num = false;
        boolean upper = false;
        boolean lower = false;

        for(char c : pwd){
            if(Character.isDigit(c)){
                num = true;
            }
            else if(Character.isUpperCase(c)){
                upper = true;
            }
            else{
                lower = true;
            }
        }

        if(num && upper && lower){
            return true;
        }
        return false;
    }

    public float genAndTestModPass(int n, int len){
        int passed = 0;
        for(int i = 0; i < n; i++){
            char [] pwd = generatePasswordModulo(len);
            if(testPassword(pwd)){
                passed++;
            }
        }
        return passed/(float)n * 100;
    }

    public float genAndTestRandPass(int n, int len){
        int passed = 0;
        for(int i = 0; i < n; i++){
            char [] pwd = genPasswordRandom(len);
            if(testPassword(pwd)){
                passed++;
            }
        }

        return passed/(float)n * 100;
    }

    // lastInput: -1 = none(start), 0 = digit, 1 = lower letter, 2 = upper letter
    public char[] genPasswordAlg(int len){
        char [] pwd = new char[len];
        int lastInput = -1;
        for(int i = 0; i < len; i++){
            if(lastInput == -1){
                int randomNum = rand.nextInt(3);
                lastInput = randomNum;
                pwd[i] = fullArr[randomNum][rand.nextInt(fullArr[randomNum].length)];
            } else if(lastInput == 0){
                int randomNum = rand.nextInt(2) + 1;
                lastInput = randomNum;
                pwd[i] = fullArr[randomNum][rand.nextInt(fullArr[randomNum].length)];
            } else if(lastInput == 1){
                int randomNum;
                while(true) {
                    randomNum = rand.nextInt(3);
                    if(randomNum == 0 || randomNum == 2){
                        break;
                    }
                }
                lastInput = randomNum;
                pwd[i] = fullArr[randomNum][rand.nextInt(fullArr[randomNum].length)];
            } else if(lastInput == 2){
                int randomNum = rand.nextInt(2);
                lastInput = randomNum;
                pwd[i] = fullArr[randomNum][rand.nextInt(fullArr[randomNum].length)];
            }
        }
        return pwd;
    }

    public float genAndTestAlgPass(int n, int len){
        int passed = 0;
        for(int i = 0; i < n; i++){
            char [] pwd = genPasswordAlg(len);
            if(testPassword(pwd)){
                passed++;
            }
        }

        return passed/(float)n * 100;
    }

    private char[] genRandomPasswordTwo(int len){
        char [] allChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        char [] pwd = new char[len];
        for(int i = 0; i < len; i++){
            pwd[i] = allChars[rand.nextInt(62)];
        }
        return pwd;
    }

    public float genAndTestRandTwoPass(int n, int len){
        int passed = 0;
        for(int i = 0; i < n; i++){
            char [] pwd = genRandomPasswordTwo(len);
            if(testPassword(pwd)){
                passed++;
            }
        }

        return passed/(float)n * 100;
    }

    public static void main(String[] args) {
        Passwords pwd = new Passwords();
        long start0 = System.currentTimeMillis();
        System.out.println("Passed passwords generated with Modulus: " + pwd.genAndTestModPass(100, 10) + "%");
        long end0 = System.currentTimeMillis();
        long total0 = end0 - start0;
        System.out.println("Time consumed with modulus gen: " + total0 + "ms");
        long start1 = System.currentTimeMillis();
        System.out.println("Passed passwords generated with Random: " + pwd.genAndTestRandPass(100, 10) + "%");
        long end1 = System.currentTimeMillis();
        long total1 = end1 - start1;
        System.out.println("Time consumed with random gen: " + total1 + "ms");
        long start2 = System.currentTimeMillis();
        System.out.println("Passed passwords generated with Random-Algorithm: " + pwd.genAndTestAlgPass(100, 10)  + "%");
        long end2 = System.currentTimeMillis();
        long total2 = end2 - start2;
        System.out.println("Time consumed with  random-alg gen: " + total2 + "ms");
        long start3 = System.currentTimeMillis();
        System.out.println("Passed passwords generated with Random-Two: " + pwd.genAndTestRandTwoPass(100, 10) + "%");
        long end3 = System.currentTimeMillis();
        long total3 = end3 - start3;
        System.out.println("Time consumed with  random-two gen: " + total3 + "ms");


    }
}
