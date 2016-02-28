package rafamv.deextinction.common.block;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import rafamv.deextinction.DeExtinction;

public class DeExtinctionFluid extends Fluid
{

	public DeExtinctionFluid(String fluidName)
	{
		super(fluidName, new ResourceLocation(DeExtinction.MODID, "blocks/fluids/" + fluidName + "_still"), new ResourceLocation(DeExtinction.MODID, "blocks/fluids/" + fluidName + "_flow"));
	}

	public DeExtinctionFluid(String fluidName, int luminosity, int density, int temperature, int viscosity, EnumRarity rarity, boolean isGaseous)
	{
		super(fluidName, new ResourceLocation(DeExtinction.MODID, "blocks/fluids/" + fluidName + "_still"), new ResourceLocation(DeExtinction.MODID, "blocks/fluids/" + fluidName + "_flow"));
		this.setLuminosity(luminosity);
		this.setDensity(density);
		this.setTemperature(temperature);
		this.setViscosity(viscosity);
		this.setGaseous(isGaseous);
	}
}
