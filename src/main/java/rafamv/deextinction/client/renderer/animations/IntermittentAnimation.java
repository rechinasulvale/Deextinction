package rafamv.deextinction.client.renderer.animations;

import java.util.Random;

/**
 * This is a timer that can be used to easily animate models with intermittent
 * poses. You have to set the number of ticks between poses, a number of ticks
 * that represents the interval of the pose change, increase or decrease the
 * timer, and get the percentage using a specific function.
 *
 * @author RafaMv
 * @author Paul Fulham
 * @since 0.1.1
 */
public class IntermittentAnimation extends ControlledAnimation
{
	/**
	 * It is the random used to randomize the movement.
	 */
	private Random rand = new Random();

	/**
	 * It is a boolean that shows if the animation is already in the new pose.
	 */
	private boolean isRunning;

	/**
	 * It is the timer used for the interval.
	 */
	private int timeIdle;
	/**
	 * It is the interval to return to the first animation.
	 */
	private int minIdleTime;
	/**
	 * It is the chance to go to the new animation.
	 */
	private int startProbability;

	/**
	 * @param duration
	 *            duration
	 * @param intervalDuration
	 *            minium ticks between animation cycles
	 * @param startPropbability
	 *            propbablity that the animation will begain, higher values have
	 *            lower probability
	 */
	public IntermittentAnimation(int duration, int intervalDuration, int startPropbability)
	{
		super(duration);
		this.minIdleTime = intervalDuration;
		this.startProbability = startPropbability;
		this.isRunning = false;
		this.timerChange = -1;
	}

	/**
	 * Sets the timer to a specific value.
	 *
	 * @param timeRunning
	 *            is the number of ticks to be set.
	 */
	public void setTimeRunning(int timeRunning)
	{
		this.timer = timeRunning;

		if (this.timer > this.duration)
		{
			this.timer = this.duration;
		}
		else if (this.timer < 0)
		{
			this.timer = 0;
		}
	}

	/**
	 * Increases the timer by 1.
	 */
	@Override
	public void update()
	{
		super.update();
		if (this.isRunning)
		{
			if (this.timer < this.duration && this.timer > 0)
			{
				this.timer += this.timerChange;
			}
			else
			{
				if (this.timer >= this.duration)
				{
					this.timer = this.duration;
				}
				else if (this.timer <= 0)
				{
					this.timer = 0;
				}
				this.timeIdle = 0;
				this.isRunning = false;
			}
		}
	}

	public void start()
	{
		this.timerChange = -this.timerChange;
		this.timer += this.timerChange;
		this.isRunning = true;
	}

	/**
	 * Decreases the timer by 1.
	 */
	public void stop()
	{
		if (this.timer > 0)
		{
			this.timer--;
		}
		else
		{
			this.timer = 0;
			this.isRunning = false;
			this.timeIdle = 0;
			this.timerChange = 1;
		}
	}

	/**
	 * Decreases the timer by a specific value.
	 *
	 * @param timeDelta
	 *            is the number of ticks to be decreased in the timer
	 */
	public void stop(int timeDelta)
	{
		if (this.timer - timeDelta > 0)
		{
			this.timer -= timeDelta;
		}
		else
		{
			this.timer = 0;
			this.isRunning = false;
			this.timeIdle = 0;
			this.timerChange = 1;
		}
	}
}
