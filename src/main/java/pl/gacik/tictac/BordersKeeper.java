package pl.gacik.tictac;

import java.util.Arrays;
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
        Arrays.stream(BorderDirection.values()).forEach(direction -> borderDirectionIntegerMap.put(direction, bordersKeeper.borderDirectionIntegerMap.get(direction)));
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
