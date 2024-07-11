package YUZUMod.cards;

import YUZUMod.action.YUZUApplyCriticalRateAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUFireWarning extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("FireWarning");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUFireWarning() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=15;
    }

    @Override
    protected void upgradeMethod() {
        upgradeMagicNumber(10);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            boolean upgrade=YUZUFireWarning.this.upgraded;
            @Override
            public void update() {
                if(AbstractDungeon.player instanceof YuzuCharacter){
                    int c=((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().getAmount();
                    int max=((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().getMAX();
                    if(upgrade){
                        addToTop(new YUZUApplyCriticalRateAction(2*max-c));
                    }else{
                        addToTop(new YUZUApplyCriticalRateAction(max-c));
                    }

                }
                this.isDone=true;
            }
        });
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUApplyCriticalRateAction(this.magicNumber));
    }
}
