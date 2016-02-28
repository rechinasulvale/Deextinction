package rafamv.deextinction.common.block;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import rafamv.deextinction.DeExtinction;

import com.google.common.collect.Lists;

@SideOnly(Side.CLIENT)
public class CustomStateMap extends StateMapperBase
{
	protected List<IProperty> ignoreProperties;

	public CustomStateMap(IProperty... ignoreProperties)
	{
		this.ignoreProperties = Lists.newArrayList(ignoreProperties);
	}

	public CustomStateMap clearIgnoredProperties()
	{
		this.ignoreProperties.clear();
		return this;
	}

	public CustomStateMap addIgnoredProperties(IProperty... addProperties)
	{
		Collections.addAll(this.ignoreProperties, addProperties);
		return this;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state)
	{
		String blockItemUnlocalizedName = state.getBlock().getUnlocalizedName().substring(5);

		Map<IProperty, Comparable<?>> propertyMap = new LinkedHashMap<IProperty, Comparable<?>>(state.getProperties());
		for (IProperty property : this.ignoreProperties)
			propertyMap.remove(property);

		return new ModelResourceLocation(DeExtinction.prependModID(blockItemUnlocalizedName), getPropertyString(propertyMap));
	}
}
