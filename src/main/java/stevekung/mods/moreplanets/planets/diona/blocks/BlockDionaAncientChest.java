/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import stevekung.mods.moreplanets.core.blocks.BlockAncientChestMP;
import stevekung.mods.moreplanets.planets.diona.entities.EntityDionaMinionCreeper;
import stevekung.mods.moreplanets.planets.diona.tileentities.TileEntityDionaAncientChest;

public class BlockDionaAncientChest extends BlockAncientChestMP
{
	public BlockDionaAncientChest(String name)
	{
		super();
		this.setBlockName(name);
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5)
	{
		super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
		TileEntityDionaAncientChest tileentitychest = (TileEntityDionaAncientChest)par1World.getTileEntity(par2, par3, par4);

		if (tileentitychest != null)
		{
			tileentitychest.updateContainingBlockInfo();
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		TileEntityDionaAncientChest tileentitychest = (TileEntityDionaAncientChest)par1World.getTileEntity(par2, par3, par4);

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
						entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
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
			par1World.func_147453_f(par2, par3, par4, par5);
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (par1World.isRemote)
		{
			return true;
		}
		else if (par1World.rand.nextInt(10) == 0 && !player.capabilities.isCreativeMode)
		{
			if (!par1World.isRemote)
			{
				EntityDionaMinionCreeper creeper = new EntityDionaMinionCreeper(par1World);
				creeper.setPosition(par2 + 0.5, par3 + 2, par4 + 0.5);
				par1World.spawnEntityInWorld(creeper);
			}
		}
		else
		{
			IInventory iinventory = this.getInventory(par1World, par2, par3, par4);

			if (iinventory != null)
			{
				player.displayGUIChest(iinventory);
			}
		}
		return true;
	}

	public IInventory getInventory(World par1World, int par2, int par3, int par4)
	{
		Object object = par1World.getTileEntity(par2, par3, par4);

		if (object == null)
		{
			return null;
		}
		else if (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN))
		{
			return null;
		}
		else if (par1World.getBlock(par2 - 1, par3, par4) == this && par1World.isSideSolid(par2 - 1, par3 + 1, par4, ForgeDirection.DOWN))
		{
			return null;
		}
		else if (par1World.getBlock(par2 + 1, par3, par4) == this && par1World.isSideSolid(par2 + 1, par3 + 1, par4, ForgeDirection.DOWN))
		{
			return null;
		}
		else if (par1World.getBlock(par2, par3, par4 - 1) == this && par1World.isSideSolid(par2, par3 + 1, par4 - 1, ForgeDirection.DOWN))
		{
			return null;
		}
		else if (par1World.getBlock(par2, par3, par4 + 1) == this && par1World.isSideSolid(par2, par3 + 1, par4 + 1, ForgeDirection.DOWN))
		{
			return null;
		}
		else
		{
			if (par1World.getBlock(par2 - 1, par3, par4) == this)
			{
				object = new InventoryLargeChest(StatCollector.translateToLocal("container.diona.ancientchest.name"), (TileEntityDionaAncientChest)par1World.getTileEntity(par2 - 1, par3, par4), (IInventory)object);
			}

			if (par1World.getBlock(par2 + 1, par3, par4) == this)
			{
				object = new InventoryLargeChest(StatCollector.translateToLocal("container.diona.ancientchest.name"), (IInventory)object, (TileEntityDionaAncientChest)par1World.getTileEntity(par2 + 1, par3, par4));
			}

			if (par1World.getBlock(par2, par3, par4 - 1) == this)
			{
				object = new InventoryLargeChest(StatCollector.translateToLocal("container.diona.ancientchest.name"), (TileEntityDionaAncientChest)par1World.getTileEntity(par2, par3, par4 - 1), (IInventory)object);
			}

			if (par1World.getBlock(par2, par3, par4 + 1) == this)
			{
				object = new InventoryLargeChest(StatCollector.translateToLocal("container.diona.ancientchest.name"), (IInventory)object, (TileEntityDionaAncientChest)par1World.getTileEntity(par2, par3, par4 + 1));
			}
			return (IInventory)object;
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int par2, int par3, int par4, int par5)
	{
		if (world.rand.nextInt(10) == 0)
		{
			if (!world.isRemote)
			{
				EntityDionaMinionCreeper creeper = new EntityDionaMinionCreeper(world);
				creeper.setPosition(par2 + 0.5, par3 + 2, par4 + 0.5);
				world.spawnEntityInWorld(creeper);
			}
		}
		super.onBlockDestroyedByPlayer(world, par2, par3, par4, par5);
	}

	@Override
	public String chestTexture()
	{
		return "diona:diona_ancient_chest";
	}

	@Override
	public TileEntity getChestTile()
	{
		return new TileEntityDionaAncientChest();
	}
}