package life.board.app.http;

public class SimulationRequest {
    private static final String DEFAULT_LIBRARY = "java.awt";
    private static final int DEFAULT_RESOLUTION = 5;
    private static final int DEFAULT_MATRIX_SIZE = 16384;
    private static final boolean DEFAULT_WITHGRID = false;
    public long step;
    public int cellResolution;
    public String drawingLibrary;
    public int matrixSize;
    public boolean withGrid;

    SimulationRequest(long step, int cellResolution, String drawingLibrary, int matrixSize, boolean withGrid){
        this.step = step;
        this.cellResolution = cellResolution;
        this.drawingLibrary = drawingLibrary;
        this.matrixSize = matrixSize;
        this.withGrid = withGrid;
        System.out.println(this.step);
    }

    public long getStep() {
        return this.step;
    }

    public void setStep(long step) {
        this.step = step;
    }

    public int getCellResolution() {
        if (cellResolution == 0){
            return DEFAULT_RESOLUTION;
        } else {
            return cellResolution;
        }
    }

    public void setCellResolution(int cellResolution) {
        if (cellResolution == 0){
            this.cellResolution = DEFAULT_RESOLUTION;
        } else {
            this.cellResolution = cellResolution;
        }
    }

    public String getDrawingLibrary() {
        if (drawingLibrary == null){
            return DEFAULT_LIBRARY;
        } else {
            return this.drawingLibrary;
        }
    }

    public void setDrawingLibrary(String drawingLibrary) {
        if (drawingLibrary == null){
            this.drawingLibrary = DEFAULT_LIBRARY;
        } else {
            this.drawingLibrary = drawingLibrary;
        }
    }

    public int getMatrixSize() {
        if (matrixSize == 0){
            return DEFAULT_MATRIX_SIZE;
        } else {
            return matrixSize;
        }
    }

    public void setMatrixSize(int matrixSize) {
        if (matrixSize == 0) {
            this.matrixSize = DEFAULT_MATRIX_SIZE;
        } else {
            this.matrixSize = matrixSize;
        }
    }

    public boolean isWithGrid() {
        return withGrid;
    }

    public void setWithGrid(boolean withGrid) {
        if (!withGrid) {
            this.withGrid = DEFAULT_WITHGRID;
        } else {
            this.withGrid = withGrid;
        }
    }

    @Override
    public String toString() {
        return "SimulationRequest{" +
                "step=" + step +
                ", cellResolution=" + cellResolution +
                ", drawingLibrary='" + drawingLibrary + '\'' +
                ", matrixSize=" + matrixSize +
                ", withGrid=" + withGrid +
                '}';
    }
}
