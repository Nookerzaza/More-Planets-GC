/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

//package stevekung.mods.moreplanets.planets.fronos.inventory.container;
//
//import micdoodle8.mods.galacticraft.api.item.IItemElectric;
//import micdoodle8.mods.galacticraft.core.energy.item.ItemElectricBase;
//import micdoodle8.mods.galacticraft.core.inventory.SlotSpecific;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.Slot;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.FluidContainerRegistry;
//import net.minecraftforge.fluids.FluidStack;
//import stevekung.mods.moreplanets.planets.fronos.tileentities.TileEntityMineralWaterGenerator;
//
//public class ContainerMineralWaterGenerator extends Container
//{
//	private final TileEntityMineralWaterGenerator tileEntity;
//
//	public ContainerMineralWaterGenerator(InventoryPlayer par1InventoryPlayer, TileEntityMineralWaterGenerator tileEntity)
//	{
//		this.tileEntity = tileEntity;
//
//		// Electric Input Slot
//		this.addSlotToContainer(new SlotSpecific(tileEntity, 0, 34, 50, ItemElectricBase.class));
//
//		// Input slot
//		this.addSlotToContainer(new Slot(tileEntity, 1, 7, 7));
//
//		// output slot
//		this.addSlotToContainer(new Slot(tileEntity, 3, 153, 7));
//		int var3;
//
//		for (var3 = 0; var3 < 3; ++var3)
//		{
//			for (int var4 = 0; var4 < 9; ++var4)
//			{
//				this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 104 + var3 * 18 - 18));
//			}
//		}
//
//		for (var3 = 0; var3 < 9; ++var3)
//		{
//			this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 144));
//		}
//
//		tileEntity.openInventory();
//	}
//
//	@Override
//	public void onContainerClosed(EntityPlayer entityplayer)
//	{
//		super.onContainerClosed(entityplayer);
//		this.tileEntity.closeInventory();
//	}
//
//	@Override
//	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
//	{
//		return this.tileEntity.isUseableByPlayer(par1EntityPlayer);
//	}
//
//	@Override
//	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
//	{
//		ItemStack var2 = null;
//		final Slot slot = (Slot) this.inventorySlots.get(par1);
//
//		if (slot != null && slot.getHasStack())
//		{
//			final ItemStack var4 = slot.getStack();
//			var2 = var4.copy();
//
//			if (par1 < 3)
//			{
//				if (!this.mergeItemStack(var4, 4, 39, true))
//				{
//					return null;
//				}
//				if (par1 == 2)
//				{
//					slot.onSlotChange(var4, var2);
//				}
//			}
//			else
//			{
//				if (var4.getItem() instanceof IItemElectric)
//				{
//					if (!this.mergeItemStack(var4, 0, 1, false))
//					{
//						return null;
//					}
//				}
//				else
//				{
//					FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(var4);
//					if (liquid != null && liquid.getFluid() != null && liquid.getFluid().getName().equals("water"))
//					{
//						if (!this.mergeItemStack(var4, 1, 2, false))
//						{
//							return null;
//						}
//					}
//					else if (FluidContainerRegistry.isEmptyContainer(var4))
//					{
//						if (!this.mergeItemStack(var4, 2, 4, false))
//						{
//							return null;
//						}
//					}
//					else if (par1 < 31)
//					{
//						if (!this.mergeItemStack(var4, 31, 35, false))
//						{
//							return null;
//						}
//					}
//					else if (!this.mergeItemStack(var4, 4, 31, false))
//					{
//						return null;
//					}
//				}
//			}
//
//			if (var4.stackSize == 0)
//			{
//				slot.putStack((ItemStack) null);
//			}
//			else
//			{
//				slot.onSlotChanged();
//			}
//
//			if (var4.stackSize == var2.stackSize)
//			{
//				return null;
//			}
//
//			slot.onPickupFromSlot(par1EntityPlayer, var4);
//		}
//
//		return var2;
//	}
//}