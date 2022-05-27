package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class TileManager {
    // ** Come back and find the best implementation of storage method **
    // Set would probably work better
    GamePanel gp;
    Tile[] mapTile; // Array storing mapTile images
    Tile[] objectTile; // Array storing objectTile images
    int mapTileNum [][]; // Array storing tile values to map from txt file

    public TileManager(GamePanel gp) { // Stores each type of tile and associated images

        this.gp = gp;

        mapTile = new Tile[10]; // Set size of mapTile storage array
        objectTile = new Tile[10]; // Set size of object storage array
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage(); // Sets tile images according to specific data structure and tile type
        loadMap("maps/Base_Map_Layer.txt"); // Loads map from specified txt file

    }

    public void getTileImage() { // Retrieves Tile images and assigns to corresponding Tile

        try { // Assigns image to each index specified

            // Map Tile Assignments
            mapTile[0] = new Tile();
            mapTile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Stone_1.png"));

            mapTile[1] = new Tile();
            mapTile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Dirt_1.png"));

            mapTile[2] = new Tile();
            mapTile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Dirt_2.png"));

            mapTile[3] = new Tile();
            mapTile[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Door_Closed_In.png"));

            mapTile[4] = new Tile();
            mapTile[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/Door_Closed_Out_Right_16W.png"));

            // Testing Methods
            System.out.println(getClass());
            System.out.println(getClass().getClassLoader());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Reads txt file

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

                String line = br.readLine(); // converts each read line to String

                while (col < gp.maxScreenCol) {

                    String numbers[] = line.split(" "); // Stores each "index" individually into a string array
                                                            // from txt file Spaces in the text file denote next tile

                    int num = Integer.parseInt(numbers[col]); // Converts string to int

                    mapTileNum[col][row] = num; // Stores num to assigned position
                    col++; // Iterates column to traverse the txt file

                }
                if (col == gp.maxScreenCol) { // Once all columns are stored, move over row
                    col = 0; // Back to top of txt file
                    row++; // Over one row

                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2) { // Draws specified tiles at specified locations

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) { // Draws the background from top left down

            int tileNum = mapTileNum[col][row];

            g2.drawImage(mapTile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize; // Moving right one tile width

            if(col == gp.maxScreenCol) { // Once the column is at the max, it will reset x to 0 and move down one row
                col = 0;
                x = 0;
                row++; // Going down one row from the top left
                y += gp.tileSize; // Moving down one tile height

            }
        }
        g2.drawImage(mapTile[4].image, (gp.tileSize + 50), 0, gp.tileSize/2, gp.tileSize, null);
    }
}
