package YUZUMod.modifier;

import YUZUMod.power.YUZUCriticalHitPower;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class YUZUExtraCriticalModifier extends AbstractDamageModifier {
    private static String ID="YUZUExtraCritical";

    public void setMulti(float multi) {
        this.multi = multi;
    }

    private float multi=0.0F;
    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        if(type== DamageInfo.DamageType.NORMAL){
            return damage*=this.multi;
        }
        return damage;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new YUZUExtraCriticalModifier();
    }

    @Override
    public boolean isInherent() {
        return true;
    }

    public static void setExtraCriticalMod(AbstractCard card){
        for(AbstractDamageModifier m: DamageModifierManager.modifiers(card)){
            if(m instanceof YUZUExtraCriticalModifier){
                ((YUZUExtraCriticalModifier) m).setMulti(YUZUCriticalHitPower.getMulti(card));
            }
        }
    }
}
