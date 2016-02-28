package rafamv.deextinction.client.renderer.animations;

import java.util.Random;

import net.minecraft.util.MathHelper;

public class FlippableAnimation
{
	private static final Random RANDOM = new Random();
	private double duration;
	private double timer;
	private int inverter;
	private int chance;

	public FlippableAnimation(int duration, int chance)
	{
		this.duration = duration;
		this.timer = 0.0D;
		this.inverter = 1;
		this.chance = chance;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
		if (this.timer >= duration)
			this.timer = 0.0D;
	}

	public double getTimer()
	{
		return timer;
	}

	public void setTimer(int time)
	{
		this.timer = time;
		if (this.timer > this.duration)
			this.timer = this.duration;
		else if (this.timer < 0.0D)
			this.timer = 0.0D;
	}

	public void resetTimer()
	{
		this.timer = 0.0D;
	}

	public void invertAnimation()
	{
		this.inverter = this.inverter == 1 ? -1 : 1;
	}

	public void setChance(int chance)
	{
		this.chance = chance;
	}

	public void runAnimation()
	{
		if (this.inverter == 1)
		{
			if (this.timer < this.duration)
				this.timer++;
			else if (this.RANDOM.nextInt(this.chance) == 0)
				this.inverter = -1;
		}
		else
		{
			if (this.timer > 0.0D)
				this.timer--;
			else if (this.RANDOM.nextInt(this.chance) == 0)
				this.inverter = 1;
		}
	}

	public void runAnimation(int time)
	{
		if (this.inverter == 1)
		{
			if (this.timer + time < this.duration)
				this.timer += time;
			else
			{
				this.timer = duration;
				if (this.RANDOM.nextInt(this.chance) == 0)
					this.inverter = -1;
			}
		}
		else
		{
			if (this.timer - time > 0.0D)
				this.timer -= time;
			else
			{
				this.timer = 0.0D;
				if (this.RANDOM.nextInt(this.chance) == 0)
					this.inverter = 1;
			}
		}
	}

	public void stopAnimation()
	{
		if (this.timer > 0.0D)
			this.timer--;
		else
			this.timer = 0.0D;
	}

	public void stopAnimation(int time)
	{
		if (this.timer - time > 0.0D)
			this.timer -= time;
		else
			this.timer = 0.0D;
	}

	public float getAnimationFraction()
	{
		return (float) (this.timer / this.duration);
	}

	public float getAnimationProgressSmooth()
	{
		if (this.timer > 0.0D)
		{
			if (this.timer < this.duration)
				return (float) (1.0D / (1.0D + Math.exp(4.0D - 8.0D * (this.timer / this.duration))));
			else
				return 1.0F;
		}
		return 0.0F;
	}

	public float getAnimationProgressSteep()
	{
		return (float) (1.0D / (1.0D + Math.exp(6.0D - 12.0D * (this.timer / this.duration))));
	}

	public float getAnimationProgressSin()
	{
		return MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration));
	}

	public float getAnimationProgressSinSqrt()
	{
		float result = MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration));
		return result * result;
	}

	public float getAnimationProgressSinToTen()
	{
		return (float) Math.pow(MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration)), 10);
	}

	public float getAnimationProgressSinPowerOf(int i)
	{
		return (float) Math.pow(MathHelper.sin(1.57079632679F * (float) (this.timer / this.duration)), i);
	}

	public float getAnimationProgressPoly2()
	{
		float x = (float) (this.timer / this.duration);
		float x2 = x * x;
		return x2 / (x2 + (1.0F - x) * (1.0F - x));
	}

	public float getAnimationProgressPoly3()
	{
		float x = (float) (this.timer / this.duration);
		float x3 = x * x * x;
		return x3 / (x3 + (1.0F - x) * (1.0F - x) * (1.0F - x));
	}

	public float getAnimationProgressPolyN(int n)
	{
		double x = this.timer / this.duration;
		double xi = Math.pow(x, n);
		return (float) (xi / (xi + Math.pow((1.0D - x), n)));
	}

	public float getAnimationProgressArcTan()
	{
		return 0.5F + 0.49806510671F * (float) (Math.atan(3.14159265359D * (this.timer / this.duration - 0.5D)));
	}

	public float getAnimationProgressTemporary()
	{
		float x = 6.28318530718F * (float) (this.timer / this.duration);
		return 0.5F - 0.5F * MathHelper.cos(x + MathHelper.sin(x));
	}

	public float getAnimationProgressTemporaryFS()
	{
		float x = 3.14159265359F * (float) (this.timer / this.duration);
		return MathHelper.sin(x + MathHelper.sin(x));
	}

	public float getAnimationProgressTemporaryInvesed()
	{
		float x = 6.28318530718F * (float) (this.timer / this.duration);
		return 0.5F + 0.5F * MathHelper.cos(x + MathHelper.sin(x));
	}
}
