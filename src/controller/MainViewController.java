
package controller;

import cripto.hash;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ConsultUser;



public class MainViewController implements Initializable  {

    
    
    @FXML
    private Button logginButton;
    
    @FXML
    private Label ForgotPassLabel;
   
    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField emailTextField;
    
    private static String myVariable;
    
    
    @FXML
    private void onLoginButtonClick(ActionEvent event) throws IOException{
        
         String email = emailTextField.getText();
         String pass = passwordField.getText();
            
         ConsultUser con = new ConsultUser();
        
         String passwordEncode = hash.encryptThisString(pass);

         String validate = con.login(email, passwordEncode);
        
       
       if(validate != null){
           
        String fullname =con.searchFullName(validate);
           
          
            String tittle = "Bienvenido(a) a MediAlarm";
            String message = "Hola "+fullname+ " es un gusto.";
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/image/clockW.png").toString()));
            alert.setTitle(tittle);
            alert.setContentText(message);
            alert.showAndWait();
   
                logginButton.getScene().getWindow().hide();
                
                setMyVariable(validate);
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Menu.fxml"));
                Parent root = (Parent) loader.load();           
                MenuController secController = loader.getController();
                secController.onGetData(fullname, validate);     
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image("/image/clockW.png"));
                stage.setResizable(false);
                stage.setTitle("MediAlarm");
                stage.show();
                
                 stage.setOnCloseRequest((e) -> {
                    Platform.exit();
                    System.exit(0);
                });
         
         
        
       }else if( validate == null){
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error en login ");
            alert.setContentText("Correo y/o contraseña son incorrectos");
            alert.showAndWait();
           
           
       }
       
    }
    
   @FXML
    private void onRegisterButton(ActionEvent event) throws IOException{
        logginButton.getScene().getWindow().hide();
        
    Parent root = FXMLLoader.load(getClass().getResource("/view/Register.fxml"));     
           Scene scene = new Scene(root);     
           Stage stage = new Stage();  
           stage.setResizable(false);
           stage.setScene(scene);       
           stage.getIcons().add(new Image("/image/clockW.png"));
           stage.setTitle("MediAlarm"); 
           stage.show();
    
    }
    
    @FXML
    private void OnForgotPassword() throws IOException{
    
    AnchorPane .getScene().getWindow().hide();
    
    Parent root = FXMLLoader.load(getClass().getResource("/view/PasswordRecovery.fxml"));     
           Scene scene = new Scene(root);     
           Stage stage = new Stage();     
           stage.setResizable(false);
           stage.setScene(scene);       
           stage.getIcons().add(new Image("/image/clockW.png"));
           stage.setTitle("MediAlarm"); 
           stage.show();
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
    }    
       
    
  public static String getMyVariable() {
        return myVariable;
    }

    public static void setMyVariable(String myVariable) {
        MainViewController.myVariable = myVariable;
    }
  
}
