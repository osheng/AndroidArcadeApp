package Twenty;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import fall2018.csc2017.CoreClasses.Tile;
import fall2018.csc2017.CoreClasses.R;


public class TwentyTile extends Tile {

    /**
     * Constructor for a 2048 tile.
     * Preconditions: <id> is a power of 2
     */
    public TwentyTile(int id, int background){
        super();
        ArrayList<Integer> picts = new ArrayList<>();
        picts.add(R.drawable.twentytile_blank);
        picts.add(R.drawable.twentytile_2);
        picts.add(R.drawable.twentytile_4);
        picts.add(R.drawable.twentytile_8);
        picts.add(R.drawable.twentytile_16);
        picts.add(R.drawable.twentytile_32);
        picts.add(R.drawable.twentytile_64);
        picts.add(R.drawable.twentytile_128);
        picts.add(R.drawable.twentytile_256);
        picts.add(R.drawable.twentytile_512);
        picts.add(R.drawable.twentytile_1024);
        picts.add(R.drawable.twentytile_2048);
        picts.add(R.drawable.twentytile_4096);
        picts.add(R.drawable.twentytile_8192);

        // Validate preconditions
        if(id != 0){
            super.setBackground(picts.get(id));
            super.setId((int)Math.pow(2, id));
        }else{
            // Set to a blank tile otherwise.
            super.setId(0);
            super.setBackground(picts.get(0));
        }
    }

    /**
     * Boolean method which tells you if this tile is a blank one.
     */
    public boolean isBlank(){
        return getId() == 0;
    }

    /**
     * Compares two Tiles by id for order.
     * -1 denotes not equal, 0 denotes that Tile <other> is a blank tile,
     * and 1 denotes two tiles are equal
     */
    public int compareTo(@NonNull TwentyTile other) {
        if(other.getId() == 0){
            return 0;
        }else if(other.getId() != this.getId()){
            return -1;
        }else{
            return 1;
        }
    }



}
