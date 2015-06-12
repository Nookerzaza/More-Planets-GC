/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.common.entities.ai.EntityAIFronosBeg;
import stevekung.mods.moreplanets.common.entities.ai.EntityAITemptMP;
import stevekung.mods.moreplanets.core.init.MPItems;
import stevekung.mods.moreplanets.planets.fronos.items.FronosItems;

public class EntityKiwi extends IFronosPet
{
	public EntityKiwi(World world)
	{
		super(world);
		this.setSize(0.5F, 1.0F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
		this.tasks.addTask(2, this.aiSit);
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(5, new EntityAITemptMP(this, 1.1D, new ItemStack(FronosItems.candy_food, 1, 1), false));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(8, new EntityAIFronosBeg(this, 8.0F));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.15D);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + par2);

		for (int k = 0; k < j; ++k)
		{
			this.entityDropItem(new ItemStack(FronosItems.fronos_fruits, 1, 0), 0.0F);
		}
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemStack = player.inventory.getCurrentItem();

		if (itemStack != null && itemStack.getItem() == MPItems.spawn_egg_mp && itemStack.getItemDamage() == 1017)
		{
			if (!this.worldObj.isRemote)
			{
				EntityAgeable entityageable = this.createChild(this);

				if (entityageable != null)
				{
					entityageable.setGrowingAge(-24000);
					entityageable.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
					this.worldObj.spawnEntityInWorld(entityageable);

					if (itemStack.hasDisplayName())
					{
						entityageable.setCustomNameTag(itemStack.getDisplayName());
					}
					if (!player.capabilities.isCreativeMode)
					{
						--itemStack.stackSize;

						if (itemStack.stackSize <= 0)
						{
							player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
						}
					}
				}
			}
			return true;
		}
		return super.interact(player);
	}

	@Override
	public ItemStack getPickedResult(MovingObjectPosition target)
	{
		return new ItemStack(MPItems.spawn_egg_mp, 1, 1017);
	}

	@Override
	public EntityKiwi createChild(EntityAgeable entity)
	{
		EntityKiwi pet = new EntityKiwi(this.worldObj);
		String owner = this.getOwnerId();

		if (owner != null && owner.trim().length() > 0)
		{
			pet.setOwnerId(owner);
			pet.setTamed(true);
		}
		return pet;
	}
}