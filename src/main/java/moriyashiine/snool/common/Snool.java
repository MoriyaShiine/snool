package moriyashiine.snool.common;

import moriyashiine.snool.common.entity.SnoolEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;

public class Snool implements ModInitializer {
	public static final String MOD_ID = "snool";

	public static final EntityType<SnoolEntity> SNOOL = EntityType.Builder.create(SnoolEntity::new, SpawnGroup.CREATURE).dimensions(0.8F, 0.4F).build();
	public static final Item SNOOL_SPAWN_EGG = new SpawnEggItem(SNOOL, 0xF1E6B7, 0x8354D4, new Item.Settings());
	public static final SoundEvent ENTITY_SNOOL_AMBIENT = SoundEvent.of(id("entity.snool.ambient"));
	public static final SoundEvent ENTITY_SNOOL_DEATH = SoundEvent.of(id("entity.snool.death"));

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, id("snool_spawn_egg"), SNOOL_SPAWN_EGG);
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> entries.add(SNOOL_SPAWN_EGG));

		Registry.register(Registries.ENTITY_TYPE, id("snool"), SNOOL);
		FabricDefaultAttributeRegistry.register(SNOOL, SnoolEntity.createAttributes());

		Registry.register(Registries.SOUND_EVENT, ENTITY_SNOOL_AMBIENT.getId(), ENTITY_SNOOL_AMBIENT);
		Registry.register(Registries.SOUND_EVENT, ENTITY_SNOOL_DEATH.getId(), ENTITY_SNOOL_DEATH);

		SpawnRestriction.register(SNOOL, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::isValidNaturalSpawn);
		BiomeModifications.addSpawn(BiomeSelectors.tag(ConventionalBiomeTags.IS_SWAMP), SNOOL.getSpawnGroup(), SNOOL, 8, 2, 4);
	}

	public static Identifier id(String value) {
		return Identifier.of(MOD_ID, value);
	}
}
