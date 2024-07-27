package YUZUMod.cards;

import YUZUMod.action.YUZUClearCriticalRateAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUSpan extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Span");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUSpan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeBaseCost(0);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(AbstractDungeon.player instanceof YuzuCharacter){
                    this.amount=((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().getAmount();
                    int drawAmount=this.amount/2;
                    if(drawAmount>0){
                        addToTop(new DrawCardAction(drawAmount));
                    }
                    if(drawAmount!=this.amount){
                        addToTop(new GainEnergyAction(this.amount-drawAmount));
                    }
                    addToTop(new YUZUClearCriticalRateAction());
                }
                this.isDone=true;
            }
        });
    }
}
