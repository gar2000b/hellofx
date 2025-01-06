package com.example.hellofx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScrollableBoxesApp extends Application {

    private final VBox boxContainer = new VBox(); // Container for the graphical items
    private int boxCount = 0; // Counter to distinguish boxes
    private Rectangle dragIndicator;

    // Define the blue color for the boxes
    private static final Color BOX_COLOR = Color.web("#4A90E2");
    StackPane dragIndicatorStackPane;

    private static final String DEFAULT_COMPONENT = "Default Component";
    private static final String BEGIN_COMPONENT = "Begin Component";
    private static final String END_COMPONENT = "End Component";
    private static final String EVOLUTIONARY_SEARCH_COMPONENT = "Evolutionary Search Component";
    private static final String DATA_LOAD_COMPONENT = "Data Load Component";
    private static final String GENERIC_WRAPPER_COMPONENT = "Generic Wrapper Component";
    private static final String SIGNALS_WRAPPER_COMPONENT = "Signals Wrapper Component";
    private static final String SIGNAL_GENERATION_COMPONENT = "Signal Generation Component";
    private static final String STANDARD_COMPONENT = "Standard Component";
    private static final String CODE_BLOCK_COMPONENT = "Code Block Component";

    private static final Set<String> VALID_COMPONENTS = Set.of(
            BEGIN_COMPONENT,
            END_COMPONENT,
            DATA_LOAD_COMPONENT,
            GENERIC_WRAPPER_COMPONENT,
            SIGNALS_WRAPPER_COMPONENT,
            SIGNAL_GENERATION_COMPONENT,
            STANDARD_COMPONENT,
            CODE_BLOCK_COMPONENT,
            EVOLUTIONARY_SEARCH_COMPONENT
            );

    private static final Color DEFAULT_COMPONENT_COLOUR = Color.web("#4A90E2");
    private static final Color BEGIN_COMPONENT_COLOUR = Color.web("#A9A9A9");
    private static final Color END_COMPONENT_COLOUR = Color.web("#808080");
    private static final Color DATA_LOAD_COMPONENT_COLOUR = Color.web("#8BC34A");
    private static final Color GENERIC_WRAPPER_COMPONENT_COLOUR = Color.web("#FF9800");
    private static final Color SIGNALS_WRAPPER_COMPONENT_COLOUR = Color.web("#4A90E2");
    private static final Color SIGNAL_GENERATION_COMPONENT_COLOUR = Color.web("#5AA7FF");
    private static final Color STANDARD_COMPONENT_COLOUR = Color.web("#1ABC9C");
    private static final Color CODE_BLOCK_COMPONENT_COLOUR = Color.web("#9B59B6");
    private static final Color EVOLUTIONARY_SEARCH_COMPONENT_COLOUR = Color.web("#F44336");

    @Override
    public void start(Stage primaryStage) {
        dragIndicator = new Rectangle(200, 2, Color.ORANGE);
        dragIndicator.setVisible(false); // Initially hidden
        dragIndicatorStackPane = new StackPane(dragIndicator);
        dragIndicatorStackPane.setAlignment(Pos.CENTER);

        // Left-hand side scrollable area
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(boxContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300); // Fixed height for the scroll area
        scrollPane.setStyle("-fx-background: #2E2E2E; -fx-background-color: #2E2E2E; -fx-background-radius: 5; -fx-border-radius: 5;"); // Match the gray background

        // Add a border to the boxContainer
        boxContainer.setStyle(
                "-fx-padding: 10; " +
                        "-fx-background-color: #2E2E2E; " +
                        "-fx-border-color: black; " +  // Border color
                        "-fx-border-width: 0;"        // Border thickness
        );
        boxContainer.setSpacing(0); // Remove spacing between child nodes
        boxContainer.getChildren().add(0, dragIndicatorStackPane);

        // Right-hand side controls
        Label titleLabel = new Label("Components:");

        // Create a Begin Component box
        Rectangle beginComponentBox = new Rectangle(150, 50, Color.web("#A9A9A9"));
        beginComponentBox.setArcWidth(10); // Rounded corners
        beginComponentBox.setArcHeight(10);
        beginComponentBox.setStroke(Color.BLACK); // Add a black border
        beginComponentBox.setStrokeWidth(1);

        Text beginComponentText = new Text("Begin");
        beginComponentText.setUnderline(true);
        beginComponentText.setFill(Color.BLACK);
        beginComponentText.setTextAlignment(TextAlignment.CENTER);

        StackPane beginComponentBoxPane = new StackPane(beginComponentBox, beginComponentText);
        beginComponentBoxPane.setAlignment(Pos.CENTER);

        Tooltip beginComponentToolTip = new Tooltip("Begin Component");
        Tooltip.install(beginComponentBoxPane, beginComponentToolTip);
        beginComponentToolTip.setShowDelay(Duration.millis(100));

        beginComponentBoxPane.setOnMousePressed(event -> {
            beginComponentBoxPane.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        beginComponentBoxPane.setOnMouseReleased(event -> {
            beginComponentBoxPane.setStyle(""); // Reset the border
        });

        beginComponentBoxPane.setOnDragDetected(event -> {
            var db = beginComponentBoxPane.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(BEGIN_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        beginComponentBoxPane.setOnDragDone(event -> {
            beginComponentBoxPane.setStyle(""); // Ensure border is reset
            event.consume();
        });

        // Create an End Component box
        Rectangle endComponentBox = new Rectangle(150, 50, Color.web("#808080"));
        endComponentBox.setArcWidth(10); // Rounded corners
        endComponentBox.setArcHeight(10);
        endComponentBox.setStroke(Color.BLACK); // Add a black border
        endComponentBox.setStrokeWidth(1);

        Text endComponentText = new Text("End");
        endComponentText.setUnderline(true);
        endComponentText.setFill(Color.BLACK);
        endComponentText.setTextAlignment(TextAlignment.CENTER);

        StackPane endComponentBoxPane = new StackPane(endComponentBox, endComponentText);
        endComponentBoxPane.setAlignment(Pos.CENTER);

        Tooltip endComponentToolTip = new Tooltip("End Component");
        Tooltip.install(endComponentBoxPane, endComponentToolTip);
        endComponentToolTip.setShowDelay(Duration.millis(100));

        endComponentBoxPane.setOnMousePressed(event -> {
            endComponentBoxPane.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        endComponentBoxPane.setOnMouseReleased(event -> {
            endComponentBoxPane.setStyle(""); // Reset the border
        });

        endComponentBoxPane.setOnDragDetected(event -> {
            var db = endComponentBoxPane.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(END_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        endComponentBoxPane.setOnDragDone(event -> {
            endComponentBoxPane.setStyle(""); // Ensure border is reset
            event.consume();
        });

        // Create a Data Load Component box
        Rectangle dataLoadComponentBox1 = new Rectangle(150, 50, Color.web("#8BC34A"));
        dataLoadComponentBox1.setArcWidth(10); // Rounded corners
        dataLoadComponentBox1.setArcHeight(10);
        dataLoadComponentBox1.setStroke(Color.BLACK); // Add a black border
        dataLoadComponentBox1.setStrokeWidth(1);

        Text dataLoadComponentText1 = new Text("Load Single Token Data");
        dataLoadComponentText1.setUnderline(true);
        dataLoadComponentText1.setFill(Color.BLACK);
        dataLoadComponentText1.setTextAlignment(TextAlignment.CENTER);

        StackPane dataLoadComponentBoxPane1 = new StackPane(dataLoadComponentBox1, dataLoadComponentText1);
        dataLoadComponentBoxPane1.setAlignment(Pos.CENTER);

        Tooltip dataLoadComponentToolTip = new Tooltip("Data Load Component");
        Tooltip.install(dataLoadComponentBoxPane1, dataLoadComponentToolTip);
        dataLoadComponentToolTip.setShowDelay(Duration.millis(100));

        // Create a Generic Wrapper Component box
        Rectangle genericWrapperComponentBox1 = new Rectangle(150, 50, Color.web("#FF9800"));
        genericWrapperComponentBox1.setArcWidth(10); // Rounded corners
        genericWrapperComponentBox1.setArcHeight(10);
        genericWrapperComponentBox1.setStroke(Color.BLACK); // Add a black border
        genericWrapperComponentBox1.setStrokeWidth(1);

        Text genericWrapperComponentText1 = new Text("Execute Standard \nTrade Rules");
        genericWrapperComponentText1.setUnderline(true);
        genericWrapperComponentText1.setFill(Color.BLACK);
        genericWrapperComponentText1.setTextAlignment(TextAlignment.CENTER);

        StackPane genericWrapperComponentBoxPane1 = new StackPane(genericWrapperComponentBox1, genericWrapperComponentText1);
        genericWrapperComponentBoxPane1.setAlignment(Pos.CENTER);

        Tooltip genericWrapperComponentToolTip = new Tooltip("Generic Wrapper Component");
        Tooltip.install(genericWrapperComponentBoxPane1, genericWrapperComponentToolTip);
        genericWrapperComponentToolTip.setShowDelay(Duration.millis(100));

        // Create a Signals Wrapper Component box
        Rectangle signalsWrapperComponentBox1 = new Rectangle(150, 50, Color.web("#4A90E2"));
        signalsWrapperComponentBox1.setArcWidth(10); // Rounded corners
        signalsWrapperComponentBox1.setArcHeight(10);
        signalsWrapperComponentBox1.setStroke(Color.BLACK); // Add a black border
        signalsWrapperComponentBox1.setStrokeWidth(1);

        Text signalsWrapperComponentText1 = new Text("Generate ZOD signals");
        signalsWrapperComponentText1.setUnderline(true);
        signalsWrapperComponentText1.setFill(Color.BLACK);
        signalsWrapperComponentText1.setTextAlignment(TextAlignment.CENTER);

        StackPane signalsWrapperComponentBoxPane1 = new StackPane(signalsWrapperComponentBox1, signalsWrapperComponentText1);
        signalsWrapperComponentBoxPane1.setAlignment(Pos.CENTER);

        Tooltip signalsWrapperComponentToolTip = new Tooltip("Signals Wrapper Component");
        Tooltip.install(signalsWrapperComponentBoxPane1, signalsWrapperComponentToolTip);
        signalsWrapperComponentToolTip.setShowDelay(Duration.millis(100));

        // Create a Signal Generation Component box
        Rectangle signalGenerationComponentBox1 = new Rectangle(150, 50, Color.web("#5AA7FF"));
        signalGenerationComponentBox1.setArcWidth(10); // Rounded corners
        signalGenerationComponentBox1.setArcHeight(10);
        signalGenerationComponentBox1.setStroke(Color.BLACK); // Add a black border
        signalGenerationComponentBox1.setStrokeWidth(1);

        Text signalsGenerationComponentText1 = new Text("Generate Simple\nMoving Average");
        signalsGenerationComponentText1.setUnderline(true);
        signalsGenerationComponentText1.setFill(Color.BLACK);
        signalsGenerationComponentText1.setTextAlignment(TextAlignment.CENTER);

        StackPane signalGenerationComponentBoxPane1 = new StackPane(signalGenerationComponentBox1, signalsGenerationComponentText1);
        signalGenerationComponentBoxPane1.setAlignment(Pos.CENTER);

        Tooltip signalGenerationComponentToolTip = new Tooltip("Signal Generation Component");
        Tooltip.install(signalGenerationComponentBoxPane1, signalGenerationComponentToolTip);
        signalGenerationComponentToolTip.setShowDelay(Duration.millis(100));

        signalGenerationComponentBoxPane1.setOnMousePressed(event -> {
            signalGenerationComponentBoxPane1.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        signalGenerationComponentBoxPane1.setOnMouseReleased(event -> {
            signalGenerationComponentBoxPane1.setStyle(""); // Reset the border
        });

        signalGenerationComponentBoxPane1.setOnDragDetected(event -> {
            var db = signalGenerationComponentBoxPane1.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(SIGNAL_GENERATION_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        signalGenerationComponentBoxPane1.setOnDragDone(event -> {
            signalGenerationComponentBoxPane1.setStyle(""); // Ensure border is reset
            event.consume();
        });

        // Create a Signal Generation Component box
        Rectangle signalGenerationComponentBox2 = new Rectangle(150, 50, Color.web("#5AA7FF"));
        signalGenerationComponentBox2.setArcWidth(10); // Rounded corners
        signalGenerationComponentBox2.setArcHeight(10);
        signalGenerationComponentBox2.setStroke(Color.BLACK); // Add a black border
        signalGenerationComponentBox2.setStrokeWidth(1);

        Text signalsGenerationComponentText2 = new Text("Generate ZOD Order\n Signal");
        signalsGenerationComponentText2.setUnderline(true);
        signalsGenerationComponentText2.setFill(Color.BLACK);
        signalsGenerationComponentText2.setTextAlignment(TextAlignment.CENTER);

        StackPane signalGenerationComponentBoxPane2 = new StackPane(signalGenerationComponentBox2, signalsGenerationComponentText2);
        signalGenerationComponentBoxPane2.setAlignment(Pos.CENTER);

        Tooltip.install(signalGenerationComponentBoxPane2, signalGenerationComponentToolTip);
        signalGenerationComponentToolTip.setShowDelay(Duration.millis(100));

        signalGenerationComponentBoxPane2.setOnMousePressed(event -> {
            signalGenerationComponentBoxPane2.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        signalGenerationComponentBoxPane2.setOnMouseReleased(event -> {
            signalGenerationComponentBoxPane2.setStyle(""); // Reset the border
        });

        signalGenerationComponentBoxPane2.setOnDragDetected(event -> {
            var db = signalGenerationComponentBoxPane2.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(SIGNAL_GENERATION_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        signalGenerationComponentBoxPane2.setOnDragDone(event -> {
            signalGenerationComponentBoxPane2.setStyle(""); // Ensure border is reset
            event.consume();
        });

        // Create a Standard Component box
        Rectangle standardComponentBox = new Rectangle(150, 50, Color.web("#1ABC9C"));
        standardComponentBox.setArcWidth(10); // Rounded corners
        standardComponentBox.setArcHeight(10);
        standardComponentBox.setStroke(Color.BLACK); // Add a black border
        standardComponentBox.setStrokeWidth(1);

        Text standardComponentText = new Text("Calculate Profit");
        standardComponentText.setUnderline(true);
        standardComponentText.setFill(Color.BLACK);
        standardComponentText.setTextAlignment(TextAlignment.CENTER);

        StackPane standardComponentBoxPane = new StackPane(standardComponentBox, standardComponentText);
        standardComponentBoxPane.setAlignment(Pos.CENTER);

        Tooltip standardComponentToolTip = new Tooltip("Standard Component");
        Tooltip.install(standardComponentBoxPane, standardComponentToolTip);
        standardComponentToolTip.setShowDelay(Duration.millis(100));

        standardComponentBoxPane.setOnMousePressed(event -> {
            standardComponentBoxPane.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        standardComponentBoxPane.setOnMouseReleased(event -> {
            standardComponentBoxPane.setStyle(""); // Reset the border
        });

        standardComponentBoxPane.setOnDragDetected(event -> {
            var db = standardComponentBoxPane.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(STANDARD_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        standardComponentBoxPane.setOnDragDone(event -> {
            standardComponentBoxPane.setStyle(""); // Ensure border is reset
            event.consume();
        });

        // Create a Code Block Component box
        Rectangle codeBlockComponentBox = new Rectangle(150, 50, Color.web("#9B59B6"));
        codeBlockComponentBox.setArcWidth(10); // Rounded corners
        codeBlockComponentBox.setArcHeight(10);
        codeBlockComponentBox.setStroke(Color.BLACK); // Add a black border
        codeBlockComponentBox.setStrokeWidth(1);

        Text codeBlockComponentText = new Text("Calculate xOverHodl");
        codeBlockComponentText.setUnderline(true);
        codeBlockComponentText.setFill(Color.BLACK);
        codeBlockComponentText.setTextAlignment(TextAlignment.CENTER);

        StackPane codeBlockComponentBoxPane = new StackPane(codeBlockComponentBox, codeBlockComponentText);
        codeBlockComponentBoxPane.setAlignment(Pos.CENTER);

        Tooltip codeBlockComponentToolTip = new Tooltip("Code Block Component");
        Tooltip.install(codeBlockComponentBoxPane, codeBlockComponentToolTip);
        codeBlockComponentToolTip.setShowDelay(Duration.millis(100));

        codeBlockComponentBoxPane.setOnMousePressed(event -> {
            codeBlockComponentBoxPane.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        codeBlockComponentBoxPane.setOnMouseReleased(event -> {
            codeBlockComponentBoxPane.setStyle(""); // Reset the border
        });

        codeBlockComponentBoxPane.setOnDragDetected(event -> {
            var db = codeBlockComponentBoxPane.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(CODE_BLOCK_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        codeBlockComponentBoxPane.setOnDragDone(event -> {
            codeBlockComponentBoxPane.setStyle(""); // Ensure border is reset
            event.consume();
        });

        // Create a Genetic Algorithm Component box
        Rectangle geneticAlgorithmComponentBox = new Rectangle(150, 50, Color.web("#F44336"));
        geneticAlgorithmComponentBox.setArcWidth(10); // Rounded corners
        geneticAlgorithmComponentBox.setArcHeight(10);
        geneticAlgorithmComponentBox.setStroke(Color.BLACK); // Add a black border
        geneticAlgorithmComponentBox.setStrokeWidth(1);

        Text geneticAlgorithmComponentText = new Text("Genetic Algorithm");
        geneticAlgorithmComponentText.setUnderline(true);
        geneticAlgorithmComponentText.setFill(Color.BLACK);
        geneticAlgorithmComponentText.setTextAlignment(TextAlignment.CENTER);

        StackPane geneticAlgorithmComponentBoxPane = new StackPane(geneticAlgorithmComponentBox, geneticAlgorithmComponentText);
        geneticAlgorithmComponentBoxPane.setAlignment(Pos.CENTER);

        Tooltip geneticAlgorithmComponentToolTip = new Tooltip("Evolutionary Search Component");
        Tooltip.install(geneticAlgorithmComponentBoxPane, geneticAlgorithmComponentToolTip);
        geneticAlgorithmComponentToolTip.setShowDelay(Duration.millis(100));

        geneticAlgorithmComponentBoxPane.setOnMousePressed(event -> {
            geneticAlgorithmComponentBoxPane.setStyle("-fx-border-color: orange; -fx-border-width: 3;");
        });

        geneticAlgorithmComponentBoxPane.setOnMouseReleased(event -> {
            geneticAlgorithmComponentBoxPane.setStyle(""); // Reset the border
        });

        geneticAlgorithmComponentBoxPane.setOnDragDetected(event -> {
            var db = geneticAlgorithmComponentBoxPane.startDragAndDrop(TransferMode.COPY);
            var content = new javafx.scene.input.ClipboardContent();
            content.putString(EVOLUTIONARY_SEARCH_COMPONENT);
            db.setContent(content);
            event.consume();
        });

        geneticAlgorithmComponentBoxPane.setOnDragDone(event -> {
            geneticAlgorithmComponentBoxPane.setStyle(""); // Ensure border is reset
            event.consume();
        });

        /**
         * Logic for when components are dragged on to the scrollPane/boxContainer
         */
        AtomicBoolean indicatorAdded = new AtomicBoolean(false);

        scrollPane.setOnDragOver(event -> {
            if (!VALID_COMPONENTS.contains(event.getDragboard().getString())) {
                return;
            }

            if (event.getGestureSource() != boxContainer && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }

            for (int i = 0; i < boxContainer.getChildren().size(); i++) {
                if (boxContainer.getChildren().get(i) instanceof VBox vBox) {
                    // Check if the mouse is above the first box
                    if (i == 0 && vBox.localToScene(vBox.getBoundsInLocal()).getMinY() > event.getSceneY()) {
                        boxContainer.getChildren().remove(dragIndicatorStackPane);
                        boxContainer.getChildren().add(0, dragIndicatorStackPane); // Add at the very top
                        indicatorAdded.set(true);
                        break;
                    }

                    // Check if the mouse is over this VBox
                    if (vBox.localToScene(vBox.getBoundsInLocal()).contains(event.getSceneX(), event.getSceneY())) {
                        boxContainer.getChildren().remove(dragIndicatorStackPane);
                        if (i + 1 <= boxContainer.getChildren().size()) {
                            boxContainer.getChildren().add(i + 1, dragIndicatorStackPane);
                        } else {
                            boxContainer.getChildren().add(dragIndicatorStackPane);
                        }
                        indicatorAdded.set(true);
                        break;
                    }
                }
            }

            // Fallback: Add the drag indicator at the very bottom when no VBox is hovered
            if (!indicatorAdded.get()) {
                boxContainer.getChildren().remove(dragIndicatorStackPane);
                boxContainer.getChildren().add(dragIndicatorStackPane); // Add at the bottom
            }

            // Ensure the indicator is visible
            dragIndicator.setVisible(true);

            // Auto-scroll logic
            double viewportHeight = scrollPane.getViewportBounds().getHeight();
            double contentHeight = scrollPane.getContent().getBoundsInLocal().getHeight();
            double yPos = event.getY();

            // Scroll down if dragging near the bottom
            if (yPos > scrollPane.getLayoutY() + viewportHeight - 40) {
                double newVValue = Math.min(1.0, scrollPane.getVvalue() + 0.02);
                scrollPane.setVvalue(newVValue);
            }

            // Scroll up if dragging near the top
            if (yPos < scrollPane.getLayoutY() + 10) {
                double newVValue = Math.max(0.0, scrollPane.getVvalue() - 0.02);
                scrollPane.setVvalue(newVValue);
            }

            event.consume();
        });

        scrollPane.setOnDragExited(event -> {
            if (!VALID_COMPONENTS.contains(event.getDragboard().getString())) {
                return;
            }

            // Hide the drag indicator when dragging exits the scroll pane
            dragIndicator.setVisible(false);
            event.consume();
        });

        scrollPane.setOnDragDropped(event -> {
            if (!VALID_COMPONENTS.contains(event.getDragboard().getString())) {
                return;
            }

            if (event.getDragboard().hasString()) {
                // Get the current position of the dragIndicator
                int indicatorIndex = boxContainer.getChildren().indexOf(dragIndicatorStackPane);

                // Add a new box at the appropriate position
                addComponentInstance(indicatorIndex, event.getDragboard().getString());

                // Reset styles and visibility
                beginComponentBoxPane.setStyle(""); // Reset the border
                dragIndicator.setVisible(false); // Hide the drag indicator
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        Button addAboveButton = new Button("Above");
        Button addBelowButton = new Button("Below");
        Button clearButton = new Button("Clear");
        Label widthLabel = new Label(); // Label to display boxContainer width

        // Bind the label text to the width of the boxContainer
        widthLabel.textProperty().bind(Bindings.format("Box Container Width: %.2f", boxContainer.widthProperty()));

        // Components container for buttons and label
        VBox componentsContainer = new VBox(10, titleLabel, beginComponentBoxPane, endComponentBoxPane, dataLoadComponentBoxPane1, genericWrapperComponentBoxPane1, signalsWrapperComponentBoxPane1, signalGenerationComponentBoxPane1, signalGenerationComponentBoxPane2, standardComponentBoxPane, codeBlockComponentBoxPane, geneticAlgorithmComponentBoxPane, addAboveButton, addBelowButton, clearButton, widthLabel);
        componentsContainer.setStyle("-fx-padding: 10; -fx-background-color: #2E2E2E;");
        componentsContainer.setPrefWidth(200);

        // Scrollable container for components
        ScrollPane componentsScrollPane = new ScrollPane();
        componentsScrollPane.setContent(componentsContainer);
        componentsScrollPane.setFitToWidth(true);
        componentsScrollPane.setPrefHeight(300); // Set appropriate height for the scroll pane
        componentsScrollPane.setStyle("-fx-background: #2E2E2E; -fx-background-color: #2E2E2E; -fx-background-radius: 5; -fx-border-radius: 5;"); // Match gray background

        // Main layout
        HBox mainLayout = new HBox(10, scrollPane, componentsScrollPane);
        mainLayout.setStyle("-fx-background-color: #1E1E1E; -fx-padding: 20; "); // Set a light gray background
        HBox.setHgrow(scrollPane, Priority.ALWAYS); // Let the scroll area expand to fill space

        // Add event handlers for buttons
        addAboveButton.setOnAction(e -> addComponentInstanceAbove());
        addBelowButton.setOnAction(e -> addComponentInstanceBelow());
        clearButton.setOnAction(e -> clearComponentInstances());

        // Scene and stage
        Scene scene = new Scene(mainLayout, 800, 800, Color.web("#2E2E2E")); // Set scene background to gray
        primaryStage.setScene(scene);
        primaryStage.setTitle("Integrated Development Platform (IDP) for time-series and evolutionary solutions!");
        primaryStage.getIcons().add(new javafx.scene.image.Image(getClass().getResourceAsStream("/graph.png")));
        primaryStage.show();
    }

    private void addComponentInstance(int position, String componentType) {
        VBox componentInstance = createComponentInstance(componentType);
        if (position >= 0 && position <= boxContainer.getChildren().size()) {
            boxContainer.getChildren().add(position, componentInstance); // Add at the specified position
        } else {
            boxContainer.getChildren().add(componentInstance); // Fallback to adding at the end
        }
    }

    private void addComponentInstanceAbove() {
        boxContainer.getChildren().add(0, createComponentInstance(DEFAULT_COMPONENT));
    }

    private void addComponentInstanceBelow() {
        boxContainer.getChildren().add(createComponentInstance(DEFAULT_COMPONENT));
    }

    private void clearComponentInstances() {
        boxContainer.getChildren().clear();
        boxCount = 0; // Reset box counter
        boxContainer.getChildren().add(0, dragIndicatorStackPane);
    }

    private VBox createComponentInstance(String componentType) {
        int currentBoxNumber = ++boxCount; // Capture the current box number

        Rectangle componentInstance = null;
        // Create the box
        if (componentType.equals(DEFAULT_COMPONENT)) {
            componentInstance = new Rectangle(500, 150, DEFAULT_COMPONENT_COLOUR); // Wider fixed size
        }
        if (componentType.equals(BEGIN_COMPONENT)) {
            componentInstance = new Rectangle(200, 70, BEGIN_COMPONENT_COLOUR); // Wider fixed size
        }
        if (componentType.equals(END_COMPONENT)) {
            componentInstance = new Rectangle(200, 70, END_COMPONENT_COLOUR); // Wider fixed size
        }
        if (componentType.equals(SIGNAL_GENERATION_COMPONENT)) {
            componentInstance = new Rectangle(500, 150, SIGNAL_GENERATION_COMPONENT_COLOUR); // Wider fixed size
        }
        if (componentType.equals(STANDARD_COMPONENT)) {
            componentInstance = new Rectangle(500, 150, STANDARD_COMPONENT_COLOUR); // Wider fixed size
        }
        if (componentType.equals(CODE_BLOCK_COMPONENT)) {
            componentInstance = new Rectangle(500, 150, CODE_BLOCK_COMPONENT_COLOUR); // Wider fixed size
        }
        if (componentType.equals(EVOLUTIONARY_SEARCH_COMPONENT)) {
            componentInstance = new Rectangle(500, 150, EVOLUTIONARY_SEARCH_COMPONENT_COLOUR); // Wider fixed size
        }
        componentInstance.setArcWidth(10);
        componentInstance.setArcHeight(10); // Rounded corners for aesthetics
        componentInstance.setStroke(Color.BLACK); // Add black border
        componentInstance.setStrokeWidth(1); // Set border thickness to 1 pixel

        // Add a number label in the middle of the box
        Text numberText = new Text(String.valueOf(currentBoxNumber));
        numberText.setFill(Color.WHITE);

        // Create a StackPane to combine the box and the text
        StackPane boxPane = new StackPane(componentInstance, numberText);
        boxPane.setAlignment(Pos.CENTER);

        // Add click event to display box number
        boxPane.setOnMouseClicked(event -> showBoxNumberDialog(currentBoxNumber));

        // Create a VBox to stack the box and the arrow
        VBox boxWithArrow = new VBox();
        boxWithArrow.setAlignment(Pos.TOP_CENTER); // Center alignment
        boxWithArrow.setSpacing(0); // Remove any spacing

        // Add the box and the arrow to the VBox
        boxWithArrow.getChildren().addAll(
                boxPane, // Box with centered number
                createArrow() // Arrow below the box
        );

        return boxWithArrow;
    }

    private StackPane createArrow() {
        // Dimensions reduced by about a third
        double bodyWidth = 7;   // Reduced width of the vertical body
        double bodyHeight = 14; // Reduced height of the vertical body
        double arrowHeadWidth = 14; // Reduced width of the arrowhead base
        double arrowHeadHeight = 14; // Reduced height of the arrowhead

        // Create the vertical body of the arrow (no bottom border)
        Path body = new Path();
        body.getElements().addAll(
                new MoveTo(0, 0),                  // Top-left corner
                new LineTo(bodyWidth, 0),          // Top-right corner
                new LineTo(bodyWidth, bodyHeight), // Bottom-right corner
                new LineTo(0, bodyHeight),         // Bottom-left corner
                new LineTo(0, 0)                   // Back to Top-left corner
        );
        body.setFill(BOX_COLOR);          // Fill the body with blue
        body.setStroke(Color.BLACK);      // Add black borders
        body.setStrokeWidth(1);           // 1px thick borders

        // Create the arrowhead
        Polygon arrowHead = new Polygon();
        arrowHead.getPoints().addAll(
                0.0, 0.0,                       // Top point of the arrowhead
                arrowHeadWidth, 0.0,            // Right point of the base
                arrowHeadWidth / 2.0, arrowHeadHeight // Bottom point of the arrowhead
        );
        arrowHead.setFill(BOX_COLOR);
        arrowHead.setStroke(Color.BLACK);
        arrowHead.setStrokeWidth(1);

        // Combine the body and the arrowhead in a vertical stack
        VBox arrow = new VBox(arrowHead);
        arrow.setAlignment(Pos.TOP_CENTER);
        arrow.setSpacing(0); // Remove any gap between the body and arrowhead

        // Wrap the arrow in a StackPane for proper alignment
        StackPane arrowPane = new StackPane(arrow);
        arrowPane.setAlignment(Pos.CENTER);

        return arrowPane;
    }

    private void showBoxNumberDialog(int boxNumber) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Box Number");
        alert.setHeaderText(null);
        alert.setContentText("You clicked on Box #" + boxNumber);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
