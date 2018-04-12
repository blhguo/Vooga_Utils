package voogasalad.util.transform_library;

/**
 * 
 * @author Rayan
 *	This class will handle transformation in world space for any object.
 *  Requires a Vector2 object to function.
 *  Rotation and world and local space functions will be implemented in the future.
 *  It is recommended to attach this transform object to your object that involves world coordinates 
 *  and communicate with the transform module when handling transformations.
 */

public class Transform {
	
	private Vector2 position;
	private double rotation;

	
	public Transform(Vector2 position)
	{
		this.position = position;
	}
	
	public Transform(Vector2 position, float rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	
	public double getRotation()
	{
		return rotation;
	}
	
	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between this object and another Transform
	 */
	public Vector2 getDisplacementVector(Transform target)
	{
		return getDisplacementVector(this, target);
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between any two Tranform objects
	 */
	public Vector2 getDisplacementVector(Transform origin, Transform target)
	{
		return target.getPosition().SubtractVector(origin.getPosition());
	}
	
	/**]
	 * 
	 * @param target
	 * @return
	 * Get the magnitude of the displacement between this object and another
	 */
	public double getDisplacement(Transform target)
	{
		return getDisplacement(this, target);
	}
	
	/**
	 * 
	 * @param origin
	 * @param target
	 * @return
	 * Get the magnitude of the displacement between two gameobjects.
	 */
	public double getDisplacement(Transform origin, Transform target)
	{
		return getDisplacementVector(origin, target).getMagnitude();
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * returns the angle between this object and another
	 */
	public double getAngle(Transform target)
	{
		return getAngle(this, target);
	}
	
	/**
	 * 
	 * @param origin
	 * @param target
	 * @return
	 * returns the angle between two Transform objects
	 */
	public double getAngle(Transform origin, Transform target)
	{
		return Math.acos(origin.position.getDotProduct(target.getPosition()) / (origin.getPosition().getMagnitude() * target.getPosition().getMagnitude()));
	}
	
	/**
	 * 
	 * @param direction
	 * @param stepDistance: the distance to be moved each step
	 * 
	 * Move a transform object in a specific direction
	 */
	public void Move(Vector2 direction, double stepDistance)
	{
		position = position.AddVector(direction.MultiplyVector(stepDistance));
	}
	
	/**
	 * 
	 * @param target: target transform object
	 * @param stepDistance: the distance to be moved at every step
	 * 
	 * Moves the current object towards a new object at a constant speed. 
	 */
	public void MoveTowards(Transform target, double stepDistance)
	{
		Vector2 resultantVector = this.getDisplacementVector(target);
		Vector2 temp = position.AddVector(resultantVector.getNormalized().MultiplyVector(stepDistance));
		if(getDisplacement(this, target) <= stepDistance)
		{
			return;
		}
		position = temp;
	
	}
	
	/**
	 * 
	 * @param target: target transform object
	 * @param stepDistance: the distance to be moved at every step
	 * @param deltaTime: The time between frames
	 * 
	 * Moves the current object towards a new object, accelerating the object based upon the distance.
	 */
	public void DampedMoveTowards(Transform target, double stepDistance)
	{
		Vector2 resultantVector = this.getDisplacementVector(target);
		Vector2 temp = position.AddVector(resultantVector.MultiplyVector(stepDistance));
		if(getDisplacement(this, target) <= stepDistance)
		{
			return;
		}
		position = temp;
	
	}
	
}
