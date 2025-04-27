import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class StaffApp extends Application {

    private TextField tfId, tfLastName, tfFirstName, tfAddress, tfCity, tfState, tfPhone, tfEmail;
    private Label lblMessage;

    @Override
    public void start(Stage primaryStage) {
        // Form fields
        tfId = new TextField();
        tfLastName = new TextField();
        tfFirstName = new TextField();
        tfAddress = new TextField();
        tfCity = new TextField();
        tfState = new TextField();
        tfPhone = new TextField();
        tfEmail = new TextField();

        Button btnView = new Button("View");
        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        Button btnClear = new Button("Clear");

        lblMessage = new Label();

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(tfId, 1, 0);
        gridPane.add(new Label("Last Name:"), 0, 1);
        gridPane.add(tfLastName, 1, 1);
        gridPane.add(new Label("First Name:"), 0, 2);
        gridPane.add(tfFirstName, 1, 2);
        gridPane.add(new Label("Address:"), 0, 3);
        gridPane.add(tfAddress, 1, 3);
        gridPane.add(new Label("City:"), 0, 4);
        gridPane.add(tfCity, 1, 4);
        gridPane.add(new Label("State:"), 0, 5);
        gridPane.add(tfState, 1, 5);
        gridPane.add(new Label("Telephone:"), 0, 6);
        gridPane.add(tfPhone, 1, 6);
        gridPane.add(new Label("Email:"), 0, 7);
        gridPane.add(tfEmail, 1, 7);

        HBox buttonBox = new HBox(10, btnView, btnInsert, btnUpdate, btnClear);
        VBox vbox = new VBox(10, gridPane, buttonBox, lblMessage);

        // Button Actions
        btnView.setOnAction(e -> viewRecord());
        btnInsert.setOnAction(e -> insertRecord());
        btnUpdate.setOnAction(e -> updateRecord());
        btnClear.setOnAction(e -> clearFields());

        // Scene
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setTitle("Staff Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void viewRecord() {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Staff WHERE id = ?")) {
            ps.setString(1, tfId.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tfLastName.setText(rs.getString("lastName"));
                tfFirstName.setText(rs.getString("firstName"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfPhone.setText(rs.getString("telephone"));
                tfEmail.setText(rs.getString("email"));
                lblMessage.setText("Record Found!");
            } else {
                lblMessage.setText("Record Not Found!");
            }
        } catch (SQLException e) {
            lblMessage.setText("Error: " + e.getMessage());
        }
    }

    private void insertRecord() {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Staff VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, tfId.getText());
            ps.setString(2, tfLastName.getText());
            ps.setString(3, tfFirstName.getText());
            ps.setString(4, ""); // m (gender placeholder)
            ps.setString(5, tfAddress.getText());
            ps.setString(6, tfCity.getText());
            ps.setString(7, tfState.getText());
            ps.setString(8, tfPhone.getText());
            ps.setString(9, tfEmail.getText());
            ps.executeUpdate();

            lblMessage.setText("Record Inserted!");
        } catch (SQLException e) {
            lblMessage.setText("Error: " + e.getMessage());
        }
    }

    private void updateRecord() {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE Staff SET lastName = ?, firstName = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? WHERE id = ?")) {
            ps.setString(1, tfLastName.getText());
            ps.setString(2, tfFirstName.getText());
            ps.setString(3, tfAddress.getText());
            ps.setString(4, tfCity.getText());
            ps.setString(5, tfState.getText());
            ps.setString(6, tfPhone.getText());
            ps.setString(7, tfEmail.getText());
            ps.setString(8, tfId.getText());
            ps.executeUpdate();

            lblMessage.setText("Record Updated!");
        } catch (SQLException e) {
            lblMessage.setText("Error: " + e.getMessage());
        }
    }

    private void clearFields() {
        tfId.clear();
        tfLastName.clear();
        tfFirstName.clear();
        tfAddress.clear();
        tfCity.clear();
        tfState.clear();
        tfPhone.clear();
        tfEmail.clear();
        lblMessage.setText("");
    }

    public static void main(String[] args) {
        launch(args);
    }
