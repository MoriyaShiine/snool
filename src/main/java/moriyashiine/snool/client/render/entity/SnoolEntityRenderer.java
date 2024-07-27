package moriyashiine.snool.client.render.entity;

import moriyashiine.snool.client.render.entity.model.SnoolEntityModel;
import moriyashiine.snool.common.Snool;
import moriyashiine.snool.common.entity.SnoolEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SnoolEntityRenderer extends MobEntityRenderer<SnoolEntity, SnoolEntityModel<SnoolEntity>> {
	private static final Identifier TEXTURE = Snool.id("textures/entity/living/snool.png");

	public SnoolEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new SnoolEntityModel<>(context.getPart(SnoolEntityModel.MODEL_LAYER)), 0.2F);
	}

	@Override
	public Identifier getTexture(SnoolEntity entity) {
		return TEXTURE;
	}
}
