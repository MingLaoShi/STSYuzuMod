package baModDeveloper.power;

import baModDeveloper.cards.YUZUCustomCard;
import baModDeveloper.cards.YUZUEmergencyEnergy;
import baModDeveloper.helper.ModHelper;
import baModDeveloper.inter.YUZUAddCriticalMultiInterface;
import baModDeveloper.inter.YUZUMultiCriticalMultiInterface;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YUZUCriticalHitPower extends AbstractPower{
    public static final String POWER_ID= ModHelper.makePath("CriticalHit");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","default84");
    private static final String IMG_32=ModHelper.makeImgPath("power","default32");

    public static float Multiplier=2.0F;

    public YUZUCriticalHitPower(AbstractCreature owner,int amount){
        this.name=NAME;
        this.ID=POWER_ID;
        this.type=TYPE;
        this.owner=owner;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=amount;
        this.priority=6;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description=String.format(DESCRIPTIONS[0],this.amount,(int)(getMulti(null)*100));
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        this.updateDescription();
    }

    //    @Override
//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        if(type== DamageInfo.DamageType.NORMAL){
//            if(AbstractDungeon.player.hasPower(YUZUPreheatPower.POWER_ID)){
//                return damage*=(Multiplier+AbstractDungeon.player.getPower(YUZUPreheatPower.POWER_ID).amount/100.0F);
//            }
//            return damage*=Multiplier;
//        }
//        return damage;
//    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if(type== DamageInfo.DamageType.NORMAL&&!card.hasTag(YUZUCustomCard.YUZUCardTag.NoNeedCriticalHit)){

            return damage*=getMulti(card);
        }
        return damage;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type== AbstractCard.CardType.ATTACK&&!card.hasTag(YUZUCustomCard.YUZUCardTag.NoNeedCriticalHit)){
            if(card instanceof YUZUCustomCard){
                ((YUZUCustomCard) card).triggerOnCriticalHit(action.target);
            }

            for(AbstractCard c:AbstractDungeon.player.drawPile.group){
                if(c instanceof YUZUEmergencyEnergy){
                    ((YUZUEmergencyEnergy) c).trigger();
                }
            }
            addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
        }
    }


    public static float getMulti(AbstractCard card) {
        float multi=YUZUCriticalHitPower.Multiplier;
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p instanceof YUZUAddCriticalMultiInterface){
                multi=((YUZUAddCriticalMultiInterface) p).addMulti(multi);
            }
        }
        if(card instanceof YUZUAddCriticalMultiInterface){
            multi=((YUZUAddCriticalMultiInterface) card).addMulti(multi);
        }
        for(AbstractPower p: AbstractDungeon.player.powers){
            if(p instanceof YUZUMultiCriticalMultiInterface){
                multi=((YUZUMultiCriticalMultiInterface) p).multiMulti(multi);
            }
        }
        if(card instanceof YUZUMultiCriticalMultiInterface){
            multi=((YUZUMultiCriticalMultiInterface) card).multiMulti(multi);
        }
        return multi;
    }
}
