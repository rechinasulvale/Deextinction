package rafamv.deextinction.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This is an extension of ModelRenderer that is useful to reset the rotate
 * angles and rotation points of this ModelRenderer (box) to the initial pose,
 * so the entity can be easily animated.
 * 
 * @author RafaMv
 */
@SideOnly(Side.CLIENT)
public class ResettableModelRenderer extends ModelRenderer
{
	public float firstRotateAngleX;
	public float firstRotateAngleY;
	public float firstRotateAngleZ;
	public float firstRotationPointX;
	public float firstRotationPointY;
	public float firstRotationPointZ;

	public ResettableModelRenderer(ModelBase modelBase, int textureOffsetX, int textureOffsetY)
	{
		super(modelBase, textureOffsetX, textureOffsetY);
	}

	public void saveParameters()
	{
		this.firstRotationPointX = this.rotationPointX;
		this.firstRotationPointY = this.rotationPointY;
		this.firstRotationPointZ = this.rotationPointZ;
		this.firstRotateAngleX = this.rotateAngleX;
		this.firstRotateAngleY = this.rotateAngleY;
		this.firstRotateAngleZ = this.rotateAngleZ;
	}

	/**
	 * Resets all parameters.
	 */
	public void resetParameters()
	{
		this.rotationPointX = this.firstRotationPointX;
		this.rotationPointY = this.firstRotationPointY;
		this.rotationPointZ = this.firstRotationPointZ;
		this.rotateAngleX = this.firstRotateAngleX;
		this.rotateAngleY = this.firstRotateAngleY;
		this.rotateAngleZ = this.firstRotateAngleZ;
	}

	/**
	 * Resets all rotation points.
	 */
	public void resetRotationPoints()
	{
		this.rotationPointX = this.firstRotationPointX;
		this.rotationPointY = this.firstRotationPointY;
		this.rotationPointZ = this.firstRotationPointZ;
	}

	/**
	 * Resets X rotation point.
	 */
	public void resetRotationPointX()
	{
		this.rotationPointX = this.firstRotationPointX;
	}

	/**
	 * Resets Y rotation point.
	 */
	public void resetRotationPointY()
	{
		this.rotationPointY = this.firstRotationPointY;
	}

	/**
	 * Resets Z rotation point.
	 */
	public void resetRotationPointZ()
	{
		this.rotationPointZ = this.firstRotationPointZ;
	}

	/**
	 * Copies all rotation points from another box.
	 * 
	 * @target is the box whose rotation points will be copied.
	 */
	public void copyRotationPointsFrom(ResettableModelRenderer target)
	{
		this.rotationPointX = target.rotationPointX;
		this.rotationPointY = target.rotationPointY;
		this.rotationPointZ = target.rotationPointZ;
	}

	/**
	 * Copies the rotation point X from another box.
	 * 
	 * @target is the box whose rotation point X will be copied.
	 */
	public void copyRotationPointXFrom(ResettableModelRenderer target)
	{
		this.rotationPointX = target.rotationPointX;
	}

	/**
	 * Copies the rotation point Y from another box.
	 * 
	 * @target is the box whose rotation point Y will be copied.
	 */
	public void copyRotationPointYFrom(ResettableModelRenderer target)
	{
		this.rotationPointY = target.rotationPointY;
	}

	/**
	 * Copies the rotation point Z from another box.
	 * 
	 * @target is the box whose rotation point Z will be copied.
	 */
	public void copyRotationPointZFrom(ResettableModelRenderer target)
	{
		this.rotationPointZ = target.rotationPointZ;
	}

	/**
	 * Resets all rotations.
	 */
	public void resetRotateAngles()
	{
		this.rotateAngleX = this.firstRotateAngleX;
		this.rotateAngleY = this.firstRotateAngleY;
		this.rotateAngleZ = this.firstRotateAngleZ;
	}

	/**
	 * Resets X rotation.
	 */
	public void resetRotateAngleX()
	{
		this.rotateAngleX = this.firstRotateAngleX;
	}

	/**
	 * Resets Y rotation.
	 */
	public void resetRotateAngleY()
	{
		this.rotateAngleY = this.firstRotateAngleY;
	}

	/**
	 * Resets Z rotation.
	 */
	public void resetRotateAngleZ()
	{
		this.rotateAngleZ = this.firstRotateAngleZ;
	}

	/**
	 * Copies all rotate angles from another box.
	 * 
	 * @target is the box whose rotate angles will be copied.
	 */
	public void copyRotateAnglesFrom(ResettableModelRenderer target)
	{
		this.rotateAngleX = target.rotateAngleX;
		this.rotateAngleY = target.rotateAngleY;
		this.rotateAngleZ = target.rotateAngleZ;
	}

	/**
	 * Copies the rotate angle X from another box.
	 * 
	 * @target is the box whose rotate angle X will be copied.
	 */
	public void copyRotateAngleXFrom(ResettableModelRenderer target)
	{
		this.rotateAngleX = target.rotateAngleX;
	}

	/**
	 * Copies the rotate angle Y from another box.
	 * 
	 * @target is the box whose rotate angle Y will be copied.
	 */
	public void copyRotateAngleYFrom(ResettableModelRenderer target)
	{
		this.rotateAngleY = target.rotateAngleY;
	}

	/**
	 * Copies the rotate angle Z from another box.
	 * 
	 * @target is the box whose rotate angle Z will be copied.
	 */
	public void copyRotateAngleZFrom(ResettableModelRenderer target)
	{
		this.rotateAngleZ = target.rotateAngleZ;
	}

	/**
	 * Calculates the distance between two rotation points.
	 * 
	 * @target is the box that will be used to calculate the distance.
	 */
	public double calculateDistanceTo(ResettableModelRenderer target)
	{
		return Math.sqrt(Math.pow((target.firstRotationPointX - this.firstRotationPointX), 2) + Math.pow((target.firstRotationPointY - this.firstRotationPointY), 2) + Math.pow((target.firstRotationPointZ - this.firstRotationPointZ), 2));
	}

	/**
	 * Calculates the distance squared between two rotation points.
	 * 
	 * @target is the box that will be used to calculate the distance.
	 */
	public double calculateDistanceSquaredTo(ResettableModelRenderer target)
	{
		return Math.pow((target.firstRotationPointX - this.firstRotationPointX), 2) + Math.pow((target.firstRotationPointY - this.firstRotationPointY), 2) + Math.pow((target.firstRotationPointZ - this.firstRotationPointZ), 2);
	}
}
