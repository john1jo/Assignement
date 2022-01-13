/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxdb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 *
 * @author CSS Chega
 */
public class FXAssigntry extends Application {

    @Override
    public void start(Stage primaryStage) {

        TableView table = new TableView<Table1>();
//        TableColumn sidColumn = new TableColumn<Table1, String>("SID");
//        sidColumn.setCellValueFactory(new PropertyValueFactory<Table1, String>("SID"));
//        sidColumn.setMinWidth(100);
//        TableColumn studidColumn = new TableColumn<Table1, String>("STUDID");
//        studidColumn.setCellValueFactory(new PropertyValueFactory<Table1, String>("STUDID"));
//        studidColumn.setMinWidth(100);
//        TableColumn fnameColumn = new TableColumn<Table1, String>("FIRSTNAME");
//        fnameColumn.setCellValueFactory(new PropertyValueFactory<Table1, String>("FIRSTNAME"));
//        fnameColumn.setMinWidth(150);
//        TableColumn lnameColumn = new TableColumn<Table1, String>("LASTNAME");
//        lnameColumn.setCellValueFactory(new PropertyValueFactory<Table1, String>("LASTNAME"));
//        lnameColumn.setMinWidth(150);
//        TableColumn sectionColumn = new TableColumn<Table1, String>("SECTION");
//        sectionColumn.setCellValueFactory(new PropertyValueFactory<Table1, String>("SECTION"));
//        sectionColumn.setMinWidth(100);
//        TableColumn departmentColumn = new TableColumn<Table1, String>("DEPARTMENT");
//        departmentColumn.setCellValueFactory(new PropertyValueFactory<Table1, String>("DEPARTMENT"));
//        departmentColumn.setMinWidth(150);
//
//        table.getColumns().add(sidColumn);
//        table.getColumns().add(studidColumn);
//        table.getColumns().add(fnameColumn);
//        table.getColumns().add(lnameColumn);
//        table.getColumns().add(sectionColumn);
//        table.getColumns().add(departmentColumn);
//
//        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Label dsid = new Label("SID");
        Label dstdid = new Label("Studid");
        Label dfname = new Label("Firstname");
        Label dlname = new Label("Lastname");
        Label dsection = new Label("Section");
        Label ddept = new Label("Departement");

        TextField tsid = new TextField();
        TextField tstudid = new TextField();
        TextField tfname = new TextField();
        TextField tlname = new TextField();
        TextField tsection = new TextField();
        TextField tdept = new TextField();

        Button insert = new Button();

        insert.setText("Insert");
        Button display = new Button();
        display.setText("display");
        Button update = new Button();
        update.setText("Update");
Button distinct=new Button();
distinct.setText("distinct");
 Button select =new Button();
select.setText("select");
        HBox hbox = new HBox(dsid, tsid);
        hbox.setSpacing(20);

        HBox hboxb = new HBox(insert, update, display,distinct,select);
        hboxb.setSpacing(20);

        HBox hbox2 = new HBox(dstdid, tstudid);
        hbox2.setSpacing(20);

        HBox hbox3 = new HBox(dfname, tfname);
        hbox.setSpacing(20);

        HBox hbox4 = new HBox(dlname, tlname);
        hbox2.setSpacing(20);

        HBox hbox5 = new HBox(dsection, tsection);
        hbox.setSpacing(20);

        HBox hbox6 = new HBox(ddept, tdept);
        hbox2.setSpacing(20);

        VBox vb = new VBox();
        vb.setSpacing(20);

        vb.getChildren().addAll(hbox, hbox2, hbox3, hbox4, hbox5, hbox6, hboxb, table);

        insert.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBConnection db = new DBConnection();
                Connection con = null;
                try {
                    con = db.connMethod();
                    if (con != null) {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("you are connected sucssfuly");
                        a.showAndWait();
                    } else {
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setContentText("you are not connected sucssfuly");
                        a.showAndWait();
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FXAssigntry.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    //Connection conn= DriverManager.getConnection(db.con);
                    String query = "insert into dept_tbl(SID,Studid,Firstname,Lastname,Section,Department) values(?,?,?,?,?,?)";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, tsid.getText());
                    ps.setString(2, tstudid.getText());
                    ps.setString(3, tfname.getText());
                    ps.setString(4, tlname.getText());
                    ps.setString(5, tsection.getText());
                    ps.setString(6, tdept.getText());
                    ps.execute();
                    JOptionPane.showMessageDialog(null, "inserted successfuly");
                    //System.out.println("inserted successfuly");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "insertion failed");
                    //System.out.println(ex.getMessage());
                }

            }
        });

        update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                DBConnection db = new DBConnection();
                Connection con = null;
                try {
                    con = db.connMethod();
                    String value = tfname.getText();
                    String value1 = "john";
                    String sql = "UPDATE dept_tbl SET Firstname='" + value + "' WHERE Firstname='" + value1 + "'";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.executeUpdate();

                    a.setContentText("Updated successfuly");
                    a.showAndWait();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        display.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;
            //private TableView tbl;

            @Override
            public void handle(ActionEvent event) {

                DBConnection obj1;
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    table.setStyle("-fx-background-color:white; -fx-font-color:white ");
                    obj1 = new DBConnection();
                    c = obj1.connMethod();
                    String SQL = "SELECT * from dept_tbl";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                        table.getColumns().addAll(col);
                        System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                        System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }
            }
        });
        distinct.setOnAction(new EventHandler<ActionEvent>() {
            private ObservableList<ObservableList> data;

            //private TableView tbl;
            @Override
            public void handle(ActionEvent event) {

                DBConnection db = new DBConnection();
                Connection c;
                ResultSet rs;
                data = FXCollections.observableArrayList();
                try {

                    // table.setStyle("-fx-background-color:red; -fx-font-color:yellow ");
                    c = db.connMethod();
                    String SQL = "SELECT distinct Section from dept_tbl";
                    rs = c.createStatement().executeQuery(SQL);
                    for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));
                        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                        col.setMinWidth(100);
                        table.getColumns().addAll(col);
                       // System.out.println("Column [" + i + "] ");

                    }

                    while (rs.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row.add(rs.getString(i));
                        }
                       // System.out.println("Row[1]added " + row);
                        data.add(row);

                    }

                    table.setItems(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error ");
                }

            }
        });
       
        
      
        select.setOnAction(new EventHandler<ActionEvent>() {
 private ObservableList<ObservableList> data;
 //private TableView tbl;
            @Override
            public void handle(ActionEvent event) 
            {
             
                DBConnection obj1;
        Connection c;
        ResultSet rs;
        data = FXCollections.observableArrayList();
        try {

            table.setStyle("-fx-background-color:white; -fx-font-color:white ");
            obj1 = new DBConnection();
            c = obj1.connMethod();
                String SQL = "SELECT Department from dept_tbl where Firstname = 'Elias' and Section='secA'";
                rs = c.createStatement().executeQuery(SQL);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>,
                        ObservableValue<String>>) param -> new SimpleStringProperty(param.getValue().get(j).toString()));

                table.getColumns().addAll(col);
                //System.out.println("Column [" + i + "] ");

            }


            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
                }
                //System.out.println("Row[1]added " + row);
                data.add(row);

            }


            table.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error ");
        }      
         
        }
        });
        
        
        
        
        
        
        
        
        Scene scene = new Scene(vb, 900, 600);
        primaryStage.setTitle("Dept_tbl");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
