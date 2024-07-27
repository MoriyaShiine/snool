package moriyashiine.snool.client;

import moriyashiine.snool.client.render.entity.SnoolEntityRenderer;
import moriyashiine.snool.client.render.entity.model.SnoolEntityModel;
import moriyashiine.snool.common.Snool;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class SnoolClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(SnoolEntityModel.MODEL_LAYER, SnoolEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(Snool.SNOOL, SnoolEntityRenderer::new);
	}
}
