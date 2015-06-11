/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.nibiru.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import stevekung.mods.moreplanets.nibiru.core.ModuleNibiru;
import stevekung.mods.moreplanets.nibiru.worldgen.tree.NibiruWorldGenAppleTree;
import stevekung.mods.moreplanets.nibiru.worldgen.tree.NibiruWorldGenOrangeTree;

public class NibiruBlockSapling extends BlockSapling
{
	private static final String[] saplings = new String[] {"apple", "orange"};
	private Icon[] textures;
	private static final int TYPES = 15;

	public NibiruBlockSapling(int par1)
	{
		super(par1);
		this.setHardness(0.0F);
		this.setStepSound(Block.soundGrassFootstep);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		this.textures = new Icon[saplings.length];

		for (int i = 0; i < saplings.length; ++i)
		{
			this.textures[i] = iconRegister.registerIcon("nibiru:sapling_" + saplings[i]);
		}
	}

	@Override
	public CreativeTabs getCreativeTabToDisplayOn()
	{
		return ModuleNibiru.nibiruTab;
	}

	@Override
	public Icon getIcon(int side, int meta)
	{
		if (meta < 0 || meta >= saplings.length)
		{
			meta = 0;
		}

		return this.textures[meta];
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < saplings.length; ++i)
		{
			list.add(new ItemStack(blockID, 1, i));
		}
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemStack)
	{
		int id = world.getBlockId(x, y - 1, z);
		int meta = itemStack.getItemDamage();

		if (itemStack.itemID == this.blockID && id != 0)
		{
			switch (meta)
			{
			default:
				return id == NibiruBlocks.infectedGrass.blockID || id == NibiruBlocks.infectedDirt.blockID || blocksList[id].canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
			}
		}
		else
		{
			return this.canPlaceBlockOnSide(world, x, y, z, side);
		}
	}

	@Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
	{
		Block soil = blocksList[par1World.getBlockId(par2, par3 - 1, par4)];

		if (par1World.getBlockMetadata(par2, par3, par4) != 7)
		{
			return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
		}
		else
		{
			return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || par1World.canBlockSeeTheSky(par2, par3, par4)) && soil != null && soil.canSustainPlant(par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (world.isRemote)
		{
			return;
		}

		this.checkFlowerChange(world, x, y, z);

		if (world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(7) == 0)
		{
			this.growTree(world, x, y, z, random);
		}
	}

	@Override
	public void growTree(World world, int x, int y, int z, Random random)
	{
		int meta = world.getBlockMetadata(x, y, z) & TYPES;
		Object obj = null;
		random.nextInt(8);

		if (obj == null)
		{
			switch (meta)
			{
			case 0:
				obj = new NibiruWorldGenAppleTree();
				break;
			case 1:
				obj = new NibiruWorldGenOrangeTree();
				break;
			}
		}

		if (obj != null)
		{
			world.setBlockToAir(x, y, z);

			if (!((WorldGenerator)obj).generate(world, random, x, y, z))
			{
				world.setBlock(x, y, z, this.blockID, meta, 2);
			}
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta & TYPES;
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) & TYPES;
	}
}