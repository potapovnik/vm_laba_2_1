public class Starter {
    public static void main(String[] args) {
        Rang test=new Rang();
        int err=test.calculateMatrixTriangular();
        /*if (err==1){
            System.out.print("код ошибки:1-деление на ноль");
            System.exit(1);
        }*/
        System.out.println("Ранг матрицы:"+test.calculateRang()+"   код ошибки:0");
        test.showMatrix();
        System.exit(0);
    }
}
