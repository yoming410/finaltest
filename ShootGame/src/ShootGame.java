import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ShootGame extends JFrame {
    private Container cp;

    private JLabel jlbParachute_left=new JLabel(); // parachute_left
    private JLabel jlbParachute_right=new JLabel(); // parachute_right
    private JLabel jlbMissle=new JLabel(); // missle

    private  Timer t1;
    private  Timer t2;
    private ImageIcon imgparachute1=new ImageIcon("parachute_left.png");
    private ImageIcon imgparachute2=new ImageIcon("parachute_right.png");
    private ImageIcon missle=new ImageIcon("missle.png");

    private int targetX,targetY;
    private int origX,origY;
    private boolean isobselect=false;

    public ShootGame() {
        super("跳傘");
        setSize(800,1000);
        Container con=getContentPane();
        con.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });
        this.setBounds(50,50,1000,800);
        cp=this.getContentPane();
        cp.setLayout(null);

        Image img1=imgparachute1.getImage();
        Image img11=img1.getScaledInstance(120,180,Image.SCALE_AREA_AVERAGING);
        imgparachute1.setImage(img11);
        jlbParachute_left.setIcon(imgparachute1);

        Image img2=imgparachute2.getImage();
        Image img22=img2.getScaledInstance(120,180,Image.SCALE_AREA_AVERAGING);
        imgparachute2.setImage(img22);
        jlbParachute_right.setIcon(imgparachute2);

        Image img3=missle.getImage();
        Image img33=img3.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle.setIcon(missle);
        missle.setImage(img33);

        jlbParachute_left.setBounds(350,550,269,187);
        cp.add(jlbParachute_left);
        t1=new Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jlbMissle.setLocation(jlbMissle.getX(),jlbMissle.getY()-5);
                if (jlbMissle.getY()<-60){
                    t1.stop();
                }
            }
        });
        t2=new Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlbParachute_left.setLocation(jlbParachute_left.getX(),jlbParachute_left.getY());
                if (jlbParachute_left.getX()<0||jlbParachute_left.getX()>350){
                    t2.stop();
                }
            }
        });
        jlbParachute_left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isobselect=true;
                origX=e.getX();
                origY=e.getY();
                System.out.println("origX:"+origX+"origY:"+origY);
            }
        });
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)){
                    targetX=e.getX();
                    targetY=e.getY();
                    System.out.println("origX:"+origX+"origY:"+origY);
                    if (isobselect){
                        jlbParachute_left.setLocation(targetX,targetY);

                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getExtendedKeyCode());
                switch (e.getKeyCode()){
                    case 37:  // left
                        jlbParachute_left.setIcon(imgparachute1);
                        jlbParachute_left.setLocation(jlbParachute_left.getX()-5,jlbParachute_left.getY());
                        break;
                    case 38:  // up
                        jlbParachute_left.setIcon(imgparachute1);
                        jlbParachute_left.setLocation(jlbParachute_left.getX(),jlbParachute_left.getY()-5);
                    case 39: // right
                        jlbParachute_left.setIcon(imgparachute2);
                        jlbParachute_left.setLocation(jlbParachute_left.getX()+5,jlbParachute_left.getY());
                        break;
                    case 40: // down
                        jlbParachute_left.setIcon(imgparachute1);
                        jlbParachute_left.setLocation(jlbParachute_left.getX(),jlbParachute_left.getY()+5);
                    case 32: // space
                        jlbMissle.setBounds(jlbParachute_left.getX()+50,jlbParachute_left.getY(),30,60);
                        cp.add(jlbMissle);
                        t1.start();
                        break;
                }

            }
        });
    }
}
// ShootGame
