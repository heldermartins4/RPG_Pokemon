package controllers.interfaces.map.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MapDataManager {

    public String map_name;
    public int[][] map_data;
    public int map_x, map_y;

    private BufferedReader map_txt_file;

    public MapDataManager(String map_name) {
        this.map_txt_file = new BufferedReader(new InputStreamReader(MapDataManager.class.getResourceAsStream("./" + map_name + ".txt")));

        loadMapData();
    }

    public void loadMapData() {

        try {
            String line;
    
            // Assuming the map has at least one row
            if ((line = map_txt_file.readLine()) != null) {
    
                // Initialize map_y to 1 for the first row
                map_y = 1;
    
                // Get map_x for the first row
                char[] firstRow = line.toCharArray();
                map_x = firstRow.length;
    
                // Initialize map_data
                map_data = new int[map_x][map_y];
    
                // Fill the first row of map_data
                for (int x = 0; x < map_x; x++) {
                    map_data[x][0] = Character.getNumericValue(firstRow[x]);
                }
    
                // Read and process the rest of the rows
                while ((line = map_txt_file.readLine()) != null) {
                    char[] line_data = line.toCharArray();
    
                    // Ensure the width of the map is consistent
                    if (line_data.length != map_x) {
                        throw new IOException("Inconsistent map width");
                    }
    
                    // Increment map_y
                    map_y++;
    
                    // Resize map_data to accommodate the new row
                    int[][] newMapData = new int[map_x][map_y];
                    // Copy existing data to new array
                    for (int x = 0; x < map_x; x++) {
                        System.arraycopy(map_data[x], 0, newMapData[x], 0, map_y - 1);
                    }
                    map_data = newMapData;
    
                    // Fill the new row of map_data
                    for (int x = 0; x < map_x; x++) {
                        map_data[x][map_y - 1] = Character.getNumericValue(line_data[x]);
                    }
                }
            }
    
            map_txt_file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
