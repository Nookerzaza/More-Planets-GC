/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.moons.koentus.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import stevekung.mods.moreplanets.moons.koentus.dimension.IKoentusMeteor;
import stevekung.mods.moreplanets.moons.koentus.entities.EntityKoentusMeteor;

public class KoentusMeteorTickHandler
{
	@SubscribeEvent
	public void meteorTickUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayerMP)
		{
			this.onPlayerUpdate((EntityPlayerMP) event.entityLiving);
		}
	}

	private void onPlayerUpdate(EntityPlayerMP player)
	{
		this.throwKoentusMeteors(player);
	}

	private void throwKoentusMeteors(EntityPlayerMP player)
	{
		World world = player.worldObj;

		if (world.provider instanceof IKoentusMeteor && FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
		{
			if (((IKoentusMeteor) world.provider).getKoentusMeteorFrequency() > 0)
			{
				int f = (int) (((IKoentusMeteor) world.provider).getKoentusMeteorFrequency() * 1000D);

				if (world.rand.nextInt(f) == 0)
				{
					EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

					if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
					{
						int x, y, z;
						double motX, motZ;
						x = world.rand.nextInt(20) - 10;
						y = world.rand.nextInt(20) + 200;
						z = world.rand.nextInt(20) - 10;
						motX = world.rand.nextDouble() * 5;
						motZ = world.rand.nextDouble() * 5;

						EntityKoentusMeteor meteor = new EntityKoentusMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 1);

						if (!world.isRemote)
						{
							world.spawnEntityInWorld(meteor);
						}
					}
				}

				if (world.rand.nextInt(f * 3) == 0)
				{
					EntityPlayer closestPlayer = world.getClosestPlayerToEntity(player, 100);

					if (closestPlayer == null || closestPlayer.getEntityId() <= player.getEntityId())
					{
						int x, y, z;
						double motX, motZ;
						x = world.rand.nextInt(20) - 10;
						y = world.rand.nextInt(20) + 200;
						z = world.rand.nextInt(20) - 10;
						motX = world.rand.nextDouble() * 5;
						motZ = world.rand.nextDouble() * 5;

						EntityKoentusMeteor meteor = new EntityKoentusMeteor(world, player.posX + x, player.posY + y, player.posZ + z, motX - 2.5D, 0, motZ - 2.5D, 6);

						if (!world.isRemote)
						{
							world.spawnEntityInWorld(meteor);
						}
					}
				}
			}
		}
	}
}