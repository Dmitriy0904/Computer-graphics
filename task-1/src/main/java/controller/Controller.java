package controller;

import model.Square;
import util.SquareUtil;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private BufferedReader bf;


    public Controller() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public void parallelMove(Square square) throws IOException{
        System.out.println("Enter X coordinate:");
        int x = Integer.parseInt(bf.readLine());

        System.out.println("Enter Y coordinate:");
        int y = Integer.parseInt(bf.readLine());

        SquareUtil.parallelMove(square, x, y);
        System.out.println("Square was moved successfully");
    }


    public void rotate(Square square) throws IOException{
        System.out.println("Enter X coordinate of point:");
        int x = Integer.parseInt(bf.readLine());

        System.out.println("Enter Y coordinate of point:");
        int y = Integer.parseInt(bf.readLine());

        Point2D point = new Point2D.Double(x, y);

        System.out.println("Enter angle:");
        int angle = Integer.parseInt(bf.readLine());

        SquareUtil.rotate(square, point, angle);
        System.out.println("Square was rotated successfully");
    }

}