import java.io.*;
import java.util.*;

public class transition_probability_matrix {
    public static int previous_key = 100;

    public static void print_matrix(int[][] matrix){
        for (int i = 0; i < 20; i++) {
            float total_row = 0;
            for (int j = 0; j < 20; j++) {
                total_row += matrix[i][j];
            }
            for (int j = 0; j < 20; j++) {
                if (total_row != 0) {
                    System.out.print(matrix[i][j] / total_row);
                    float rate_trans = (matrix[i][j] / total_row) * 100;
                    System.out.print(rate_trans + " ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }

            }
            System.out.println();
        }
    }

    public static int get_key(double number){
        double key;
        key = (number * 20);
        int key_int = Double.valueOf(key).intValue();
        return key_int;
    }

    public static int[][] create_matrix(File name_file) {

        int[][] matrix = new int[20][20];
        // Gán các phần tử của ma trận là 0
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                matrix[i][j] = 0;
            }
        }

        try {
            Scanner train = new Scanner(name_file);
            train.useDelimiter(",");

            while (train.hasNext()) {

                String index = train.nextLine();
                Double index_double = Double.valueOf(index);
                int key_int = get_key(index_double);

                // System.out.println(previous_key);
                if (previous_key == 100) {
                    previous_key = key_int;
                } else {
                    int now_key = key_int;
                    if (now_key == 20) {
                        now_key = 19;
                    }
                    matrix[previous_key][now_key] += 1;
                    // System.out.println(matrix[previous_key][now_key]);
                    previous_key = now_key;
                }
            }
            train.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return matrix;
    }

    // public static void main(String[] args) {
    // File loop_training = new
    // File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
    // // File loop_training = new
    // // File("/home/duc/Java_Project/Do_an_3/src/LOOP_w5_thur_attack.csv");
    // int[][] matrix = create_matrix(loop_training);
    // }
}
