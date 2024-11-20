//package net.hatedero.warlocksmod.block.entity.renderer;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.hatedero.warlocksmod.block.entity.AbyssReactorBlockEntity;
//import net.hatedero.warlocksmod.block.entity.client.ModModelLayers;
//import net.minecraft.client.Camera;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.*;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.texture.TextureAtlas;
//import net.minecraft.client.resources.model.Material;
//import net.minecraft.resources.ResourceLocation;
//import org.joml.Quaternionf;
//import org.joml.Vector3f;
//
//public class AbyssReactorBlockEntityRenderer implements BlockEntityRenderer<AbyssReactorBlockEntity> {
//    public static final Material SHELL_TEXTURE;
//    public static final Material ACTIVE_SHELL_TEXTURE;
//    public static final Material WIND_TEXTURE;
//    public static final Material VERTICAL_WIND_TEXTURE;
//    public static final Material OPEN_EYE_TEXTURE;
//    public static final Material CLOSED_EYE_TEXTURE;
//    private final ModelPart eye;
//    private final ModelPart wind;
//    private final ModelPart shell;
//    private final ModelPart cage;
//    private final BlockEntityRenderDispatcher renderer;
//
//    public AbyssReactorBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
//        this.renderer = pContext.getBlockEntityRenderDispatcher();
//        this.eye = pContext.bakeLayer(ModModelLayers.ABYSS_REACTOR_EYE);
//        this.wind = pContext.bakeLayer(ModModelLayers.ABYSS_REACTOR_WIND);
//        this.shell = pContext.bakeLayer(ModModelLayers.ABYSS_REACTOR_SHELL);
//        this.cage = pContext.bakeLayer(ModModelLayers.ABYSS_REACTOR_CAGE);
//    }
//
//    public static LayerDefinition createEyeLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//        partdefinition.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.ZERO);
//        return LayerDefinition.create(meshdefinition, 16, 16);
//    }
//
//    public static LayerDefinition createWindLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//        partdefinition.addOrReplaceChild("wind", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F), PartPose.ZERO);
//        return LayerDefinition.create(meshdefinition, 64, 32);
//    }
//
//    public static LayerDefinition createShellLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//        partdefinition.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.ZERO);
//        return LayerDefinition.create(meshdefinition, 32, 16);
//    }
//
//    public static LayerDefinition createCageLayer() {
//        MeshDefinition meshdefinition = new MeshDefinition();
//        PartDefinition partdefinition = meshdefinition.getRoot();
//        partdefinition.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
//        return LayerDefinition.create(meshdefinition, 32, 16);
//    }
//
//
//    public void render(AbyssReactorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
//        float f = (float)pBlockEntity.tickCount + pPartialTick;
//        float f5;
//        if (!pBlockEntity.isActive()) {
//            f5 = pBlockEntity.getActiveRotation(0.0F);
//            VertexConsumer vertexconsumer1 = SHELL_TEXTURE.buffer(pBuffer, RenderType::m_110446_);
//            pPoseStack.m_85836_();
//            pPoseStack.scale(0.5F, 0.5F, 0.5F);
//            pPoseStack.mulPose((new Quaternionf()).rotationY(f5 * 0.017453292F));
//            this.shell.render(pPoseStack, vertexconsumer1, pPackedLight, pPackedOverlay);
//            pPoseStack.m_85849_();
//        } else {
//            f5 = pBlockEntity.getActiveRotation(pPartialTick) * 57.295776F;
//            float f2 = Mth.m_14031_(f * 0.1F) / 2.0F + 0.5F;
//            f2 += f2 * f2;
//            pPoseStack.m_85836_();
//            pPoseStack.scale(0.5F, 0.3F + f2 * 0.2F, 0.5F);
//            Vector3f vector3f = (new Vector3f(0.5F, 1.0F, 0.5F)).normalize();
//            pPoseStack.mulPose((new Quaternionf()).rotationAxis(f5 * 0.017453292F, vector3f));
//            this.cage.render(pPoseStack, ACTIVE_SHELL_TEXTURE.buffer(pBuffer, RenderType::m_110458_), pPackedLight, pPackedOverlay);
//            pPoseStack.m_85849_();
//            int i = pBlockEntity.tickCount / 66 % 3;
//            pPoseStack.m_85836_();
//            pPoseStack.scale(0.5F, 0.5F, 0.5F);
//            if (i == 1) {
//                pPoseStack.mulPose((new Quaternionf()).rotationX(1.5707964F));
//            } else if (i == 2) {
//                pPoseStack.mulPose((new Quaternionf()).rotationZ(1.5707964F));
//            }
//
//            VertexConsumer vertexconsumer = (i == 1 ? VERTICAL_WIND_TEXTURE : WIND_TEXTURE).buffer(pBuffer, RenderType::m_110458_);
//            this.wind.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
//            pPoseStack.m_85849_();
//            pPoseStack.m_85836_();
//            pPoseStack.scale(0.5F, 0.5F, 0.5F);
//            pPoseStack.translate(0.875F, 0.875F, 0.875F);
//            pPoseStack.mulPose((new Quaternionf()).rotationXYZ(3.1415927F, 0.0F, 3.1415927F));
//            this.wind.render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
//            pPoseStack.m_85849_();
//            Camera camera = this.renderer.camera;
//            pPoseStack.m_85836_();
//            pPoseStack.scale(0.5F, 0.3F + f2 * 0.2F, 0.5F);
//            pPoseStack.translate(0.5F, 0.5F, 0.5F);
//            float f3 = -camera.m_90590_();
//            pPoseStack.mulPose((new Quaternionf()).rotationYXZ(f3 * 0.017453292F, camera.getXRot() * 0.017453292F, 3.1415927F));
//            float f4 = 1.3333334F;
//            pPoseStack.translate(1.3333334F, 1.3333334F, 1.3333334F);
//            this.eye.render(pPoseStack, (pBlockEntity.isHunting() ? OPEN_EYE_TEXTURE : CLOSED_EYE_TEXTURE).buffer(pBuffer, RenderType::m_110458_), pPackedLight, pPackedOverlay);
//            pPoseStack.m_85849_();
//        }
//
//    }
//
//    static {
//        SHELL_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath("warlocksmod", "entity/conduit/flight_base"));
//        ACTIVE_SHELL_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath("warlocksmod", "entity/conduit/flight_cage"));
//        WIND_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath("warlocksmod", "entity/conduit/flight_wind"));
//        VERTICAL_WIND_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath("warlocksmod", "entity/conduit/flight_wind_vertical"));
//        OPEN_EYE_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath("warlocksmod", "entity/conduit/flight_open_eye"));
//        CLOSED_EYE_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, ResourceLocation.fromNamespaceAndPath("warlocksmod", "entity/conduit/flight_closed_eye"));
//    }
//}
