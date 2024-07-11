package YUZUMod.power;

import YUZUMod.helper.ModHelper;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.List;

public class YUZUProperPlanningPower extends AbstractPower {
    public static final String POWER_ID= ModHelper.makePath("ProperPlanningPower");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","default84");
    private static final String IMG_32=ModHelper.makeImgPath("power","default32");
    public YUZUProperPlanningPower(AbstractPlayer owner, int amount) {
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
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer){
            addToBot(new SelectCardsInHandAction(this.amount,"",this::filter,this::callback));
        }
    }

    private void callback(List<AbstractCard> cards) {
        for(AbstractCard c:cards){
            CardModifierManager.addModifier(c,new RetainMod());
        }
    }

    private boolean filter(AbstractCard card){
        return true;
    }


    @Override
    public void updateDescription() {
        this.description=String.format(DESCRIPTIONS[0],this.amount);

    }
}
