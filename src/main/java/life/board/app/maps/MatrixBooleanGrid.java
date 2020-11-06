package life.board.app.maps;


import ar.com.hjg.pngj.ImageInfo;
import ar.com.hjg.pngj.ImageLineHelper;
import ar.com.hjg.pngj.ImageLineInt;
import ar.com.hjg.pngj.PngWriter;
import ar.com.hjg.pngj.chunks.PngChunkTextVar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MatrixBooleanGrid extends AbstractBooleanGrid {
    final private boolean[][] matrix;

    public MatrixBooleanGrid(int matrixSize) {
        this.matrix = new boolean[matrixSize][matrixSize];
        this.setXShift(matrixSize / 2);
        this.setYShift(matrixSize / 2);
    }

    /**
     * Set a value to x, y coordinate. We also store the maximum and minimum value of x, y
     * only for convenience to perform dynamic scoping without asking the user to enter a watching window
     *
     * @param x     coordinate
     * @param y     coordinate
     * @param value value we set at this x, y coordinate
     */
    @Override
    public void setValue(int x, int y, boolean value) {
        this.matrix[getXShift() + x][getYShift() + y] = value;
    }

    @Override
    public boolean getValue(int x, int y) {
        return this.matrix[getXShift() + x][getYShift() + y];
    }

    /**
     * Given a matrix an array of 4 values [minX, maxX, minY, maxY] correspond the border values of True value
     *
     * @param matrix a 2 dimensial array of boolean
     * @return an array with 4 values [minX, maxX, minY, maxY]
     */
    static private ArrayList<Integer> getBorderLimits(boolean[][] matrix) {
        ArrayList<Integer> bounds = new ArrayList<>();
        int minx = matrix.length;
        int maxx = 0;
        int miny = matrix.length;
        int maxy = 0;

        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                if (matrix[x][y]) {
                    if (x < minx) {
                        minx = x;
                    }
                    if (x > maxx) {
                        maxx = x;
                    }
                    if (y < miny) {
                        miny = y;
                    }
                    if (y > maxy) {
                        maxy = y;
                    }
                }
            }
        }
        bounds.add(minx);
        bounds.add(maxx);
        bounds.add(miny);
        bounds.add(maxy);
        return bounds;
    }


    public MatrixBooleanProperties saveToDisk(String pathname, int cellResolution,
                                              String drawingLibrary, boolean withGrid) {
        ArrayList<Integer> bounds = MatrixBooleanGrid.getBorderLimits(matrix);
        this.AdjustBounds(bounds);
        // TODO externalize in an Image class
        if(drawingLibrary.equals("java.awt")){
            return MatrixBooleanGrid.saveToDiskJavaAwt(matrix, pathname,
                    bounds.get(0),bounds.get(1),bounds.get(2),bounds.get(3), cellResolution, withGrid);
        } else {
            return MatrixBooleanGrid.saveToDiskPngj(matrix, pathname,
                    bounds.get(0), bounds.get(1), bounds.get(2), bounds.get(3));
        }
    }
    static protected MatrixBooleanProperties saveToDiskPngj(boolean[][] matrix, String pathname, int minX,
                                                            int maxX, int minY, int maxY) {

        int imageWidth = maxX - minX;
        int imageHeight = maxY - minY;
        int imageXp;
        try {
            ImageInfo imi = new ImageInfo(imageWidth, imageHeight, 8, false);
            OutputStream outputStream = new FileOutputStream(pathname);
            PngWriter png = new PngWriter(outputStream, imi);
            png.getMetadata().setDpi(100.0);
            png.getMetadata().setTimeNow(0); // 0 seconds fron now = now
            png.getMetadata().setText(PngChunkTextVar.KEY_Title, "just a text image");
            for (int y = maxY; y > minY; y--) {
                imageXp = 0;
                ImageLineInt iline = new ImageLineInt(imi);
                for (int x = minX; x < maxX; x++) {

                    if (matrix[x][y]) {
                        ImageLineHelper.setPixelRGB8(iline, imageXp, 0, 0, 0);
                    } else {
                        ImageLineHelper.setPixelRGB8(iline, imageXp, 255, 255, 255);
                    }
                    imageXp += 1;
                }
                png.writeRow(iline);
            }
            png.end();
        } catch (FileNotFoundException e) {
            return new MatrixBooleanProperties(pathname, imageWidth, imageHeight, true, e.toString());
        }
        return new MatrixBooleanProperties(pathname, imageWidth, imageHeight, false, null);
    }

    static protected MatrixBooleanProperties saveToDiskJavaAwt(boolean[][] matrix, String pathname, int minX,
                                                               int maxX, int minY, int maxY,
                                                               int cellResolution, boolean withGrid) {
        // If a cell is one pixel we cannot anymore draw the grid on it
        if (cellResolution < 2) {
            cellResolution = 1;
            withGrid = false;
        }
        // imageWidth +1 and imageHeight +1 to close the grid with a grey line (it is only cosmetic)
        int imageWidth = ((maxX - minX) * cellResolution) + cellResolution + 1;
        int imageHeight = ((maxY - minY) * cellResolution) + cellResolution + 1;
        int imageYp = 0;
        int imageXp;

        try {
            Color newColor;
            BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            boolean v;
            for (int y = maxY; y >= minY; y--) {
                imageXp = 0;
                for (int x = minX; x <= maxX; x++) {
                    v = matrix[x][y];
                    if (v) {
                        newColor = new Color(0, 0, 0);
                    } else {
                        newColor = new Color(255, 255, 255);
                    }
                    // If the cellResolution > 1 we draw a square, because one pixel could be tiny to observe
                    for (int yy = 0; yy < cellResolution; yy++) {
                        for (int xx = 0; xx < cellResolution; xx++) {
                            image.setRGB((imageXp * cellResolution) + xx,
                                    (imageYp * cellResolution) + yy, newColor.getRGB());
                        }
                    }
                    imageXp += 1;
                }
                imageYp += 1;
            }

            if (withGrid) {
                newColor = new Color(114, 108, 108);
                // Vertical lines for the grid
                for (int x = 0; x < imageWidth; x += cellResolution) {
                    for (int y = 0; y < imageHeight; y++) {
                        image.setRGB(x, y, newColor.getRGB());
                    }
                }
                // Horizontal lines for the grid
                for (int y = 0; y < imageHeight; y += cellResolution) {
                    for (int x = 0; x < imageWidth; x++) {
                        image.setRGB(x, y, newColor.getRGB());
                    }
                }
            }
            File output = new File(pathname);
            ImageIO.write(image, "png", output);
        } catch (OutOfMemoryError e) {
            return new MatrixBooleanProperties(pathname, imageWidth, imageHeight, true, e.toString());
        } catch (Exception e) {
            return new MatrixBooleanProperties(pathname, imageWidth, imageHeight, true, e.toString());
        }
        return new MatrixBooleanProperties(pathname, imageWidth, imageHeight, false, null);
    }
}
