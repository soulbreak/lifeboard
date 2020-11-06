package life.board.app.maps;

public interface IGridProperties {
    int getWidth();
    int getHeight();
    String getPath();
    boolean error();
    String toString();
    String getDetails();
}
