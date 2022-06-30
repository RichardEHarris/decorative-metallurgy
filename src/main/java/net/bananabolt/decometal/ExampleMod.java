package net.bananabolt.decometal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String namespace = "decometal";
	public static final Logger LOGGER = LoggerFactory.getLogger(namespace);

	/* Declare and initialize our custom block instance.
       We set our block material to `METAL`, which requires a pickaxe to efficiently break.

       `strength` sets both the hardness and the resistance of a block to the same value.
       Hardness determines how long the block takes to break, and resistance determines how strong the block is against blast damage (e.g. explosions).
       Stone has a hardness of 1.5f and a resistance of 6.0f, while Obsidian has a hardness of 50.0f and a resistance of 1200.0f.

       You can find the stats of all vanilla blocks in the class `Blocks`, where you can also reference other blocks.
    */
	//Blocks
	//public static final Block EXAMPLE_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).strength(1.0f).requiresTool());
	@Override
	public void onInitialize() {

		//Blocks
		newBlockFactory("example", Material.METAL, 1.0f, true,true, true, false, false);
		LOGGER.info("Decorative Metal Initialized.");
	}

	private void newBlockFactory(String block_name, Material material, Float block_strength, boolean requires_tool, boolean do_block, boolean do_slab, boolean do_stair, boolean do_wall) {
		Block new_block  = requires_tool ? new Block(FabricBlockSettings.of(material).strength(block_strength).requiresTool()) : new Block(FabricBlockSettings.of(material).strength(block_strength));
		if (do_block) {
			Registry.register(Registry.BLOCK, new Identifier(namespace, block_name+"_block"), new_block);
			Registry.register(Registry.ITEM, new Identifier(namespace, block_name+"_block"), new BlockItem(new_block, new FabricItemSettings().group(ItemGroup.MISC)));
		}
		if (do_slab) {
			Block new_slab = requires_tool ? new SlabBlock(FabricBlockSettings.of(material).strength(block_strength).requiresTool()) : new SlabBlock(FabricBlockSettings.of(material).strength(block_strength));
			Registry.register(Registry.BLOCK, new Identifier(namespace, block_name+"_slab"), new_slab);
			Registry.register(Registry.ITEM, new Identifier(namespace, block_name+"_slab"), new BlockItem(new_slab, new FabricItemSettings().group(ItemGroup.MISC)));
		}
		if (do_stair) {
			Block new_stair = requires_tool ? new StairsBlock(new_block.getDefaultState() ,FabricBlockSettings.of(material).strength(block_strength).requiresTool()) : new StairsBlock(new_block.getDefaultState() ,FabricBlockSettings.of(material).strength(block_strength));
			Registry.register(Registry.BLOCK, new Identifier(namespace, block_name+"_stair"), new_stair);
			Registry.register(Registry.ITEM, new Identifier(namespace, block_name+"_stair"), new BlockItem(new_stair, new FabricItemSettings().group(ItemGroup.MISC)));
		}
		/*if (do_wall) {
			Registry.register(Registry.BLOCK, new Identifier(namespace, block_name+"_wall"), block);
			Registry.register(Registry.ITEM, new Identifier(namespace, block_name+"_wall"), new BlockItem(block, new FabricItemSettings().group(ItemGroup.MISC)));
		}*/
	}

}
