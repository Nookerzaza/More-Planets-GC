/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.planets.nibiru.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import stevekung.mods.moreplanets.core.blocks.base.BlockBaseMP;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNibiruLog extends BlockBaseMP
{
	public static enum NibiruLogCategory
	{
		CAT1, CAT2, CAT3, CAT4;
	}

	private static String[] types = new String[] {
		"ancient_dark",
		"orange"
	};

	private IIcon[] textures;
	private IIcon[] logHearts;

	private NibiruLogCategory category;

	public BlockNibiruLog(String name, NibiruLogCategory cat)
	{
		super(Material.wood);
		this.category = cat;
		this.setHardness(2.0F);
		this.setResistance(5.0F);
		this.setStepSound(Block.soundTypeWood);
		this.setBlockName(name);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.textures = new IIcon[BlockNibiruLog.types.length];
		this.logHearts = new IIcon[BlockNibiruLog.types.length];

		for (int i = 0; i < BlockNibiruLog.types.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("nibiru:log_" + BlockNibiruLog.types[i] + "_side");
			this.logHearts[i] = iconRegister.registerIcon("nibiru:log_" + BlockNibiruLog.types[i] + "_top");
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		int pos = meta & 12;
		if (pos == 0 && (side == 1 || side == 0) || pos == 4 && (side == 5 || side == 4) || pos == 8 && (side == 2 || side == 3))
		{
			return this.logHearts[BlockNibiruLog.getTypeFromMeta(meta) + this.category.ordinal() * 4];
		}
		else
		{
			return this.textures[BlockNibiruLog.getTypeFromMeta(meta) + this.category.ordinal() * 4];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		if (this.category != NibiruLogCategory.CAT4)
		{
			for (int i = 0; i < 2; ++i)
			{
				list.add(new ItemStack(this, 1, i));
			}
		}
		else
		{
			for (int i = 0; i < 3; ++i)
			{
				list.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		byte radius = 4;
		int bounds = radius + 1;

		if (world.checkChunksExist(x - bounds, y - bounds, z - bounds, x + bounds, y + bounds, z + bounds))
		{
			for (int i = -radius; i <= radius; ++i)
			{
				for (int j = -radius; j <= radius; ++j)
				{
					for (int k = -radius; k <= radius; ++k)
					{
						Block block = world.getBlock(x + i, y + j, z + k);

						if (block != null)
						{
							block.beginLeavesDecay(world, x + i, y + j, z + k);
						}
					}
				}
			}
		}
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
	{
		int type = BlockNibiruLog.getTypeFromMeta(meta);
		byte orientation = 0;

		switch (side)
		{
		case 0:
		case 1:
			orientation = 0;
			break;

		case 2:
		case 3:
			orientation = 8;
			break;

		case 4:
		case 5:
			orientation = 4;
			break;
		}
		return type | orientation;
	}

	@Override
	public int damageDropped(int meta)
	{
		return BlockNibiruLog.getTypeFromMeta(meta);
	}

	@Override
	protected ItemStack createStackedBlock(int meta)
	{
		return new ItemStack(this, 1, BlockNibiruLog.getTypeFromMeta(meta));
	}

	@Override
	public int getRenderType()
	{
		return 31;
	}

	@Override
	public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isWood(IBlockAccess world, int x, int y, int z)
	{
		return true;
	}

	public String getWoodType(int meta)
	{
		return BlockNibiruLog.types[BlockNibiruLog.getTypeFromMeta(meta) & 1];
	}

	private static int getTypeFromMeta(int meta)
	{
		return meta & 1;
	}
}