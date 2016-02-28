package rafamv.deextinction.common.block.foliage;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import rafamv.deextinction.common.registry.DECreativeTabs;

public class BlockDeExtinctedPlanks extends Block
{

	public BlockDeExtinctedPlanks(String treeName, int variant)
	{
		super(Material.wood);
		this.setUnlocalizedName("block_" + treeName + "_" + variant + "_planks");
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setCreativeTab(DECreativeTabs.blocks);
	}
}
