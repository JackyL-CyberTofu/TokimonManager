package ca.cmpt213.asn4.web.ui;

import ca.cmpt213.asn4.web.models.Tokimon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

//import java.awt.*;
//package test.jackson;

/**
 *  Responsible for the UI of the the Tokimon Manager
 *  Developer: Jacky Lim
 */

public class TokimonApplication extends Application {

    int rowCount = 3;
    int colCount = 0;
    int labelSizeCount = 0;
    Label labelArray[] = new Label[1000];

    int weightValue = 0;
    int heightValue = 0;
    int strengthValue = 0;
    String abilityValue = "";
    String colorValue = "";

    int tokiWeight = 0;
    int tokiHeight = 0;

    HttpServletResponse response;

    Image tokimonPNG = new Image("file:zzz.jpg", 300, 300, false, false);
    Image greenPNG = new Image("file:green.png");
    Image redPNG = new Image("file:red.png");
    Image bluePNG = new Image("file:blue.png");
    Image purplePNG = new Image("file:purple.png");

    ImageView tokimonImage = new ImageView(tokimonPNG);

    Tokimon selectedToki = new Tokimon();

    boolean finished = false;

    public static void main(String[] args) {
        // Launch the application.
        launch(args);
    }

    GridPane gridpane = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        // Create a Label component.
        Button displayTokimon = new Button("Display Tokimon's");
        Button addTokimon = new Button("Add New Tokimon");
        Button editTokimon = new Button("Edit Tokimon");
        Button deleteTokimon = new Button("Delete Tokimon");
        Button saveToFile = new Button("Save Changes");
        Button visualizeToki = new Button("Visualize Tokimon");
        Button empty2 = new Button("About/Help");
        Label nameLabel = new Label("Name");
        Label idLabel = new Label("ID");
        Label weightLabel = new Label("Weight");
        Label heightLabel = new Label("Height");
        Label abilityLabel = new Label("Ability");
        Label strengthLabel = new Label("Strength");
        Label colorLabel = new Label("Color");

        Label nameLabel2 = new Label("Name: ");
        Label idLabel2 = new Label("ID: ");
        Label weightLabel2 = new Label("Weight: ");
        Label heightLabel2 = new Label("Height: ");
        Label abilityLabel2 = new Label("Ability: ");
        Label strengthLabel2 = new Label("Strength: ");
        Label colorLabel2 = new Label("Color: ");

        GridPane gridpane = new GridPane();
        gridpane.add(displayTokimon, 0, 0);
        gridpane.add(addTokimon, 1, 0);
        gridpane.add(editTokimon, 2, 0);
        gridpane.add(deleteTokimon, 3, 0);
        gridpane.add(saveToFile, 4, 0);
        gridpane.add(visualizeToki,5,0);
        gridpane.add(empty2, 6, 0);
        gridpane.add(nameLabel,0,1);
        gridpane.add(idLabel,1,1);
        gridpane.add(weightLabel,2,1);
        gridpane.add(heightLabel,3,1);
        gridpane.add(abilityLabel,4,1);
        gridpane.add(strengthLabel,5,1);
        gridpane.add(colorLabel,6,1);


        //VBox vb = new VBox(20, gridpane);

        displayTokimon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    URL url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    System.out.println(connection.getResponseCode());
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }

                    // convert JSON array to Java List
                    ObjectMapper mapper = new ObjectMapper();
                    List<Tokimon> users = new ObjectMapper().readValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), new TypeReference<List<Tokimon>>() {});

                    for (int i=0; i<labelSizeCount; i++){
                        labelArray[i].setText("");
                    }
                    labelSizeCount = 0;
                    for (Tokimon eachTokimon : users){
                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(eachTokimon.getName());
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        colCount = colCount + 1;
                        labelSizeCount = labelSizeCount + 1;

                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(Integer.toString(eachTokimon.getPid()));
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        colCount = colCount + 1;
                        labelSizeCount = labelSizeCount + 1;

                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(Integer.toString(eachTokimon.getWeight()));
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        colCount = colCount + 1;
                        labelSizeCount = labelSizeCount + 1;

                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(Integer.toString(eachTokimon.getHeight()));
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        colCount = colCount + 1;
                        labelSizeCount = labelSizeCount + 1;

                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(eachTokimon.getAbility());
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        colCount = colCount + 1;
                        labelSizeCount = labelSizeCount + 1;

                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(Integer.toString(eachTokimon.getStrength()));
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        colCount = colCount + 1;
                        labelSizeCount = labelSizeCount + 1;

                        labelArray[labelSizeCount] = new Label();
                        labelArray[labelSizeCount].setText(eachTokimon.getColor());
                        gridpane.add(labelArray[labelSizeCount], colCount, rowCount);
                        labelSizeCount = labelSizeCount + 1;
                        colCount = 0;
                        rowCount = rowCount + 1;
                    }
                    rowCount = 3;
                    colCount = 0;
                    connection.disconnect();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        addTokimon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    URL url = new URL("http://localhost:8080/api/tokimon/add");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    // get textbox values

                    //Name TextField
                    TextField nameTextField = new TextField();

                    //Weight Slider
                    Slider weightSlider = new Slider(0,100,50);
                    weightSlider.setMajorTickUnit(5);
                    weightSlider.setShowTickMarks(true);
                    weightSlider.setShowTickLabels(true);
                    weightSlider.setSnapToTicks(true);

                    //Height Slider
                    Slider heightSlider = new Slider(0,100,50);
                    heightSlider.setMajorTickUnit(5);
                    heightSlider.setShowTickMarks(true);
                    heightSlider.setShowTickLabels(true);
                    heightSlider.setSnapToTicks(true);

                    //Ability ComboBox
                    ComboBox<String> abilityComboBox = new ComboBox<>();
                    abilityComboBox.getItems().addAll("Fire","Water","Air","Ground");
                    abilityComboBox.getSelectionModel().selectFirst();

                    //Strength Slider
                    Slider strengthSlider = new Slider(0,100,50);
                    strengthSlider.setMajorTickUnit(5);
                    strengthSlider.setShowTickMarks(true);
                    strengthSlider.setShowTickLabels(true);
                    strengthSlider.setSnapToTicks(true);

                    //Color ComboBox
                    ComboBox<String> colorComboBox = new ComboBox<>();
                    colorComboBox.getItems().addAll("Blue","Green","Red","Purple");
                    colorComboBox.getSelectionModel().selectFirst();

                    //Labels
                    Label nameLabel = new Label("Name: ");
                    Label weightLabel = new Label("Weight: ");
                    Label heightLabel = new Label("Height: ");
                    Label abilityLabel = new Label("Ability: ");
                    Label strengthLabel = new Label("Strength: ");
                    Label colorLabel = new Label("Color: ");


                    Button submitButton = new Button("Submit Changes");
                    submitButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try{
                                URL url = new URL("http://localhost:8080/api/tokimon/add");
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setDoOutput(true);
                                connection.setRequestProperty("Content-Type", "application/json");

                                heightValue = (int) heightSlider.getValue();
                                weightValue = (int) weightSlider.getValue();
                                abilityValue = abilityComboBox.getValue();
                                strengthValue = (int) strengthSlider.getValue();
                                colorValue = colorComboBox.getValue();

                                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                                wr.write("{\"name\":\""+nameTextField.getText()+"\",\"weight\":"+weightValue+",\"height\":"+heightValue+",\"ability\":\""+abilityValue+"\",\"strength\":"+strengthValue+",\"color\":\""+colorValue+"\"}");
                                wr.flush();
                                wr.close();
                                connection.connect();
                                System.out.println(connection.getResponseCode());
                                connection.disconnect();
                                displayTokimon.fire();
                            }
                            catch (IOException e){
                            }
                        }
                    });

                    HBox name = new HBox(nameLabel, nameTextField);
                    HBox weight = new HBox(weightLabel, weightSlider);
                    HBox height = new HBox(heightLabel, heightSlider);
                    HBox ability = new HBox(abilityLabel, abilityComboBox);
                    HBox strength = new HBox(strengthLabel, strengthSlider);
                    HBox color = new HBox(colorLabel, colorComboBox);


                    VBox vBox = new VBox(name, weight, height, ability, strength, color, submitButton);
                    Scene scene = new Scene(vBox);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                    // add them in request body ..
                    //wr.write("{\"name\":\""+nameTextField.getText()+"\",\"weight\":"+weightValue+",\"height\":"+heightValue+",\"ability\":\""+abilityValue+"\",\"strength\":"+strengthValue+",\"color\":\""+colorValue+"\"}");
                    wr.flush();
                    wr.close();
                    connection.connect();
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();
                } catch (IOException e) {

                }


            }
        });

        deleteTokimon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    URL url = new URL("http://localhost:8080/api/tokimon/");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                    // get textbox values

                    ObjectMapper mapper = new ObjectMapper();
                    List<Tokimon> users = new ObjectMapper().readValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), new TypeReference<List<Tokimon>>() {});

                    //Name TextField
                    Label IDLabel = new Label("ID of Tokimon: ");
                    TextField IDTextField = new TextField();

                    Button submitButton = new Button("Submit Changes");
                    submitButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try{
                                try{
                                    Integer.parseInt(IDTextField.getText());
                                }
                                catch (NumberFormatException e){
                                    System.out.println("List Empty/Letters Input Illegal");
                                    IDTextField.setText("Toki Does not Exist");
                                }

                                selectedToki = null;
                                int id = Integer.parseInt(IDTextField.getText());
                                for (Tokimon eachTokimon : users) {
                                    if (eachTokimon.getPid() == id){
                                        selectedToki = eachTokimon;
                                    }
                                }

                                if (selectedToki == null)
                                    IDTextField.setText("Toki Does not exist");

                                System.out.println(IDTextField.getText());
                                URL url = new URL("http://localhost:8080/api/tokimon/"+IDTextField.getText());
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("DELETE");
                                connection.setDoOutput(true);
                                connection.setRequestProperty("Content-Type", "application/json");

                                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                                //wr.write(IDTextField.getText());
                                //wr.flush();
                                //wr.close();
                                connection.connect();
                                System.out.println(connection.getResponseCode());
                                connection.disconnect();
                                displayTokimon.fire();
                            }
                            catch (IOException e){
                            }
                        }
                    });

                    HBox selectedID = new HBox(IDLabel, IDTextField);

                    VBox vBox = new VBox(selectedID, submitButton);
                    Scene scene = new Scene(vBox);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();

                    // add them in request body ..
                    wr.flush();
                    wr.close();
                    connection.connect();
                    System.out.println(connection.getResponseCode());
                    connection.disconnect();
                } catch (IOException e) {
                }
            }
        });

        editTokimon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    URL url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    System.out.println(connection.getResponseCode());
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }

                    // convert JSON array to Java List
                    ObjectMapper mapper = new ObjectMapper();
                    List<Tokimon> users = new ObjectMapper().readValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), new TypeReference<List<Tokimon>>() {});

                    //Name TextField
                    Label IDLabel = new Label("ID of Tokimon: ");
                    TextField IDTextField = new TextField();
                    Button searchButton = new Button("Search Tokimon");

                    //Find Tokimon




                    //Name TextField
                    TextField nameTextField = new TextField();

                    //Weight Slider
                    Slider weightSlider = new Slider(0,100,50);
                    weightSlider.setMajorTickUnit(5);
                    weightSlider.setShowTickMarks(true);
                    weightSlider.setShowTickLabels(true);
                    weightSlider.setSnapToTicks(true);

                    //Height Slider
                    Slider heightSlider = new Slider(0,100,50);
                    heightSlider.setMajorTickUnit(5);
                    heightSlider.setShowTickMarks(true);
                    heightSlider.setShowTickLabels(true);
                    heightSlider.setSnapToTicks(true);

                    //Ability ComboBox
                    ComboBox<String> abilityComboBox = new ComboBox<>();
                    abilityComboBox.getItems().addAll("Fire","Water","Air","Ground");
                    abilityComboBox.getSelectionModel().selectFirst();

                    //Strength Slider
                    Slider strengthSlider = new Slider(0,100,50);
                    strengthSlider.setMajorTickUnit(5);
                    strengthSlider.setShowTickMarks(true);
                    strengthSlider.setShowTickLabels(true);
                    strengthSlider.setSnapToTicks(true);

                    //Color ComboBox
                    ComboBox<String> colorComboBox = new ComboBox<>();
                    colorComboBox.getItems().addAll("Blue","Green","Red","Purple");
                    colorComboBox.getSelectionModel().selectFirst();

                    //Labels
                    Label nameLabel = new Label("Name: ");
                    Label weightLabel = new Label("Weight: ");
                    Label heightLabel = new Label("Height: ");
                    Label abilityLabel = new Label("Ability: ");
                    Label strengthLabel = new Label("Strength: ");
                    Label colorLabel = new Label("Color: ");


                    searchButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {

                            try{
                                Integer.parseInt(IDTextField.getText());
                            }
                            catch (NumberFormatException e){
                                System.out.println("List Empty/Letters Input Illegal");
                                IDTextField.setText("Toki Does not Exist");
                            }

                            selectedToki = null;
                            int id = Integer.parseInt(IDTextField.getText());
                            for (Tokimon eachTokimon : users) {
                                if (eachTokimon.getPid() == id){
                                    selectedToki = eachTokimon;
                                }
                            }

                            if (selectedToki == null)
                                IDTextField.setText("Toki Does not exist");

                            nameTextField.setText(selectedToki.getName());
                            colorComboBox.getSelectionModel().select(selectedToki.getColor());
                            abilityComboBox.getSelectionModel().select(selectedToki.getAbility());
                            weightSlider.setValue(selectedToki.getWeight());
                            heightSlider.setValue(selectedToki.getHeight());
                            strengthSlider.setValue(selectedToki.getStrength());
                        }
                    });

                    Button submitButton = new Button("Submit Changes");
                    submitButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            try{
                                //System.out.println("Editing Tokimon");
                                URL url = new URL("http://localhost:8080/api/tokimon/change/"+IDTextField.getText()+"/");
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                connection.setRequestMethod("POST");
                                connection.setDoOutput(true);
                                connection.setRequestProperty("Content-Type", "application/json");

                                heightValue = (int) heightSlider.getValue();
                                weightValue = (int) weightSlider.getValue();
                                abilityValue = abilityComboBox.getValue();
                                strengthValue = (int) strengthSlider.getValue();
                                colorValue = colorComboBox.getValue();

                                try{
                                    Integer.parseInt(IDTextField.getText());
                                }
                                catch (NumberFormatException e){
                                    System.out.println("List Empty/Letters Input Illegal/Does Not Exist");
                                }

                                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
                                wr.write("{\"pid\":\""+IDTextField.getText()+"\",\"name\":\""+nameTextField.getText()+"\",\"weight\":"+weightValue+",\"height\":"+heightValue+",\"ability\":\""+abilityValue+"\",\"strength\":"+strengthValue+",\"color\":\""+colorValue+"\"}");
                                wr.flush();
                                wr.close();
                                connection.connect();
                                System.out.println(connection.getResponseCode());
                                connection.disconnect();
                                displayTokimon.fire();

                            }
                            catch (IOException e){
                                System.out.println("Empty Search");
                            }
                        }
                    });

                    HBox name = new HBox(nameLabel, nameTextField);
                    HBox weight = new HBox(weightLabel, weightSlider);
                    HBox height = new HBox(heightLabel, heightSlider);
                    HBox ability = new HBox(abilityLabel, abilityComboBox);
                    HBox strength = new HBox(strengthLabel, strengthSlider);
                    HBox color = new HBox(colorLabel, colorComboBox);


                    VBox vBox = new VBox(IDLabel, IDTextField, searchButton, name, weight, height, ability, strength, color, submitButton);
                    Scene scene = new Scene(vBox);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();


                } catch (IOException e) {
                    System.out.println("Empty Search");
                }


            }
        });

        visualizeToki.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    System.out.println("visualize toki");
                    URL url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    System.out.println(connection.getResponseCode());
                    String output;
                    while ((output = br.readLine()) != null) {
                        System.out.println(output);
                    }

                    // convert JSON array to Java List
                    ObjectMapper mapper = new ObjectMapper();
                    List<Tokimon> users = new ObjectMapper().readValue(Paths.get(System.getProperty("user.dir")+"\\tokimon.json").toFile(), new TypeReference<List<Tokimon>>() {});

                    //Name TextField
                    Label IDLabel = new Label("ID of Tokimon: ");
                    TextField IDTextField = new TextField();
                    Button searchButton = new Button("Search Tokimon");

                    searchButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            System.out.println("Visualize toki search");
                            selectedToki = null;

                            try{
                                Integer.parseInt(IDTextField.getText());
                            }
                            catch (NumberFormatException e){
                                System.out.println("Numbers Only!");
                            }

                            int id = Integer.parseInt(IDTextField.getText());
                            for (Tokimon eachTokimon : users) {
                                if (eachTokimon.getPid() == id){
                                    selectedToki = eachTokimon;
                                }
                            }
                            if (selectedToki == null) {
                                System.out.println("No Toki Found");
                                IDTextField.setText("Toki does not exist");
                            }

                            if (selectedToki.getColor().equals("Blue"))
                            {
                                tokimonImage.setImage(new Image("file:blue.png"));
                                colorLabel2.setTextFill(Color.BLUE);
                            }
                            if (selectedToki.getColor().equals("Green"))
                            {
                                tokimonImage.setImage(new Image("file:green.png"));
                                colorLabel2.setTextFill(Color.GREEN);
                            }
                            if (selectedToki.getColor().equals("Red"))
                            {
                                tokimonImage.setImage(new Image("file:red.png"));
                                colorLabel2.setTextFill(Color.RED);
                            }
                            if (selectedToki.getColor().equals("Purple"))
                            {
                                tokimonImage.setImage(new Image("file:purple.png"));
                                colorLabel2.setTextFill(Color.PURPLE);
                            }
                            tokiWeight = 100-selectedToki.getWeight();
                            tokiHeight = 100-selectedToki.getHeight();
                            tokimonImage.setFitHeight(300-tokiHeight*3);
                            tokimonImage.setFitWidth(300-tokiWeight*3);

                            if(selectedToki.getAbility().equals("Fire")) {
                                abilityLabel2.setTextFill(Color.RED);
                            }
                            if(selectedToki.getAbility().equals("Water")) {
                                abilityLabel2.setTextFill(Color.AQUA);
                            }
                            if(selectedToki.getAbility().equals("Air")) {
                                abilityLabel2.setTextFill(Color.SKYBLUE);
                            }
                            if(selectedToki.getAbility().equals("Ground")) {
                                abilityLabel2.setTextFill(Color.BROWN);
                            }
                            nameLabel2.setText("Name: "+ selectedToki.getName());
                            idLabel2.setText("ID: " + selectedToki.getPid());
                            weightLabel2.setText("Weight (Fatness): " + selectedToki.getWeight());
                            heightLabel2.setText("Height: "+selectedToki.getHeight());
                            abilityLabel2.setText("Ability Power: "+ selectedToki.getAbility());
                            strengthLabel2.setText("Strength: "+ selectedToki.getStrength());
                            colorLabel2.setText("Color: "+ selectedToki.getColor());
                        }
                    });

                    VBox vBox = new VBox(IDLabel, IDTextField, searchButton,tokimonImage, nameLabel2, idLabel2, weightLabel2, heightLabel2, abilityLabel2, strengthLabel2, colorLabel2);
                    vBox.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(vBox);
                    Stage stage = new Stage();
                    stage.setWidth(400);
                    stage.setHeight(600);
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException e) {
                    System.out.println("Empty Search");
                }


            }
        });


        Scene scene = new Scene(gridpane,700,600); // (parent, hor, vert)
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Assignment 5");
        primaryStage.show();
    }
}