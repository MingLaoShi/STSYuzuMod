package baModDeveloper.power;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.cards.YUZUCustomCard;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class YUZUAnalysisPower extends AbstractPower {
    public static final String POWER_ID= ModHelper.makePath("AnalysisPower");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","default84");
    private static final String IMG_32=ModHelper.makeImgPath("power","default32");

    private Set<AbstractCard.CardType> masteredType;

    public YUZUAnalysisPower(AbstractCreature owner){
        this.name=NAME;
        this.ID=POWER_ID;
        this.type=TYPE;
        this.owner=owner;
        this.region128=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84),0,0,84,84);
        this.region48=new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32),0,0,32,32);
        this.amount=-1;

        this.masteredType=new HashSet<>();
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description=DESCRIPTIONS[0];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof YUZUCustomCard&&YUZUCustomCard.isMasteredWithChangeNum((YUZUCustomCard) card)){
            action.exhaustCard=true;
        }
    }

    public boolean isMastered(YUZUCustomCard card){
        return this.masteredType.contains(card.type);
    }

    public void masterCard(YUZUCustomCard card){
        this.masteredType.add(card.type);
    }
}
