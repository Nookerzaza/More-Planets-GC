/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.common.blocks.BlockBaseMP;

public class BlockInfectedDirt extends BlockBaseMP /*implements ITerraformableBlock*/
{
	public static PropertyEnum VARIANT = PropertyEnum.create("variant", BlockType.class);

	public BlockInfectedDirt(String name)
	{
		super(Material.ground);
		this.setStepSound(Block.soundTypeGravel);
		this.setHardness(0.55F);
		this.blockState.getBaseState().withProperty(VARIANT, BlockType.infected_dirt);
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing side, IPlantable plant)
	{
		Block block = plant.getPlant(world, pos).getBlock();
		return block == NibiruBlocks.nibiru_sapling;
	}

	/*@Override
	public boolean isTerraformable(World world, BlockPos pos, IBlockState state)
	{
		return true;
	}*/

	@Override
	public int damageDropped(IBlockState state)
	{
		return this.getMetaFromState(state);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < 2; ++i)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitVecX, float hitVecY, float hitVecZ)
	{
		if (player.getCurrentEquippedItem() != null)
		{
			if (player.getCurrentEquippedItem().getDisplayName().toLowerCase().contains("hoe"))
			{
				if (state == state.withProperty(VARIANT, BlockType.infected_dirt))
				{
					Block farmland = NibiruBlocks.infected_farmland;

					world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, farmland.stepSound.getStepSound(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getFrequency() * 0.8F);

					if (!world.isRemote)
					{
						world.setBlockState(pos, farmland.getDefaultState(), 2);
					}
					player.getCurrentEquippedItem().damageItem(1, player);
				}
				else if (state == state.withProperty(VARIANT, BlockType.coarse_infected_dirt))
				{
					Block block = this;

					world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getFrequency() * 0.8F);

					if (!world.isRemote)
					{
						world.setBlockState(pos, state.withProperty(VARIANT, BlockType.infected_dirt), 2);
					}
					player.getCurrentEquippedItem().damageItem(1, player);
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, BlockType.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((BlockType)state.getValue(VARIANT)).ordinal();
	}

	public static enum BlockType implements IStringSerializable
	{
		infected_dirt,
		coarse_infected_dirt;

		@Override
		public String toString()
		{
			return this.getName();
		}

		@Override
		public String getName()
		{
			return this.name();
		}
	}
}