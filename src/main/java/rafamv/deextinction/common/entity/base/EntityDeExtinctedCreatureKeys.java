package rafamv.deextinction.common.entity.base;

public class EntityDeExtinctedCreatureKeys
{
	public static final int FLAG_UPDATE = 1 << 0;
	public static final int FLAG_GENDER = 1 << 1;
	public static final int FLAG_TAMED = 1 << 2;
	public static final int FLAG_TAKING_OFF = 1 << 3;
	public static final int FLAG_FLYING = 1 << 4;
	public static final int FLAG_SITTING = 1 << 5;
	public static final int FLAG_SLEEPING = 1 << 6;
	public static final int FLAG_SOCIALIZING = 1 << 7;
	public static final int FLAG_STALKING = 1 << 8;
	public static final int FLAG_IN_LOVE = 1 << 9;
	public static final int FLAG_ON_NEST = 1 << 10;
	public static final int FLAG_FLEEING = 1 << 11;

	public static final int KEY_RESEARCH_QUALITY = 5;
	public static final int KEY_STATES = 10;
	public static final int KEY_OWNER_UUID = 11;
	public static final int KEY_AGE_STAGE = 12;
	public static final int KEY_AGE_SCALE = 13;
	public static final int KEY_TEXTURE_ID = 14;
	public static final int KEY_PREGNANCY_PROGRESS = 31;

	public static final byte PACKET_HATCH = 6;

	public static final int TICKS_PER_DAY = 24000;
	public static final int MAX_TAKEOFF_TIME = 60;
}
