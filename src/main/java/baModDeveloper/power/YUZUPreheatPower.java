package baModDeveloper.power;

import baModDeveloper.helper.ModHelper;
import baModDeveloper.inter.YUZUAddCriticalMultiInterface;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YUZUPreheatPower extends AbstractPower implements YUZUAddCriticalMultiInterface {
    public static final String POWER_ID= ModHelper.makePath("PreheatPower");
    private static final AbstractPower.PowerType TYPE= AbstractPower.PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","default84");
    private static final String IMG_32=ModHelper.makeImgPath("power","default32");

    public YUZUPreheatPower(AbstractCreature owner,int amount){
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
    public int onAttacked(DamageInfo info, int damageAmount) {
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        return super.onAttacked(info, damageAmount);
    }

    @Override
    public float addMulti(float multi) {
        return multi+multi*(this.amount)/100;
    }
}
