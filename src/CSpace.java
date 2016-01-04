import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MPI on 2016/1/4.
 */
public class CSpace {

    public static void main(String []argus) throws IOException {
        JFrame frame=new JFrame("CSpace");

        Polygon robot = new Polygon("E:\\Project\\java_prj\\generate_C-space\\robot.txt");
        Polygon obstacle = new Polygon("E:\\Project\\java_prj\\generate_C-space\\obstacle.txt");
        Polygon cSpace = calCSpace(robot.getVertexes(),obstacle.getVertexes());
        CPanel panel = new CPanel(robot,obstacle,cSpace);

        //在frame中添加panel
        frame.getContentPane().add(panel);
        //设置frame的大小为500x500，
        frame.setSize(500,500);
        frame.setVisible(true);
        //使右上角的关闭按钮生效，如果没有这句，点击右上角的关闭按钮只能关闭窗口，无法结束进程
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 通过机器人多边形与障碍物多边形，生成障碍物的C-空间
     * 多边形需要进行预处理
     *   排序（逆时针排序）
     *   将y最小的点存在数组的第一个和最后一个，第一个点会存两次，数组比多边形边数多1
     * @param robot 输入构成机器人多边形的顶点
     * @param obstacle  输入构成障碍物多边形的顶点
     */
    public static Polygon calCSpace(Point[] robot, Point[] obstacle){
        int i=0;
        int j=0;
        int n = robot.length-1;
        int m = obstacle.length-1;
        List<Point> cSpace = new ArrayList<Point>();

        while(i!=n || j!=m  ){
            int x = robot[i].x + obstacle[j].x;
            int y = robot[i].y + obstacle[j].y;
            cSpace.add(new Point(x,y));
            if(i == n){
                j++;
                continue;
            }
            if(j == m){
                i++;
                continue;
            }
            if(angle(robot[i],robot[i+1]) < angle(obstacle[j], obstacle[j+1]))
                i++;
            else if(angle(robot[i],robot[i+1]) > angle(obstacle[j], obstacle[j+1]))
                j++;
            else{
                i++;
                j++;
            }
        }

        return new Polygon(cSpace.toArray(new Point[cSpace.size()]));
//        return new Polygon((Point [])list.to)
    }

    /**
     * 计算两个点的角度
     * 如果两个点重合返回360
     * @param p1 第一个点
     * @param p2 第二个点
     * @return
     */
    public static double angle(Point p1, Point p2){
        double dis = Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
        if (p2.y > p1.y)
        {
            return Math.acos((p2.x - p1.x) / dis) / Math.PI * 180;
        }
        else if (p1.y == p2.y) {
            if (p2.x > p1.x)
                return 0;
            else if (p2.x < p1.x)
                return 180;
            else    //设两点相同时角度为360
                return 360;
        }
        else
            return 360 - (Math.acos((p2.x - p1.x) / dis)) / Math.PI * 180;

    }


}

