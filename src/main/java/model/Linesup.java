package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Linesup{
    public ObservableList<String> initializeLines() {
        return FXCollections.observableArrayList(
                "Cronos",
                "Ares"
        );
    }
}