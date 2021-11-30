package form;

import controller.Controller;
import model.Square;
import util.SquareCreator;
import util.SquareUtil;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Form extends JFrame {
    private final Controller controller = new Controller();
    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private JPanel panel = new JPanel();

    public Form() {
        getContentPane().add(panel);
        setSize(800,500);
    }

    @Override
    public void paint(Graphics graphics) {

        Square square = SquareCreator.newSquare();

        while(true){
            try{
                SquareUtil.paint(square, graphics);

                System.out.println("1-Parallel move, 2-Rotate, 0-Exit");
                int choose = Integer.parseInt(bf.readLine());

                switch (choose){
                    case 1 -> controller.parallelMove(square);
                    case 2 -> controller.rotate(square);
                    case 0 -> System.exit(1);
                    default -> System.out.println("Such choose does not supported");
                }

            } catch (IOException exception){
                throw new RuntimeException(exception.getMessage());
            }
        }

    }
}
