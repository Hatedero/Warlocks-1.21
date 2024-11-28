package net.hatedero.warlocksmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.hatedero.warlocksmod.WarlocksMod;
import net.hatedero.warlocksmod.entity.custom.BlackHoleEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BlackHoleRenderer extends EntityRenderer<BlackHoleEntity>{

//    @OnlyIn(Dist.CLIENT)
        private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(WarlocksMod.MOD_ID, "textures/entity/black_hole.png");
        private static final RenderType RENDER_TYPE;

    public BlackHoleRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BlackHoleEntity blackHoleEntity) {
        return TEXTURE_LOCATION;
    }

    protected int getBlockLightLevel(BlackHoleEntity entity, BlockPos pos) {
            return 15;
        }

        public void render(BlackHoleEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
            poseStack.pushPose();
            poseStack.scale(1.0F, 1.0F, 1.0F);
            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
            PoseStack.Pose posestack$pose = poseStack.last();
            VertexConsumer vertexconsumer = buffer.getBuffer(RENDER_TYPE);
            vertex(vertexconsumer, posestack$pose, packedLight, 0.0F, 0, 0, 1);
            vertex(vertexconsumer, posestack$pose, packedLight, 1.0F, 0, 1, 1);
            vertex(vertexconsumer, posestack$pose, packedLight, 1.0F, 1, 1, 0);
            vertex(vertexconsumer, posestack$pose, packedLight, 0.0F, 1, 0, 0);
            poseStack.popPose();
            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }

        private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, int packedLight, float x, int y, int u, int v) {
            consumer.addVertex(pose, x-0.5F, (float)y-0.25F, 0.0F).setColor(-1).setUv((float)u, (float)v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pose, 0.0F, 1.0F, 0.0F);
        }

        static {
            RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);
        }

}
