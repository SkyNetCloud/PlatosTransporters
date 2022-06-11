package gd.rf.acro.platos.items;

import gd.rf.acro.platos.PlatosTransporters;
import gd.rf.acro.platos.entity.BlockShipEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

public class BoardingStairsItem extends Item {
    public BoardingStairsItem(Properties settings) {
        super(settings);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        return super.use(p_41432_, p_41433_, p_41434_);
        //RayTraceResult result = user.pick(100,0,true);
        for (int i = 0; i < 30; i++) {
            p_41432_.addParticle(ParticleTypes.SMOKE,p_41433_.getX(i),p_41433_.getY(i),p_41433_.getZ(i),0,0,0);
        }
        List<BlockShipEntity> vv = p_41432_.getEntitiesWithinAABB(PlatosTransporters.BLOCK_SHIP_ENTITY_ENTITY_TYPE,new AABB(result.getHitVec().add(-10,-10,-10),result.getHitVec().add(10,10,10)), LivingEntity::isAlive);
        if(vv.size()>0)
        {
            user.startRiding(vv.get(0));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(Level world, PlayerEntity user, Hand hand) {

        RayTraceResult result = user.pick(100,0,true);
        for (int i = 0; i < 30; i++) {
            world.addParticle(ParticleTypes.SMOKE,user.getLookVec().scale(i).x,user.getLookVec().scale(i).y,user.getLookVec().scale(i).z,0,0,0);
        }
        List<BlockShipEntity> vv = world.getEntitiesWithinAABB(PlatosTransporters.BLOCK_SHIP_ENTITY_ENTITY_TYPE,new AxisAlignedBB(result.getHitVec().add(-10,-10,-10),result.getHitVec().add(10,10,10)), LivingEntity::isAlive);
        if(vv.size()>0)
        {
            user.startRiding(vv.get(0));
        }
        return super.onItemRightClick(world, user, hand);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslationTextComponent("boardingstairs.platos.tooltip"));
    }

}
