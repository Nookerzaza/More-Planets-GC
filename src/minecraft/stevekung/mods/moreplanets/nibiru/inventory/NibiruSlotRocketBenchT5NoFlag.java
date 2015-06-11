/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.inventory;

import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.GCCorePacketHandlerClient.EnumPacketClient;
import micdoodle8.mods.galacticraft.core.util.PacketUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import stevekung.mods.moreplanets.diona.items.DionaItems;
import stevekung.mods.moreplanets.nibiru.items.NibiruItems;
import stevekung.mods.moreplanets.polongnius.items.PolongniusItems;

public class NibiruSlotRocketBenchT5NoFlag extends Slot
{
	private final int index;
	private final int x, y, z;
	private final EntityPlayer player;

	public NibiruSlotRocketBenchT5NoFlag(IInventory par2IInventory, int par3, int par4, int par5, int x, int y, int z, EntityPlayer player)
	{
		super(par2IInventory, par3, par4, par5);
		this.index = par3;
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
	}

	@Override
	public void onSlotChanged()
	{
		if (this.player instanceof EntityPlayerMP)
		{
			final Object[] toSend = { this.x, this.y, this.z };

			for (int var12 = 0; var12 < this.player.worldObj.playerEntities.size(); ++var12)
			{
				final EntityPlayerMP var13 = (EntityPlayerMP) this.player.worldObj.playerEntities.get(var12);

				if (var13.dimension == this.player.worldObj.provider.dimensionId)
				{
					final double var14 = this.x - var13.posX;
					final double var16 = this.y - var13.posY;
					final double var18 = this.z - var13.posZ;

					if (var14 * var14 + var16 * var16 + var18 * var18 < 20 * 20)
					{
						var13.playerNetServerHandler.sendPacketToPlayer(PacketUtil.createPacket(GalacticraftCore.CHANNEL, EnumPacketClient.SPAWN_SPARK_PARTICLES, toSend));
					}
				}
			}
		}
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		switch (this.index)
		{
		case 1:
			return par1ItemStack.getItem().itemID == DionaItems.rocketPartT3.itemID && par1ItemStack.getItemDamage() == 4 ? true : false;
		case 2:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 3:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 4:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 5:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 6:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 7:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 8:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 9:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 10:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 11:
			return par1ItemStack.getItem().itemID == PolongniusItems.rocketPartT4.itemID && par1ItemStack.getItemDamage() == 2 ? true : false;
		case 12:
			return par1ItemStack.getItem().itemID == NibiruItems.rocketPartT5.itemID && par1ItemStack.getItemDamage() == 1 ? true : false;
		case 13:
			return par1ItemStack.getItem().itemID == DionaItems.rocketPartT3.itemID && par1ItemStack.getItemDamage() == 1 ? true : false;
		case 14:
			return par1ItemStack.getItem().itemID == DionaItems.rocketPartT3.itemID && par1ItemStack.getItemDamage() == 1 ? true : false;
		case 15:
			return par1ItemStack.getItem().itemID == NibiruItems.rocketPartT5.itemID && par1ItemStack.getItemDamage() == 0 ? true : false;
		case 16:
			return par1ItemStack.getItem().itemID == NibiruItems.rocketPartT5.itemID && par1ItemStack.getItemDamage() == 1 ? true : false;
		case 17:
			return par1ItemStack.getItem().itemID == DionaItems.rocketPartT3.itemID && par1ItemStack.getItemDamage() == 1 ? true : false;
		case 18:
			return par1ItemStack.getItem().itemID == DionaItems.rocketPartT3.itemID && par1ItemStack.getItemDamage() == 1 ? true : false;
		case 19:
			return true;
		case 20:
			return true;
		case 21:
			return true;
		}
		return false;
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
}