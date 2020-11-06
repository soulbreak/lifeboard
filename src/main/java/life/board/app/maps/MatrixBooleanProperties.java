package life.board.app.maps;

public class MatrixBooleanProperties implements IGridProperties {
    final private int width;
    final private int height;
    final private String path;
    final private String details;
    final boolean error;

    public MatrixBooleanProperties(String path, int width, int height, boolean error, String details) {
        this.width = width;
        this.height = height;
        this.error = error;
        this.details = details;
        this.path = path;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean error() {
        return this.error;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString(){
        return String.format("%s (%s x %s bytes)", getPath(), getHeight(), getWidth());
    }
}
