package voogasalad.example.transform_library;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import voogasalad.util.transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Example that showcases the transform library. Drag the red square around to see it in action.
 */

public class TestTransform extends Application {
	
	private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	
	private Scene scene;
	private Group root;
	private Player player;
	private Player player2;
	private Player player3;
	
    
    private double mouseX;
    private double mouseY;
    
	private double player1Speed = 3;
	private double player2Speed = 10;
	private double player3Speed = 3;
    
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
  
        setKeyPresses();
        scene.setOnMousePressed(e -> {
        	
        	mouseX = e.getSceneX();
        	mouseY = e.getSceneY();
        	
        });
        
        scene.setOnMouseDragged(event -> {
        	double deltaX = event.getSceneX() - mouseX;
        	double deltaY = event.getSceneY() - mouseY;
        	
        	/**
        	 * The below code is an example of how the vector class can be used 
        	 */
        	
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
	
	private void setKeyPresses()
	{
		scene.setOnKeyPressed(e -> {
			
			Vector2 direction = new Vector2(0,0);
			
			if(e.getCode() == KeyCode.LEFT)
			{
				direction = new Vector2().Left();
			}
			
			if(e.getCode() == KeyCode.RIGHT)
			{
				direction = new Vector2().Right();
			}
			
			if(e.getCode() == KeyCode.DOWN)
			{
				direction = new Vector2().Up();
			}
			
			if(e.getCode() == KeyCode.UP)
			{
				direction = new Vector2().Down();
			}
			
			player2.moveInDirection(direction, player2.getSpeed());
		});
		
		scene.setOnKeyPressed(ef -> {
			
			if(ef.getCode() == KeyCode.H)
			{
				boolean val = player.getRect().isVisible();
				player.getRect().setVisible(!val);
			}
			
			if(ef.getCode() == KeyCode.J)
			{
				boolean val = player3.getRect().isVisible();
				player3.getRect().setVisible(!val);
			}
			
		});
	}
	
	private void initialization()
	{
		player = new Player(new Vector2(300, 300), 40, Color.BLACK, player1Speed);
		root.getChildren().add(player.getRect());
		
		player3 = new Player(new Vector2(100, 100), 40, Color.GREEN, player3Speed);
		root.getChildren().add(player3.getRect());
		
		player2 = new Player(new Vector2(200, 200), 40, Color.RED, player2Speed);
		root.getChildren().add(player2.getRect());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeElapsed) {
		
		player.moveTowards(player2, player.getSpeed());
		player3.moveTowardsDamped(player2, player3.getSpeed(), SECOND_DELAY);
		player.UpdateRectangle();
		player2.UpdateRectangle();
		player3.UpdateRectangle();
		
	}

	
}
