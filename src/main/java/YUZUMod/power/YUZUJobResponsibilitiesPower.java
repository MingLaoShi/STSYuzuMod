package YUZUMod.power;

import YUZUMod.helper.ModHelper;
import YUZUMod.cards.YUZUCustomCard;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YUZUJobResponsibilitiesPower extends AbstractPower {
    public static final String POWER_ID= ModHelper.makePath("JobResponsibilitiesPower");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    private static final String IMG_84=ModHelper.makeImgPath("power","JobResponsibilitiesPower84");
    private static final String IMG_32=ModHelper.makeImgPath("power","JobResponsibilitiesPower32");

    public YUZUJobResponsibilitiesPower(AbstractCreature owner,int amount){
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
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card instanceof YUZUCustomCard &&YUZUCustomCard.isMastered((YUZUCustomCard) card)>0&&!card.purgeOnUse&&(card.isStarterStrike()||card.isStarterDefend())){
            this.flash();
            AbstractMonster target=null;
            if(action.target!=null){
                target= (AbstractMonster) action.target;
            }
            for(int i=0;i<this.amount;i++){
                AbstractCard temp=card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(temp);
                temp.current_x=card.current_x;
                temp.current_y=card.current_y;
                temp.target_x= Settings.WIDTH/2.0F-300.0F*Settings.scale;
                temp.target_y=Settings.HEIGHT/2.0F;

                if(target!=null){
                    temp.calculateCardDamage(target);
                }
                temp.purgeOnUse=true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(temp,target,card.energyOnUse,true,true),false);
            }

        }
    }
}
