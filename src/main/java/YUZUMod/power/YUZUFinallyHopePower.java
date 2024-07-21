package YUZUMod.power;

import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YUZUFinallyHopePower extends AbstractPower {
    public static final String POWER_ID= ModHelper.makePath("FinallyHope");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","FinallyHope84");
    private static final String IMG_32= ModHelper.makeImgPath("power","FinallyHope32");
    private static int IDOffset=0;
    private int energyLost,drawLose;
    private static YUZUFinallyHopePower EffectivePower=null;
    public YUZUFinallyHopePower(int energyLose,int drawLost){
        this.name=NAME;
        this.ID=POWER_ID+IDOffset;
        IDOffset++;
        this.type=TYPE;
        this.owner= AbstractDungeon.player;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=-1;
        this.energyLost=energyLose;
        this.drawLose=drawLost;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description= String.format(DESCRIPTIONS[0],this.energyLost,this.drawLose);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            if(EffectivePower==null){
                this.flash();
                AbstractDungeon.player.gameHandSize-=this.drawLose;
                addToBot(new SkipEnemiesTurnAction());
                EffectivePower=this;
            }

        }
    }

    @Override
    public void onEnergyRecharge() {
        if(EffectivePower==this){
            AbstractDungeon.player.loseEnergy(this.energyLost);
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            EffectivePower=null;
        }

    }

    @Override
    public void onRemove() {
        AbstractDungeon.player.gameHandSize+=this.drawLose;
        EffectivePower=null;
    }
}
