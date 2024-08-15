package YUZUMod.cards;

import YUZUMod.action.EasyXCostAction;
import YUZUMod.action.YUZUSimplifiedComboAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;

public class YUZUSimplifiedCombo extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("SimplifiedCombo");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=-1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUSimplifiedCombo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=2;
        MultiCardPreview.add(this,new YUZUStrike());
        MultiCardPreview.add(this,new YUZUDefend());
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        BiFunction<Integer, int[], Boolean> function= (integer, ints) -> {
            AbstractCard strike=new YUZUStrike();
            AbstractCard defend=new YUZUDefend();
            if(integer>0){
                strike.upgraded=true;
                defend.upgraded=true;
                strike.name=strike.name+"+"+integer;
                defend.name=defend.name+"+"+integer;
                for(int i=0;i<integer;i++){
                    strike.baseDamage += 3;
                    strike.upgradedDamage = true;
                    defend.baseBlock+=3;
                    defend.upgradedBlock=true;
                }
            }
            addToTop(new YUZUSimplifiedComboAction(new ArrayList<>(Arrays.asList(strike,defend)),ints[0]));
            return true;
        };
        addToBot(new EasyXCostAction(this,function,this.magicNumber));
    }
}
