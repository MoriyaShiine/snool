package moriyashiine.snool.common.entity;

import moriyashiine.snool.common.Snool;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SnoolEntity extends AnimalEntity {
	public SnoolEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createAttributes() {
		return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 128).add(EntityAttributes.GENERIC_MAX_HEALTH, 0.1).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.isOf(Items.GLOW_BERRIES);
	}

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return new SnoolEntity(Snool.SNOOL, world);
	}

	@Override
	protected void initGoals() {
		goalSelector.add(0, new EscapeDangerGoal(this, 1.25));
		goalSelector.add(1, new AnimalMateGoal(this, 1));
		goalSelector.add(2, new TemptGoal(this, 1.2, stack -> true, false));
		goalSelector.add(3, new FollowParentGoal(this, 1.1));
		goalSelector.add(4, new WanderAroundFarGoal(this, 1));
		goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 6));
		goalSelector.add(6, new LookAroundGoal(this));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return Snool.ENTITY_SNOOL_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return Snool.ENTITY_SNOOL_DEATH;
	}

	@Override
	public boolean damage(DamageSource source, float amount) {
		return super.damage(source, Integer.MAX_VALUE);
	}

	@Override
	public void onPlayerCollision(PlayerEntity player) {
		if (!player.isCreative()) {
			kill();
			if (player instanceof ServerPlayerEntity serverPlayer) {
				serverPlayer.networkHandler.sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.GAME_WON, GameStateChangeS2CPacket.DEMO_OPEN_SCREEN));
			}
		}
	}
}
