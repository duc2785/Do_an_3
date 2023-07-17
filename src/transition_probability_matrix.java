import java.io.*;
import java.util.*;

public class transition_probability_matrix {
    public static int previous_key = 100;

    public static float[][] cal_rate(float[][] matrix) {
        float[][] matrix_rate = new float[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            float total_row = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                total_row += matrix[i][j];
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (total_row != 0) {
                    // System.out.print(matrix[i][j] / total_row);
                    float rate_index = ((matrix[i][j] / total_row) * 100);
                    // System.out.print(rate_trans + " ");
                    matrix_rate[i][j] = rate_index;
                } else {
                    // System.out.print(matrix[i][j] + " ");
                    matrix_rate[i][j] = 0;
                }

            }
            // System.out.println();
        }
        return matrix_rate;
    }

    public static void write_csv(float[][] matrix, String file_name) {
        String csvFilePath = "/home/duc/Java_Project/Do_an_3/src/" + file_name + ".csv";
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

    // public static double calculateProbabilityStandardDeviation(double[] probabilities) {
    //     // Tính giá trị trung bình
    //     double mean = Arrays.stream(probabilities).average().orElse(0.0);

    //     // Tính tổng bình phương sai số
    //     double sumOfSquaredDifferences = Arrays.stream(probabilities)
    //             .map(probability -> Math.pow(probability - mean, 2))
    //             .sum();

    //     // Tính độ lệch chuẩn
    //     double standardDeviation = Math.sqrt(sumOfSquaredDifferences / probabilities.length);

    //     return standardDeviation;
    // }

    // public static int[][] create_matrix_b2(int[][] matrix_b1) {
    //     int[][] matrix_b2 = new int[400][400];

    //     // Gán các phần tử của ma trận là 0
    //     for (int i = 0; i < 400; i++) {
    //         for (int j = 0; j < 400; j++) {
    //             matrix_b2[i][j] = 0;
    //         }
    //     }

    //     for (int i = 0; i < 20; i++) {
    //         for (int j = 0; j < 20; j++) {
    //             for (int k = 0; k < 20; k++) {
    //                 for (int l = 0; l < 20; l++) {
    //                     matrix_b2[i * 20 + j][k * 20 + l] = matrix_b1[i][k] * matrix_b1[j][l];
    //                 }
    //             }
    //         }
    //     }

    //     return matrix_b2;
    // }

    public static int get_key(double number) {
        double key;
        key = (number * 20);
        int key_int = Double.valueOf(key).intValue();
        if(key_int == 20){
            key_int = 19;
        }
        return key_int;
    }

    public static int get_key_2(double number1, double number2) {
        int key_1, key_2;
        key_1 = get_key(number1);
        key_2 = get_key(number2);
        int key_int = (key_1 * 20 + key_2);
        return key_int;
    }

    public static int get_key_5(double number1, double number2, double number3, double number4, double number5){
        int key_1, key_2, key_3, key_4, key_5;
        key_1 = get_key(number1);
        key_2 = get_key(number2);
        key_3 = get_key(number3);
        key_4 = get_key(number4);
        key_5 = get_key(number5);   
        int key_int =  (int) (key_1 * Math.pow(key_1, 4) + key_2 * Math.pow(key_2, 3) + key_3 * 400 + key_4 * 20 + key_5);    
        return key_int;
    }

    public static float[][] create_matrix(File name_file, int level_matrix) {
        int len_matrix = (int) Math.pow(20, level_matrix);
        float[][] matrix = new float[len_matrix][len_matrix];
        // Gán các phần tử của ma trận là 0
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = 0;
            }
        }

        try {

            List<Double> arr_train = read_csv.write_array(name_file);
            // int key_int = get_key(arr_train.get(0));

            switch (level_matrix) {
                case 1:
                    int pre_key1 = get_key(arr_train.get(0));
                    for (int i = 1; i < arr_train.size(); i++) {
                        int now_key = get_key(arr_train.get(i));
                        matrix[pre_key1][now_key] += 1;
                        // System.out.println(matrix[previous_key][now_key]);
                        pre_key1 = now_key;
                        // }
                    }
                    break;
                case 2:
                    double pre_num_1 = arr_train.get(0);
                    double pre_num_2 = arr_train.get(1);
                    int pre_key2 = get_key_2(pre_num_1, pre_num_2);

                    for (int i = 1; i < arr_train.size() - 1; i += 1) {
                        double now_num_1 = arr_train.get(i);
                        double now_num_2 = arr_train.get(i + 1);
                        int now_key = get_key_2(now_num_1, now_num_2);

                        matrix[pre_key2][now_key] += 1;
                        pre_key2 = now_key;
                    }
                    break;
                case 5:
                    double pre_num1 = arr_train.get(0);
                    double pre_num2 = arr_train.get(1);
                    double pre_num3 = arr_train.get(2);
                    double pre_num4 = arr_train.get(3);
                    double pre_num5 = arr_train.get(4);
                    int pre_key5 = get_key_5(pre_num1, pre_num2, pre_num3, pre_num4, pre_num5);

                    for (int i = 1; i < arr_train.size() - 4; i++){
                        double now_num_1 = arr_train.get(i);
                        double now_num_2 = arr_train.get(i + 1);
                        double now_num_3 = arr_train.get(i + 2);
                        double now_num_4 = arr_train.get(i + 3);
                        double now_num_5 = arr_train.get(i + 4);
                        int now_key = get_key_5(now_num_1, now_num_2, now_num_3, now_num_4, now_num_5);

                        matrix[pre_key5][now_key] += 1;
                        pre_key5 = now_key;
                    }
                    break;
            }
            // for (int i = 0; i < matrix.length; i++) {
            //     float total_row = 0;
            //     for (int j = 0; j < matrix[0].length; j++) {
            //         total_row += matrix[i][j];
            //     }
            //     for (int j = 0; j < matrix[0].length; j++) {
            //         if (total_row != 0) {
            //             // System.out.print(matrix[i][j] / total_row);
            //             float rate_trans = (matrix[i][j] / total_row) * 100;
            //             // System.out.print(rate_trans + " ");
            //             matrix[i][j] = (int) rate_trans;
            //         } else {
            //             // System.out.print(matrix[i][j] + " ");
            //             matrix[i][j] = 0;
            //         }

            //     }
            //     // System.out.println();
            matrix = cal_rate(matrix);
            
            write_csv(matrix, "rate");

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

    // public static void main(String[] args) {
    // File loop_training = new
    // File("/home/duc/Java_Project/Do_an_3/src/loop_training.csv");
    // int[][] matrix_b1 = create_matrix(loop_training);
    // int[][] matrix_b2 = create_matrix_b2(matrix_b1);
    // rate_matrix(matrix_b1);
    // System.out.println("...........................");
    // float[][] matrix = rate_matrix(matrix_b2);
    // write_csv(matrix);
    // }
}
