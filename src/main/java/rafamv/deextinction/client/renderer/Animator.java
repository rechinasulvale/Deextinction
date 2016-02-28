package rafamv.deextinction.client.renderer;

import java.util.HashMap;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;
import rafamv.deextinction.common.entity.base.IAnimatedEntity;

@SideOnly(Side.CLIENT)
public class Animator
{
	private HashMap<ModelRenderer, Transform> transformMap, prevTransformMap;
	private IAnimatedEntity animEntity;
	private boolean isCorrectAnim;
	private int prevTempTick;
	private int tempTick;

	public Animator()
	{
		this.tempTick = 0;
		this.isCorrectAnim = false;
		this.transformMap = new HashMap<ModelRenderer, Transform>();
		this.prevTransformMap = new HashMap<ModelRenderer, Transform>();
	}

	public IAnimatedEntity getEntity()
	{
		return this.animEntity;
	}

	public void update(IAnimatedEntity entity)
	{
		this.tempTick = this.prevTempTick = 0;
		this.isCorrectAnim = false;
		this.animEntity = entity;
		this.transformMap.clear();
		this.prevTransformMap.clear();
	}

	public boolean setAnim(int animID)
	{
		this.tempTick = this.prevTempTick = 0;
		this.isCorrectAnim = this.animEntity.getAnimID() == animID;
		return this.isCorrectAnim;
	}

	public void startPhase(int duration)
	{
		if (!this.isCorrectAnim)
			return;
		this.prevTempTick = this.tempTick;
		this.tempTick += duration;
	}

	public void setStationaryPhase(int duration)
	{
		this.startPhase(duration);
		this.endPhase(true);
	}

	public void resetPhase(int duration)
	{
		this.startPhase(duration);
		this.endPhase();
	}

	public void rotate(ModelRenderer box, float x, float y, float z)
	{
		if (!this.isCorrectAnim)
			return;
		if (!this.transformMap.containsKey(box))
			this.transformMap.put(box, new Transform(x, y, z));
		else
			this.transformMap.get(box).addRot(x, y, z);
	}

	public void move(ModelRenderer box, float x, float y, float z)
	{
		if (!this.isCorrectAnim)
			return;
		if (!this.transformMap.containsKey(box))
			this.transformMap.put(box, new Transform(x, y, z, 0F, 0F, 0F));
		else
			this.transformMap.get(box).addOffset(x, y, z);
	}

	public void endPhase()
	{
		this.endPhase(false);
	}

	private void endPhase(boolean stationary)
	{
		if (!this.isCorrectAnim)
			return;
		int animTick = this.animEntity.getAnimTick();
		if (animTick >= this.prevTempTick && animTick < this.tempTick)
		{
			if (stationary)
			{
				for (ModelRenderer box : this.prevTransformMap.keySet())
				{
					Transform transform = this.prevTransformMap.get(box);
					box.rotateAngleX += transform.rotX;
					box.rotateAngleY += transform.rotY;
					box.rotateAngleZ += transform.rotZ;
					box.rotationPointX += transform.offsetX;
					box.rotationPointY += transform.offsetY;
					box.rotationPointZ += transform.offsetZ;
				}
			}
			else
			{
				float tick = (animTick - this.prevTempTick + DeExtinction.proxy.getPartialTick()) / (this.tempTick - this.prevTempTick);
				float inc = MathHelper.sin(tick * 1.57079632679F), dec = 1.0F - inc;
				for (ModelRenderer box : this.prevTransformMap.keySet())
				{
					Transform transform = this.prevTransformMap.get(box);
					box.rotateAngleX += dec * transform.rotX;
					box.rotateAngleY += dec * transform.rotY;
					box.rotateAngleZ += dec * transform.rotZ;
					box.rotationPointX += dec * transform.offsetX;
					box.rotationPointY += dec * transform.offsetY;
					box.rotationPointZ += dec * transform.offsetZ;
				}
				for (ModelRenderer box : transformMap.keySet())
				{
					Transform transform = transformMap.get(box);
					box.rotateAngleX += inc * transform.rotX;
					box.rotateAngleY += inc * transform.rotY;
					box.rotateAngleZ += inc * transform.rotZ;
					box.rotationPointX += inc * transform.offsetX;
					box.rotationPointY += inc * transform.offsetY;
					box.rotationPointZ += inc * transform.offsetZ;
				}
			}
		}
		if (!stationary)
		{
			this.prevTransformMap.clear();
			this.prevTransformMap.putAll(this.transformMap);
			this.transformMap.clear();
		}
	}
}
