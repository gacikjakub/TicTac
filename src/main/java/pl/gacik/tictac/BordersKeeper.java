package pl.gacik.tictac;

import java.util.HashMap;
import java.util.Map;

public class BordersKeeper {

    private Map<BorderDirection, Integer> borderDirectionIntegerMap = new HashMap<>();

    {
        borderDirectionIntegerMap.put(BorderDirection.TOP, Integer.MIN_VALUE);
        borderDirectionIntegerMap.put(BorderDirection.RIGHT, Integer.MIN_VALUE);
        borderDirectionIntegerMap.put(BorderDirection.LEFT, Integer.MAX_VALUE);
        borderDirectionIntegerMap.put(BorderDirection.DOWN, Integer.MAX_VALUE);
    }

    public BordersKeeper(BordersKeeper bordersKeeper) {
        this.borderDirectionIntegerMap.put(BorderDirection.TOP, bordersKeeper.borderDirectionIntegerMap.get(BorderDirection.TOP));
        this.borderDirectionIntegerMap.put(BorderDirection.DOWN, bordersKeeper.borderDirectionIntegerMap.get(BorderDirection.DOWN));
        this.borderDirectionIntegerMap.put(BorderDirection.RIGHT, bordersKeeper.borderDirectionIntegerMap.get(BorderDirection.RIGHT));
        this.borderDirectionIntegerMap.put(BorderDirection.LEFT, bordersKeeper.borderDirectionIntegerMap.get(BorderDirection.LEFT));
    }

    public BordersKeeper() {
    }


    public void updateBorder(BorderDirection direction, Integer value) {
        borderDirectionIntegerMap.put(direction, value);
    }

    public Integer getBorder(BorderDirection direction) {
        return borderDirectionIntegerMap.get(direction);
    }

}
