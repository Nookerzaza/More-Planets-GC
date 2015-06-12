/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockCakeMP extends BlockBaseMP
{
	public static PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);

	public BlockCakeMP()
	{
		super(Material.cake);
		this.setTickRandomly(true);
		this.setHardness(0.5F);
		this.setDefaultState(this.getDefaultState().withProperty(BITES, 0));
		this.setStepSound(soundTypeCloth);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, BlockPos pos)
	{
		float f1 = 0.0625F;
		float f2 = (1 + ((Integer)world.getBlockState(pos).getValue(BITES)).intValue() * 2) / 16.0F;
		float f3 = 0.5F;
		this.setBlockBounds(f2, 0.0F, f1, 1.0F - f1, f3, 1.0F - f1);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		float f1 = 0.0625F;
		float f2 = 0.5F;
		this.setBlockBounds(f1, 0.0F, f1, 1.0F - f1, f2, 1.0F - f1);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(World world, BlockPos pos, IBlockState state)
	{
		float f1 = 0.0625F;
		float f2 = (1 + ((Integer)state.getValue(BITES)).intValue() * 2) / 16.0F;
		float f3 = 0.5F;
		return new AxisAlignedBB(pos.getX() + f2, pos.getY(), pos.getZ() + f1, pos.getX() + 1 - f1, pos.getY() + f3, pos.getZ() + 1 - f1);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos)
	{
		return this.getCollisionBoundingBox(world, pos, world.getBlockState(pos));
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float x, float y, float z)
	{
		this.eatCake(world, pos, state, player);
		return true;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player)
	{
		this.eatCake(world, pos, world.getBlockState(pos), player);
	}

	private void eatCake(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		if (!player.canEat(false))
		{
			return;
		}

		player.getFoodStats().addStats(this.getFoodAmount(), this.getSaturationAmount());
		int i = ((Integer)state.getValue(BITES)).intValue();

		if (i < 6)
		{
			world.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
		}
		else
		{
			world.setBlockToAir(pos);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		if (super.canPlaceBlockAt(world, pos))
		{
			return this.canBlockStay(world, pos);
		}
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block block)
	{
		if (!this.canBlockStay(world, pos))
		{
			world.setBlockToAir(pos);
		}
	}

	private boolean canBlockStay(World world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).getBlock().getMaterial().isSolid();
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return null;
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition moving, World world, BlockPos pos)
	{
		return new ItemStack(this, 1, 0);
	}

	@Override
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(BITES, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(BITES)).intValue();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { BITES });
	}

	@Override
	public int getComparatorInputOverride(World world, BlockPos pos)
	{
		return (7 - ((Integer)world.getBlockState(pos).getValue(BITES)).intValue()) * 2;
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	public abstract int getFoodAmount();
	public abstract float getSaturationAmount();
}