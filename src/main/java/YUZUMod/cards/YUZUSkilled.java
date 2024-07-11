package YUZUMod.cards;

import YUZUMod.action.YUZUFilteredDrawCardAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUSkilled extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Skilled");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUSkilled() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    protected void upgradeMethod() {
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(!upgraded)
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    int count= (int) AbstractDungeon.player.drawPile.group.stream().filter(c->YUZUCustomCard.isMastered(c)>0).count();
                    addToTop(new YUZUFilteredDrawCardAction(count,YUZUSkilled.this::filter,true,null));
                    this.isDone=true;
                }
            });
        else
            addToBot(new YUZUFilteredDrawCardAction(BaseMod.MAX_HAND_SIZE,this::filter,true,null));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        addToBot(new GainEnergyAction(1));
    }

    private boolean filter(AbstractCard card){
        return YUZUCustomCard.isMastered(card)>0;
    }
}
