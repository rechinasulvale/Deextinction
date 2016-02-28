package rafamv.deextinction.client.renderer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This is an extension of ModelBase that provides useful methods to animate any
 * entity.
 * 
 * @author RafaMv
 */
@SideOnly(Side.CLIENT)
public class ResettableModelBase extends ModelBase
{

	/**
	 * Saves the initial rotate angles and initial rotation points. Call this at
	 * the end of the constructor to save the original entity pose. Remember,
	 * the modelParts array must be reset first.
	 */
	protected List<ResettableModelRenderer> getAllParts(Class modelClass)
	{
		List<ResettableModelRenderer> partList = new ArrayList<ResettableModelRenderer>();
		for (Field field : modelClass.getFields())
		{
			try
			{
				if (field.get(modelClass) instanceof ResettableModelRenderer)
					partList.add((ResettableModelRenderer) field.get(modelClass));
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return partList;
	}

	/**
	 * Saves the initial rotate angles and initial rotation points. Note: Call
	 * this at the end of the constructor to save the original entity pose.
	 */
	protected void saveInitialPose(ResettableModelRenderer modelPart)
	{
		modelPart.saveParameters();
	}

	/**
	 * Saves the initial rotate angles and initial rotation points. Note: Call
	 * this at the end of the constructor to save the original entity pose.
	 * Remember, the modelParts array must be set first.
	 */
	protected void saveInitialPose(ResettableModelRenderer[] modelParts)
	{
		for (ResettableModelRenderer box : modelParts)
			box.saveParameters();
	}

	/**
	 * Resets the initial rotate angles and initial rotation points.
	 */
	protected void resetInitialPose(ResettableModelRenderer modelPart)
	{
		if (modelPart != null)
			modelPart.resetParameters();
	}

	/**
	 * Resets the initial rotate angles and initial rotation points. Remember,
	 * the modelParts array must be set first.
	 */
	protected void resetInitialPose(ResettableModelRenderer[] modelParts)
	{
		if (modelParts != null)
			for (ResettableModelRenderer box : modelParts)
				box.resetParameters();
	}

	/**
	 * Returns a float value that represents the rotation of a head piece.
	 * 
	 * @param angle
	 *            is the entity yaw (left and right) or pitch (up and down)
	 *            depending on what direction should be animated.
	 */
	protected float getHeadAngle(float angle)
	{
		return angle / 57.2957795131F;
	}

	/**
	 * returns a float value that represents the rotation of a head.
	 * 
	 * @param angle
	 *            is the entity yaw (left and right) or pitch (up and down)
	 *            depending on what direction should be animated.
	 * @param rotationLimit
	 *            is the maximum angle that the entity can turn its head. Note:
	 *            in degrees, e.g. 60.0F.
	 */
	protected float getHeadAngle(float angle, float rotationLimit)
	{
		return angle / rotationLimit;
	}

	/**
	 * returns a float value that represents the rotation of a head.
	 * 
	 * @param angle
	 *            is the entity yaw (left and right) or pitch (up and down)
	 *            depending on what direction should be animated.
	 * @param rotationLimit
	 *            is the maximum angle that the entity can turn its head. Note:
	 *            in degrees, e.g. 60.0F.
	 * @param divider
	 *            is a float that can be used to reduce the final angle. Useful
	 *            to set rotation to neck pieces.
	 */
	protected float getHeadAngle(float angle, float rotationLimit, float divider)
	{
		return angle / (divider * rotationLimit);
	}

	/**
	 * Returns a float value that represents a box bouncing.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will bounce.
	 * @param height
	 *            is how far the box will move.
	 */
	protected float getBouncingRotationPoint(float time, float walkSpeed, float speed, float height)
	{
		return MathHelper.abs(height * walkSpeed * MathHelper.sin(time * speed));
	}

	/**
	 * Returns a float value that represents a box bouncing.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param speed
	 *            is how fast the box will bounce.
	 * @param height
	 *            is how far the box will move.
	 */
	protected float getAlwaysBouncingRotationPoint(float time, float speed, float height)
	{
		return MathHelper.abs(height * MathHelper.sin(time * speed));
	}

	/**
	 * Returns a float value that represents a box which is moving from a
	 * certain position.
	 * 
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param distance
	 *            is how far the box will move.
	 */
	protected float getRotationPointMovementSmooth(float walkSpeed, float distance)
	{
		return distance * MathHelper.sin(walkSpeed);
	}

	/**
	 * Returns a float value that represents a box which is moving from a
	 * certain position.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param speed
	 *            is how fast the box will move.
	 * @param distance
	 *            is how far the box will move.
	 */
	protected float getAlwaysMovingRotationPoint(float time, float speed, float distance)
	{
		return distance * MathHelper.sin(time * speed);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param angle
	 *            is how far the box will rotate.
	 */
	protected float getRotateAngleSimple(float time, float walkSpeed, float angle)
	{
		return angle * walkSpeed * MathHelper.sin(time);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param angle
	 *            is how far the box will rotate.
	 */
	protected float getAlwaysRotateAngleSimple(float time, float angle)
	{
		return angle * MathHelper.sin(time);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 */
	protected float getRotateAngle(float time, float walkSpeed, float speed, float angle)
	{
		return angle * walkSpeed * MathHelper.sin(time * speed);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 */
	protected float getAlwaysRotateAngle(float time, float speed, float angle)
	{
		return angle * MathHelper.sin(time * speed);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 * @param offset
	 *            is an offset for the same animation cycle.
	 */
	protected float getRotateAngleComplex(float time, float walkSpeed, float speed, float angle, float offset)
	{
		return angle * walkSpeed * MathHelper.sin(time * speed + offset);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 * @param offset
	 *            is an offset for the same animation cycle.
	 */
	public float getAlwaysRotateAngleComplex(float time, float speed, float angle, float offset)
	{
		return angle * MathHelper.sin(time * speed + offset);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 * @param offset
	 *            is an offset for the same animation cycle.
	 * @param inclination
	 *            is an extra rotation to the piece.
	 */
	public float getAlwaysRotateAngleComplexInclinated(float time, float walkSpeed, float speed, float angle, float offset, float inclination)
	{
		return inclination * walkSpeed + angle * MathHelper.sin(time * speed + offset);
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 * @param offset
	 *            is an offset for the same animation cycle.
	 * @param inclination
	 *            is an extra rotation to the piece.
	 */
	public float getAlwaysRotateAngleComplexInclinatedInverted(float time, float walkSpeed, float speed, float angle, float offset, float inclination)
	{
		return inclination * walkSpeed - MathHelper.sin(time * speed + offset) * angle;
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 * @param offset
	 *            is an offset for the same animation cycle.
	 * @param inclination
	 *            is an extra rotation to the piece.
	 */
	public float getRotateAngleComplexInclinated(float time, float walkSpeed, float speed, float angle, float offset, float inclination)
	{
		return inclination * walkSpeed + MathHelper.sin(time * speed + offset) * angle * walkSpeed;
	}

	/**
	 * Returns a float value that represents a box rotating in a certain axis.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the box will rotate.
	 * @param angle
	 *            is how far the box will rotate.
	 * @param offset
	 *            is an offset for the same animation cycle.
	 * @param inclination
	 *            is an extra rotation to the piece.
	 */
	public float getRotateAngleComplexInclinatedInverted(float time, float walkSpeed, float speed, float angle, float offset, float inclination)
	{
		return inclination * walkSpeed - MathHelper.sin(time * speed + offset) * angle * walkSpeed;
	}

	/**
	 * Return a float array that can be used to animate a chain of parented
	 * boxes.
	 * 
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param numberBoxes
	 *            is the number of boxes to be animated;
	 * @param speed
	 *            is how fast the animation runs;
	 * @param angle
	 *            is how far the box will move;
	 * @param rootOffset
	 *            changes the delay between boxes. Try values from 0.0D to 5.0D
	 *            or so until you like the effect;
	 */
	public float[] getChainMovement(float time, float walkSpeed, int numberBoxes, float speed, float angle, float rootOffset)
	{
		float[] swing = new float[numberBoxes];
		float offset = rootOffset * 1.57079632679F / numberBoxes;
		float animSpeed = time * speed;
		float limitAngle = walkSpeed * angle;
		for (int i = 0; i < numberBoxes; i++)
			swing[i] = MathHelper.cos(animSpeed + offset * i) * limitAngle;
		return swing;
	}

	/**
	 * Swings a chain of parented boxes up and down (rotateAngleY).
	 * 
	 * @param pieces
	 *            are the pieces to be animated;
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the animation runs;
	 * @param angle
	 *            is how far the box will move;
	 * @param rootOffset
	 *            changes the delay between boxes. Try values from 0.0D to 5.0D
	 *            or so until you like the effect;
	 */
	public void setChainSwing(ResettableModelRenderer[] pieces, float time, float walkSpeed, float speed, float angle, float rootOffset)
	{
		int numberOfSegments = pieces.length;
		float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
		for (int i = 0; i < numberOfSegments; i++)
			pieces[i].rotateAngleY += MathHelper.cos(time * speed + offset * i) * angle;
	}

	/**
	 * Swings a chain of parented boxes back and forth (rotateAngleX).
	 * 
	 * @param pieces
	 *            are the boxes to be animated;
	 * @param time
	 *            is a timer. It is usually the time variable of the entity (3rd
	 *            parameter).
	 * @param walkSpeed
	 *            is the walk speed of the entity (2nd parameter). It will
	 *            determine if the animation will occur or not.
	 * @param speed
	 *            is how fast the animation runs;
	 * @param angle
	 *            is how far the box will move;
	 * @param rootOffset
	 *            changes the delay between boxes. Try values from 0.0D to 5.0D
	 *            or so until you like the effect;
	 */
	public void setChainWave(ResettableModelRenderer[] pieces, float time, float walkSpeed, float speed, float angle, float rootOffset)
	{
		int numberOfSegments = pieces.length;
		float offset = (float) ((rootOffset * Math.PI) / (2 * numberOfSegments));
		for (int i = 0; i < numberOfSegments; i++)
			pieces[i].rotateAngleX += MathHelper.cos(time * speed + offset * i) * angle;
	}

	/**
	 * Converts an angle in degrees to radians.
	 * 
	 * @param angle
	 *            is the angle in degrees that will be converted into radians.
	 */
	public float fromDegreesToRads(float angleDegrees)
	{
		return angleDegrees * 0.01745329251F;
	}

	/**
	 * Converts an angle in radians to degrees.
	 * 
	 * @param angle
	 *            is the angle in radians that will be converted into degrees.
	 */
	public float fromRadiansToDegrees(float angleRads)
	{
		return angleRads * 57.2957795131F;
	}
}
