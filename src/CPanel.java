import javax.swing.*;
import java.awt.*;


/**
 * Created by MPI on 2016/1/4.
 */
public class CPanel extends JPanel {

    Polygon robot;
    Polygon obstacle;
    Polygon cspace;

    public CPanel(Polygon robot, Polygon obstacle, Polygon cspace){
        this.robot = robot;
        this.obstacle = obstacle;
        this.cspace = cspace;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        robot.draw(g,Color.black);
        obstacle.draw(g,Color.red);
        cspace.draw(g,Color.blue);
    }
}
