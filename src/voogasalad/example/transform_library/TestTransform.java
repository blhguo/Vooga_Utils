package voogasalad.example.transform_library;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import voogasalad.util.transform_library.Vector2;

public class TestTransform extends Application {
	
	private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Scene scene;
	private Group root;
	private Player player;
	private Player player2;
	
    
    private double mouseX;
    private double mouseY;
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		root = new Group();
		scene = new Scene(root, 600, 600);
		arg0.setTitle("Transform example");
		arg0.setScene(scene);
        arg0.show();
        initialization();
        
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
  
        
        scene.setOnMousePressed(e -> {
        	
        	mouseX = e.getSceneX();
        	mouseY = e.getSceneY();
        	
        });
        
        scene.setOnMouseDragged(event -> {
        	double deltaX = event.getSceneX() - mouseX;
        	double deltaY = event.getSceneY() - mouseY;
        	Vector2 deltaXY = new Vector2(deltaX, deltaY);
        	
        	Vector2 playerPos = player2.getTransform().getPosition();
        	//Vector2 pos = new Vector2(deltaX + playerPos.getX(), deltaY + playerPos.getY());
        	Vector2 pos = playerPos.AddVector(deltaXY);
        	player2.getTransform().setPosition(pos);
        	player2.UpdateRectangle();
        	
        	mouseX = event.getSceneX();
        	mouseY = event.getSceneY();
        	
        });
        

	}
	
	private void initialization()
	{
		player = new Player(new Vector2(300, 300), 40, Color.BLACK);
		root.getChildren().add(player.getRect());
		
		player2 = new Player(new Vector2(200, 200), 40, Color.RED);
		root.getChildren().add(player2.getRect());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeElapsed) {
		
		player.moveObject(player2, 3);
		player.UpdateRectangle();
		player2.UpdateRectangle();
		
	}

	
}
