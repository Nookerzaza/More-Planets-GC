/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.fronos.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelTomato extends ModelBase
{
	public ModelRenderer body;

	public ModelTomato()
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.body = new ModelRenderer(this, 0, 0);
		this.body.addBox(0F, 0F, 0F, 16, 14, 14, 0F);
		this.body.setRotationPoint(-8F, 10F, -7F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);

		if (this.isChild)
		{
			GlStateManager.pushMatrix();
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * par7, 0.0F);
			this.body.render(par7);
			GlStateManager.popMatrix();
		}
		else
		{
			this.body.render(par7);
		}
	}
}