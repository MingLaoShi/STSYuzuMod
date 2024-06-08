package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.action.ExhaustTopCardAction;
import baModDeveloper.character.YuzuCharacter;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUExtremeMeasures extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ExtremeMeasures");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUExtremeMeasures() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=12;
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        upgradeBaseCost(0);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            {
                this.amount=YUZUExtremeMeasures.this.magicNumber;
            }
            @Override
            public void update() {
                int handAmount= AbstractDungeon.player.hand.size();
                int drawAmount=BaseMod.MAX_HAND_SIZE-handAmount;
                if(this.amount>handAmount){
                    addToTop(new ExhaustTopCardAction(this.amount,false));
                }
                addToTop(new DrawCardAction(BaseMod.MAX_HAND_SIZE-handAmount));
                this.isDone=true;
            }
        });
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
    }
}
