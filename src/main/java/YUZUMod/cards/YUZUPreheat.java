package YUZUMod.cards;

import YUZUMod.action.YUZUApplyCriticalRateAction;
import YUZUMod.action.YUZUChangeCriticalHitRateMaxAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import YUZUMod.power.YUZUExtraCriticalRatePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUPreheat extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Preheat");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUPreheat() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=25;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(25);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(AbstractDungeon.player instanceof YuzuCharacter){
                    addToTop(new YUZUApplyCriticalRateAction(((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().getMAX()-((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().getAmount()));
                    this.isDone=true;
                }
            }
        });
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUExtraCriticalRatePower(abstractPlayer,this.magicNumber)));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        addToBot(new YUZUChangeCriticalHitRateMaxAction(-2));
    }
}
