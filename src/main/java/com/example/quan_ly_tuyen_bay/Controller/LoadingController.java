package com.example.quan_ly_tuyen_bay.Controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    @FXML
    private MediaView media;

    @FXML
    private ProgressBar load;
    @FXML
    private Label lable;
    doWork task;
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        File mediaFile = new File("C:\\Users\\vanth\\IdeaProjects\\HK2\\IO_Stream\\Quan_Ly_Tuyen_Bay\\src\\main\\resources\\com\\example\\quan_ly_tuyen_bay\\Image\\video.mp4");
        Media media1 = new Media(mediaFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media1);

        media.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setOnReady(() -> {
            System.out.println("Media is ready to play!");
            // Bạn có thể đặt thêm mã xử lý khác ở đây, nếu cần

        });

        task = new doWork();
        load.progressProperty().bind(task.progressProperty());
        lable.textProperty().bind(task.messageProperty());
        new Thread(task).start();

    }

    class doWork extends Task<Void>{

        @Override
        protected Void call() throws Exception {
            for (int i=0 ; i<10 ; i++){
                if(isCancelled()){
                    updateMessage("Cancelled");
                    break;
                }
                updateProgress(i+1,10);
                updateMessage("Loading");
                Thread.sleep(1000);
            }
            updateMessage("Finished");
            Thread.sleep(1000);
            navigateToHome();
            return null;
        }
    }


    private void navigateToHome() {
        Platform.runLater(() -> {
            Stage stage = (Stage) media.getScene().getWindow();
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/example/quan_ly_tuyen_bay/View/Home.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


