package voogasalad.util.transformlibrary;

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
	
	private boolean toTarget = true;
	private double tCircle = 0.0;

	
	public Transform(Vector2 position)
	{
		this.position = position;
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
		return target.getPosition().subtractVector(origin.getPosition());
	}
	
	/**
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
	public void move(Vector2 direction, double stepDistance)
	{
		position = position.addVector(direction.multiplyVector(stepDistance));
	}
	
	/**
	 * 
	 * @param target: target transform object
	 * @param stepDistance: the distance to be moved at every step
	 * 
	 * Moves the current object towards a new object at a constant speed.
	 * Returns true when object has reached its destination 
	 */
	public boolean moveTowards(Transform target, double stepDistance)
	{
		Vector2 resultantVector = this.getDisplacementVector(target).getNormalized();
		return moveTowardsHelper(target, resultantVector, stepDistance);
	}
	
	/**
	 * 
	 * @param target: target transform object
	 * @param stepDistance: the distance to be moved at every step
	 * @param deltaTime: The time between frames
	 * 
	 * Moves the current object towards a new object, accelerating the object based upon the distance.
	 * Returns true when object has reached its destination
	 */
	public boolean acceleratedMoveTowards(Transform target, double stepDistance)
	{
		Vector2 resultantVector = this.getDisplacementVector(target);
		return moveTowardsHelper(target, resultantVector, stepDistance);
	
	}
	
	private boolean moveTowardsHelper(Transform target, Vector2 resultantVector, double stepDistance)
	{
		Vector2 temp = position.addVector(resultantVector.multiplyVector(stepDistance));
		if(getDisplacement(this, target) <= stepDistance)
		{
			return true;
		}
		position = temp;
		return false;
	}
	
	/**
	 * @param original
	 * @param target
	 * @param stepDistance
	 * @param isAccelerated
	 * Moves the object back and forth between two positions
	 * User can specify if they desire acceleration or not
	 */
	public void pingPongObject(Transform original, Transform target, double stepDistance)
	{		
		if(toTarget)
		{
			toTarget = !moveTowards(target, stepDistance);
		}		
		else
		{	
			toTarget = moveTowards(original, stepDistance);
		}
	}
	
	/**
	 * 
	 * @param radius
	 * @param stepDistance
	 * Parametric function that moves the object in a circle
	 */
	public void moveObjectInCircle(double radius, double stepDistance)
	{
		tCircle += stepDistance;
		if(tCircle >= 360.0)
		{
			tCircle = 0.0;
		}
		Vector2 newPos = new Vector2(radius * Math.cos(tCircle) + position.getX(),
				radius * Math.sin(tCircle) + position.getY());
		this.position = newPos;
		
	}
	
}
