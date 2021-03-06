/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.world.gen.village;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import stevekung.mods.moreplanets.planets.fronos.blocks.FronosBlocks;

public class ComponentFronosVillageHall extends ComponentFronosVillage
{
	private int averageGroundLevel = -1;

	public ComponentFronosVillageHall() {}

	public ComponentFronosVillageHall(ComponentFronosVillageStartPiece component, int type, StructureBoundingBox box, EnumFacing side)
	{
		super(component, type);
		this.coordBaseMode = side;
		this.boundingBox = box;
	}

	public static ComponentFronosVillageHall func_74906_a(ComponentFronosVillageStartPiece component, List list, Random rand, int x, int y, int z, EnumFacing side, int type)
	{
		StructureBoundingBox structureboundingbox = StructureBoundingBox.func_175897_a(x, y, z, 0, 0, 0, 9, 7, 11, side);
		return canVillageGoDeeper(structureboundingbox) && StructureComponent.findIntersecting(list, structureboundingbox) == null ? new ComponentFronosVillageHall(component, type, structureboundingbox, side) : null;
	}

	@Override
	protected void writeStructureToNBT(NBTTagCompound nbt)
	{
		super.writeStructureToNBT(nbt);
		nbt.setInteger("AvgGroundLevel", this.averageGroundLevel);
	}

	@Override
	protected void readStructureFromNBT(NBTTagCompound nbt)
	{
		super.readStructureFromNBT(nbt);
		this.averageGroundLevel = nbt.getInteger("AvgGroundLevel");
	}

	@Override
	public boolean addComponentParts(World world, Random rand, StructureBoundingBox box)
	{
		if (this.averageGroundLevel < 0)
		{
			this.averageGroundLevel = this.getAverageGroundLevel(world, box);

			if (this.averageGroundLevel < 0)
			{
				return true;
			}
			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 7 - 1, 0);
		}

		this.func_175804_a(world, box, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.func_175804_a(world, box, 2, 0, 6, 8, 0, 10, FronosBlocks.fronos_dirt.getDefaultState(), FronosBlocks.fronos_dirt.getDefaultState(), false);
		this.func_175811_a(world, Blocks.cobblestone.getDefaultState(), 6, 0, 6, box);
		this.func_175804_a(world, box, 2, 1, 6, 2, 1, 10, FronosBlocks.fronos_fence.getDefaultState(), FronosBlocks.fronos_fence.getDefaultState(), false);
		this.func_175804_a(world, box, 8, 1, 6, 8, 1, 10, FronosBlocks.fronos_fence.getDefaultState(), FronosBlocks.fronos_fence.getDefaultState(), false);
		this.func_175804_a(world, box, 3, 1, 10, 7, 1, 10, FronosBlocks.fronos_fence.getDefaultState(), FronosBlocks.fronos_fence.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 1, 7, 0, 4, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 8, 0, 0, 8, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 0, 5, 7, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 2, 0, 7, 3, 0, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 1, 2, 5, 7, 3, 5, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 4, 1, 8, 4, 1, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 4, 4, 8, 4, 4, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175804_a(world, box, 0, 5, 2, 8, 5, 3, FronosBlocks.fronos_planks.getDefaultState(), FronosBlocks.fronos_planks.getDefaultState(), false);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 0, 4, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 0, 4, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 8, 4, 2, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 8, 4, 3, box);
		int i = this.getMetadataWithOffset(Blocks.oak_stairs, 3);
		int j = this.getMetadataWithOffset(Blocks.oak_stairs, 2);
		int k;
		int l;

		for (k = -1; k <= 2; ++k)
		{
			for (l = 0; l <= 8; ++l)
			{
				this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(i), l, 4 + k, k, box);
				this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(j), l, 4 + k, 5 - k, box);
			}
		}

		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 2, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 0, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 8, 2, 1, box);
		this.func_175811_a(world, FronosBlocks.fronos_log.getDefaultState(), 8, 2, 4, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 0, 2, 3, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 8, 2, 2, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 8, 2, 3, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 2, 2, 5, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 3, 2, 5, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 5, 2, 0, box);
		this.func_175811_a(world, FronosBlocks.cheese_glass_pane.getDefaultState(), 6, 2, 5, box);
		this.func_175811_a(world, Blocks.oak_fence.getDefaultState(), 2, 1, 3, box);
		this.func_175811_a(world, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 3, box);
		this.func_175811_a(world, FronosBlocks.fronos_planks.getDefaultState(), 1, 1, 4, box);
		this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 4, box);
		this.func_175811_a(world, Blocks.oak_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 3, box);
		this.func_175804_a(world, box, 5, 0, 1, 7, 0, 3, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
		this.func_175811_a(world, Blocks.double_stone_slab.getDefaultState(), 6, 1, 1, box);
		this.func_175811_a(world, Blocks.double_stone_slab.getDefaultState(), 6, 1, 2, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 2, 1, 0, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 2, 2, 0, box);
		this.func_175811_a(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode), 2, 3, 1, box);
		this.func_175810_a(world, box, rand, 2, 1, 0, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

		if (this.func_175807_a(world, 2, 0, -1, box).getBlock().getMaterial() == Material.air && this.func_175807_a(world, 2, -1, -1, box).getBlock().getMaterial() != Material.air)
		{
			this.func_175811_a(world, Blocks.stone_stairs.getStateFromMeta(this.getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, box);
		}

		this.func_175811_a(world, Blocks.air.getDefaultState(), 6, 1, 5, box);
		this.func_175811_a(world, Blocks.air.getDefaultState(), 6, 2, 5, box);
		this.func_175811_a(world, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.coordBaseMode.getOpposite()), 6, 3, 4, box);
		this.func_175810_a(world, box, rand, 6, 1, 5, EnumFacing.getHorizontal(this.getMetadataWithOffset(Blocks.oak_door, 1)));

		for (k = 0; k < 5; ++k)
		{
			for (l = 0; l < 9; ++l)
			{
				this.clearCurrentPositionBlocksUpwards(world, l, 7, k, box);
				this.func_175808_b(world, Blocks.cobblestone.getDefaultState(), l, -1, k, box);
			}
		}
		this.spawnVillagers(world, box, 4, 1, 2, 2);
		return true;
	}
}