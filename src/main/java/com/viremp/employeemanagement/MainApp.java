package com.viremp.employeemanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    static Stage mainStage;
    public static boolean englishSelected = true;
    public static Locale locale;

    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        locale = new Locale(getLangProperty());
        setStageInLocale(locale);
    }
    

    static void setStageInLocale(Locale locale) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.lang", locale);
        Parent root = FXMLLoader.load(MainApp.class.getResource("/fxml/MainScene.fxml"), bundle);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        mainStage.setTitle(bundle.getString("stageTitle"));
        mainStage.setScene(scene);
        mainStage.show();
    }

    static void switchLocale(String lang) throws Exception {
        locale = new Locale(lang);
        MainApp.setStageInLocale(locale);
        changeLangProperty(lang);

    }
    public static void changeLocale(int code) throws Exception{
        switch(code){
            case 0:
                englishSelected = true;
                switchLocale("EN");
                
                break;
            case 1:
                englishSelected = false;
                switchLocale("AR");
                
                break;
        }
    }

    static String getLangProperty(){
        Properties prop = new Properties();
	InputStream input = null;
        String lang = null;
	try {

		input = new FileInputStream("app.properties");

		// load a properties file
		prop.load(input);

		// get the property value and print it out
		lang = prop.getProperty("language");

	} catch(FileNotFoundException ex){
            changeLangProperty("EN");
            return "EN";
        }
        catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return lang;
    }
    static void changeLangProperty(String newLanguage){
        Properties prop = new Properties();
        OutputStream output = null;

        try {
            
            File f = new File("app.properties");
            output = new FileOutputStream(f);

            // set the properties value
            prop.setProperty("language", newLanguage);
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
