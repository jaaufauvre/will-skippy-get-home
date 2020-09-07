package org.my_projects.skippy;

import org.my_projects.skippy.grid.Grid;
import org.my_projects.skippy.grid.Point;
import org.my_projects.skippy.kangaroo.Home;
import org.my_projects.skippy.kangaroo.Kangaroo;
import org.my_projects.skippy.direction.Die;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        // Create a grid
        System.out.println("Enter dimension of the grid (>=1):");
        long dimension = Long.parseLong(new Scanner(System.in).nextLine());
        var grid = new Grid(dimension);

        // Place Skippy on the grid
        var skippy = new Kangaroo();
        grid.placeItem(skippy, new Point(0, 0));

        // Place Skippy's home on the grid
        var home = new Home();
        grid.placeItem(home, new Point(dimension - 1, dimension - 1));

        // Ask skippy to find his home
        skippy.findHome(grid, home);

        // Print die statistics
        System.out.println();
        Die.getInstance().printStats();
    }
}
