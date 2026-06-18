package org.valkyrienskies.vs_template;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.valkyrienskies.mod.api.ValkyrienSkies;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;

import java.util.function.Supplier;

@Mod(VSTemplateMod.MOD_ID)
public class VSTemplateMod {
    public static final String MOD_ID = "vs_template";

    //Deferred Registries
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

    // Put RegistryObjects here:

    // end of RegistryObjects

    public VSTemplateMod(final FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ValkyrienSkiesMod.getApi().getPhysTickEvent().on((e) -> {

        });
    }

    // Helper function, taken from VS2.
    private static RegistryObject<Block> registerBlockAndItem(String registryName, Supplier<Block> blockSupplier) {
        RegistryObject<Block> blockRegistry = BLOCKS.register(registryName, blockSupplier);
        ITEMS.register(registryName, () -> new BlockItem(blockRegistry.get(), new Item.Properties()));
        return blockRegistry;
    }
}
