import java.awt.*;
import java.awt.image.IndexColorModel;
import java.io.*;

/**
 * Created by MPI on 2016/1/4.
 */
public class Polygon {
    Point []vertexes;

    public Polygon(Point []vertexes){
        this.vertexes = vertexes;
    }

    public Polygon(String filePath) throws IOException {
        readPoint(filePath);
    }

    public Point []getVertexes(){
        return vertexes;
    }

    public void draw(Graphics g,Color color){
        g.setColor(color);
        for(int i=0; i<vertexes.length; i++){
            if(i != vertexes.length-1){
                g.drawLine(vertexes[i].x, vertexes[i].y, vertexes[i+1].x, vertexes[i+1].y);
            }
            else{
                g.drawLine(vertexes[i].x, vertexes[i].y, vertexes[0].x, vertexes[0].y);
            }
        }
    }

    public void readPoint(String filePath) throws IOException {
        File file=new File(filePath);
        if(file.isFile() && file.exists()) { //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(read);
            int i=0;
            String lineTxt = null;
            lineTxt = bufferedReader.readLine();
            int edgeNum = Integer.parseInt(lineTxt);
            vertexes = new Point[edgeNum+1];
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String []pointStrs = lineTxt.split(" ");
                vertexes[i] = new Point();
                vertexes[i].x = Integer.parseInt(pointStrs[0]);
                vertexes[i].y = Integer.parseInt(pointStrs[1]);
                i++;
            }
            vertexes[edgeNum] = new Point();
            vertexes[edgeNum].x = vertexes[0].x;
            vertexes[edgeNum].y = vertexes[0].y;

        }

    }
}
