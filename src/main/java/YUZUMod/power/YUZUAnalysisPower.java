package YUZUMod.power;

import YUZUMod.helper.ModHelper;
import YUZUMod.cards.YUZUCustomCard;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.*;

public class YUZUAnalysisPower extends AbstractPower {
    public static final String POWER_ID= ModHelper.makePath("AnalysisPower");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","default84");
    private static final String IMG_32=ModHelper.makeImgPath("power","default32");

    private Map<AbstractCard.CardType,Integer> masteredType;

    public YUZUAnalysisPower(AbstractCreature owner){
        this.name=NAME;
        this.ID=POWER_ID;
        this.type=TYPE;
        this.owner=owner;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=-1;

        this.masteredType=new HashMap<>();
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description=DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof YUZUCustomCard&&YUZUCustomCard.isMastered(card)>0){
            action.exhaustCard=true;
        }
    }

    public int isMastered(AbstractCard card){
        return this.masteredType.getOrDefault(card.type, 0);
    }

    public void masterCard(AbstractCard card){
        this.masteredType.merge(card.type, 1, Integer::sum);
    }
}
