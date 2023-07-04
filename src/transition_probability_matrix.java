import java.io.*;
import java.util.*;

public class transition_probability_matrix {
    public static int previous_key = 100;

    public static float[][] rate_matrix(int[][] matrix){
        float[][] matrix_rate = new float[400][400];
        for (int i = 0; i < matrix.length; i++) {
            float total_row = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                total_row += matrix[i][j];
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (total_row != 0) {
                    // System.out.print(matrix[i][j] / total_row);
                    float rate_trans = (matrix[i][j] / total_row) * 100;
                    // System.out.print(rate_trans + " ");
                    matrix_rate[i][j] = rate_trans;
                } else {
                    // System.out.print(matrix[i][j] + " ");
                    matrix_rate[i][j] = 0;
                }

            }
            // System.out.println();
        }
        return matrix_rate;
    }

    public static void write_csv(float[][] matrix) {
        String csvFilePath = "/home/duc/Java_Project/Do_an_3/src/file.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
        // Ghi dữ liệu vào file CSV
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                writer.write(String.valueOf(matrix[i][j]));
                writer.write(","); // Phân tách giá trị bằng dấu phẩy
            }
            writer.newLine(); // Xuống dòng sau mỗi hàng
        }

            System.out.println("Ghi dữ liệu thành công vào file CSV.");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi dữ liệu vào file CSV: " + e.getMessage());
        }
    
    }

    public static double calculateProbabilityStandardDeviation(double[] probabilities) {
        // Tính giá trị trung bình
        double mean = Arrays.stream(probabilities).average().orElse(0.0);

        // Tính tổng bình phương sai số
        double sumOfSquaredDifferences = Arrays.stream(probabilities)
                .map(probability -> Math.pow(probability - mean, 2))
                .sum();

        // Tính độ lệch chuẩn
        double standardDeviation = Math.sqrt(sumOfSquaredDifferences / probabilities.length);

        return standardDeviation;
    }

    public static int[][] create_matrix_b2(int[][] matrix_b1) {
    int[][] matrix_b2 = new int[400][400];
    
    // Gán các phần tử của ma trận là 0
    for (int i = 0; i < 400; i++) {
        for (int j = 0; j < 400; j++) {
            matrix_b2[i][j] = 0;
        }
    }

    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 20; j++) {
            for (int k = 0; k < 20; k++) {
                for (int l = 0; l < 20; l++) {
                    matrix_b2[i * 20 + j][k * 20 + l] = matrix_b1[i][k] * matrix_b1[j][l];
                }
            }
        }
    }

    return matrix_b2;
}

    public static int get_key(double number){
        double key;
        key = (number * 20);
        int key_int = Double.valueOf(key).intValue();
        return key_int;
    }

    public static int get_key_2(double number1, double number2){
        int key_1, key_2;
        key_1 = get_key(number1);
        key_2 = get_key(number2);
        int key_int = (key_1 * 20 + key_2);
        return key_int;   
    }

    public static int[][] create_matrix(File name_file, int level_matrix) {
        int len_matrix = (int) Math.pow(20, level_matrix);
        int[][] matrix = new int[len_matrix][len_matrix];
        // Gán các phần tử của ma trận là 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }

        try {
            Scanner train = new Scanner(name_file);
            train.useDelimiter(",");

            List<Double> arr_train = read_csv.write_array(name_file);

            switch (level_matrix){
                case 1:

                    int key_int = get_key(arr_train.get(0));

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
                
                    train.close();
                    break;
                case 2:
                    
                
                }

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

//     public static void main(String[] args) {
//     File loop_training = new File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
//     int[][] matrix_b1 = create_matrix(loop_training);
//     int[][] matrix_b2 = create_matrix_b2(matrix_b1);
//     rate_matrix(matrix_b1);
//     System.out.println("...........................");
//     float[][] matrix =  rate_matrix(matrix_b2);
//     write_csv(matrix);
// }
}
