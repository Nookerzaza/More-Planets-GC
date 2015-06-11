/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import static net.minecraftforge.common.ForgeDirection.DOWN;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.MorePlanetCore;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import stevekung.mods.moreplanets.nibiru.entities.NibiruEntityInfectedWorm;
import stevekung.mods.moreplanets.nibiru.tileentities.NibiruTileEntityAncientChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NibiruBlockAncientChest extends BlockContainer
{
	private final Random random = new Random();

	public NibiruBlockAncientChest(int par1)
	{
		super(par1, Material.wood);
		this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		this.setResistance(5.0F);
		this.setHardness(3.0F);
		this.setStepSound(Block.soundWoodFootstep);
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
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public int getRenderType()
	{
		return MorePlanetCore.proxy.getBlockRenderID(this.blockID);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		if (par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID)
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
		}
		else if (par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID)
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
		}
		else if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID)
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
		else if (par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID)
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
		}
		else
		{
			this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		}
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		super.onBlockAdded(par1World, par2, par3, par4);
		this.unifyAdjacentChests(par1World, par2, par3, par4);
		int l = par1World.getBlockId(par2, par3, par4 - 1);
		int i1 = par1World.getBlockId(par2, par3, par4 + 1);
		int j1 = par1World.getBlockId(par2 - 1, par3, par4);
		int k1 = par1World.getBlockId(par2 + 1, par3, par4);

		if (l == this.blockID)
		{
			this.unifyAdjacentChests(par1World, par2, par3, par4 - 1);
		}

		if (i1 == this.blockID)
		{
			this.unifyAdjacentChests(par1World, par2, par3, par4 + 1);
		}

		if (j1 == this.blockID)
		{
			this.unifyAdjacentChests(par1World, par2 - 1, par3, par4);
		}

		if (k1 == this.blockID)
		{
			this.unifyAdjacentChests(par1World, par2 + 1, par3, par4);
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = par1World.getBlockId(par2, par3, par4 - 1);
		int i1 = par1World.getBlockId(par2, par3, par4 + 1);
		int j1 = par1World.getBlockId(par2 - 1, par3, par4);
		int k1 = par1World.getBlockId(par2 + 1, par3, par4);
		byte b0 = 0;
		int l1 = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l1 == 0)
		{
			b0 = 2;
		}

		if (l1 == 1)
		{
			b0 = 5;
		}

		if (l1 == 2)
		{
			b0 = 3;
		}

		if (l1 == 3)
		{
			b0 = 4;
		}

		if (l != this.blockID && i1 != this.blockID && j1 != this.blockID && k1 != this.blockID)
		{
			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
		}
		else
		{
			if ((l == this.blockID || i1 == this.blockID) && (b0 == 4 || b0 == 5))
			{
				if (l == this.blockID)
				{
					par1World.setBlockMetadataWithNotify(par2, par3, par4 - 1, b0, 3);
				}
				else
				{
					par1World.setBlockMetadataWithNotify(par2, par3, par4 + 1, b0, 3);
				}

				par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
			}

			if ((j1 == this.blockID || k1 == this.blockID) && (b0 == 2 || b0 == 3))
			{
				if (j1 == this.blockID)
				{
					par1World.setBlockMetadataWithNotify(par2 - 1, par3, par4, b0, 3);
				}
				else
				{
					par1World.setBlockMetadataWithNotify(par2 + 1, par3, par4, b0, 3);
				}

				par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
			}
		}
	}

	public void unifyAdjacentChests(World par1World, int par2, int par3, int par4)
	{
		if (!par1World.isRemote)
		{
			int l = par1World.getBlockId(par2, par3, par4 - 1);
			int i1 = par1World.getBlockId(par2, par3, par4 + 1);
			int j1 = par1World.getBlockId(par2 - 1, par3, par4);
			int k1 = par1World.getBlockId(par2 + 1, par3, par4);
			int l1;
			int i2;
			byte b0;
			int j2;

			if (l != this.blockID && i1 != this.blockID)
			{
				if (j1 != this.blockID && k1 != this.blockID)
				{
					b0 = 3;

					if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
					{
						b0 = 3;
					}

					if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
					{
						b0 = 2;
					}

					if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
					{
						b0 = 5;
					}

					if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
					{
						b0 = 4;
					}
				}
				else
				{
					l1 = par1World.getBlockId(j1 == this.blockID ? par2 - 1 : par2 + 1, par3, par4 - 1);
					i2 = par1World.getBlockId(j1 == this.blockID ? par2 - 1 : par2 + 1, par3, par4 + 1);
					b0 = 3;
					if (j1 == this.blockID)
					{
						j2 = par1World.getBlockMetadata(par2 - 1, par3, par4);
					}
					else
					{
						j2 = par1World.getBlockMetadata(par2 + 1, par3, par4);
					}

					if (j2 == 2)
					{
						b0 = 2;
					}

					if ((Block.opaqueCubeLookup[l] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[i2])
					{
						b0 = 3;
					}

					if ((Block.opaqueCubeLookup[i1] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[l1])
					{
						b0 = 2;
					}
				}
			}
			else
			{
				l1 = par1World.getBlockId(par2 - 1, par3, l == this.blockID ? par4 - 1 : par4 + 1);
				i2 = par1World.getBlockId(par2 + 1, par3, l == this.blockID ? par4 - 1 : par4 + 1);
				b0 = 5;
				if (l == this.blockID)
				{
					j2 = par1World.getBlockMetadata(par2, par3, par4 - 1);
				}
				else
				{
					j2 = par1World.getBlockMetadata(par2, par3, par4 + 1);
				}

				if (j2 == 4)
				{
					b0 = 4;
				}

				if ((Block.opaqueCubeLookup[j1] || Block.opaqueCubeLookup[l1]) && !Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[i2])
				{
					b0 = 5;
				}

				if ((Block.opaqueCubeLookup[k1] || Block.opaqueCubeLookup[i2]) && !Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[l1])
				{
					b0 = 4;
				}
			}

			par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 3);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		int l = 0;

		if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID)
		{
			++l;
		}

		if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
		{
			++l;
		}

		if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID)
		{
			++l;
		}

		if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)
		{
			++l;
		}

		return l > 1 ? false : this.isThereANeighborChest(par1World, par2 - 1, par3, par4) ? false : this.isThereANeighborChest(par1World, par2 + 1, par3, par4) ? false : this.isThereANeighborChest(par1World, par2, par3, par4 - 1) ? false : !this.isThereANeighborChest(par1World, par2, par3, par4 + 1);
	}

	private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4)
	{
		return par1World.getBlockId(par2, par3, par4) != this.blockID ? false : par1World.getBlockId(par2 - 1, par3, par4) == this.blockID ? true : par1World.getBlockId(par2 + 1, par3, par4) == this.blockID ? true : par1World.getBlockId(par2, par3, par4 - 1) == this.blockID ? true : par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		NibiruTileEntityAncientChest tileentitychest = (NibiruTileEntityAncientChest)par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentitychest != null)
		{
			tileentitychest.updateContainingBlockInfo();
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		NibiruTileEntityAncientChest tileentitychest = (NibiruTileEntityAncientChest)par1World.getBlockTileEntity(par2, par3, par4);

		if (tileentitychest != null)
		{
			for (int j1 = 0; j1 < tileentitychest.getSizeInventory(); ++j1)
			{
				ItemStack itemstack = tileentitychest.getStackInSlot(j1);

				if (itemstack != null)
				{
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; par1World.spawnEntityInWorld(entityitem))
					{
						int k1 = this.random.nextInt(21) + 10;

						if (k1 > itemstack.stackSize)
						{
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (float)this.random.nextGaussian() * f3;
						entityitem.motionY = (float)this.random.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float)this.random.nextGaussian() * f3;

						if (itemstack.hasTagCompound())
						{
							entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
					}
				}
			}

			par1World.func_96440_m(par2, par3, par4, par5);
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else if (par1World.rand.nextInt(10) == 0 && !par5EntityPlayer.capabilities.isCreativeMode)
		{
			if (!par1World.isRemote)
			{
				NibiruEntityInfectedWorm infectedWorm = new NibiruEntityInfectedWorm(par1World);
				infectedWorm.setPosition(par2 + 0.5, par3 + 2, par4 + 0.5);
				par1World.spawnEntityInWorld(infectedWorm);
			}
		}
		else
		{
			IInventory iinventory = this.getInventory(par1World, par2, par3, par4);

			if (iinventory != null)
			{
				par5EntityPlayer.displayGUIChest(iinventory);
			}
		}
		return true;
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
	{
		if (par1World.rand.nextInt(10) == 0)
		{
			if (!par1World.isRemote)
			{
				NibiruEntityInfectedWorm infectedWorm = new NibiruEntityInfectedWorm(par1World);
				infectedWorm.setPosition(par2 + 0.5, par3 + 2, par4 + 0.5);
				par1World.spawnEntityInWorld(infectedWorm);
			}
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4)
	{
		Object object = par1World.getBlockTileEntity(par2, par3, par4);

		if (object == null)
		{
			return null;
		}
		else if (par1World.isBlockSolidOnSide(par2, par3 + 1, par4, DOWN))
		{
			return null;
		}
		else if (isOcelotBlockingChest(par1World, par2, par3, par4))
		{
			return null;
		}
		else if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 - 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 - 1, par3, par4)))
		{
			return null;
		}
		else if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID && (par1World.isBlockSolidOnSide(par2 + 1, par3 + 1, par4, DOWN) || isOcelotBlockingChest(par1World, par2 + 1, par3, par4)))
		{
			return null;
		}
		else if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 - 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 - 1)))
		{
			return null;
		}
		else if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID && (par1World.isBlockSolidOnSide(par2, par3 + 1, par4 + 1, DOWN) || isOcelotBlockingChest(par1World, par2, par3, par4 + 1)))
		{
			return null;
		}
		else
		{
			if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID)
			{
				object = new InventoryLargeChest("container.chestDouble", (NibiruTileEntityAncientChest)par1World.getBlockTileEntity(par2 - 1, par3, par4), (IInventory)object);
			}

			if (par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (NibiruTileEntityAncientChest)par1World.getBlockTileEntity(par2 + 1, par3, par4));
			}

			if (par1World.getBlockId(par2, par3, par4 - 1) == this.blockID)
			{
				object = new InventoryLargeChest("container.chestDouble", (NibiruTileEntityAncientChest)par1World.getBlockTileEntity(par2, par3, par4 - 1), (IInventory)object);
			}

			if (par1World.getBlockId(par2, par3, par4 + 1) == this.blockID)
			{
				object = new InventoryLargeChest("container.chestDouble", (IInventory)object, (NibiruTileEntityAncientChest)par1World.getBlockTileEntity(par2, par3, par4 + 1));
			}

			return (IInventory)object;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new NibiruTileEntityAncientChest();
	}

	public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3)
	{
		Iterator iterator = par0World.getEntitiesWithinAABB(EntityOcelot.class, AxisAlignedBB.getAABBPool().getAABB(par1, par2 + 1, par3, par1 + 1, par2 + 2, par3 + 1)).iterator();
		EntityOcelot entityocelot;

		do
		{
			if (!iterator.hasNext())
			{
				return false;
			}

			EntityOcelot entityocelot1 = (EntityOcelot)iterator.next();
			entityocelot = entityocelot1;
		}
		while (!entityocelot.isSitting());

		return true;
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory(this.getInventory(par1World, par2, par3, par4));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("nibiru:nibiruAncientChest");
	}
}