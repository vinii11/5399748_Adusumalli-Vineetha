import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here
int r = grid.size();
        int c = grid.get(0).length();

        if (n == 1) {
            return grid;
        }
        if (n % 2 == 0) {
           
            List<String> full = new ArrayList<>();
           char[] arr = new char[c];
Arrays.fill(arr, 'O');
String row = new String(arr);
            for (int i = 0; i < r; i++) full.add(row);
            return full;
        }

   
        List<String> afterFirstExplosion = explode(grid);

        if (n % 4 == 3) {
            return afterFirstExplosion;
        }

        return explode(afterFirstExplosion);
    }

    private static List<String> explode(List<String> grid) {
        int r = grid.size();
        int c = grid.get(0).length();
        char[][] result = new char[r][c];

     
        for (int i = 0; i < r; i++) {
            Arrays.fill(result[i], 'O');
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid.get(i).charAt(j) == 'O') {
                    result[i][j] = '.'; // bomb cell
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
                            result[nr][nc] = '.';
                        }
                    }
                }
            }
        }

       
        List<String> out = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            out.add(new String(result[i]));
        }
        return out;
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
