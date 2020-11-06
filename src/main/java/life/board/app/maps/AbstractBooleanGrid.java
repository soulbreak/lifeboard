package life.board.app.maps;

import java.util.ArrayList;

public abstract class AbstractBooleanGrid implements IBooleanGrid {
    public static int MIN_CELL_FROM_IMAGE_BORDER = 5;
    public static int MIN_IMAGE_SIZE = 40;
    public static int cellResolution = 1;
    private int xshift = 0;
    private int yshift = 0;

    @Override
    abstract public void setValue(int x, int y, boolean value);

    @Override
    abstract public boolean getValue(int x, int y);

    /**
     * ASCII draw of the matrix, ideal to observe the matrix in the console during development
     * @param matrix to draw
     */
    static protected void ASCIIdraw(boolean[][] matrix){
        for (int y = matrix.length - 1; y >= 0 ; y--) {
            for (int x = 0; x < matrix[y].length; x++) {
                if(matrix[y][x]){
                    System.out.print("X");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    protected int getXShift() {
        return this.xshift;
    }

    protected int getYShift() {
        return this.yshift;
    }

    protected void setXShift(int xShift) {
        this.xshift = xShift;
    }

    protected void setYShift(int yshift) {
        this.yshift = yshift;
    }


    protected void AdjustBounds(ArrayList<Integer> bounds){
        if(bounds.get(0) > (getXShift() - (MIN_IMAGE_SIZE/2))){
            bounds.set(0, getXShift() - (MIN_IMAGE_SIZE/2));
        } else {
            bounds.set(0, bounds.get(0) - MIN_CELL_FROM_IMAGE_BORDER);
        }
        if(bounds.get(1) < (getXShift() + (MIN_IMAGE_SIZE/2))){
            bounds.set(1, getXShift() + (MIN_IMAGE_SIZE/2));
        } else {
            bounds.set(1, bounds.get(1) + MIN_CELL_FROM_IMAGE_BORDER);
        }
        if(bounds.get(2) > (getYShift() - (MIN_IMAGE_SIZE/2))){
            bounds.set(2, getYShift() - (MIN_IMAGE_SIZE/2));
        } else {
            bounds.set(2, bounds.get(2) - MIN_CELL_FROM_IMAGE_BORDER);
        }
        if(bounds.get(3) < (getYShift() + (MIN_IMAGE_SIZE/2))){
            bounds.set(3, getYShift() + (MIN_IMAGE_SIZE/2));
        } else {
            bounds.set(3, bounds.get(3) + MIN_CELL_FROM_IMAGE_BORDER);
        }
    }


}
