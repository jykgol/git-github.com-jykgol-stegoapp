/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servieces;

import java.util.ArrayList;

/**
 *
 * @author jyk22
 */
public class EncryptDecrypt {

    private Integer[][] inputMatrix;
    private final Integer N;
    private String buf_num;
    public String count;

    public EncryptDecrypt(Integer[][] inputMatrix) {
        this.inputMatrix = inputMatrix;
        this.N = this.inputMatrix.length;
    }

    public String getCount() {
        return this.count;
    }

    public Integer[][] encrypt(String ChaoticMapChoosed) {
        Integer[][] result;
        if ("Baker".equals(ChaoticMapChoosed)) {
            result = this.Baker(this.inputMatrix);
        } else {
            result = this.ACM(this.inputMatrix);
        }
        return result;
    }

    public Integer[][] decrypt(String ChaoticMapChoosed) {
        Integer[][] result;
        if ("Baker".equals(ChaoticMapChoosed)) {
            result = this.Baker_decrypt(this.inputMatrix);
        } else {
            result = this.ACM_decrypt(this.inputMatrix);
        }
        return result;
    }

    private Integer[][] ACM_decrypt(Integer[][] matrix) {
        Integer[] hasil = new Integer[2];
        Integer[][] result = new Integer[N][N];
        int number = 1;

        for (int i = 0; i < number; i++) {
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    hasil[0] = (1 * a + 1 * b) % N;
                    hasil[1] = (1 * a + 2 * b) % N;
                    result[hasil[0]][hasil[1]] = matrix[a][b];
                }
            }
            for (int a = 0; a < N; a++) {
                System.arraycopy(result[a], 0, matrix[a], 0, N);
            }
        }

        return result;

    }

    private Integer[][] ACM(Integer[][] matrix) {

        Integer[] hasil = new Integer[2];
        Integer[][] result = new Integer[N][N];
        int number;

        if (!buf_num.equals("")) {
            number = Integer.parseInt(buf_num);
        } else {
            number = 1;
        }

        for (int i = 0; i < number; i++) {
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    hasil[0] = (1 * a + 1 * b) % N;
                    hasil[1] = (1 * a + 2 * b) % N;
                    result[a][b] = matrix[hasil[0]][hasil[1]];
                }
            }
            for (int a = 0; a < N; a++) {
                System.arraycopy(result[a], 0, matrix[a], 0, N);
            }
        }

        return result;
    }

    private Integer[][] Baker_decrypt(Integer[][] matrix) {
        Integer[] hasil = new Integer[2];
        Integer[][] result = new Integer[N][N];
        ArrayList<Integer> array1 = new ArrayList<Integer>();
        ArrayList<Integer> array2 = new ArrayList<Integer>();
        ArrayList<Integer> array3 = new ArrayList<Integer>();
        ArrayList<Integer> array4 = new ArrayList<Integer>();

        int number;
        int celayaChast;

        number = 1;

        for (int repeat = 0; repeat < number; repeat++) {
            int a;
            for (int y = 0; y < N / 2; y++) {
                for (int x = 0; x < N / 2; x++) {
                    celayaChast = y * N + x;
                    array1.add(celayaChast);
                }
            }
            for (int y = 0; y < N / 2; y++) {
                for (int x = N / 2; x < N; x++) {
                    celayaChast = y * N + x;
                    array2.add(celayaChast);
                }
            }
            for (int y = N / 2; y < N; y++) {
                for (int x = 0; x < N / 2; x++) {
                    celayaChast = y * N + x;
                    array3.add(celayaChast);
                }
            }
            for (int y = N / 2; y < N; y++) {
                for (int x = N / 2; x < N; x++) {
                    celayaChast = y * N + x;
                    array4.add(celayaChast);
                }
            }
            a = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x += 4) {
                    hasil[0] = array1.get(a) / N;
                    hasil[1] = array1.get(a) % N;
                    result[y][x] = matrix[hasil[0]][hasil[1]];
                    hasil[0] = array2.get(a) / N;
                    hasil[1] = array2.get(a) % N;
                    result[y][x + 1] = matrix[hasil[0]][hasil[1]];
                    hasil[0] = array3.get(a) / N;
                    hasil[1] = array3.get(a) % N;
                    result[y][x + 2] = matrix[hasil[0]][hasil[1]];
                    hasil[0] = array4.get(a) / N;
                    hasil[1] = array4.get(a) % N;
                    result[y][x + 3] = matrix[hasil[0]][hasil[1]];
                    a++;
                }
            }
            for (int b = 0; b < N; b++) {
                System.arraycopy(result[b], 0, matrix[b], 0, N);
            }
        }

        return result;

    }

    private Integer[][] Baker(Integer[][] matrix) {
        Integer[] hasil = new Integer[2];
        Integer[][] result = new Integer[N][N];
        ArrayList<Integer> array1 = new ArrayList<Integer>();
        ArrayList<Integer> array2 = new ArrayList<Integer>();
        ArrayList<Integer> array3 = new ArrayList<Integer>();
        ArrayList<Integer> array4 = new ArrayList<Integer>();
        int number;
        int celayaChast;

        if (!buf_num.equals("")) {
            number = Integer.parseInt(buf_num);
        } else {
            number = 1;
        }

        for (int repeat = 0; repeat < number; repeat++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x += 4) {
                    celayaChast = y * N + x;
                    array1.add(celayaChast);
                    celayaChast = y * N + x + 1;
                    array2.add(celayaChast);
                    celayaChast = y * N + x + 2;
                    array3.add(celayaChast);
                    celayaChast = y * N + x + 3;
                    array4.add(celayaChast);
                }
            }
            int a = 0;
            for (int y = 0; y < N / 2; y++) {
                for (int x = 0; x < N / 2; x++) {
                    hasil[0] = array1.get(a) / N;
                    hasil[1] = array1.get(a) % N;
                    result[y][x] = matrix[hasil[0]][hasil[1]];
                    a++;
                }
            }
            a = 0;
            for (int y = 0; y < N / 2; y++) {
                for (int x = N / 2; x < N; x++) {
                    hasil[0] = array2.get(a) / N;
                    hasil[1] = array2.get(a) % N;
                    result[y][x] = matrix[hasil[0]][hasil[1]];
                    a++;
                }
            }
            a = 0;
            for (int y = N / 2; y < N; y++) {
                for (int x = 0; x < N / 2; x++) {
                    hasil[0] = array3.get(a) / N;
                    hasil[1] = array3.get(a) % N;
                    result[y][x] = matrix[hasil[0]][hasil[1]];
                    a++;
                }
            }
            a = 0;
            for (int y = N / 2; y < N; y++) {
                for (int x = N / 2; x < N; x++) {
                    hasil[0] = array4.get(a) / N;
                    hasil[1] = array4.get(a) % N;
                    result[y][x] = matrix[hasil[0]][hasil[1]];
                    a++;
                }
            }
            for (int b = 0; b < N; b++) {
                System.arraycopy(result[b], 0, matrix[b], 0, N);
            }
        }

        return result;

    }

    public String GetNum(String a) {
        buf_num = a;
        return buf_num;
    }
}
