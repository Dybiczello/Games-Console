import java.awt.*;

import static java.lang.Math.random;

public class MapGenerator {
    public int map[][];
    public int brickWidht;
    public int brickHeight;
    public MapGenerator(int row, int col, int trudnosc){
        map = new int[row][col];
        for(int i=0; i < map.length; i++){
            for(int j=0; j< map[0].length; j++){
                map[i][j] = (int)(Math.random() * 3 + 1);
            }
        }
        if(trudnosc == 1) {
            brickWidht = 540 / col;
            brickHeight = 150 / row;
        }
        if(trudnosc == 2) {
            brickWidht = 540 / col;
            brickHeight = 250 / row;
        }
        if(trudnosc == 3) {
            brickWidht = 540 / col;
            brickHeight = 350 / row;
        }
    }
    public void draw(Graphics2D g) {
        for(int i=0; i < map.length; i++){
            for(int j=0; j< map[0].length; j++){
                if (map[i][j] == 1){
                    g.setColor(Color.CYAN);
                    g.fillRect(j * brickWidht + 80, i * brickHeight + 50, brickWidht, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidht + 80, i * brickHeight + 50, brickWidht, brickHeight);
                }
                if (map[i][j] == 2){
                    g.setColor(Color.GREEN);
                    g.fillRect(j * brickWidht + 80, i * brickHeight + 50, brickWidht, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidht + 80, i * brickHeight + 50, brickWidht, brickHeight);
                }
                if (map[i][j] == 3){
                    g.setColor(Color.RED);
                    g.fillRect(j * brickWidht + 80, i * brickHeight + 50, brickWidht, brickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j * brickWidht + 80, i * brickHeight + 50, brickWidht, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }

}
