/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.diona.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.items.ItemMorePlanet;
import stevekung.mods.moreplanets.planets.diona.entities.projectiles.EntityLaserMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLaserGun extends ItemMorePlanet
{
	public ItemLaserGun(String name)
	{
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(860);
		this.setUnlocalizedName(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon("diona:laser_gun");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		boolean flag = player.capabilities.isCreativeMode;

		if (flag || player.inventory.hasItem(DionaItems.laser_charge))
		{
			EntityLaserMP laser = new EntityLaserMP(world, player, 1.0F);
			itemStack.damageItem(1, player);
			world.playSoundAtEntity(player, "mpcore:player.laser", 1.0F, 2.0F / (1.0F * 0.4F + 1.2F) + 1.0F * 0.5F);
			int slot = -1;

			if (player.inventory.hasItemStack(new ItemStack(DionaItems.laser_charge, 1, 0)))
			{
				laser.setLaserType(0);

				for (int k = 0; k < player.inventory.mainInventory.length; ++k)
				{
					if (player.inventory.mainInventory[k] != null && player.inventory.mainInventory[k].getItem() == DionaItems.laser_charge && player.inventory.mainInventory[k].getItemDamage() == 0)
					{
						slot = k;
						break;
					}
				}
			}
			else if (player.inventory.hasItemStack(new ItemStack(DionaItems.laser_charge, 1, 1)))
			{
				laser.setLaserType(1);

				for (int k = 0; k < player.inventory.mainInventory.length; ++k)
				{
					if (player.inventory.mainInventory[k] != null && player.inventory.mainInventory[k].getItem() == DionaItems.laser_charge && player.inventory.mainInventory[k].getItemDamage() == 1)
					{
						slot = k;
						break;
					}
				}
			}
			else if (player.inventory.hasItemStack(new ItemStack(DionaItems.laser_charge, 1, 2)))
			{
				laser.setLaserType(2);

				for (int k = 0; k < player.inventory.mainInventory.length; ++k)
				{
					if (player.inventory.mainInventory[k] != null && player.inventory.mainInventory[k].getItem() == DionaItems.laser_charge && player.inventory.mainInventory[k].getItemDamage() == 2)
					{
						slot = k;
						break;
					}
				}
			}
			else if (player.inventory.hasItemStack(new ItemStack(DionaItems.laser_charge, 1, 3)))
			{
				laser.setLaserType(3);

				for (int k = 0; k < player.inventory.mainInventory.length; ++k)
				{
					if (player.inventory.mainInventory[k] != null && player.inventory.mainInventory[k].getItem() == DionaItems.laser_charge && player.inventory.mainInventory[k].getItemDamage() == 3)
					{
						slot = k;
						break;
					}
				}
			}
			else if (player.inventory.hasItemStack(new ItemStack(DionaItems.laser_charge, 1, 4)))
			{
				laser.setLaserType(4);

				for (int k = 0; k < player.inventory.mainInventory.length; ++k)
				{
					if (player.inventory.mainInventory[k] != null && player.inventory.mainInventory[k].getItem() == DionaItems.laser_charge && player.inventory.mainInventory[k].getItemDamage() == 4)
					{
						slot = k;
						break;
					}
				}
			}
			if (!world.isRemote)
			{
				world.spawnEntityInWorld(laser);
			}
			if (!flag && slot >= 0)
			{
				player.inventory.decrStackSize(slot, 1);
			}
		}
		return itemStack;
	}
}