/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.koentus.proxy;

import java.util.EnumSet;

import micdoodle8.mods.galacticraft.core.client.GCCoreCloudRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import stevekung.mods.moreplanets.koentus.core.planets.KoentusSkyProvider;
import stevekung.mods.moreplanets.koentus.dimension.KoentusWorldProvider;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxyKoentus extends CommonProxyKoentus
{
	@Override
	public void init(FMLInitializationEvent event)
	{
		TickRegistry.registerTickHandler(new TickHandlerClient(), Side.CLIENT);
	}

	public static class TickHandlerClient implements ITickHandler
	{
		@Override
		public void tickStart(EnumSet<TickType> type, Object... tickData)
		{
			final Minecraft minecraft = FMLClientHandler.instance().getClient();

			final WorldClient world = minecraft.theWorld;

			if (type.equals(EnumSet.of(TickType.CLIENT)))
			{
				if (world != null && world.provider instanceof KoentusWorldProvider)
				{
					if (world.provider.getSkyRenderer() == null)
					{
						world.provider.setSkyRenderer(new KoentusSkyProvider());
					}
					if (world.provider.getCloudRenderer() == null)
					{
						world.provider.setCloudRenderer(new GCCoreCloudRenderer());
					}
				}
			}
		}

		@Override
		public void tickEnd(EnumSet<TickType> type, Object... tickData)
		{
		}

		@Override
		public String getLabel()
		{
			return "More Planet's : Koentus Client";
		}

		@Override
		public EnumSet<TickType> ticks()
		{
			return EnumSet.of(TickType.CLIENT);
		}
	}

	public void postInit(FMLPostInitializationEvent event)
	{
	}
}