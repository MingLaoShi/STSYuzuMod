package YUZUMod.power;

import YUZUMod.effect.YUZUDispersedStarEffect;
import YUZUMod.effect.YUZUGlowBlessingEffect;
import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YUZUGlowBlessingPower extends AbstractPower implements OnReceivePowerPower {
    public static final String POWER_ID= ModHelper.makePath("GlowBlessingPower");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","GlowBlessingPower84");
    private static final String IMG_32= ModHelper.makeImgPath("power","GlowBlessingPower32");

    private static Texture star= TextureLoader.getTexture(ModHelper.makeImgPath("effect","star"));

    private float fixedEffectDuration=1.5F;
    private Color color=Color.WHITE.cpy();
    public YUZUGlowBlessingPower(AbstractCreature owner, int amount) {
        this.name=NAME;
        this.ID=POWER_ID;
        this.type=TYPE;
        this.owner=owner;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=amount;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description=String.format(DESCRIPTIONS[0],this.amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        addEffect(10);
        return 0;
    }
    @Override
    public void atStartOfTurn() {
        addToBot(new ReducePowerAction(this.owner,this.owner,this,1));
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        addEffect(10);
        return abstractPower.type != PowerType.DEBUFF;
    }

    @Override
    public void update(int slot) {
        super.update(slot);


        this.fixedEffectDuration-= Gdx.graphics.getDeltaTime();
        if(this.fixedEffectDuration<0.0F){
            float random_x=this.owner.hb.x+ MathUtils.random(0,this.owner.hb_w);
            float random_y=this.owner.hb.y+MathUtils.random(0,this.owner.hb_h);
            AbstractDungeon.topLevelEffectsQueue.add(new YUZUGlowBlessingEffect(random_x,random_y));
            this.fixedEffectDuration=0.5F;

        }
    }


    private void addEffect(int count){
        for(int i=0;i<count;i++){
            float offset=30.0F* Settings.scale;
            float random_x=this.owner.hb.cX+ MathUtils.random(-offset,offset);
            float random_y=this.owner.hb.cY+MathUtils.random(-offset,offset);

            AbstractDungeon.topLevelEffectsQueue.add(new YUZUDispersedStarEffect(random_x,random_y,this.owner.hb.cX,this.owner.hb.cY));
        }
    }

}
