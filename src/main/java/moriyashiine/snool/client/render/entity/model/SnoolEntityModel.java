package moriyashiine.snool.client.render.entity.model;

import moriyashiine.snool.common.Snool;
import moriyashiine.snool.common.entity.SnoolEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.math.MathHelper;

import java.util.Set;

public class SnoolEntityModel<T extends SnoolEntity> extends AnimalModel<T> {
	public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Snool.id("snool"), "main");

	private final ModelPart body;
	private final ModelPart fin1;
	private final ModelPart fin2;
	private final ModelPart backfin;

	public SnoolEntityModel(ModelPart root) {
		body = root.getChild("body");
		fin1 = root.getChild("fin1");
		fin2 = root.getChild("fin2");
		backfin = root.getChild("backfin");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, 21, -6.0F, 10.0F, 3.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData fin1 = modelPartData.addChild("fin1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24, 0.0F));
		fin1.addChild("cube_r1", ModelPartBuilder.create().uv(15, 18).cuboid(-2.0F, -0.1F, -1.5F, 3.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-5.0F, -2.0F, -2.5F, 0.0F, -0.6545F, 0.0F));
		ModelPartData fin2 = modelPartData.addChild("fin2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24, 0.0F));
		fin2.addChild("cube_r2", ModelPartBuilder.create().uv(15, 13).cuboid(-1.0F, -0.1F, -1.5F, 3.0F, 0.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -2.0F, -2.5F, 0.0F, 0.6545F, 0.0F));
		ModelPartData backfin = modelPartData.addChild("backfin", ModelPartBuilder.create().uv(0, 13).cuboid(-3.0F, 22, 4.0F, 6.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		backfin.addChild("cube_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, 22, -1.0F, 4.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 9.0F, 0.0F, 1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return Set.of();
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return Set.of(body, fin1, fin2, backfin);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.fin1.yaw = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
		this.fin2.yaw = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
		this.backfin.yaw = MathHelper.cos(limbAngle * 0.6662F + (float) Math.PI) * 1.4F * limbDistance;
	}
}
