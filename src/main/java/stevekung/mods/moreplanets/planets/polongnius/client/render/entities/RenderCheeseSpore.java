/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.polongnius.client.render.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import stevekung.mods.moreplanets.planets.polongnius.entities.projectiles.EntityCheeseSpore;
import stevekung.mods.moreplanets.planets.polongnius.items.PolongniusItems;

@SideOnly(Side.CLIENT)
public class RenderCheeseSpore extends Render
{
	public RenderCheeseSpore(RenderManager render)
	{
		super(render);
	}

	public void doRender(EntityCheeseSpore entity, double x, double y, double z, float par5, float partialTicks)
	{
		GlStateManager.pushMatrix();
		this.bindEntityTexture(entity);
		GlStateManager.translate((float)x, (float)y, (float)z);
		GlStateManager.enableRescaleNormal();
		float f2 = 2.0F;
		GlStateManager.scale(f2 / 1.0F, f2 / 1.0F, f2 / 1.0F);
		TextureAtlasSprite textureatlassprite = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(PolongniusItems.polongnius_item, 11);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		float f3 = textureatlassprite.getMinU();
		float f4 = textureatlassprite.getMaxU();
		float f5 = textureatlassprite.getMinV();
		float f6 = textureatlassprite.getMaxV();
		float f7 = 1.0F;
		float f8 = 0.5F;
		float f9 = 0.25F;
		GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		worldrenderer.startDrawingQuads();
		worldrenderer.setNormal(0.0F, 1.0F, 0.0F);
		worldrenderer.addVertexWithUV(0.0F - f8, 0.0F - f9, 0.0D, f3, f6);
		worldrenderer.addVertexWithUV(f7 - f8, 0.0F - f9, 0.0D, f4, f6);
		worldrenderer.addVertexWithUV(f7 - f8, 1.0F - f9, 0.0D, f4, f5);
		worldrenderer.addVertexWithUV(0.0F - f8, 1.0F - f9, 0.0D, f3, f5);
		tessellator.draw();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, par5, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return TextureMap.locationBlocksTexture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float par5, float partialTicks)
	{
		this.doRender((EntityCheeseSpore)entity, x, y, z, par5, partialTicks);
	}
}