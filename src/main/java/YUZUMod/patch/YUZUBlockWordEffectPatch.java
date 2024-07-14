package YUZUMod.patch;

import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.combat.DamageNumberEffect;

public class YUZUBlockWordEffectPatch {
    private static Texture texture= ImageMaster.loadImage(ModHelper.makeImgPath("UI","CriticalHit_bg"));
    public static boolean isCriticalHit=false;
    @SpirePatch(clz = DamageNumberEffect.class,method = SpirePatch.CLASS)
    public static class FieldPatch{
        public static SpireField<Boolean> isCriticalHit=new SpireField<>(()->false);
    }
    @SpirePatch(clz = DamageNumberEffect.class,method = SpirePatch.CONSTRUCTOR)
    public static class constructorPatch{
        @SpirePostfixPatch
        public static void postFixPatch(DamageNumberEffect _instance, AbstractCreature target,float x, float y, int amt){
            FieldPatch.isCriticalHit.set(_instance,isCriticalHit);
        }
    }
    @SpirePatch(clz = DamageNumberEffect.class,method = "render")
    public static class renderPatch{
        @SpirePrefixPatch
        public static void preFixPatch(DamageNumberEffect __instance, SpriteBatch sb, float ___x, float ___y, Color ___color){
            if(FieldPatch.isCriticalHit.get(__instance)){
                sb.setColor(___color);
                sb.draw(texture,___x-texture.getWidth()/2.0F,___y-texture.getHeight()/2.0F);
            }
        }
    }
}
