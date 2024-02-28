package model;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Models{
    public  Map<String, Map<String, List<String>>> initializeModels() {
        Map<String, Map<String, List<String>>> modelsPerLine = new HashMap<>();

        Map<String, List<String>> modelsCronos = new HashMap<>();
        modelsCronos.put("Cronos Old", Arrays.asList("Cronos 6001-A", "Cronos 6003", "Cronos 7023"));
        modelsCronos.put("Cronos L", Arrays.asList("Cronos 6021L", "Cronos 7023L"));
        modelsCronos.put("Cronos-NG", Arrays.asList("Cronos 6001‑NG", "Cronos 6003‑NG", "Cronos 6021‑NG", "Cronos 6031‑NG", "Cronos 7021‑NG", "Cronos 7023‑NG"));

        Map<String, List<String>> modelsAres = new HashMap<>();
        modelsAres.put("Ares TB", Arrays.asList("Ares 7021", "Ares 7031", "Ares 7023"));
        modelsAres.put("Ares THS", Arrays.asList("Ares 8023 15", "Ares 8023 200", "Ares 8023 2,5"));

        modelsPerLine.put("Cronos", modelsCronos);
        modelsPerLine.put("Ares", modelsAres);

        return modelsPerLine;
    }
}