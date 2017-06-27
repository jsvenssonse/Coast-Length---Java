
package soteholm2;
/**
 *
 * @author Jesper Svensson
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import java.util.StringTokenizer;

public class Soteholm2 {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int rows = io.getInt();
        int cols = io.getInt(); 
        //io.print(rows + " " + cols);
        //io.close();

        int coastline = 0;
        int[][] gridMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String numbers = io.getWord();
            for (int j = 0; j < cols; j++) {
                String[] splitmap = numbers.split("");
                int nr = Integer.parseInt(splitmap[j]);
                gridMap[i][j] = nr;
            } 
        }

        NewMap buildMap = new NewMap();
        
        int[][] mapNew = buildMap.newMap(gridMap, rows, cols);

        
        
        for (int i = 0; i <= rows-1; i++) {
            for (int j = 0; j <= cols - 1; j++) {
                if (mapNew[i][j] == 1 && i == 0) {
                    coastline++;
                }
                if (mapNew[i][j] == 1 && j == 0) {
                    coastline++;
                }
                if (mapNew[i][j]==1) {
                    if (j > 0 && j <= cols) {
                        if (mapNew[i][j-1]==0) {
                            coastline++;
                        }
                        if (mapNew[i][j+1]==0) {
                            coastline++;
                        }
                    }
                    if (i > 0 && i <= rows ) {
                        if ( mapNew[i-1][j]==0) {
                             coastline++;
                         }
                         if (mapNew[i+1][j]==0) {
                             coastline++;
                         } 
                    }
                    
                }
            }
        }
        io.print(coastline);
        io.close();
    }
}

class NewMap{
    int[][] newMap(int[][] gridMap, int row, int cols) {
        Kattio io = new Kattio(System.in, System.out);
        
        int[][] gridMapNew = new int[5][6];
    
        for (int i = 0; i <= row-1; i++) {
            for (int j = 0; j <= cols-1; j++) {
                int count = 0;
                if (gridMap[i][j]==0) {
                    if (j > 0 && j < 5) {
                        if (gridMap[i][j-1]==1) {
                            count++;
                        }
                        if (gridMap[i][j+1]==1) {
                            count++;
                        }
                    }
                    if (i > 0 && i < 4) {
                        if (gridMap[i-1][j]==1) {
                            count++;
                        }
                        if (gridMap[i+1][j]==1) {
                            count++;
                        }  
                    }
                    if (count == 4) {
                    gridMapNew[i][j] = 1;
                    }
                }else{
                    gridMapNew[i][j] = gridMap[i][j];
                }
            }
        }
        return gridMapNew;
    }
}

class Kattio extends PrintWriter{
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }
    
    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}