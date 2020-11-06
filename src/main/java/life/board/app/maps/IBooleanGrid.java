package life.board.app.maps;

public interface IBooleanGrid {
    /**
     *
     * @param x coordinate
     * @param y coordinate
     * @param value value we set at this x, y coordinate
     */
    void setValue(int x, int y, boolean value);

    /**
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the value at x, y coordinate
     */
    boolean getValue(int x, int y);

    /**
     * Save the image under jpg format, the scene is dynamically drawed
     * @param pathname
     * @param withGrid
     * @return
     */
    IGridProperties saveToDisk(String pathname, int cellResolution, String drawingLibrary, boolean withGrid);


}