/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.core.blocks;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import stevekung.mods.moreplanets.core.MorePlanetsCore;

public class BlockStairsMP extends BlockStairs
{
	private StairsCategory category;

	public static enum StairsCategory
	{
		ancient_dark_wood ("wood"),
		ORANGE_WOOD ("wood"),
		COCONUT_WOOD ("wood"),
		RED_MAPLE_WOOD ("wood"),
		CRYSTAL_WOOD ("wood"),

		DIONA_COBBLESTONE ("stone"),
		DIONA_BRICK ("stone"),
		CHISELED_QUONTONIUM ("stone"),
		QUONTONIUM_BRICK ("stone"),
		POLONGNIUS_COBBLESTONE ("stone"),
		POLONGNIUS_BRICK ("stone"),
		NIBIRU_COBBLESTONE ("stone"),
		NIBIRU_BRICK ("stone"),
		KOENTUS_COBBLESTONE ("stone"),
		KOENTUS_BRICK ("stone"),
		KOENTUS_ANCIENT_STONE ("stone"),
		KOENTUS_ANCIENT_BRICK ("stone"),
		FRONOS_COBBLESTONE ("stone"),
		FRONOS_STONE_BRICK ("stone"),
		FRONOS_CRACK_BRICK ("stone"),
		FRONOS_BRICK ("stone"),
		KAPTEYNB_COBBLESTONE ("stone"),
		KAPTEYNB_BRICK ("stone"),
		SIRIUS_COBBLESTONE ("stone"),
		SIRIUS_BRICK ("stone"),
		mercury_cobblestone ("stone"),
		mercury_brick ("stone");

		private List<String> values;
		private String type;

		private StairsCategory(String type)
		{
			this.type = type;
			this.values = Arrays.asList(type);
		}
	}

	public BlockStairsMP(String name, float hardness, StairsCategory cat, Block material)
	{
		super(material, 0);
		this.category = cat;
		this.setBlockName(name);
		this.setHardness(hardness);

		if (this.isWoodCategory(this.category.toString()))
		{
			this.setStepSound(Block.soundTypeWood);
		}
		if (this.category == StairsCategory.SIRIUS_COBBLESTONE || this.category == StairsCategory.SIRIUS_BRICK)
		{
			this.setLightLevel(1.0F);
		}
		this.useNeighborBrightness = true;
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return MorePlanetsCore.mpBlocksTab;
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		if (this.category == StairsCategory.QUONTONIUM_BRICK)//Quontonium Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:quontonium_brick");
		}
		else if (this.category == StairsCategory.DIONA_COBBLESTONE)//Diona Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:diona_cobblestone");
		}
		else if (this.category == StairsCategory.KOENTUS_COBBLESTONE)//Koentus Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("koentus:koentus_cobblestone");
		}
		else if (this.category == StairsCategory.NIBIRU_COBBLESTONE)//Nibiru Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:nibiru_cobblestone");
		}
		else if (this.category == StairsCategory.ORANGE_WOOD)//Orange Wood Planks
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:orange_wood_planks");
		}
		else if (this.category == StairsCategory.POLONGNIUS_COBBLESTONE)//Polongnius Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("polongnius:polongnius_cobblestone");
		}
		else if (this.category == StairsCategory.CHISELED_QUONTONIUM)//Chiseled Quontonium
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:chiseled_quontonium");
		}
		else if (this.category == StairsCategory.ancient_dark_wood)//Ancient Dark Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:ancient_dark_wood_planks");
		}
		else if (this.category == StairsCategory.COCONUT_WOOD)//Coconut Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:coconut_wood_planks");
		}
		else if (this.category == StairsCategory.FRONOS_COBBLESTONE)//Fronos Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:fronos_cobblestone");
		}
		else if (this.category == StairsCategory.KAPTEYNB_COBBLESTONE)//Kapteyn B Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("kapteynb:kapteyn_b_cobblestone");
		}
		else if (this.category == StairsCategory.RED_MAPLE_WOOD)//Red Maple Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:maple_wood_planks");
		}
		else if (this.category == StairsCategory.DIONA_BRICK)//Diona Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("diona:diona_dungeon_brick");
		}
		else if (this.category == StairsCategory.POLONGNIUS_BRICK)//Polongnius Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("polongnius:polongnius_dungeon_brick");
		}
		else if (this.category == StairsCategory.NIBIRU_BRICK)//Nibiru Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("nibiru:nibiru_dungeon_brick");
		}
		else if (this.category == StairsCategory.KOENTUS_BRICK)//Koentus Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("koentus:koentus_dungeon_brick");
		}
		else if (this.category == StairsCategory.KOENTUS_ANCIENT_STONE)//Koentus Ancient Stone
		{
			this.blockIcon = par1IconRegister.registerIcon("koentus:koentus_ancient_stone");
		}
		else if (this.category == StairsCategory.KOENTUS_ANCIENT_BRICK)//Koentus Ancient Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("koentus:ancient_koentus_brick");
		}
		else if (this.category == StairsCategory.FRONOS_BRICK)//Fronos Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:fronos_dungeon_brick");
		}
		else if (this.category == StairsCategory.FRONOS_STONE_BRICK)//Fronos Stone Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:fronos_stone_brick");
		}
		else if (this.category == StairsCategory.FRONOS_CRACK_BRICK)//Fronos Cracked Stone Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("fronos:cracked_fronos_stone_brick");
		}
		else if (this.category == StairsCategory.KAPTEYNB_BRICK)//Kapteyn B Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("kapteynb:kapteyn_b_dungeon_brick");
		}
		else if (this.category == StairsCategory.SIRIUS_COBBLESTONE)//Sirius B Cobblestone
		{
			this.blockIcon = par1IconRegister.registerIcon("siriusb:sirius_b_carbon_cobblestone");
		}
		else if (this.category == StairsCategory.SIRIUS_BRICK)//Sirius B Dungeon Brick
		{
			this.blockIcon = par1IconRegister.registerIcon("siriusb:sirius_b_dungeon_brick");
		}
		else if (this.category == StairsCategory.CRYSTAL_WOOD)//Crystal Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("koentus:crystal_planks");
		}
		else if (this.category == StairsCategory.mercury_cobblestone)//Crystal Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("mercury:mercury_cobblestone");
		}
		else if (this.category == StairsCategory.mercury_brick)//Crystal Wood
		{
			this.blockIcon = par1IconRegister.registerIcon("mercury:mercury_dungeon_brick");
		}
	}

	public boolean isWoodCategory(String block)
	{
		String type = StairsCategory.valueOf(block).type;

		if (type == "wood")
		{
			return true;
		}
		return false;
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}
}