package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;

public class mainController {

    @FXML
    private TitledPane titledPaneModels;

    @FXML
    private TreeView<String> treeView;

    @FXML
    private ComboBox<String> comboBoxLines;

    @FXML
    private Accordion accordion;

    private final Map<String, Map<String, List<String>>> modelsPerLine = new HashMap<>();

    @FXML
    public void initialize() {
        titledPaneModels.setDisable(true);
        initializeComboBox();
        initializeModelsByLine();
    }

    private void initializeComboBox() {
        ObservableList<String> lines = FXCollections.observableArrayList("Cronos", "Ares");
        comboBoxLines.setItems(lines);
        comboBoxLines.setOnAction(event -> handleLineSelection());
    }

    @FXML
    private void handleLineSelection() {
        String selectedLine = comboBoxLines.getValue();
        if (selectedLine != null) {
            titledPaneModels.setDisable(false);
            displayModels(selectedLine);
        }
    }

    private void initializeModelsByLine() {
        modelsPerLine.put("Cronos", createCronosModels());
        modelsPerLine.put("Ares", createAresModels());
    }

    private Map<String, List<String>> createCronosModels() {
        Map<String, List<String>> modelsCronos = new LinkedHashMap<>();
        //CRONOS CATEGORY
        modelsCronos.put("Cronos Old", Arrays.asList("Cronos 6001-A", "Cronos 6003", "Cronos 7023"));
        modelsCronos.put("Cronos L", Arrays.asList("Cronos 6021L", "Cronos 6021L", "Cronos 7023L"));
        modelsCronos.put("Cronos-NG", Arrays.asList("Cronos 6001-NG", "Cronos 6003-NG", "Cronos 6021-NG", "Cronos 6031-NG", "Cronos 7021-NG", "Cronos 7023-NG"));
        return modelsCronos;
    }

    private Map<String, List<String>> createAresModels() {
        Map<String, List<String>> modelsAres = new LinkedHashMap<>();
        //ARES CATEGORY
        modelsAres.put("Ares TB", Arrays.asList("ARES 7021", "ARES 7031", "ARES 7023"));
        modelsAres.put("Ares THS", Arrays.asList("ARES 8023 15", "ARES 8023 200", "ARES 8023 2,5"));
        return modelsAres;
    }

    private void displayModels(String selectedLine) {
        Map<String, List<String>> subcategories = modelsPerLine.get(selectedLine);
        if (subcategories != null) {
            TreeItem<String> rootItem = new TreeItem<>(selectedLine);
            subcategories.forEach((subcategory, models) -> {
                TreeItem<String> subcategoryItem = new TreeItem<>(subcategory);
                models.forEach(model -> subcategoryItem.getChildren().add(new TreeItem<>(model)));
                rootItem.getChildren().add(subcategoryItem);
            });
            treeView.setRoot(rootItem);
            expandModels();
        }
    }

    private void expandModels() {
        titledPaneModels.setExpanded(true);
        accordion.setExpandedPane(accordion.getPanes().get(1));
    }
}
