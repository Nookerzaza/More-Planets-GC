/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;

public abstract class BlockFilledCup extends BlockBaseMP implements ITileEntityProvider
{
	public BlockFilledCup()
	{
		super(Material.plants);
		this.setHardness(0.6F);
		this.setTickRandomly(true);
		this.setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.5F, 0.7F);
		this.setBlockTextureName("fronos:cup");
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		this.canBlockStay(par1World, par2, par3, par4);
	}

	@Override
	public boolean canBlockStay(World world, int par2, int par3, int par4)
	{
		if (!this.canPlaceBlockAt(world, par2, par3, par4))
		{
			this.dropBlockAsItem(world, par2, par3, par4, world.getBlockMetadata(par2, par3, par4), 0);
			world.setBlockToAir(par2, par3, par4);
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		Block block = par1World.getBlock(par2, par3 - 1, par4);

		if (block == null)
		{
			return false;
		}
		if (!block.isLeaves(par1World, par2, par3 - 1, par4) && !block.isOpaqueCube())
		{
			return false;
		}
		return par1World.getBlock(par2, par3 - 1, par4).getMaterial().blocksMovement();
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		int angle = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		byte change = 0;

		switch (angle)
		{
		case 0:
			change = 0;
			break;
		case 1:
			change = 3;
			break;
		case 2:
			change = 1;
			break;
		case 3:
			change = 2;
			break;
		}
		world.setBlockMetadataWithNotify(x, y, z, change, 3);
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return null;
	}
}