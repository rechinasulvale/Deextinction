package rafamv.deextinction.common.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import rafamv.deextinction.common.registry.DEBlockRegistry;

import com.google.common.base.Predicates;

public class DEWorldGen implements IWorldGenerator
{
	private WorldGenerator gen_gypsum_block;
	private WorldGenerator gen_creature_fossil_stone_block;
	private WorldGenerator gen_creature_fossil_granite_block;
	private WorldGenerator gen_creature_fossil_diorite_block;
	private WorldGenerator gen_creature_fossil_andesite_block;
	private WorldGenerator gen_creature_fossil_sandstone_block;
	private WorldGenerator gen_foliage_fossil_stone_block;

	public DEWorldGen()
	{
		this.gen_gypsum_block = new WorldGenMinable(DEBlockRegistry.gypsum_block.getDefaultState(), 16, Predicates.equalTo(Blocks.stone.getStateFromMeta(BlockStone.EnumType.STONE.getMetadata())));

		List<IBlockState> blockStateList = this.getDirectionBlockStateList(DEBlockRegistry.creature_fossil_stone_block);
		if (blockStateList != null)
			this.gen_creature_fossil_stone_block = new WorldGenMinableMultipleStates(4, Predicates.equalTo(Blocks.stone.getStateFromMeta(BlockStone.EnumType.STONE.getMetadata())), blockStateList);

		blockStateList = this.getDirectionBlockStateList(DEBlockRegistry.creature_fossil_granite_block);
		if (blockStateList != null)
			this.gen_creature_fossil_granite_block = new WorldGenMinableMultipleStates(4, Predicates.equalTo(Blocks.stone.getStateFromMeta(BlockStone.EnumType.GRANITE.getMetadata())), blockStateList);

		blockStateList = this.getDirectionBlockStateList(DEBlockRegistry.creature_fossil_diorite_block);
		if (blockStateList != null)
			this.gen_creature_fossil_diorite_block = new WorldGenMinableMultipleStates(4, Predicates.equalTo(Blocks.stone.getStateFromMeta(BlockStone.EnumType.DIORITE.getMetadata())), blockStateList);

		blockStateList = this.getDirectionBlockStateList(DEBlockRegistry.creature_fossil_andesite_block);
		if (blockStateList != null)
			this.gen_creature_fossil_andesite_block = new WorldGenMinableMultipleStates(4, Predicates.equalTo(Blocks.stone.getStateFromMeta(BlockStone.EnumType.ANDESITE.getMetadata())), blockStateList);

		blockStateList = this.getDirectionBlockStateList(DEBlockRegistry.creature_fossil_sandstone_block);
		if (blockStateList != null)
			this.gen_creature_fossil_sandstone_block = new WorldGenMinableMultipleStates(4, Predicates.equalTo(Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata())), blockStateList);

		blockStateList = this.getDirectionBlockStateList(DEBlockRegistry.foliage_fossil_stone_block);
		if (blockStateList != null)
			this.gen_foliage_fossil_stone_block = new WorldGenMinableMultipleStates(4, Predicates.equalTo(Blocks.stone.getStateFromMeta(BlockStone.EnumType.STONE.getMetadata())), blockStateList);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.getDimensionId())
		{
			case 0:
				// Overworld
				this.runGenerator(this.gen_gypsum_block, world, random, chunkX, chunkZ, 16, 16, 64);
				this.runGenerator(this.gen_creature_fossil_stone_block, world, random, chunkX, chunkZ, 16, 0, 80);
				this.runGenerator(this.gen_creature_fossil_granite_block, world, random, chunkX, chunkZ, 12, 0, 80);
				this.runGenerator(this.gen_creature_fossil_diorite_block, world, random, chunkX, chunkZ, 12, 0, 80);
				this.runGenerator(this.gen_creature_fossil_andesite_block, world, random, chunkX, chunkZ, 12, 0, 80);
				this.runGenerator(this.gen_creature_fossil_sandstone_block, world, random, chunkX, chunkZ, 16, 0, 80);
				this.runGenerator(this.gen_foliage_fossil_stone_block, world, random, chunkX, chunkZ, 16, 0, 80);
				break;
			case -1:
				// Nether
				break;
			case 1:
				// End
				break;
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight)
	{
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++)
		{
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	private List<IBlockState> getDirectionBlockStateList(Block block)
	{
		List<IBlockState> blockStateList = new ArrayList<IBlockState>();
		for (EnumFacing side : EnumFacing.Plane.HORIZONTAL)
			blockStateList.add(block.getStateFromMeta(side.getIndex()));
		return blockStateList;
	}
	/*
	 * 0 Stone 1 Granite 3 Diorite 5 Andesite
	 */
}
