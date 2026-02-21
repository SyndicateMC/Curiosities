package com.syndicatemc.curiosities.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
//credit to the Oreganized mod for the system of implementing the custom armor model
//oreganized/client/model/ElectrumArmorModel
public class InvarArmorModel<T extends LivingEntity> extends HumanoidArmorModel {
    private final EquipmentSlot slot;
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Waist;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;
    private final ModelPart RightBoot;
    private final ModelPart LeftBoot;

    public InvarArmorModel(ModelPart root, EquipmentSlot slot) {
        super(root);
        this.slot = slot;
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.Waist = root.getChild("Waist");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
        this.RightBoot = root.getChild("RightBoot");
        this.LeftBoot = root.getChild("LeftBoot");
    }

    public static MeshDefinition createBodyLayer() {
        MeshDefinition meshDefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition Head = partDefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F))
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition Body = partDefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightArm = partDefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F))
                .texOffs(40, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.35F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

        RightArm.addOrReplaceChild("RightPauldron", CubeListBuilder.create().texOffs(0, 32).addBox(-5.0F, -4.0F, -3.5F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.3F)), PartPose.rotation(0.0F, 0.0F, (float) -(15 * Math.PI/180)));

        PartDefinition LeftArm = partDefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 32).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(5.0F, 2.0F, 0.0F));

        PartDefinition Waist = partDefinition.addOrReplaceChild("Waist", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition RightLeg = partDefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition LeftLeg = partDefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 48).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        PartDefinition RightBoot = partDefinition.addOrReplaceChild("RightBoot", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

        PartDefinition LeftBoot = partDefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(1.9F, 12.0F, 0.0F));

        return meshDefinition;
    }

    public static LayerDefinition createLayerDefinition() {
        return LayerDefinition.create(createBodyLayer(), 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        if (slot == EquipmentSlot.HEAD) {
            poseStack.pushPose();
            this.Head.copyFrom(this.head);
            if (this.young) {
                poseStack.scale(0.75F, 0.75F, 0.75F);
                this.Head.setPos(0.0F, 15.0F, 0.0F);
            }
            this.Head.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
        if (slot == EquipmentSlot.CHEST) {
            poseStack.pushPose();
            this.Body.copyFrom(this.body);
            this.RightArm.copyFrom(this.rightArm);
            this.LeftArm.copyFrom(this.leftArm);
            if (this.young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.Body.setPos(0.0F, 24.0F, 0.0F);
                this.RightArm.setPos(-5.0F, 24.0F, 0.0F);
                this.LeftArm.setPos(5.0F, 24.0F, 0.0F);
            }
            this.RightArm.render(poseStack, buffer, packedLight, packedOverlay);
            this.LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
            this.Body.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
        if (slot == EquipmentSlot.LEGS) {
            poseStack.pushPose();
            this.Waist.copyFrom(this.body);
            this.RightLeg.copyFrom(this.rightLeg);
            this.LeftLeg.copyFrom(this.leftLeg);
            if (this.young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.Waist.setPos(0.0F, 24.0F, 0.0F);
                this.LeftLeg.setPos(2.0F, 36.0F, 0.0F);
                this.RightLeg.setPos(-2.0F, 36.0F, 0.0F);
            }
            this.RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
            this.LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
        if (slot == EquipmentSlot.FEET) {
            poseStack.pushPose();
            this.RightBoot.copyFrom(this.rightLeg);
            this.LeftBoot.copyFrom(this.leftLeg);
            if (this.young) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
                this.LeftBoot.setPos(2.0F, 37.0F, 0.0F);
                this.RightBoot.setPos(-2.0F, 37.0F, 0.0F);
            }
            this.RightBoot.render(poseStack, buffer, packedLight, packedOverlay);
            this.LeftBoot.render(poseStack, buffer, packedLight, packedOverlay);
            poseStack.popPose();
        }
    }
}
