/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.fronos.blocks;

import static net.minecraftforge.common.ForgeDirection.EAST;
import static net.minecraftforge.common.ForgeDirection.NORTH;
import static net.minecraftforge.common.ForgeDirection.SOUTH;
import static net.minecraftforge.common.ForgeDirection.WEST;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.fronos.core.ModuleFronos;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FronosBlockPurpleCandyTorch extends BlockTorch
{
	public FronosBlockPurpleCandyTorch(int id)
	{
		super(id);
		this.setTickRandomly(true);
		this.setLightValue(1.00F);
		this.setStepSound(Block.soundClothFootstep);
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("fronos:purpleCandyTorch");
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleFronos.fronosTab;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public float getAmbientOcclusionLightValue(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		return par1IBlockAccess.isBlockNormalCube(par2, par3, par4) ? 0.2F : 1.0F;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	private boolean canPlaceTorchOn(World par1World, int par2, int par3, int par4)
	{
		if (par1World.doesBlockHaveSolidTopSurface(par2, par3, par4))
		{
			return true;
		}
		else
		{
			int block = par1World.getBlockId(par2, par3, par4);
			return Block.blocksList[block] != null && Block.blocksList[block].canPlaceTorchOnTop(par1World, par2, par3, par4);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST, true) || par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST, true) || par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH, true) || par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH, true) || this.canPlaceTorchOn(par1World, par2, par3 - 1, par4);
	}

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
	{
		int j1 = par9;

		if (par5 == 1 && this.canPlaceTorchOn(par1World, par2, par3 - 1, par4))
		{
			j1 = 5;
		}

		if (par5 == 2 && par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH, true))
		{
			j1 = 4;
		}

		if (par5 == 3 && par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH, true))
		{
			j1 = 3;
		}

		if (par5 == 4 && par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST, true))
		{
			j1 = 2;
		}

		if (par5 == 5 && par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST, true))
		{
			j1 = 1;
		}

		return j1;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
		{
			this.onBlockAdded(par1World, par2, par3, par4);
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (par1World.getBlockMetadata(par2, par3, par4) == 0)
		{
			if (par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
			}
			else if (par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			}
			else if (par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			}
			else if (par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH, true))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			}
			else if (this.canPlaceTorchOn(par1World, par2, par3 - 1, par4))
			{
				par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			}
		}
		this.dropTorchIfCantStay(par1World, par2, par3, par4);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if (this.dropTorchIfCantStay(par1World, par2, par3, par4))
		{
			int i1 = par1World.getBlockMetadata(par2, par3, par4);
			boolean flag = false;

			if (!par1World.isBlockSolidOnSide(par2 - 1, par3, par4, EAST, true) && i1 == 1)
			{
				flag = true;
			}

			if (!par1World.isBlockSolidOnSide(par2 + 1, par3, par4, WEST, true) && i1 == 2)
			{
				flag = true;
			}

			if (!par1World.isBlockSolidOnSide(par2, par3, par4 - 1, SOUTH, true) && i1 == 3)
			{
				flag = true;
			}

			if (!par1World.isBlockSolidOnSide(par2, par3, par4 + 1, NORTH, true) && i1 == 4)
			{
				flag = true;
			}

			if (!this.canPlaceTorchOn(par1World, par2, par3 - 1, par4) && i1 == 5)
			{
				flag = true;
			}

			if (flag)
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}
		}
	}

	@Override
	protected boolean dropTorchIfCantStay(World par1World, int par2, int par3, int par4)
	{
		if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
		{
			if (par1World.getBlockId(par2, par3, par4) == this.blockID)
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}

			return false;
		}
		else
		{
			return true;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4);
		double d0 = par2 + 0.5F;
		double d1 = par3 + 0.7F;
		double d2 = par4 + 0.5F;
		double d3 = 0.2199999988079071D;
		double d4 = 0.27000001072883606D;

		if (l == 1)
		{
			par1World.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 2)
		{
			par1World.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 3)
		{
			par1World.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
		}
		else if (l == 4)
		{
			par1World.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
		}
		else
		{
			par1World.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
			par1World.spawnParticle("flame", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3)
	{
		int l = par1World.getBlockMetadata(par2, par3, par4) & 7;
		float f = 0.15F;

		if (l == 1)
		{
			this.setBlockBounds(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
		}
		else if (l == 2)
		{
			this.setBlockBounds(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
		}
		else if (l == 3)
		{
			this.setBlockBounds(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
		}
		else if (l == 4)
		{
			this.setBlockBounds(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
		}
		else
		{
			f = 0.1F;
			this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
		}
		return super.collisionRayTrace(par1World, par2, par3, par4, par5Vec3, par6Vec3);
	}
}