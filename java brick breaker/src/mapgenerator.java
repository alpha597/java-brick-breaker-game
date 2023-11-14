import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

public class mapgenerator {
    public int map[][];
    public int brickwidth;
    public int brickheight;

    public mapgenerator(int row,int col){
        map = new int[row][col];
        for(int a=0 ;a<map.length;a++){
            for(int b=0 ;b<map[0].length;b++){
                map[a][b] =1;
            }
        }
        brickwidth = 540/col;
        brickheight= 150/row;
    }
    public void draw (Graphics2D g){
        for(int a=0 ;a<map.length;a++){
            for(int b=0 ;b<map[0].length;b++){
                if(map[a][b] >0){
                    g.setColor(Color.WHITE);
                    g.fillRect(b*brickwidth+80, a*brickheight +50, brickwidth, brickheight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(b*brickwidth+80, a*brickheight +50, brickwidth, brickheight);
                }
            }
        }
    }
    public void setBrickValue(int val,int row,int col){
        map[row][col] = val;
    }
}
