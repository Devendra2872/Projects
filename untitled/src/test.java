public class test {
    public static void main(String[] args){
        char[][] s = new char[3][3];

        for (int i = 0; i<=2; i++){
            for (int j=0; j<=2; j++){
                s[i][j] = 'x';
                System.out.print(s[i][j]);
            }
            System.out.println();
        }
    }
}
