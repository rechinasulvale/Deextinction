package rafamv.deextinction.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TilePlasterJacket extends TileEntity
{
	private String fossilName = "";

	public TilePlasterJacket()
	{

	}

	public void setFossilName(String fossilName)
	{
		this.fossilName = fossilName;
	}

	public String getFossilName()
	{
		return this.fossilName;
	}

	public boolean hasFossilName()
	{
		return this.fossilName != null && this.fossilName != "";
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setString("FossilName", this.fossilName);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		if (compound.hasKey("FossilName"))
			this.fossilName = compound.getString("FossilName");
	}
}
