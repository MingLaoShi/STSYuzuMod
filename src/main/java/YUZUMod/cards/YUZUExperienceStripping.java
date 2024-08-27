package YUZUMod.cards;

import YUZUMod.cards.colorless.YUZUExperience;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

public class YUZUExperienceStripping extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ExperienceStripping");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.NONE;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUExperienceStripping() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=2;
        this.cardsToPreview=new YUZUExperience();
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(this.magicNumber,"//",false,false,this::filter,this::callback));
    }

    private void callback(List<AbstractCard> cards) {
        for(AbstractCard card:cards){
            YUZUCustomCard.removeMaster(card);
        }
        addToBot(new MakeTempCardInHandAction(new YUZUExperience(),cards.size()));
    }

    private boolean filter(AbstractCard card) {
        return YUZUCustomCard.isMastered(card)>0;
    }
}
